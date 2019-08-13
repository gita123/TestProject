
package uk.gov.ho.domain.component.api.soap;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uk.gov.ho.domain.component.api.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uk.gov.ho.domain.component.api.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NewSponsorApplication }
     * 
     */
    public NewSponsorApplication createNewSponsorApplication() {
        return new NewSponsorApplication();
    }

    /**
     * Create an instance of {@link LinkToExistingApplication }
     * 
     */
    public LinkToExistingApplication createLinkToExistingApplication() {
        return new LinkToExistingApplication();
    }

    /**
     * Create an instance of {@link NewSponsorApplicationResponse }
     * 
     */
    public NewSponsorApplicationResponse createNewSponsorApplicationResponse() {
        return new NewSponsorApplicationResponse();
    }

    /**
     * Create an instance of {@link PBSWebServiceError }
     * 
     */
    public PBSWebServiceError createPBSWebServiceError() {
        return new PBSWebServiceError();
    }

    /**
     * Create an instance of {@link LinkToExistingApplicationResponse }
     * 
     */
    public LinkToExistingApplicationResponse createLinkToExistingApplicationResponse() {
        return new LinkToExistingApplicationResponse();
    }

}
