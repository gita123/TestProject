package uk.gov.ho.domain.component.api.stepLib;

import io.restassured.RestAssured;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import uk.gov.ho.cts.utils.Configuration;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static net.serenitybdd.rest.SerenityRest.when;

import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;
import java.io.InputStream;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.MimeHeaders;

import uk.gov.ho.domain.component.api.soap.NewSponsorApplicationResponse;
import uk.gov.ho.domain.component.api.soap.NotificationServiceSoap;
import uk.gov.ho.domain.component.api.soap.NotificationService;
import uk.gov.ho.domain.component.api.soap.PBSWebServiceError;

import java.net.URL;

public class ApiActions {
    @Step
    public static int getResponseStatusCode() {
        return then().extract().statusCode();
    }

    @Step
    public static String getResponseBody() {
        return then().extract().response().asString();
    }

    public static String endpointRoot = null;

    @Step
    public static void restEndpointIsAvailable(String name) {
        String locator = name.replace(' ', '.') ;
        //+ ".endpoint";
        System.out.println(locator);

        //  URL endpoint = URL
        endpointRoot = Configuration.get(locator);

        System.out.println("endPointRoot is :" + endpointRoot);
        Assert.assertNotNull(endpointRoot);
        System.out.println(endpointRoot);
    }

/*    @Step
    public void hitEndpoint() {
        given()
                .log().all()
                .when()
                .get(endpointRoot);
    } */


    public void setEndpoint() {
        RestAssured.baseURI = endpointRoot;
    }

    public void getResource(String resourceName) {
        String resourcePath = Configuration.get(resourceName);
        when().get(resourcePath);
    }

    public void sendSoapRequest() {

        URL url = null;

        try {
            url = new URL(endpointRoot);
        }

        catch (Exception e) {
            Assert.assertTrue(false);
        }

       /* try {
            Thread.sleep(120000);
        } catch (InterruptedException ie) {
        } */


        NotificationService ns = new NotificationService(url);

        NotificationServiceSoap soapSponsorApp = ns.getNotificationServiceSoap();


        try {
            PBSWebServiceError servErr = soapSponsorApp.newSponsorApplication("SPL6666600087");

            System.out.println("We get back" + servErr.getErrorCode());

            Assert.assertEquals(0, servErr.getErrorCode());

        }

        catch (Exception e) {
            System.out.println("Caught an Exception");
            System.out.println(e.toString());
            Assert.assertTrue(false);
        }


  }


}



