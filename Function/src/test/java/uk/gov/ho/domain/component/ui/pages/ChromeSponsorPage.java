package uk.gov.ho.domain.component.ui.pages;


import net.thucydides.core.pages.PageObject;
import org.apache.commons.io.FileUtils;
import org.jruby.RubyProcess;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.ho.cts.utils.Driver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.github.javafaker.Faker;
import java.util.List;

//Register Sponsor in Chrome/Firefox
public class ChromeSponsorPage extends PageObject {

    public String licenceNumber;

    @FindBy(xpath = "//a[@id='formSponsorHome:licence']")
    WebElement applySponsorLicenceButton;
    @FindBy(name = "sponsorappcategory:apply_0")
    WebElement tier2Generl;
    @FindBy(name = "sponsorappcategory:apply_4")
    WebElement tier4Generl;
    @FindBy(name = "sponsorappcategory:apply_6")
    WebElement tier5CretvSprt;
    @FindBy(id = "sponsorappcategory:previouslicence:1")
    WebElement regstrdSponsr;
    @FindBy(name = "sponsorappcategory:next")
    WebElement nextButton;

    @FindBy(name = "sponsorapporg1:orgName")
    WebElement orgName;
    @FindBy(name = "sponsorapporg1:orgAddress1")
    WebElement orgAddrss;
    @FindBy(name = "sponsorapporg1:orgAddress4")
    WebElement orgAddrss4;
    @FindBy(name = "sponsorapporg1:orgPostcode")
    WebElement orgPostCode;
    @FindBy(name = "sponsorapporg1:orgTelephone")
    WebElement orgTelephoneNumber;
    @FindBy(name = "sponsorapporg1:next")
    WebElement OrganisationnextButton;
    @FindBy(name = "sponsorapporg2:apply_3")
    WebElement orgRegions;
    @FindBy(name = "sponsorapporg2:next")
    WebElement orgRegionsNext;


    @FindBy(name = "sponsorapporg3:organisationSize")
    WebElement orgSize;
    @FindBy(id = "sponsorapporg3:employingMigrantWorkers:0")
    WebElement orgMigrantWrkrs;
    @FindBy(name = "sponsorapporg3:numberMigrantWorkers")
    WebElement orgMigrantWrkrsSize;
    @FindBy(id = "sponsorapporg3:businessSector")
    WebElement orgSectorDropDown;
    @FindBy(id = "sponsorapporg3:orgRegType")
    WebElement orgTypeDropDown;
    @FindBy(id = "sponsorapporg3:charitableStatus:1")
    WebElement orgCharityStatus;
    @FindBy(id = "sponsorapporg3:organisationType")
    WebElement orgType;
    @FindBy(id = "sponsorapporg3:timeTrading")
    WebElement orgTradingDropdown;
    @FindBy(id = "sponsorapporg3:registeredHMRC:1")
    WebElement orgVATRestrd;
    @FindBy(id = "sponsorapporg3:next")
    WebElement step3NextButton;

    @FindBy(id = "sponsorapporg4:stockExchangeReg:1")
    WebElement orgnstn4StckExchnge;
    @FindBy(id = "sponsorapporg4:accreditationRequired:1")
    WebElement orgnstn4Accreditation;
    @FindBy(id = "sponsorapporg4:next")
    WebElement step4NextButton;

    @FindBy(id = "sponsorappeducation:EducationalId")
    WebElement institutionType;
    @FindBy(id = "sponsorappeducation:publiclyfunded:1")
    WebElement publicFundStatus;
    @FindBy(id = "sponsorappeducation:InspectingAuditingId")
    WebElement auditingBody;
    @FindBy(id = "sponsorappeducation:accreditationbody")
    WebElement exemptionReview;
    @FindBy(id = "sponsorappeducation:next")
    WebElement sponsorAppNextButton;

    @FindBy(name = "sponsorAppSubTierCOS:cos")
    WebElement sponsorCOSCnt;
    @FindBy(name = "sponsorAppSubTierCOS:reason")
    WebElement sponsorCOSTxt;
    @FindBy(name = "sponsorAppSubTierCOS:next")
    WebElement sponsorCOSNextButton;
    @FindBy(name = "sponsorAppT4GCOS:cos")
    WebElement sponsorT4CASCnt;
    @FindBy(name = "sponsorAppT4GCOS:reason")
    WebElement sponsorT4CASTxt;
    @FindBy(name = "sponsorAppT4GCOS:next")
    WebElement sponsorCAST4NextButton;

