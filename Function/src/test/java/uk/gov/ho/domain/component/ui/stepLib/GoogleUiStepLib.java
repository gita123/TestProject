package uk.gov.ho.domain.component.ui.stepLib;

import net.serenitybdd.core.Serenity;
import org.junit.Assert;
//import uk.gov.ho.domain.component.ui.pages.GovHomePage;
//import uk.gov.ho.domain.component.ui.pages.GovUkSearchResultPage;
import uk.gov.ho.domain.component.ui.pages.Page_GoogleHomepage;


public class GoogleUiStepLib {
    private Page_GoogleHomepage googleHomepage;
//    private GovUkSearchResultPage searchResultPage;

    public void navigateToGoogleHomePage() {
        googleHomepage.open();
    }

    public void checkSearchBoxIsDisplayed() { googleHomepage.checkSearchBoxIsDisplayed(); }

    public void checkSearchButtonIsDisplayed() { googleHomepage.checkGoogleSearchButtonIsDisplayed(); }



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
}

