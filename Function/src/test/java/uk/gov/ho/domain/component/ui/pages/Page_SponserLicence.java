package uk.gov.ho.domain.component.ui.pages;

import net.thucydides.core.pages.PageObject;
import org.apache.commons.io.FileUtils;
import org.jruby.RubyProcess;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.ho.cts.utils.Configuration;
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
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// Register Sponsor in IE
public class Page_SponserLicence{

    public String licenceNumber;
    WebDriver driver;
    private String PBS_SPONSOR_URL="pbs.SponsorHome.url";
    public String userID_value;
    public String sponserPassword;

//    @FindBy(xpath = "//a[@id='formSponsorHome:licence']")
//    WebElement applySponsorLicenceButton;
//    @FindBy(name = "sponsorappcategory:apply_0")
//    WebElement tier2Generl;
//    @FindBy(name = "sponsorappcategory:apply_4")
//    WebElement tier4Generl;
//    @FindBy(name = "sponsorappcategory:apply_6")
//    WebElement tier5CretvSprt;
//    @FindBy(id = "sponsorappcategory:previouslicence:1")
//    WebElement regstrdSponsr;
//    @FindBy(name = "sponsorappcategory:next")
//    WebElement nextButton;
//
//    @FindBy(name = "sponsorapporg1:orgName")
//    WebElement orgName;
//    @FindBy(name = "sponsorapporg1:orgAddress1")
//    WebElement orgAddrss;
//    @FindBy(name = "sponsorapporg1:orgAddress4")
//    WebElement orgAddrss4;
//    @FindBy(name = "sponsorapporg1:orgPostcode")
//    WebElement orgPostCode;
//    @FindBy(name = "sponsorapporg1:orgTelephone")
//    WebElement orgTelephoneNumber;
//    @FindBy(name = "sponsorapporg1:next")
//    WebElement OrganisationnextButton;
//    @FindBy(name = "sponsorapporg2:apply_3")
//    WebElement orgRegions;
//    @FindBy(name = "sponsorapporg2:next")
//    WebElement orgRegionsNext;
//
//
//    @FindBy(name = "sponsorapporg3:organisationSize")
//    WebElement orgSize;
//    @FindBy(id = "sponsorapporg3:employingMigrantWorkers:0")
//    WebElement orgMigrantWrkrs;
//    @FindBy(name = "sponsorapporg3:numberMigrantWorkers")
//    WebElement orgMigrantWrkrsSize;
//    @FindBy(id = "sponsorapporg3:businessSector")
//    WebElement orgSectorDropDown;
//    @FindBy(id = "sponsorapporg3:orgRegType")
//    WebElement orgTypeDropDown;
//    @FindBy(id = "sponsorapporg3:charitableStatus:1")
//    WebElement orgCharityStatus;
//    @FindBy(id = "sponsorapporg3:organisationType")
//    WebElement orgType;
//    @FindBy(id = "sponsorapporg3:timeTrading")
//    WebElement orgTradingDropdown;
//    @FindBy(id = "sponsorapporg3:registeredHMRC:1")
//    WebElement orgVATRestrd;
//    @FindBy(id = "sponsorapporg3:next")
//    WebElement step3NextButton;
//
//    @FindBy(id = "sponsorapporg4:stockExchangeReg:1")
//    WebElement orgnstn4StckExchnge;
//    @FindBy(id = "sponsorapporg4:accreditationRequired:1")
//    WebElement orgnstn4Accreditation;
//    @FindBy(id = "sponsorapporg4:next")
//    WebElement step4NextButton;
//
//    @FindBy(id = "sponsorappeducation:EducationalId")
//    WebElement institutionType;
//    @FindBy(id = "sponsorappeducation:publiclyfunded:1")
//    WebElement publicFundStatus;
//    @FindBy(id = "sponsorappeducation:InspectingAuditingId")
//    WebElement auditingBody;
//    @FindBy(id = "sponsorappeducation:accreditationbody")
//    WebElement exemptionReview;
//    @FindBy(id = "sponsorappeducation:next")
//    WebElement sponsorAppNextButton;
//
//    @FindBy(name = "sponsorAppSubTierCOS:cos")
//    WebElement sponsorCOSCnt;
//    @FindBy(name = "sponsorAppSubTierCOS:reason")
//    WebElement sponsorCOSTxt;
//    @FindBy(name = "sponsorAppSubTierCOS:next")
//    WebElement sponsorCOSNextButton;
//    @FindBy(name = "sponsorAppT4GCOS:cos")
//    WebElement sponsorT4CASCnt;
//    @FindBy(name = "sponsorAppT4GCOS:reason")
//    WebElement sponsorT4CASTxt;
//    @FindBy(name = "sponsorAppT4GCOS:next")
//    WebElement sponsorCAST4NextButton;
//
//    @FindBy(name = "sponsorAppT5SCOS:cos")
//    WebElement sponsorT5COSCnt;
//    @FindBy(name = "sponsorAppT5SCOS:reason")
//    WebElement sponsorT5COSTxt;
//    @FindBy(id = "sponsorAppT5SCOS:group")
//    WebElement sponsorT5COSGrp;
//    @FindBy(id = "sponsorAppT5SCOS:getBodies")
//    WebElement sponsorT5COSGrpFndBdy;
//    @FindBy(id = "sponsorAppT5SCOS:body")
//    WebElement sponsorT5COSGrpSprtngBdy;
//    @FindBy(id = "sponsorAppT5SCOS:category")
//    WebElement sponsorT5COSGrpOrgGrp;
//    @FindBy(id = "sponsorAppT5SCOS:next")
//    WebElement sponsorT5NextButton;
//
//    @FindBy(xpath = "//input[@value=\"AUDANACC_provide\"]")
//    WebElement sponsorDocAcc;
//    @FindBy(xpath = "//input[@value=\"GBODREG_provide\"]")
//    WebElement sponsorDocRegstrn;
//    @FindBy(xpath = "//input[@value=\"FRANAGR_provide\"]")
//    WebElement sponsorDocFrnchsAgrrmnt;
//    @FindBy(xpath = "//input[@value=\"SPOBDEND_provide\"]")
//    WebElement sponsorDocSprtBdyEndrsment;
//    @FindBy(xpath = "//input[@value=\"DIGITECH_provide\"]")
//    WebElement sponsorDocDeclrtn;
//    @FindBy(xpath = "//input[@value=\"EMPLIAIC_provide\"]")
//    WebElement sponsorDocEmplymntInsrnc;
//    @FindBy(id = "sponsorAppDocs:next")
//    WebElement sponsorDocNextButton;
//
//
//    @FindBy(id = "formSponsorappao:title")
//    WebElement sponsorCntctTitle;
//    @FindBy(id = "formSponsorappao:firstName")
//    WebElement sponsorCntct1stName;
//    @FindBy(id = "formSponsorappao:lastName")
//    WebElement sponsorCntctLastName;
//    @FindBy(id = "formSponsorappao:addressLine1")
//    WebElement sponsorCntctAddrss1;
//    @FindBy(id = "formSponsorappao:townCity")
//    WebElement sponsorCntcCity;
//    @FindBy(id = "formSponsorappao:postcode")
//    WebElement sponsorCntctPstCde;
//    @FindBy(id = "formSponsorappao:number")
//    WebElement sponsorCntctTelphnNumber;
//    @FindBy(id = "formSponsorappao:email")
//    WebElement sponsorCntctEmail;
//    @FindBy(id = "formSponsorappao:dateOfBirth:day")
//    WebElement sponsorCntctDay;
//    @FindBy(id = "formSponsorappao:dateOfBirth:month")
//    WebElement sponsorCntctMnth;
//    @FindBy(id = "formSponsorappao:dateOfBirth:year")
//    WebElement sponsorCntctYear;
//    @FindBy(id = "formSponsorappao:nationality")
//    WebElement sponsorCntctNationality;
//    @FindBy(id = "formSponsorappao:orgPosition")
//    WebElement sponsorCntctRole;
//    @FindBy(id = "formSponsorappao:isUnderImmigrationControl:1")
//    WebElement sponsorCntctImmgrtnCntrl;
//    @FindBy(id = "formSponsorappao:buttonNext")
//    WebElement sponsorCntctNextButton;
//    @FindBy(id = "sponsorappconvictionsao:convictionPenaltyRadio:1")
//    WebElement sponsorCntcPenalty;
//    @FindBy(id = "sponsorappconvictionsao:next")
//    WebElement sponsorCntcNextButton;
//    @FindBy(id = "sponsorappcontact:officer:0")
//    WebElement sponsorCntcKeyCntct;
//    @FindBy(id = "sponsorappcontact:buttonNext")
//    WebElement sponsorCntcKeyCntctNextButton;
//
//    @FindBy(id = "sponsorapplevel1user:levelOneUserIs:0")
//    WebElement sponsorCntcUserLevel;
//    @FindBy(id = "sponsorapplevel1user:buttonNext")
//    WebElement sponsorCntcUserLevelNextButton;
//    @FindBy(id = "sponsorapprep:representativeAppointed:1")
//    WebElement sponsorCntcRepresnt;
//    @FindBy(id = "sponsorapprep:next")
//    WebElement sponsorCntcRepresntNextButton;
//    @FindBy(id = "sponsorappspondec:confirmacceptance")
//    WebElement sponsorCntcAcctptnc;
//    @FindBy(id = "sponsorappspondec:date")
//    WebElement sponsorCntcAcctptncDate;
//    @FindBy(id = "sponsorappspondec:next")
//    WebElement sponsorCntcSubmit;
//    @FindBy(id = "sponsorappcalccost:refno")
//    WebElement sponsorLicenceNumber;
//    @FindBy(id = "sponsorappcalccost:payment")
//    WebElement sponsorLicencePayment;
//    @FindBy(id = "sponsorappcalccost:next")
//    WebElement sponsorPaymentNextButton;
//    @FindBy(id = "sponsorappsponpaymentdec:confirmacceptance")
//    WebElement sponsorLicencePaymentDeclartn;
//    @FindBy(id = "sponsorappsponpaymentdec:date")
//    WebElement sponsorLicencePaymentDeclartnDate;
//    @FindBy(id = "sponsorappsponpaymentdec:next")
//    WebElement sponsorLicencePaymentNextButton;
//    @FindBy(id = "epaynext")
//    WebElement epayNextButton;
//    @FindBy(name = "op-DPChoose-VISA^SSL")
//    WebElement VISAButton;
//    @FindBy(id = "cardNoInput")
//    WebElement cardNumber;
//    @FindBy(id = "cardCVV")
//    WebElement cardCVVNum;
//    @FindBy(name = "cardExp.month")
//    WebElement cardExpMonth;
//    @FindBy(name = "cardExp.year")
//    WebElement cardExpYear;
//    @FindBy(id = "name")
//    WebElement cardHolderName;
//    @FindBy(id = "address1")
//    WebElement cardHolderAddrss;
//    @FindBy(id = "town")
//    WebElement cardHolderTown;
//    @FindBy(id = "postcode")
//    WebElement cardHolderPostCode;
//    @FindBy(name = "country")
//    WebElement cardHolderCountry;
//    @FindBy(name = "email")
//    WebElement cardHolderEmail;
//    @FindBy(xpath = "//iframe[@name='a-etiw1a8mjkz4']")
//    WebElement captchaIFrame;
//    @FindBy(css = "body:nth-child(2)")
//    WebElement cardCaptcha;
//    @FindBy(name = "op-PMMakePayment")
//    WebElement makePaymnt;
//    @FindBy(xpath = "//input[@id='op-PMMakePayment']")
//    WebElement makePaymntContnue;
//    @FindBy(xpath = "//input[@type=\"submit\"]")
//    WebElement makePaymntSubmit;
//    @FindBy(id = "actionA") WebElement authorisePayment;
//    @FindBy(xpath = "//input[@value='OK']") WebElement submitPayment;
//
//    @FindBy(id = "applicationUrn") WebElement sponsorRefNumber;
//    @FindBy(id = "applicationPnn") WebElement sponsorPaymentNumber;
//    @FindBy(id = "j_idt10:sponsorNavigation:logout") WebElement sponsorLogout;


