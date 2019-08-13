
package uk.gov.ho.domain.component.api.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NewSponsorApplicationResult" type="{http://pbs.pidp.fujitsu.bia.homeoffice.gov.uk/notification/newsponsorapplication/}PBSWebServiceError"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "newSponsorApplicationResult"
})
@XmlRootElement(name = "NewSponsorApplicationResponse")
public class NewSponsorApplicationResponse {

    @XmlElement(name = "NewSponsorApplicationResult", required = true)
    protected PBSWebServiceError newSponsorApplicationResult;

    /**
     * Gets the value of the newSponsorApplicationResult property.
     * 
     * @return
     *     possible object is
     *     {@link PBSWebServiceError }
     *     
     */
    public PBSWebServiceError getNewSponsorApplicationResult() {
        return newSponsorApplicationResult;
    }

    /**
     * Sets the value of the newSponsorApplicationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PBSWebServiceError }
     *     
     */
    public void setNewSponsorApplicationResult(PBSWebServiceError value) {
        this.newSponsorApplicationResult = value;
    }

}
