package uk.gov.ho.domain.component.ui.pages;

import com.browserstack.local.Local;
import com.ibm.icu.impl.UResource;
//import net.serenitybdd.screenplay.actions.SendKeys;
import net.thucydides.core.pages.PageObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.FindBy;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//working until download popup

public class MetaPage
{
    static Local bsLocal;
    WebDriver driver;
    WebElement proceedTabEle;
    WebElement tabRowData;
    List<WebElement> cols;
    Page_SponserLicence sponsorPageObj = new Page_SponserLicence();
    //        String licence = sponsorPageObj.readLicenceNumber();
    public static final String USERNAME = "iptcompatibility";
    public static final String AUTOMATE_KEY = "fjPgJqbqtN1yhVb53FZq";
    public static final String browserStackURL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @FindBy(xpath = "//*[text()='Metastorm BPM Server']") WebElement metastormServerLogin;
    @FindBy(name = "eFormContents") WebElement metaStormiFrame;
    @FindBy(id = "eUserLoginForm") WebElement UserLoginForm;
    @FindBy(xpath = "//input[@name='username']") WebElement loginUserName;
    @FindBy(xpath = "//input[@name='pwd']") WebElement loginPassword;



    public void switchToIframe()
    {
        WebElement iframe_ele = null;
        String orgname = null;
        try {
            Thread.sleep(5000);
            String parentHandle = driver.getWindowHandle();
            System.out.println("1st window :: " + parentHandle);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            Set<String> allWindows = driver.getWindowHandles();
            for (String handle : allWindows) {
                if(!(handle.equalsIgnoreCase(parentHandle))) {
                    System.out.println("2nd window :: " + handle);
//                        Switching back to 2nd window
                    driver.switchTo().window(handle);
//                        driver.manage().window().maximize();
                    System.out.println("URL is :: " + driver.getCurrentUrl());
                    iframe_ele = driver.findElement(By.id("eFormContents"));
//                        Switching to IFRAME
                    driver.switchTo().frame(iframe_ele);
                    driver.findElement(By.xpath("//*[@id='username']")).sendKeys("ml-caseworker13");
                    driver.findElement(By.id("pwd")).sendKeys("Password101!");
//                        Switching back from IFRAME
                    driver.switchTo().defaultContent();
                    driver.findElement(By.xpath("//img[@id='btnConfirm']")).click();
                    Thread.sleep(5000);
                    System.out.println("switching to parent window :: " + parentHandle);
//                        Switching back to 1st window
                    driver.switchTo().window(parentHandle);
                    break;
                }
            }
////              On 1st window here
            driver.findElement(By.xpath("//img[@title='Administration Forms']")).click();
            WebElement tableIframe = driver.findElement(By.name("eListsView"));
            driver.switchTo().frame(tableIframe);
            getTableData();
            for (String handle : driver.getWindowHandles()) {
                System.out.println("current window is :: " + handle);
                if(!(handle.equalsIgnoreCase(parentHandle))) {
                    driver.switchTo().window(handle);
                    driver.manage().window().maximize();
                    System.out.println("URL is :: " + driver.getCurrentUrl());
                    iframe_ele = driver.findElement(By.id("eFormContents"));
                    driver.switchTo().frame(iframe_ele);
                    driver.findElement(By.id("tmpTxtCF_ApplicationURN")).sendKeys("SPL7164000034");
//////                        driver.findElement(By.id("tmpTxtCF_ApplicationURN")).sendKeys(licence);
                    driver.findElement(By.id("ebtnSearch")).click();
                    orgname = driver.findElement(By.xpath("//*[@dataFld='txtCD_OrganizationName']")).getText();
//                        orgname = "Ubaldo";
                    driver.findElement(By.xpath("//div[@dataFld='txtA_ApplicationURN' and text()='SPL7164000034']")).click();
////                        driver.findElement(By.xpath("//div[@dataFld='txtA_ApplicationURN' and text()='"+licence+"']")).click();
                    driver.findElement(By.id("btnAllocate1")).click();
                    driver.switchTo().defaultContent();
                    driver.findElement(By.xpath("//img[@id='btnConfirm']")).click();
                    System.out.println("switching to parent window :: " + parentHandle);
                    driver.switchTo().window(parentHandle);
                    break;
                }
            }

            driver.findElement(By.xpath("//img[@title='To Do List']")).click();
//                Switching to IFRAME TODOLIST
            driver.switchTo().frame(tableIframe);
            driver.findElement(By.xpath("//img[@title='Last page']")).click();
            Thread.sleep(3000);
            if((driver.findElements(By.xpath("//*[text()='New Application by "+orgname+"']"))).size() > 0 ) {
                driver.findElement(By.xpath("//*[text()='New Application by " + orgname + "']")).click();
                Thread.sleep(5000);
            }else
            {
                driver.findElement(By.xpath("//img[@title='Previous page']")).click();
                driver.findElement(By.xpath("//*[text()='New Application by " + orgname + "']")).click();
                Thread.sleep(5000);
            }
            String currentWindow = driver.getWindowHandle();
            System.out.println("current window is :: " + currentWindow);
//                Set<String> allWindows = driver.getWindowHandles();
            System.out.println("total number of windows are ::" + driver.getWindowHandles().size());
            for (String handle : driver.getWindowHandles()) {
                if(!(handle.equalsIgnoreCase(currentWindow))) {
                    System.out.println("switching to second window :: " + handle);
                    driver.switchTo().window(handle);
                    driver.manage().window().maximize();
                    System.out.println("URL is :: " + driver.getCurrentUrl());
                    System.out.println("Page source is :: " + driver.getPageSource());
                    retrieveConfirmCancelAreaTableData();
                    for(WebElement col : cols)
                    {
                        System.out.println("Inside round ");
                        System.out.println("table row data is ***::" + col.getText()+"hello");
                        if((col.getText()).equalsIgnoreCase(" Proceed to Caseworking ")) {
                            System.out.println("clicking element :: " + col.getText());
                            col.click();
                            break;
                        }
                    }

                    Thread.sleep(5000);
                    for(String newHandle : driver.getWindowHandles())
                    {
                        if(!(newHandle.equalsIgnoreCase(handle)) && !(newHandle.equalsIgnoreCase(currentWindow)))
                        {
                            System.out.println("current 3rd  window is :: " + newHandle);
                            driver.switchTo().window(newHandle);
                            driver.manage().window().maximize();
                            driver.switchTo().frame(driver.findElement(By.name("eFormContents")));
                            driver.findElement(By.id("YesBtn")).click();
                            Thread.sleep(2000);
                            driver.switchTo().window(handle);
                        }
                    }
                    retrieveConfirmCancelAreaTableData();
                    for(WebElement col : cols)
                    {
                        System.out.println("Inside round ");
                        System.out.println("table row data is ***::" + col.getText()+"hello");
                        if((col.getText()).equalsIgnoreCase(" Perform Actions ")) {
                            System.out.println("clicking element :: " + col.getText());
                            col.click();
                            Thread.sleep(3000);
                            break;
                        }
                    }

                    Thread.sleep(2000);
                    for(String newHandle : driver.getWindowHandles())
                    {
                        if(!(newHandle.equalsIgnoreCase(handle)) && !(newHandle.equalsIgnoreCase(currentWindow)))
                        {
                            System.out.println("switching to new 3rd  window is :: " + newHandle);
                            driver.switchTo().window(newHandle);
                            driver.manage().window().maximize();
                            driver.findElement(By.xpath("//img[@id='btnConfirm']")).click();
                            Thread.sleep(2000);
                            System.out.println("switching back to current 2nd window :: " + parentHandle);
                            driver.switchTo().window(handle);
                        }
                    }
                    retrieveConfirmCancelAreaTableData();
                    for(WebElement colele : cols)
                    {
                        System.out.println("Inside round ");
                        System.out.println("table row data is ***::" + colele.getText()+"hello");
                        if((colele.getText()).equalsIgnoreCase(" Update Submission Sheet Details ")) {
                            System.out.println("clicking element :: " + colele.getText());
                            colele.click();
                            Thread.sleep(3000);
                            break;
                        }
                    }

                    for(String newHandle : driver.getWindowHandles())
                    {
                        if(!(newHandle.equalsIgnoreCase(handle)) && !(newHandle.equalsIgnoreCase(currentWindow)))
                        {
                            System.out.println("switching to new 3rd  window is :: " + newHandle);
                            driver.switchTo().window(newHandle);
                            System.out.println("3rd  window URL is :: " + driver.getCurrentUrl());
                            driver.switchTo().frame(driver.findElement(By.id("eFormContents")));
                            WebElement dropdownBoxele = driver.findElement(By.id("tmpTxtSubmissionSheetReceived1"));
                            sponsorPageObj.selectDropDown(dropdownBoxele, "Received");
                            driver.findElement(By.id("memSubmissionDetails")).sendKeys("Received sponsor licence");
                            driver.switchTo().defaultContent();
                            driver.findElement(By.xpath("//img[@id='btnConfirm']")).click();
                            Thread.sleep(5000);
                            System.out.println("switching back to current 2nd window :: " + handle);
                            driver.switchTo().window(handle);
                        }
                    }

                    retrieveConfirmCancelAreaTableData();
                    for(WebElement colele : cols)
                    {
                        System.out.println("Inside round ");
                        System.out.println("table row data is ***::" + colele.getText()+"hello");
                        if((colele.getText()).equalsIgnoreCase(" Record Validation Outcome ")) {
                            System.out.println("clicking element :: " + colele.getText());
                            colele.click();
                            Thread.sleep(3000);
                            break;
                        }
                    }

                    for(String newHandle : driver.getWindowHandles())
                    {
                        if(!(newHandle.equalsIgnoreCase(handle)) && !(newHandle.equalsIgnoreCase(currentWindow)))
                        {
                            System.out.println("switching to new 3rd  window is :: " + newHandle);
                            driver.switchTo().window(newHandle);
                            System.out.println("3rd  window URL is :: " + driver.getCurrentUrl());
                            driver.switchTo().frame(driver.findElement(By.id("eFormContents")));
                            WebElement dropdownBoxele = driver.findElement(By.id("txtValidationOutcome"));
                            sponsorPageObj.selectDropDown(dropdownBoxele, "Accept");
                            driver.findElement(By.id("memValidationDetails")).sendKeys("Accepting sponsor licence");
                            driver.switchTo().defaultContent();
                            driver.findElement(By.xpath("//img[@id='btnConfirm']")).click();
                            Thread.sleep(5000);
                            System.out.println("switching back to current 2nd window :: " + handle);
                            driver.switchTo().window(handle);
                        }
                    }

                    retrieveConfirmCancelAreaTableData();
                    for(WebElement colele : cols)
                    {
                        System.out.println("Inside round ");
                        System.out.println("table row data is ***::" + colele.getText()+"hello");
                        if((colele.getText()).equalsIgnoreCase(" Continue ")) {
                            System.out.println("clicking element :: " + colele.getText());
                            colele.click();
                            Thread.sleep(5000);
                            break;
                        }
                    }

                    for(String newHandle : driver.getWindowHandles())
                    {
                        if(!(newHandle.equalsIgnoreCase(handle)) && !(newHandle.equalsIgnoreCase(currentWindow)))
                        {
                            System.out.println("switching to new 3rd  window is :: " + newHandle);
                            driver.switchTo().window(newHandle);
                            System.out.println("3rd  window URL is :: " + driver.getCurrentUrl());
                            driver.findElement(By.xpath("//img[@id='btnConfirm']")).click();
                            Thread.sleep(8000);
                            System.out.println("switching back to current 1st window after continue confirm:: " + currentWindow);
                            driver.switchTo().window(currentWindow);
                            break;
                        }
                    }

                }
            }
            driver.get(driver.getCurrentUrl());
            Thread.sleep(3000);
            driver.manage().window().maximize();
            Thread.sleep(2000);
            driver.switchTo().frame(tableIframe);
            driver.findElement(By.xpath("//*[text()='New Application by "+orgname+"']")).click();
            Thread.sleep(3000);
//                String currentWindow = driver.getWindowHandle();
            System.out.println("current window is :: " + currentWindow);

            for (String handle : driver.getWindowHandles()) {
                if (!(handle.equalsIgnoreCase(currentWindow))) {
                    System.out.println("switching to second window :: " + handle);
                    driver.switchTo().window(handle);
                    driver.manage().window().maximize();
                    System.out.println("URL is :: " + driver.getCurrentUrl());
                    retrieveConfirmCancelAreaTableData();
                    for(WebElement col : cols)
                    {
                        System.out.println("Inside round ");
                        System.out.println("table row data is ***::" + col.getText()+"hello");
                        if((col.getText()).equalsIgnoreCase(" Update Managed Record ")) {
                            System.out.println("clicking element :: " + col.getText());
                            col.click();
                            break;
                        }
                    }

                    Thread.sleep(3000);

                    retrieveConfirmCancelAreaTableData();
                    for(WebElement col : cols) {
                        System.out.println("Inside round ");
                        String expTxt = col.getText();
                        System.out.println("table row data is ***::" + expTxt + "hello");
                        if (expTxt.equalsIgnoreCase(" Changes Complete ")) {
                            System.out.println("clicking element :: " + col.getText());
                            col.click();
                            Thread.sleep(5000);
                            break;
                        }
                    }
                    for(String newHandle : driver.getWindowHandles())
                    {
                        if(!(newHandle.equalsIgnoreCase(handle)) && !(newHandle.equalsIgnoreCase(currentWindow)))
                        {
                            System.out.println("switching to new 3rd  window is :: " + newHandle);
                            driver.switchTo().window(newHandle);
                            System.out.println("3rd  window URL is :: " + driver.getCurrentUrl());
                            driver.switchTo().frame(driver.findElement(By.id("eFormContents")));
                            System.out.println("clicking yes button");
                            driver.findElement(By.xpath("//button[@id='YesBtn']")).click();
                            Thread.sleep(8000);
                            driver.switchTo().window(currentWindow);
                            break;
                        }
                    }


                }
            }
            driver.get(driver.getCurrentUrl());
            Thread.sleep(3000);
            driver.manage().window().maximize();
            Thread.sleep(2000);
            driver.switchTo().frame(tableIframe);
            Thread.sleep(3000);
            if((driver.findElements(By.xpath("//*[text()='New Application by "+orgname+"']"))).size() > 0 ) {
                driver.findElement(By.xpath("//*[text()='New Application by " + orgname + "']")).click();
                Thread.sleep(5000);
            }else
            {
                driver.findElement(By.xpath("//img[@title='Previous page']")).click();
                driver.findElement(By.xpath("//*[text()='New Application by " + orgname + "']")).click();
                Thread.sleep(5000);
            }
            for (String handle : driver.getWindowHandles()) {
                System.out.println("windows are :: " + handle);
                if (!(handle.equalsIgnoreCase(currentWindow))) {
                    System.out.println("switching to second window :: " + handle);
                    driver.switchTo().window(handle);
                    System.out.println("maximized the window");
                    driver.manage().window().maximize();

                    System.out.println("URL is :: " + driver.getCurrentUrl());
                    System.out.println("Page source is :: " + driver.getPageSource());
                    driver.findElement(By.id("ActionsRightScroller")).click();
                    Thread.sleep(1000);
                    retrieveConfirmCancelAreaTableData();
                    for (WebElement col : cols) {
                        System.out.println("Inside round ");
                        System.out.println("table row data is ***::" + col.getText() + "hello");
                        if ((col.getText()).equalsIgnoreCase(" Review Service Standard ")) {
                            System.out.println("clicking element :: " + col.getText());
                            col.click();
                            Thread.sleep(5000);
                            break;
                        }
                    }
                    for(String newHandle : driver.getWindowHandles())
                    {
                        if(!(newHandle.equalsIgnoreCase(handle)) && !(newHandle.equalsIgnoreCase(currentWindow)))
                        {
                            System.out.println("switching to new 3rd  window is :: " + newHandle);
                            driver.switchTo().window(newHandle);
                            System.out.println("3rd  window URL is :: " + driver.getCurrentUrl());
                            driver.switchTo().frame(driver.findElement(By.id("eFormContents")));
                            WebElement srvcStatusSelect = driver.findElement(By.id("txtServiceStandardOutcome"));
                            sponsorPageObj.selectDropDown(srvcStatusSelect, "Met");
                            Thread.sleep(2000);
                            driver.findElement(By.id("memServiceStdComments")).sendKeys("test");
                            driver.switchTo().defaultContent();
                            driver.findElement(By.id("btnConfirm")).click();
                            Thread.sleep(3000);
                            driver.switchTo().window(handle);
                        }
                    }
                    retrieveConfirmCancelAreaTableData();
                    for (WebElement col : cols) {
                        System.out.println("Inside round ");
                        System.out.println("table row data is ***::" + col.getText() + "hello");
                        if ((col.getText()).equalsIgnoreCase(" Ad Hoc Letters ")) {
                            System.out.println("clicking element :: " + col.getText());
                            col.click();
                            Thread.sleep(3000);
                            break;
                        }
                    }
                    for(String newHandle : driver.getWindowHandles())
                    {
                        if(!(newHandle.equalsIgnoreCase(handle)) && !(newHandle.equalsIgnoreCase(currentWindow)))
                        {
                            System.out.println("switching to new 3rd  window is :: " + newHandle);
                            driver.switchTo().window(newHandle);
                            System.out.println("3rd  window URL is :: " + driver.getCurrentUrl());
                            driver.switchTo().frame(driver.findElement(By.id("eFormContents")));
                            WebElement catSelect = driver.findElement(By.id("txtDMCategory"));
                            sponsorPageObj.selectDropDown(catSelect, "Sponsor");
                            Thread.sleep(3000);
                            WebElement subCatSelect = driver.findElement(By.id("txtDMSubCategory"));
                            subCatSelect.click();
                            Thread.sleep(3000);
                            sponsorPageObj.selectDropDown(subCatSelect, "Sponsor Templates");
                            Thread.sleep(4000);
                            WebElement catTemplteSelect = driver.findElement(By.id("txtDMTemplate"));
                            catTemplteSelect.click();
                            sponsorPageObj.selectDropDown(catTemplteSelect, "Tier 2-5 Compliance Team");
                            Thread.sleep(2000);
                            WebElement docTypeSelect = driver.findElement(By.id("txtDMDocumentTypeNew"));
                            docTypeSelect.click();
                            sponsorPageObj.selectDropDown(docTypeSelect, "Tier 2-5 Compliance Team template");
                            Thread.sleep(2000);
                            driver.findElement(By.id("txtDMTitleNew")).sendKeys("test");
                            driver.switchTo().defaultContent();
                            driver.findElement(By.id("btnConfirm")).click();
                            Thread.sleep(8000);
                            driver.switchTo().window(handle);
                        }
                    }
                    new WebDriverWait(driver, 30).until(
                            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
//                            System.out.println("new active element is :: " + driver.switchTo().activeElement().getText());
//                            System.out.println("new Alert  is :: " + driver.switchTo().alert().getText());

//                            driver.switchTo().window(handle);
//                            System.out.println("maximized the window");
//                            driver.manage().window().maximize();
//                            System.out.println("URL is :: " + driver.getCurrentUrl());
//                            System.out.println("Page source is :: " + driver.getPageSource());
//                            Robot robot = new Robot();
//                            robot.keyPress(KeyEvent.VK_CANCEL);
//                            robot.keyRelease(KeyEvent.VK_CANCEL);

//                            robot.keyPress(KeyEvent.VK_ENTER);
//                            robot.keyRelease(KeyEvent.VK_ENTER);
//                            Thread.sleep(3000);
//                            robotObj.keyPress(KeyEvent.VK_TAB);
//                            robotObj.keyRelease(KeyEvent.VK_TAB);
//                            Thread.sleep(3000);
//                            robotObj.keyPress(KeyEvent.VK_TAB);
//                            robotObj.keyRelease(KeyEvent.VK_TAB);
//                            Thread.sleep(3000);
//                            robotObj.keyPress(KeyEvent.VK_TAB);
//                            robotObj.keyRelease(KeyEvent.VK_TAB);
//                            Thread.sleep(3000);
//                            robotObj.keyPress(KeyEvent.VK_ENTER);
//                            robotObj.keyRelease(KeyEvent.VK_ENTER);
//                            Thread.sleep(3000);
//                            Actions act = new Actions(driver);
//                            act.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
//                            Thread.sleep(2000);
//                            act.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
//                            Thread.sleep(2000);
//                            act.keyDown(Keys.CONTROL).sendKeys(Keys.DOWN).build().perform();
//                            Thread.sleep(2000);
//                            act.sendKeys(Keys.TAB).build().perform();
//                            Thread.sleep(2000);
//                            act.sendKeys(Keys.TAB).build().perform();
//                            Thread.sleep(2000);
//                            act.sendKeys(Keys.TAB).build().perform();
//                            Thread.sleep(2000);
//                            act.sendKeys(Keys.ENTER).build().perform();
//                            Thread.sleep(2000);
//                            act.sendKeys(Keys.CANCEL).build().perform();
//                            Thread.sleep(2000);
//                            System.out.println("cancelled the download popup");

//                            Actions act = new Actions(driver);
//                            act.sendKeys(Keys.TAB).build().perform();
//                            Thread.sleep(2000);
//                            act.sendKeys(Keys.TAB).build().perform();
//                            Thread.sleep(2000);
//                            act.sendKeys(Keys.TAB).build().perform();
//                            Thread.sleep(2000);
//                            act.sendKeys(Keys.ENTER).build().perform();
//                            Thread.sleep(2000);
//                            Thread.sleep(3000);
//                            driver.switchTo().window(handle);
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_CANCEL);
                    robot.keyRelease(KeyEvent.VK_CANCEL);
                    System.out.println("maximized the window");
                    driver.manage().window().maximize();
                    System.out.println("URL is :: " + driver.getCurrentUrl());
                    System.out.println("Page source is :: " + driver.getPageSource());
                    retrieveConfirmCancelAreaTableData();
                    for (WebElement col : cols) {
                        System.out.println("Inside round ");
                        System.out.println("table row data is ***::" + col.getText() + "hello");
                        if ((col.getText()).equalsIgnoreCase(" Finish and Print ")) {
                            System.out.println("clicking element :: " + col.getText());
                            col.click();
                            Thread.sleep(3000);
                            break;
                        }
                    }
                    for(String newHandle : driver.getWindowHandles())
                    {
                        if(!(newHandle.equalsIgnoreCase(handle)) && !(newHandle.equalsIgnoreCase(currentWindow)))
                        {
                            System.out.println("switching to new 3rd  window is :: " + newHandle);
                            driver.switchTo().window(newHandle);
                            System.out.println("3rd  window URL is :: " + driver.getCurrentUrl());
                            driver.switchTo().frame(driver.findElement(By.id("eFormContents")));
                            System.out.println("clicking yes button");
                            driver.findElement(By.xpath("//button[@id='YesBtn']")).click();
                            Thread.sleep(8000);
                            driver.switchTo().defaultContent();
                            driver.switchTo().frame(handle);
                        }
                    }
                }
            }




//                **********************

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

    public void getTableData()
    {
        int index = 0;
        WebElement tableele = driver.findElement(By.cssSelector("div#TableContainer")).findElement(By.id("alertsGrid"));
        WebElement baseTable = driver.findElement(By.id("alertsGrid"));
        WebElement baseTableBody =  tableele.findElement(By.tagName("tbody"));
        List<WebElement> tableRows = baseTableBody.findElements(By.tagName("tr"));
        for(WebElement ele : tableRows)
        {
            List<WebElement> cols = ele.findElements(By.tagName("td"));
            for(WebElement col : cols)
            {
                if((col.getText()).equalsIgnoreCase("Select Sponsor Evaluate Case"))
                    col.click();
            }
        }

    }

    public void retrieveConfirmCancelAreaTableData()
    {
        proceedTabEle = driver.findElement(By.id("eConfirmCancelArea")).findElement(By.tagName("table")).findElement(By.tagName("tbody"));
        tabRowData = proceedTabEle.findElement(By.tagName("tr"));
        cols = tabRowData.findElements(By.tagName("td"));
    }

//        @BeforeClass
//        public static void setUp() throws Exception {
//            EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
//
//            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
//            if(accessKey == null) {
//                accessKey = (String) environmentVariables.getProperty("browserstack.key");
//            }
//
//            String environment = System.getProperty("environment");
//            String key = "browserstack.local";
//            boolean is_local = environmentVariables.getProperty(key) != null && environmentVariables.getProperty(key).equals("true");
//
//            if(environment != null && !is_local){
//                key = "environment."+environment+".browserstack.local";
//                is_local = environmentVariables.getProperty(key) != null && environmentVariables.getProperty(key).equals("true");
//            }
//
//            if(is_local){
//                bsLocal = new Local();
//                Map<String, String> bsLocalArgs = new HashMap<String, String>();
//                bsLocalArgs.put("key", accessKey);
//                bsLocal.start(bsLocalArgs);
//            }
//        }
//
//        @AfterClass
//        public static void tearDown() throws Exception {
//            if(bsLocal != null) {
//                bsLocal.stop();
//            }
//        }

    public void openMetastormURL()
    {
        try {
//            this.getDriver().get("https://metastorm-ho-it-msit1-i-spon-pbs.service.np.iptho.co.uk/metastorm");
//                String downloadPath =
            String filePath = System.getProperty("user.dir")
                    + "/src/test/resources/";
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


            caps.setCapability("browser.download.folderList", 0);
            caps.setCapability("browser.download.manager.showWhenStarting", false);
            caps.setCapability("browser.download.manager.showWhenStarting", false);
            caps.setCapability("browser.download.manager.showWhenStarting", false);
            caps.setCapability("browser.download.manager.showWhenStarting", false);
            caps.setCapability("browser.download.manager.showWhenStarting", false);
            caps.setCapability("browser.download.manager.showWhenStarting", false);
            caps.setCapability("browser.download.manager.showWhenStarting", false);


            caps.setCapability("browser.download.prompt_for_download", "false");
            caps.setCapability("browser.download.useDownloadDir", "true");
            caps.setCapability("browser.download.manager.showWhenStarting", "true");
            caps.setCapability("browser.download.folderList", "0");
            caps.setCapability("browser.download.dir", filePath);
            caps.setCapability("browser.helperApps.alwaysAsk.force", "false");
            caps.setCapability("pdfjs.disabled", "true");
            caps.setCapability("browser.download.panel.shown", "false");
            caps.setCapability("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/x-pdf,application/x-zip,application/x-zip-compressed,application/octet-stream,application/zip");
            caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
            caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            caps.setCapability("browser.requireWindowFocus", "true");
            caps.setCapability("browser.enablePersistentHover", "true");


            caps.setCapability("browser.download.manager.focusWhenStarting","true");
            caps.setCapability("browser.download.manager.alertOnExeOpen","false");
            caps.setCapability("browser.download.manager.useWindow","false");
            caps.setCapability("server.sync.prefs.sync.browser.download.manager.showWhenStarting", "true");


            driver = new RemoteWebDriver(new URL("https://iptcompatibility:fjPgJqbqtN1yhVb53FZq@hub-cloud.browserstack.com/wd/hub"), caps);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.get("https://metastorm-ho-it-msit1-i-spon-pbs.service.np.iptho.co.uk/metastorm");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void openMetaStorm()
    {
        openMetastormURL();
        switchToIframe();
    }




}