    private String currentDir = System.getProperty("user.dir");
    Faker faker = new Faker();
    WebDriverWait wait;

    //GetScreenShot Method Directory and Image File
    private File getSreenShotMethodImageFile = new File (currentDir +
            "/src/test/resources/ScreenShots/SponsorPaymentSubmissionPage.png");


    public void applySponsorLicence() {
        try {
//            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@name=\"formSponsorHome:licence\" and @id=\"formSponsorHome:licence\"]")))).click();
//            applySponsorLicenceButton.click();
            driver.findElement(By.name("formSponsorHome:licence")).click();
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorappcategory:apply_0")))).click();
//            tier2Generl.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorappcategory:apply_4")))).click();
//            tier4Generl.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorappcategory:apply_6")))).click();
//            tier5CretvSprt.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappcategory:previouslicence:1")))).click();
//            regstrdSponsr.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorappcategory:next")))).click();
//            nextButton.click();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void completeOrganisationDetails() {
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorapporg1:orgName")))).sendKeys(faker.address().firstName());
//            orgName.sendKeys(faker.address().firstName());
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorapporg1:orgAddress1")))).sendKeys(faker.address().streetAddress());
//            orgAddrss.sendKeys(faker.address().streetAddress());
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorapporg1:orgAddress4")))).sendKeys("test");
//            orgAddrss4.sendKeys("test");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorapporg1:orgPostcode")))).sendKeys("CR0 2EU");
//            orgPostCode.sendKeys("CR0 2EU");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorapporg1:orgTelephone")))).sendKeys("11111111111");
//            orgTelephoneNumber.sendKeys("11111111111");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorapporg1:next")))).click();
//            OrganisationnextButton.click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorapporg2:apply_3")))).click();
//            orgRegions.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorapporg2:next")))).click();
//            orgRegionsNext.click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorapporg3:organisationSize")))).sendKeys("1000");
            Thread.sleep(3000);
//            orgSize.sendKeys("1000");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapporg3:employingMigrantWorkers:0")))).click();
//            orgMigrantWrkrs.click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorapporg3:numberMigrantWorkers")))).sendKeys("250");
//            orgMigrantWrkrsSize.sendKeys("250");
            Thread.sleep(3000);
            WebElement orgSectorDropDown = driver.findElement(By.id("sponsorapporg3:businessSector"));
            selectDropDown(orgSectorDropDown, "Information and Communications");
            WebElement orgTypeDropDown = driver.findElement(By.id("sponsorapporg3:orgRegType"));
            selectDropDown(orgTypeDropDown, "Single Body");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapporg3:charitableStatus:1")))).click();
            Thread.sleep(5000);
//            orgCharityStatus.click();
            WebElement orgType = driver.findElement(By.id("sponsorapporg3:organisationType"));
            selectDropDown(orgType, "Sole Trader");
            WebElement orgTradingDropdown = driver.findElement(By.id("sponsorapporg3:timeTrading"));
            selectDropDown(orgTradingDropdown, "Over 36 months+");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapporg3:registeredHMRC:1")))).click();
            Thread.sleep(3000);
//            orgVATRestrd.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapporg3:next")))).click();
//            step3NextButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapporg4:stockExchangeReg:1")))).click();
//            orgnstn4StckExchnge.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapporg4:accreditationRequired:1")))).click();
//            orgnstn4Accreditation.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapporg4:next")))).click();
//            step4NextButton.click();
            Thread.sleep(5000);
            WebElement institutionType = driver.findElement(By.id("sponsorappeducation:EducationalId"));
            selectDropDown(institutionType, "Higher Education Institution (HEI)");
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappeducation:publiclyfunded:1")))).click();
//            publicFundStatus.click();
            WebElement auditingBody = driver.findElement(By.id("sponsorappeducation:InspectingAuditingId"));
            selectDropDown(auditingBody, "Ofsted");
            Thread.sleep(3000);
            WebElement exemptionReview = driver.findElement(By.id("sponsorappeducation:accreditationbody"));
            selectDropDownByValue(exemptionReview, "NO");
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappeducation:next")))).click();
            Thread.sleep(5000);
//            sponsorAppNextButton.click();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }

    }

    public void selectDropDown(WebElement element1, String optionVal) {
        Select selectElement = new Select(element1);
        List<WebElement> allOptions = selectElement.getOptions();
        selectElement.selectByVisibleText(optionVal);
    }

    public void selectDropDownByValue(WebElement element1, String optionVal) {
        Select selectElement = new Select(element1);
        List<WebElement> allOptions = selectElement.getOptions();
//        for(WebElement ele: allOptions)
//        {
//            System.out.println("Select dropdown options are ::" + ele.getText());
//        }
        selectElement.selectByValue(optionVal);
    }


    public void applyCOSCASDetails() {
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorAppSubTierCOS:cos")))).sendKeys("250");
//            sponsorCOSCnt.sendKeys("250");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorAppSubTierCOS:reason")))).sendKeys("COS licence");
//            sponsorCOSTxt.sendKeys("COS licence");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorAppSubTierCOS:next")))).click();
//            sponsorCOSNextButton.click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorAppT4GCOS:cos")))).sendKeys("250");
//            sponsorT4CASCnt.sendKeys("250");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorAppT4GCOS:reason")))).sendKeys("CAS licence");
//            sponsorT4CASTxt.sendKeys("CAS licence");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorAppT4GCOS:next")))).click();
//            sponsorCAST4NextButton.click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorAppT5SCOS:cos")))).sendKeys("250");
//            sponsorT5COSCnt.sendKeys("250");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("sponsorAppT5SCOS:reason")))).sendKeys("COS licence");
//            sponsorT5COSTxt.sendKeys("COS licence");
            WebElement sponsorT5COSGrp = driver.findElement(By.id("sponsorAppT5SCOS:group"));
            selectDropDown(sponsorT5COSGrp, "Cycling");
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorAppT5SCOS:getBodies")))).click();
//            sponsorT5COSGrpFndBdy.click();
            Thread.sleep(5000);
            WebElement sponsorT5COSGrpSprtngBdy = driver.findElement(By.id("sponsorAppT5SCOS:body"));
            selectDropDown(sponsorT5COSGrpSprtngBdy, "British Cycling");
            Thread.sleep(3000);
            WebElement sponsorT5COSGrpOrgGrp = driver.findElement(By.id("sponsorAppT5SCOS:category"));
            selectDropDown(sponsorT5COSGrpOrgGrp, "Creative - Music");
            Thread.sleep(3000);
            driver.findElement(By.id("sponsorAppT5SCOS:next")).click();
            Thread.sleep(5000);
//            sponsorT5NextButton.click();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
    }


    public void supportingDocumentCheck() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value=\"AUDANACC_provide\"]")))).click();