    @FindBy(name = "sponsorAppT5SCOS:cos")
    WebElement sponsorT5COSCnt;
    @FindBy(name = "sponsorAppT5SCOS:reason")
    WebElement sponsorT5COSTxt;
    @FindBy(id = "sponsorAppT5SCOS:group")
    WebElement sponsorT5COSGrp;
    @FindBy(id = "sponsorAppT5SCOS:getBodies")
    WebElement sponsorT5COSGrpFndBdy;
    @FindBy(id = "sponsorAppT5SCOS:body")
    WebElement sponsorT5COSGrpSprtngBdy;
    @FindBy(id = "sponsorAppT5SCOS:category")
    WebElement sponsorT5COSGrpOrgGrp;
    @FindBy(id = "sponsorAppT5SCOS:next")
    WebElement sponsorT5NextButton;

    @FindBy(xpath = "//input[@value=\"AUDANACC_provide\"]")
    WebElement sponsorDocAcc;
    @FindBy(xpath = "//input[@value=\"GBODREG_provide\"]")
    WebElement sponsorDocRegstrn;
    @FindBy(xpath = "//input[@value=\"FRANAGR_provide\"]")
    WebElement sponsorDocFrnchsAgrrmnt;
    @FindBy(xpath = "//input[@value=\"SPOBDEND_provide\"]")
    WebElement sponsorDocSprtBdyEndrsment;
    @FindBy(xpath = "//input[@value=\"DIGITECH_provide\"]")
    WebElement sponsorDocDeclrtn;
    @FindBy(xpath = "//input[@value=\"EMPLIAIC_provide\"]")
    WebElement sponsorDocEmplymntInsrnc;
    @FindBy(id = "sponsorAppDocs:next")
    WebElement sponsorDocNextButton;


    @FindBy(id = "formSponsorappao:title")
    WebElement sponsorCntctTitle;
    @FindBy(id = "formSponsorappao:firstName")
    WebElement sponsorCntct1stName;
    @FindBy(id = "formSponsorappao:lastName")
    WebElement sponsorCntctLastName;
    @FindBy(id = "formSponsorappao:addressLine1")
    WebElement sponsorCntctAddrss1;
    @FindBy(id = "formSponsorappao:townCity")
    WebElement sponsorCntcCity;
    @FindBy(id = "formSponsorappao:postcode")
    WebElement sponsorCntctPstCde;
    @FindBy(id = "formSponsorappao:number")
    WebElement sponsorCntctTelphnNumber;
    @FindBy(id = "formSponsorappao:email")
    WebElement sponsorCntctEmail;
    @FindBy(id = "formSponsorappao:dateOfBirth:day")
    WebElement sponsorCntctDay;
    @FindBy(id = "formSponsorappao:dateOfBirth:month")
    WebElement sponsorCntctMnth;
    @FindBy(id = "formSponsorappao:dateOfBirth:year")
    WebElement sponsorCntctYear;
    @FindBy(id = "formSponsorappao:nationality")
    WebElement sponsorCntctNationality;
    @FindBy(id = "formSponsorappao:orgPosition")
    WebElement sponsorCntctRole;
    @FindBy(id = "formSponsorappao:isUnderImmigrationControl:1")
    WebElement sponsorCntctImmgrtnCntrl;
    @FindBy(id = "formSponsorappao:buttonNext")
    WebElement sponsorCntctNextButton;
    @FindBy(id = "sponsorappconvictionsao:convictionPenaltyRadio:1")
    WebElement sponsorCntcPenalty;
    @FindBy(id = "sponsorappconvictionsao:next")
    WebElement sponsorCntcNextButton;
    @FindBy(id = "sponsorappcontact:officer:0")
    WebElement sponsorCntcKeyCntct;
    @FindBy(id = "sponsorappcontact:buttonNext")
    WebElement sponsorCntcKeyCntctNextButton;

