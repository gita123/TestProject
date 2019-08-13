package uk.gov.ho.domain.component.ui.stepLib;

import net.serenitybdd.core.Serenity;
import org.junit.Assert;
import uk.gov.ho.domain.component.ui.pages.GovHomePage;
import uk.gov.ho.domain.component.ui.pages.GovUkSearchResultPage;

public class UiStepLib {
    private GovHomePage govHomePage;
    private GovUkSearchResultPage searchResultPage;

    public void navigateToGovUkHomePage() {
        govHomePage.open();
    }

    public void searchForTerm(String term) {
        Serenity.setSessionVariable("searchTerm").to(term);
        govHomePage.Search(term);
    }

    public void verifySearchTermInResult(String expectedResultTerm) {
        Assert.assertTrue(searchResultPage.hasAtLeastOneMatchingSearchResult(expectedResultTerm));
    }

    public void verifyUserIsOnSearchResultPage() {
        searchResultPage.verifyIsOnSearchResultPage();
    }
}