//        sponsorDocAcc.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value=\"GBODREG_provide\"]")))).click();
//        sponsorDocRegstrn.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value=\"FRANAGR_provide\"]")))).click();
//        sponsorDocFrnchsAgrrmnt.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value=\"SPOBDEND_provide\"]")))).click();
//        sponsorDocSprtBdyEndrsment.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value=\"DIGITECH_provide\"]")))).click();
//        sponsorDocDeclrtn.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value=\"EMPLIAIC_provide\"]")))).click();
//        sponsorDocEmplymntInsrnc.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorAppDocs:next")))).click();
            Thread.sleep(5000);
//        sponsorDocNextButton.click();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void completeContactDetails() {
        try {
            WebElement sponsorCntctTitle = driver.findElement(By.id("formSponsorappao:title"));
            selectDropDown(sponsorCntctTitle, "Mrs");
            driver.findElement(By.id("formSponsorappao:firstName")).sendKeys(faker.name().firstName());
//            sponsorCntct1stName.sendKeys(faker.name().firstName());
            driver.findElement(By.id("formSponsorappao:lastName")).sendKeys(faker.name().lastName());
//            sponsorCntctLastName.sendKeys(faker.name().lastName());
            driver.findElement(By.id("formSponsorappao:addressLine1")).sendKeys(faker.address().streetAddress());
//            sponsorCntctAddrss1.sendKeys(faker.address().streetAddress());
            driver.findElement(By.id("formSponsorappao:townCity")).sendKeys(faker.address().city());
//            sponsorCntcCity.sendKeys(faker.address().city());
            driver.findElement(By.id("formSponsorappao:postcode")).sendKeys("CR0 2EU");
//            sponsorCntctPstCde.sendKeys("CR0 2EU");
            driver.findElement(By.id("formSponsorappao:number")).sendKeys("11111111111");
//            sponsorCntctTelphnNumber.sendKeys("11111111111");
            driver.findElement(By.id("formSponsorappao:email")).sendKeys("pbs.inttesting@gmail.com");
//            sponsorCntctEmail.sendKeys("pbs.inttesting@gmail.com");
            WebElement sponsorCntctDay = driver.findElement(By.id("formSponsorappao:dateOfBirth:day"));
            selectDropDown(sponsorCntctDay, "07");
            WebElement sponsorCntctMnth = driver.findElement(By.id("formSponsorappao:dateOfBirth:month"));
            selectDropDown(sponsorCntctMnth, "September");
            WebElement sponsorCntctYear = driver.findElement(By.id("formSponsorappao:dateOfBirth:year"));
            selectDropDown(sponsorCntctYear, "1995");
            WebElement sponsorCntctNationality = driver.findElement(By.id("formSponsorappao:nationality"));
            selectDropDown(sponsorCntctNationality, "AFGHANISTAN");
            driver.findElement(By.id("formSponsorappao:orgPosition")).sendKeys("Director");
//            sponsorCntctRole.sendKeys("Director");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("formSponsorappao:isUnderImmigrationControl:1")))).click();
//            sponsorCntctImmgrtnCntrl.click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("formSponsorappao:buttonNext")))).click();
//            sponsorCntctNextButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappconvictionsao:convictionPenaltyRadio:1")))).click();
//            sponsorCntcPenalty.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappconvictionsao:next")))).click();
//            sponsorCntcNextButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappcontact:officer:0")))).click();
//            sponsorCntcKeyCntct.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappcontact:buttonNext")))).click();
//            sponsorCntcKeyCntctNextButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapplevel1user:levelOneUserIs:0")))).click();
//            sponsorCntcUserLevel.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapplevel1user:buttonNext")))).click();
//            sponsorCntcUserLevelNextButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapprep:representativeAppointed:1")))).click();
//            sponsorCntcRepresnt.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorapprep:next")))).click();
//            sponsorCntcRepresntNextButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappspondec:confirmacceptance")))).click();
//            sponsorCntcAcctptnc.click();
            driver.findElement(By.id("sponsorappspondec:date")).sendKeys(getCurrentDate());
//            sponsorCntcAcctptncDate.sendKeys(getCurrentDate());
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappspondec:next")))).click();
//            sponsorCntcSubmit.click();
            Thread.sleep(5000);
            readLicenceNumber();
            WebElement sponsorLicencePayment = driver.findElement(By.id("sponsorappcalccost:payment"));
            selectDropDown(sponsorLicencePayment, "£536 (see Help below)");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappcalccost:next")))).click();
//            sponsorPaymentNextButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappsponpaymentdec:confirmacceptance")))).click();
//            sponsorLicencePaymentDeclartn.click();
            driver.findElement(By.id("sponsorappsponpaymentdec:date")).sendKeys(getCurrentDate());
//            sponsorLicencePaymentDeclartnDate.sendKeys(getCurrentDate());
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("sponsorappsponpaymentdec:next")))).click();
//            sponsorLicencePaymentNextButton.click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("epaynext")))).click();
            Thread.sleep(5000);
