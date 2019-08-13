package uk.gov.ho.domain.component.ui.stepLib;

//import uk.gov.ho.domain.component.ui.pages.GovHomePage;
//import uk.gov.ho.domain.component.ui.pages.GovUkSearchResultPage;
import uk.gov.ho.domain.component.ui.pages.Page_RegisterSponsor;
import uk.gov.ho.cts.utils.Configuration;

import java.util.concurrent.TimeUnit;


public class RegisterUiStepLib {
    private String PBS_SPONSOR_URL="pbs.SponsorHome.url";
    private Page_RegisterSponsor registerPage;

    public void navigateToHomePage() {
        System.out.println("///////////////////hello//////////////////////");
        registerPage.getDriver().manage().window().maximize();
        registerPage.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registerPage.openUrl(Configuration.get(PBS_SPONSOR_URL));
    }

    public void selectLogRegistrationLink() { registerPage.selectLogRegistrationLink(); }

    public void enterRegistrationCredentials() { registerPage.enterRegistrationCredentials(); }

    public void confirmRegistration() { registerPage.confirmRegistration(); }

    /*    public void searchForTerm(String term) {
        Serenity.setSessionVariable("searchTerm").to(term);
        govHomePage.Search(term);
    }

    public void verifySearchTermInResult(String expectedResultTerm) {
        Assert.assertTrue(searchResultPage.hasAtLeastOneMatchingSearchResult(expectedResultTerm));
    }

    public void verifyUserIsOnSearchResultPage() {
        searchResultPage.verifyIsOnSearchResultPage();
    } */


//  *********************************************************
    public void getUserID()
    {
        registerPage.retrieveUserID();
    }

    public void readEmailData()
    {
        registerPage.retrieveEmailData();
    }

    public void loginSponsor()
    {
        registerPage.logInSponser();
    }

}

