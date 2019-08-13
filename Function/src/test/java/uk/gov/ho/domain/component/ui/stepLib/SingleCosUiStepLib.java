package uk.gov.ho.domain.component.ui.stepLib;

import net.serenitybdd.core.Serenity;
import org.junit.Assert;
import uk.gov.ho.cts.utils.Configuration;
import uk.gov.ho.domain.component.ui.pages.Page_SingleCoS;


public class SingleCosUiStepLib {
    private Page_SingleCoS cosPage;
    private String PBS_SMS_URL="pbs.SMS.url";



    public void navigateToHomePage() {
        cosPage.openUrl(Configuration.get(PBS_SMS_URL));
    }


    public void verifyPageDisplaysLogInLink() {
        cosPage.verifyPageDisplaysLogInLink();
    }


    public void selectLogInLink() {
        cosPage.selectLogInLink();
    }


    public void verifySMSLogInPageDisplaysUserIdField() {
        cosPage.verifySMSLogInPageDisplaysUserIdField();
    }


    public void pageDisplaysPasswordField() {
        cosPage.pageDisplaysPasswordField();
    }


    public void pageDisplaysCancelButton() {
        cosPage.pageDisplaysCancelButton();
    }


    public void pageDisplaysLogInButton() {
        cosPage.pageDisplaysLogInButton();
    }


    public void enterLoginCredentials() {
        cosPage.enterLoginCredentials();
    }


    public void verifySMSHomepageDisplaysOkButton() {
        cosPage.verifySMSHomepageDisplaysOkButton();
    }


    public void clickOKbutton() {
        cosPage.clickOKbutton();
    }


    public void logout() {
        cosPage.logout();
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////
    
    
    public void selectWorkersLink() { cosPage.selectWorkersLink(); }
    public void selectCreateAndAssignLink() { cosPage.selectCreateAndAssignLink(); }
    public void selectEuropeanUnionAccession() { cosPage.selectEuropeanUnionAccession(); }
    public void selectTier() { cosPage.selectTier(); }
    public void selectCategory() { cosPage.selectCategory(); }
    public void selectSubCategory() { cosPage.selectSubCategory(); }
    public void selectCreateAndAssign() { cosPage.selectCreateAndAssign(); }
    public void enterPersonalInformation() { cosPage.enterPersonalInformation(); }
    public void enterPassportTraveldoc() { cosPage.enterPassportTraveldoc(); }
    public void enterCurrentAddress() { cosPage.enterCurrentAddress(); }
    public void enterWorkDates() { cosPage.enterWorkDates(); }
    public void selectMainWorkAddressUK() { cosPage.selectMainWorkAddressUK(); }
    public void enterMigrantDetailsSaveForm() { cosPage.enterMigrantDetailsSaveForm(); }
    public void checkAssignButtonAvailable() { cosPage.checkAssignButtonAvailable(); }
    public void clickAssignButton() { cosPage.clickAssignButton(); }
    public void clickAssignCoSButton() { cosPage.clickAssignCoSButton(); }
    public void clickOnlinePaymentOkButton() { cosPage.clickOnlinePaymentOkButton(); }
    public void checkWorldpayURL() { cosPage.checkWorldpayURL(); }


}