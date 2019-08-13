
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
 *         &lt;element name="SponsorApplicationURN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sponsorApplicationURN"
})
@XmlRootElement(name = "NewSponsorApplication")
public class NewSponsorApplication {

    @XmlElement(name = "SponsorApplicationURN")
    protected String sponsorApplicationURN;

    /**
     * Gets the value of the sponsorApplicationURN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSponsorApplicationURN() {
        return sponsorApplicationURN;
    }

    /**
     * Sets the value of the sponsorApplicationURN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSponsorApplicationURN(String value) {
        this.sponsorApplicationURN = value;
    }

}