    @FindBy(id = "sponsorapplevel1user:levelOneUserIs:0")
    WebElement sponsorCntcUserLevel;
    @FindBy(id = "sponsorapplevel1user:buttonNext")
    WebElement sponsorCntcUserLevelNextButton;
    @FindBy(id = "sponsorapprep:representativeAppointed:1")
    WebElement sponsorCntcRepresnt;
    @FindBy(id = "sponsorapprep:next")
    WebElement sponsorCntcRepresntNextButton;
    @FindBy(id = "sponsorappspondec:confirmacceptance")
    WebElement sponsorCntcAcctptnc;
    @FindBy(id = "sponsorappspondec:date")
    WebElement sponsorCntcAcctptncDate;
    @FindBy(id = "sponsorappspondec:next")
    WebElement sponsorCntcSubmit;
    @FindBy(id = "sponsorappcalccost:refno")
    WebElement sponsorLicenceNumber;
    @FindBy(id = "sponsorappcalccost:payment")
    WebElement sponsorLicencePayment;
    @FindBy(id = "sponsorappcalccost:next")
    WebElement sponsorPaymentNextButton;
    @FindBy(id = "sponsorappsponpaymentdec:confirmacceptance")
    WebElement sponsorLicencePaymentDeclartn;
    @FindBy(id = "sponsorappsponpaymentdec:date")
    WebElement sponsorLicencePaymentDeclartnDate;
    @FindBy(id = "sponsorappsponpaymentdec:next")
    WebElement sponsorLicencePaymentNextButton;
    @FindBy(id = "epaynext")
    WebElement epayNextButton;
    @FindBy(name = "op-DPChoose-VISA^SSL")
    WebElement VISAButton;
    @FindBy(id = "cardNoInput")
    WebElement cardNumber;
    @FindBy(id = "cardCVV")
    WebElement cardCVVNum;
    @FindBy(name = "cardExp.month")
    WebElement cardExpMonth;
    @FindBy(name = "cardExp.year")
    WebElement cardExpYear;
    @FindBy(id = "name")
    WebElement cardHolderName;
    @FindBy(id = "address1")
    WebElement cardHolderAddrss;
    @FindBy(id = "town")
    WebElement cardHolderTown;
    @FindBy(id = "postcode")
    WebElement cardHolderPostCode;
    @FindBy(name = "country")
    WebElement cardHolderCountry;
    @FindBy(name = "email")
    WebElement cardHolderEmail;
    @FindBy(xpath = "//iframe[@name='a-etiw1a8mjkz4']")
    WebElement captchaIFrame;
    @FindBy(css = "body:nth-child(2)")
    WebElement cardCaptcha;
    @FindBy(name = "op-PMMakePayment")
    WebElement makePaymnt;
    @FindBy(xpath = "//input[@id='op-PMMakePayment']")
    WebElement makePaymntContnue;
    @FindBy(xpath = "//input[@type=\"submit\"]")
    WebElement makePaymntSubmit;
    @FindBy(id = "actionA") WebElement authorisePayment;
    @FindBy(xpath = "//input[@value='OK']") WebElement submitPayment;

    @FindBy(id = "applicationUrn") WebElement sponsorRefNumber;
    @FindBy(id = "applicationPnn") WebElement sponsorPaymentNumber;
    @FindBy(id = "j_idt10:sponsorNavigation:logout") WebElement sponsorLogout;


    private String currentDir = System.getProperty("user.dir");
    Faker faker = new Faker();

    //GetScreenShot Method Directory and Image File
    private File getSreenShotMethodImageFile = new File (currentDir +
            "/src/test/resources/ScreenShots/SponsorPaymentSubmissionPage.png");


    public void initializeDriver()
    {
        Driver browser = new Driver();
        WebDriver driverObj = this.getDriver();
        driverObj = browser.newDriver();
    }

