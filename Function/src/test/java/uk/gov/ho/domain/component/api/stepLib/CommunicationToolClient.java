package uk.gov.ho.domain.component.api.stepLib;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.ho.cts.utils.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

public class CommunicationToolClient {
    public static final String SCENARIO = "Scenario";
    private static final Logger LOG = LoggerFactory.getLogger(CommunicationToolClient.class);
    private static final String FUNCTION_NAME;
    private static final String QUEUE_URL;
    private static final String MOBILE_EMAIL_QUEUE_ENDPOINT = "mobile.email.queue.endpoint";

    static {

        Class<CommunicationToolClient> thisClass = CommunicationToolClient.class;
        String functionName = StringUtils.substringBetween(thisClass.getPackage().getName(), "uk.gov.ho.eue.", ".");
        FUNCTION_NAME = functionName == null ? thisClass.getPackage().getName() : functionName;
        QUEUE_URL = Configuration.get(MOBILE_EMAIL_QUEUE_ENDPOINT) + "/euaobjectsqueue";

    }

    private RequestSpecification reqSpec;
    private ResponseSpecification respSpec;

    private void setUpUrl(final String url) {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri(url)
                .build();
        respSpec = new ResponseSpecBuilder()
                .build();
    }

    public String borrowAResource(final String resourceName) {

        String resourceReference = null;
        try {
            String url = getBorrowUri().toString();
            setUpUrl(url);
            resourceReference = SerenityRest.given()
                    .spec(reqSpec)
                    .get(getBorrowResourceParam(resourceName)).then()
                    .spec(respSpec)
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract().jsonPath().getString("value");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return resourceReference;
    }

    public void returnAResource(final String resourceName, final String resourceReference) {

        try {
            String url = getReturnUri().toString();
            setUpUrl(url);
            SerenityRest.given()
                    .spec(reqSpec)
                    .get(getReturnResourceParam(resourceName, resourceReference)).then()
                    .spec(respSpec)
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract().jsonPath().getString("value");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private URI getBorrowUri() throws URISyntaxException {
        URIBuilder builder = new URIBuilder(QUEUE_URL + "/borrow");
        return builder.build();
    }

    private URI getReturnUri() throws URISyntaxException {
        URIBuilder builder = new URIBuilder(QUEUE_URL + "/return");
        return builder.build();
    }

    private URI getBorrowResourceParam(final String type) throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setParameter("type", type);
        builder.setParameter("function", FUNCTION_NAME);
        builder.setParameter("tags", Serenity.sessionVariableCalled(SCENARIO));
        return builder.build();
    }

    private URI getReturnResourceParam(final String type, final String resourceReference) throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setParameter("type", type);
        builder.setParameter("value", resourceReference);
        builder.setParameter("function", FUNCTION_NAME);
        builder.setParameter("tags", Serenity.sessionVariableCalled(SCENARIO));
        return builder.build();
    }

    public String borrowMobile() {
        return borrowAResource("MOBILE");
    }

    public String borrowAnEmail() {
        return borrowAResource("EMAIL");
    }

    public void returnMobile(final String resourceReference) {
        returnAResource("MOBILE", resourceReference);
    }

    public void returnAnEmail(final String resourceReference) {
        returnAResource("EMAIL", resourceReference);
    }
}
