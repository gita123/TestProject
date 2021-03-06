package uk.gov.ho.domain.component.ui.pages;

import net.serenitybdd.core.Serenity;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GovUkSearchResultPage extends GovHomePage {

    private Logger logger = LoggerFactory.getLogger(GovUkSearchResultPage.class);

    //WebDriver driver;
    @FindBy(xpath = "//ol[@class=\"results-list\"]/li")
    List<WebElement> elementresults;

    public boolean hasAllMatchingResults(String expectedResultTerm) {
        List<WebElement> elementWithNoMatchingTerm;
        elementWithNoMatchingTerm = elementresults.stream().filter(e -> !(e.getText().toLowerCase().contains(expectedResultTerm.toLowerCase()))).collect(Collectors.toList());
        return !(elementWithNoMatchingTerm.size() > 0);
    }

    public boolean hasAtLeastOneMatchingSearchResult(String expectedResultTerm) {
        List<WebElement> elementWithNoMatchingTerm;
        elementWithNoMatchingTerm = elementresults.stream().filter(e -> (e.getText().toLowerCase().contains(expectedResultTerm.toLowerCase()))).collect(Collectors.toList());
        return (elementWithNoMatchingTerm.size() > 0);
    }

    public void verifyIsOnSearchResultPage() {
        logger.info("verify user is on search result page");
        String originalSearchTerm = Serenity.sessionVariableCalled("searchTerm").toString();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("search?q=" + originalSearchTerm));
    }
}
