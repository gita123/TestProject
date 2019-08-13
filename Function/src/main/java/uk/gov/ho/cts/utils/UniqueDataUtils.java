package uk.gov.ho.cts.utils;

import static org.junit.Assert.fail;
import static org.seleniumhq.jetty9.http.HttpStatus.OK_200;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import net.serenitybdd.core.Serenity;

public class UniqueDataUtils {

    private static final String EMAIL = "email";
    private static final String MOBILE = "mobile";
    /* Amazon SQS Authentication variables - to come from vault */

    private static final Logger LOGGER = LogManager.getLogger(UniqueDataUtils.class);

    private static final String FUNCTION_NAME;
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";

    private static final Set<String> BORROWED_MOBILES = new ConcurrentSkipListSet<>();
    private static final Set<String> BORROWED_EMAILS = new ConcurrentSkipListSet<>();

    private static final Map<String, UsageStatistics> MOBILE_USAGE_MAP = new ConcurrentHashMap<>();
    private static final Map<String, UsageStatistics> EMAIL_USAGE_MAP = new ConcurrentHashMap<>();

    public static final String SCENARIO = "Scenario";

    public static UniqueDataUtils mobileEmailQueueUtils;
    private static final String QUEUE_URL;

    static {

        Class<UniqueDataUtils> thisClass = UniqueDataUtils.class; // use the current class to get the
        // function name
        String functionName = StringUtils.substringBetween(thisClass.getPackage().getName(), "uk.gov.ho.eue.", ".");
        FUNCTION_NAME = functionName == null ? thisClass.getPackage().getName() : functionName;
        QUEUE_URL = Configuration.get("mobile.email.queue.endpoint") + "/euaobjectsqueue";

        Runtime.getRuntime().addShutdownHook(new Thread(() -> cleanup()));

    }

    private UniqueDataUtils() {
    }

    // static method to create instance of Singleton class
    public static UniqueDataUtils getInstance() {
        if (mobileEmailQueueUtils == null) {
            mobileEmailQueueUtils = new UniqueDataUtils();
        }
        return mobileEmailQueueUtils;
    }

    public static String borrowMobileOrEmail(boolean isMobile) throws NoAvailableMobilesException {

        CloseableHttpClient client = HttpClientBuilder.create().build();

        try {
            URI uri = getBorrowUri(isMobile);
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);

            CloseableHttpResponse resp = client.execute(httpGet);
            String json = IOUtils.toString(resp.getEntity().getContent(), Charset.defaultCharset());
            JSONObject jsonOb = new JSONObject(json);
            if (resp.getStatusLine().getStatusCode() == OK_200) {

                String mobileNumberOrEmail = jsonOb.getString("value");

                addStats(mobileNumberOrEmail, isMobile);
                LOGGER.info("Borrowed " + (isMobile ? MOBILE : EMAIL) + " : '" + mobileNumberOrEmail + "'");

                return mobileNumberOrEmail;
            } else {
                String failMessage = jsonOb.getString("message");
                LOGGER.error(failMessage);
                fail(failMessage);
            }

        } catch (URISyntaxException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return null;

    }