    public void applySponsorLicence() {
        try {
            applySponsorLicenceButton.click();
            Thread.sleep(3000);
            tier2Generl.click();
            tier4Generl.click();
            tier5CretvSprt.click();
            regstrdSponsr.click();
            nextButton.click();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void completeOrganisationDetails() {
        try {
            orgName.sendKeys(faker.address().firstName());
            orgAddrss.sendKeys(faker.address().streetAddress());
            orgAddrss4.sendKeys("test");
            orgPostCode.sendKeys("CR0 2EU");
            orgTelephoneNumber.sendKeys("11111111111");
            OrganisationnextButton.click();
            Thread.sleep(3000);
            orgRegions.click();
            orgRegionsNext.click();
            orgSize.sendKeys("1000");
            orgMigrantWrkrs.click();
            orgMigrantWrkrsSize.sendKeys("250");
            selectDropDown(orgSectorDropDown, "Information and Communications");
            selectDropDown(orgTypeDropDown, "Single Body");
            orgCharityStatus.click();
            selectDropDown(orgType, "Sole Trader");
            selectDropDown(orgTradingDropdown, "Over 36 months+");
            orgVATRestrd.click();
            step3NextButton.click();
            orgnstn4StckExchnge.click();
            orgnstn4Accreditation.click();
            step4NextButton.click();
            selectDropDown(institutionType, "Higher Education Institution (HEI)");
            publicFundStatus.click();
            selectDropDown(auditingBody, "Ofsted");
            selectDropDownByValue(exemptionReview, "NO");
            sponsorAppNextButton.click();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }

    }

    public void selectDropDown(WebElement element1, String optionVal) {
        Select selectElement = new Select(element1);
        List<WebElement> allOptions = selectElement.getOptions();
        for(WebElement ele: allOptions)
        {
            System.out.println("Select dropdown options are ::" + ele.getText()+"123");
        }
        selectElement.selectByVisibleText(optionVal);
    }

    public void selectDropDownByValue(WebElement element1, String optionVal) {
        Select selectElement = new Select(element1);
        List<WebElement> allOptions = selectElement.getOptions();
        for(WebElement ele: allOptions)
        {
            System.out.println("Select dropdown options are ::" + ele.getText());
        }
        selectElement.selectByValue(optionVal);
    }


    public void applyCOSCASDetails() {
        try {
            sponsorCOSCnt.sendKeys("250");
            sponsorCOSTxt.sendKeys("COS licence");
            sponsorCOSNextButton.click();
            sponsorT4CASCnt.sendKeys("250");
            sponsorT4CASTxt.sendKeys("CAS licence");
            sponsorCAST4NextButton.click();
            sponsorT5COSCnt.sendKeys("250");
            sponsorT5COSTxt.sendKeys("COS licence");
            selectDropDown(sponsorT5COSGrp, "Cycling");
            sponsorT5COSGrpFndBdy.click();
            Thread.sleep(3000);
            selectDropDown(sponsorT5COSGrpSprtngBdy, "British Cycling");
            selectDropDown(sponsorT5COSGrpOrgGrp, "Creative - Music");
            sponsorT5NextButton.click();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
    }


    public void supportingDocumentCheck() {
        sponsorDocAcc.click();
        sponsorDocRegstrn.click();
        sponsorDocFrnchsAgrrmnt.click();
        sponsorDocSprtBdyEndrsment.click();
        sponsorDocDeclrtn.click();
        sponsorDocEmplymntInsrnc.click();
        sponsorDocNextButton.click();
    }

    public void completeContactDetails() {
        try {
            selectDropDown(sponsorCntctTitle, "Mrs");
            sponsorCntct1stName.sendKeys(faker.name().firstName());
            sponsorCntctLastName.sendKeys(faker.name().lastName());
            sponsorCntctAddrss1.sendKeys(faker.address().streetAddress());
            sponsorCntcCity.sendKeys(faker.address().city());
            sponsorCntctPstCde.sendKeys("CR0 2EU");
            sponsorCntctTelphnNumber.sendKeys("11111111111");
            sponsorCntctEmail.sendKeys("pbs.inttesting@gmail.com");
            selectDropDown(sponsorCntctDay, "07");
            selectDropDown(sponsorCntctMnth, "September");
            selectDropDown(sponsorCntctYear, "1995");
            selectDropDown(sponsorCntctNationality, "AFGHANISTAN");
            sponsorCntctRole.sendKeys("Director");
            sponsorCntctImmgrtnCntrl.click();
            sponsorCntctNextButton.click();
            sponsorCntcPenalty.click();
            sponsorCntcNextButton.click();
            sponsorCntcKeyCntct.click();
            sponsorCntcKeyCntctNextButton.click();
            sponsorCntcUserLevel.click();
            sponsorCntcUserLevelNextButton.click();
            sponsorCntcRepresnt.click();
            sponsorCntcRepresntNextButton.click();
            sponsorCntcAcctptnc.click();
            sponsorCntcAcctptncDate.sendKeys(getCurrentDate());
            sponsorCntcSubmit.click();
            readLicenceNumber();
            selectDropDown(sponsorLicencePayment, "£536 (see Help below)");
            sponsorPaymentNextButton.click();
            sponsorLicencePaymentDeclartn.click();
            sponsorLicencePaymentDeclartnDate.sendKeys(getCurrentDate());
            sponsorLicencePaymentNextButton.click();
            epayNextButton.click();
//            VISAButton.click();
//            cardNumber.sendKeys("4111111111111111");
//            cardCVVNum.sendKeys("111");
//            selectDropDown(cardExpMonth, "10");
//            selectDropDown(cardExpYear, "2022");
//            cardHolderName.sendKeys("test");
//            cardHolderAddrss.sendKeys("test");
//            cardHolderTown.sendKeys("London");
//            cardHolderPostCode.sendKeys("CR0 2EU");
//            selectDropDown(cardHolderCountry, "Afghanistan");
//            cardHolderEmail.sendKeys("pbs.inttesting@gmail.com");
//            String pageURL = this.getDriver().getCurrentUrl();
//            String iFrame_attr = this.getDriver().findElement(By.tagName("iframe")).getAttribute("name");
//            WebElement iFrame_ele = this.getDriver().findElement(By.xpath("//iframe[@name='" + iFrame_attr + "']"));
//            this.getDriver().switchTo().frame(iFrame_ele);
////            Thread.sleep(3000);
//////            this.getDriver().findElement(By.xpath("//input[@type='hidden' and @id='recaptcha-token']")).sendKeys("03AOLTBLS0aJzMs8jgW0TBXjvaYuCH99OBr0mUZXL6fLvxgx7hLk8-VbySp4-LWQfL-6RG9Qz6ai3aqIGa_p8EPcYguPiA9dNTSJENmW-jsFKuPE4u_VIB5n5VoeZo-wLjkZERpKwpSBI3Qkl8ht8hQDiGtr61EFkoNXYMjG3o6QAalkUAAdsuK6Q2t7tZflIResrZbaendZszNMMQbbiWh8vzfZqszVGWZaorJctqsR1j9atZ_m1tC7vrLhtZ8cxzVtRQV5Kgi6EFG92K7YX1S98crWpX5gjcvaFS5bbSZnTMXwvj5dmL004");
//            cardCaptcha.click();
//            Thread.sleep(3000);
//            makePaymnt.click();
//            Thread.sleep(3000);
//            makePaymntContnue.click();
//            Thread.sleep(20000);
//            makePaymntSubmit.click();
//            Thread.sleep(3000);
            authorisePayment.click();
            submitPayment.click();
            getSponsorDetails();
            sponsorLogout.click();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.getDriver().close();
        }
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String newDate = dateFormat.format(date);
        return newDate;
    }

    public String readLicenceNumber() {
        licenceNumber = sponsorLicenceNumber.getText();
        System.out.println("Sponsor Licence number is :: " + licenceNumber);
        return licenceNumber;
    }

    public void getSponsorDetails() throws IOException
    {
        String sponsrURNnumb = sponsorRefNumber.getText();
        System.out.println("Sponsor URN number is :: " + sponsrURNnumb);
        String sponsrPaymntnumb = sponsorPaymentNumber.getText();
        System.out.println("Sponsor Payment number is :: " + sponsrPaymntnumb);
        File scrFile = ((TakesScreenshot)this.getDriver()).getScreenshotAs(OutputType.FILE);
        //Write Screenshot to a file
        FileUtils.copyFile(scrFile, getSreenShotMethodImageFile);
    }


}
