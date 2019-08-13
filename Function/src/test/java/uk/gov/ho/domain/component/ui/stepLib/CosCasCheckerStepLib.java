package uk.gov.ho.domain.component.ui.stepLib;

import uk.gov.ho.domain.component.ui.pages.PageCosCasChecker;
import uk.gov.ho.cts.utils.Configuration;


public class CosCasCheckerStepLib {
    private String PBS_COSCAS_URL="pbs.COSCAS.url";
    private PageCosCasChecker cosCasPage;

    public void navigateToHomePage() {
        cosCasPage.openUrl(Configuration.get(PBS_COSCAS_URL));
    }

    public void enterTheUserIDAndPassword() { cosCasPage.enterTheUserIDAndPassword(); }

    public void clickOnTheLogInButton() { cosCasPage.clickOnTheLogInButton(); }

    public void searchForACosNumber() { cosCasPage.searchForACosNumber(); }

    public void checkCorrectCosResultReturned() { cosCasPage.checkCorrectCosResultReturned(); }

    public void clickOnTheBackButton() { cosCasPage.clickOnTheBackButton(); }

    public void clickOnTheCasLink() { cosCasPage.clickOnTheCasLink(); }

    public void retrieveACasNumber() { cosCasPage.retrieveACasNumber(); }

    public void checkCorrectCasResultReturned() { cosCasPage.checkCorrectCasResultReturned(); }

    public void clickOnLogoutLink() { cosCasPage.clickOnLogoutLink(); }


}