    private static URI getBorrowUri(boolean isMobile) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(QUEUE_URL + "/borrow");
        builder.setParameter("type", isMobile ? "MOBILE" : "EMAIL");
        builder.setParameter("function", FUNCTION_NAME);
        builder.setParameter("tags", Serenity.sessionVariableCalled(SCENARIO));
        return builder.build();
    }

    public static void returnMobileOrEmail(String mobileNumberOrEmail, boolean isMobile) {

        LOGGER.info("Returning " + (isMobile ? MOBILE : EMAIL) + " : '" + mobileNumberOrEmail + "'");

        CloseableHttpClient client = HttpClientBuilder.create().build();

        try {
            URI url = getReturnUri(mobileNumberOrEmail, isMobile);
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(CONTENT_TYPE, APPLICATION_JSON);

            CloseableHttpResponse resp = client.execute(httpPost);
            String json = IOUtils.toString(resp.getEntity().getContent(), Charset.defaultCharset());
            JSONObject jsonOb = new JSONObject(json);
            if (resp.getStatusLine().getStatusCode() == OK_200) {

                /* Mobile number returned to the queue so we no longer need to track it */
                if (isMobile) {
                    BORROWED_MOBILES.remove(mobileNumberOrEmail);
                    if (MOBILE_USAGE_MAP.containsKey(mobileNumberOrEmail)) {
                        updateStatistics(mobileNumberOrEmail, MOBILE_USAGE_MAP);
                    }
                } else {
                    BORROWED_EMAILS.remove(mobileNumberOrEmail);
                    if (EMAIL_USAGE_MAP.containsKey(mobileNumberOrEmail)) {
                        updateStatistics(mobileNumberOrEmail, EMAIL_USAGE_MAP);
                    }
                }

                LOGGER.info("Returned " + (isMobile ? MOBILE : EMAIL) + " : '" + mobileNumberOrEmail + "'");

            } else {
                String failMessage = jsonOb.getString("message");
                LOGGER.error(failMessage);
                fail(failMessage);
            }

        } catch (URISyntaxException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

    }

    private static URI getReturnUri(String mobileNumberOrEmail, boolean isMobile) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(QUEUE_URL + "/return");
        builder.setParameter("type", isMobile ? "MOBILE" : "EMAIL");
        builder.setParameter("value", mobileNumberOrEmail);
        builder.setParameter("function", FUNCTION_NAME);
        builder.setParameter("tags", Serenity.sessionVariableCalled(SCENARIO));
        return builder.build();
    }

    private static void cleanup() {
        /*
         * For any mobile numbers and email addresses we have not returned to the queue,
         * we return them now to make them available to others #oneTeamOneDream
         */

        printStatistics();

        for (String mobileNumber : BORROWED_MOBILES) {
            returnMobileOrEmail(mobileNumber, true);
        }

        for (String emailAddress : BORROWED_EMAILS) {
            returnMobileOrEmail(emailAddress, false);
        }

    }

    public static String printStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append("Used " + MOBILE_USAGE_MAP.size() + " different mobile numbers for tests" + System.lineSeparator());
        sb.append("Used " + EMAIL_USAGE_MAP.size() + " different email addresses for tests" + System.lineSeparator());
        for (Entry<String, UsageStatistics> usageMapEntry : MOBILE_USAGE_MAP.entrySet()) {
            sb.append(usageMapEntry.getKey() + " used for "
                    + Duration.ofMillis(usageMapEntry.getValue().getDurationTotal()) + System.lineSeparator());
        }
        return sb.toString();
    }

    public static boolean mobileHasBeenReturned(String mobile) {
        return !BORROWED_MOBILES.contains(mobile);
    }

    public static boolean emailHasBeenReturned(String email) {
        return !BORROWED_EMAILS.contains(email);
    }

    private static void addStats(String mobileNumberOrEmail, boolean isMobile) {
        /*
         * Keep a track of which mobile numbers we have borrowed so we can return them
         * in a shutdown hook. Also collate statistics so we can tell which tests use
         * mobile numbers for the longest time
         */
        if (isMobile) {
            BORROWED_MOBILES.add(mobileNumberOrEmail);
            if (MOBILE_USAGE_MAP.containsKey(mobileNumberOrEmail)) {
                MOBILE_USAGE_MAP.get(mobileNumberOrEmail).getCount().incrementAndGet();
                MOBILE_USAGE_MAP.get(mobileNumberOrEmail).setStart(System.currentTimeMillis());
            } else {
                MOBILE_USAGE_MAP.put(mobileNumberOrEmail, new UsageStatistics());
            }
        } else {
            BORROWED_EMAILS.add(mobileNumberOrEmail);
            if (EMAIL_USAGE_MAP.containsKey(mobileNumberOrEmail)) {
                EMAIL_USAGE_MAP.get(mobileNumberOrEmail).getCount().incrementAndGet();
                EMAIL_USAGE_MAP.get(mobileNumberOrEmail).setStart(System.currentTimeMillis());
            } else {
                EMAIL_USAGE_MAP.put(mobileNumberOrEmail, new UsageStatistics());
            }
        }

    }

    private static void updateStatistics(String mobileNumberOrEmail, Map<String, UsageStatistics> map) {
        Long startTime = map.get(mobileNumberOrEmail).getStart();
        Long currentTotal = map.get(mobileNumberOrEmail).getDurationTotal();
        map.get(mobileNumberOrEmail).setDurationTotal(currentTotal + (System.currentTimeMillis() - startTime));
    }

    private static class UsageStatistics {

        private AtomicInteger count = new AtomicInteger(1);
        private Long start = System.currentTimeMillis();
        private Long durationTotal = 0L;

        public AtomicInteger getCount() {
            return count;
        }

        public Long getStart() {
            return start;
        }

        public void setStart(Long start) {
            this.start = start;
        }

        public Long getDurationTotal() {
            return durationTotal;
        }

        public void setDurationTotal(Long durationTotal) {
            this.durationTotal = durationTotal;
        }

    }

    public static class NoAvailableMobilesException extends Exception {

        private static final long serialVersionUID = 1L;

        NoAvailableMobilesException(String message) {
            super(message);
        }

    }

}