//            epayNextButton.click();
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
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("actionA")))).click();
//            authorisePayment.click();
            Thread.sleep(8000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@value=\"OK\"]")))).click();
//            submitPayment.click();
            Thread.sleep(8000);
            getSponsorDetails();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("j_idt10:sponsorNavigation:logout")))).click();
//            sponsorLogout.click();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            this.getDriver().close();
            System.out.println("Completed Registration");
        }
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String newDate = dateFormat.format(date);
        return newDate;
    }

    public String readLicenceNumber() {

        licenceNumber = driver.findElement(By.id("sponsorappcalccost:refno")).getText();
        System.out.println("Sponsor Licence number is :: " + licenceNumber);
        return licenceNumber;
    }

    public void getSponsorDetails() throws IOException
    {

        String sponsrURNnumb = driver.findElement(By.id("applicationUrn")).getText();
        System.out.println("Sponsor URN number is :: " + sponsrURNnumb);
        String sponsrPaymntnumb = driver.findElement(By.id("applicationPnn")).getText();
        System.out.println("Sponsor Payment number is :: " + sponsrPaymntnumb);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Write Screenshot to a file
        FileUtils.copyFile(scrFile, getSreenShotMethodImageFile);
    }

    public void openSponsorApp()
    {
        try {
//            this.getDriver().get("https://metastorm-ho-it-msit1-i-spon-pbs.service.np.iptho.co.uk/metastorm");
//                String downloadPath =
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory",
                    System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browser", "IE");
            caps.setCapability("browser_version", "11.0");
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("resolution", "1024x768");
            caps.setCapability("name", "Bstack-[Java] Sample Test");
            caps.setCapability("browserstack.local", "true");
            caps.setCapability("browserstack.localIdentifier", "Test123");
            caps.setCapability("acceptSslCerts", "true");
            caps.setCapability("browserstack.ie.noFlash", "true");
            caps.setCapability("browserstack.ie.enablePopups", "true");
            caps.setCapability("browserstack.debug", "true");
            caps.setCapability("browserstack.console", "warnings");
            caps.setCapability("ACCEPT_SSL_CERTS", "true");
            caps.setCapability("ignoreProtectedModeSettings", "true");

//                File download properties
            caps.setCapability("browser.download.folderList", 2);
            caps.setCapability("browser.download.manager.showWhenStarting", false);
            caps.setCapability("browser.download.manager.focusWhenStarting", false);
            caps.setCapability("browser.download.useDownloadDir", true);
            caps.setCapability("browser.helperApps.alwaysAsk.force", false);
            caps.setCapability("browser.download.defaultFolder", System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
            caps.setCapability("browser.download.lastDir", System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
            caps.setCapability("browser.download.manager.alertOnEXEOpen", false);
            caps.setCapability("browser.download.manager.closeWhenDone", true);
            caps.setCapability("browser.download.manager.showAlertOnComplete", false);
            caps.setCapability("browser.download.manager.useWindow", false);
            caps.setCapability("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
            caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            caps.setCapability("browser.requireWindowFocus", "true");
            caps.setCapability("browser.enablePersistentHover", "true");
            caps.setCapability("prefs", prefs);


//                caps.setCapability("browser.download.manager.focusWhenStarting","true");
//                caps.setCapability("browser.download.manager.alertOnExeOpen","false");
//                caps.setCapability("browser.download.manager.useWindow","false");
//                caps.setCapability("server.sync.prefs.sync.browser.download.manager.showWhenStarting", "true");


            driver = new RemoteWebDriver(new URL("https://iptcompatibility:fjPgJqbqtN1yhVb53FZq@hub-cloud.browserstack.com/wd/hub"), caps);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 20);
            driver.get("https://amber-ho-it-msit1-e-spon-pbs-kops10.service.np.iptho.co.uk/gui-sponsor-jsf/SponsorHome.faces");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void navigateToHomePage() {
        try {
            openSponsorApp();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"formSponsorHome:register\"]")))).click();
            Thread.sleep(5000);
            System.out.println("Register link Select");
            driver.findElement(By.name("sponsorregister:salutation")).sendKeys("Test Name");
            driver.findElement(By.name("sponsorregister:emailAddress")).sendKeys("pbs.inttesting@gmail.com");
            driver.findElement(By.name("sponsorregister:emailAddressRep")).sendKeys("pbs.inttesting@gmail.com");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorregister:register")))).click();
            Thread.sleep(3000);
            System.out.println("Enter login credentials");
            userID_value = driver.findElement(By.xpath("//*[@id=\"centralContent\"]/table/tbody/tr/td[2]")).getText();
            System.out.println("User ID is :: " + userID_value);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorregisterconf:ok")))).click();
            Thread.sleep(3000);
            System.out.println("Registration confirmed");
            retrieveEmailData();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void retrieveEmailData()
    {
        String host = "iimap.gmail.com";
        String mailStoreType = "imap";
        String username = "pbs.inttesting@gmail.com";
        String password = "P1ssword!";
        try {
            sponserPassword = ReadEmailData.check(host, mailStoreType, username, password);
            System.out.println("Password from email is :: " + sponserPassword);
            System.out.println("email USERID is :: " + userID_value);
            System.out.println("email USER PWD is ::" + sponserPassword+"I'm writing password");
            driver.findElement(By.name("j_username")).clear();
            driver.findElement(By.name("j_username")).sendKeys(userID_value);
            Thread.sleep(3000);
            driver.findElement(By.name("j_password")).clear();
            driver.findElement(By.name("j_password")).sendKeys(sponserPassword);
            Thread.sleep(3000);
            driver.findElement(By.name("login")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("sponsorpwdchange:password")).sendKeys(sponserPassword);
            driver.findElement(By.id("sponsorpwdchange:newpassword")).sendKeys("Password1!");
            driver.findElement(By.id("sponsorpwdchange:confirmnewpassword")).sendKeys("Password1!");
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorpwdchange:submit")))).click();
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("sponsorpwdchangeconf:ok")))).click();
            Thread.sleep(8000);

        }catch(Exception e)
        {
            System.err.println(e.getStackTrace());
        }

    }



}
