package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Check search result has description of article")
    @Description("We search 'Java' and make sure that search result has 'Java Object-oriented programming language' substring")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultBySubstring("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Cancel search")
    @Description("We init search and cancel it")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Cancel search result")
    @Description("We search 'Java' article and cancel search result")
    @Step("Starting test testCancelSearchResult")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearchResult() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue("Count of articles less than 2", amount_of_search_results > 1);

        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check search input has placeholder")
    @Description("We init search and make sure the input has 'Search Wikipedia' placeholder")
    @Step("Starting test testCompareSearchInputText")
    @Severity(value = SeverityLevel.MINOR)
    public void testCompareSearchInputText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isMw()) {
            SearchPageObject.initSearchInput();
        }

        SearchPageObject.assertSearchInputTextEquals("Search Wikipedia");
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Check search result has search line")
    @Description("We search 'Java' and make sure that all articles has 'Java' text")
    @Step("Starting test testCompareSearchResult")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCompareSearchResult() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String search_line = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        int amount_of_search_results_with_value = SearchPageObject.getAmountOfFoundArticlesWithSubstringContains(search_line);

        Assert.assertEquals("Not all items contains search value", amount_of_search_results, amount_of_search_results_with_value);
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Compare articles in search result with expected")
    @Description("We search 'Java' and compare three article titles and descriptions in search result")
    @Step("Starting test testCompareArticlesInSearchResult")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCompareArticlesInSearchResult() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.waitForSearchResultByTitleAndDescription("Java", "Island of Indonesia");
            SearchPageObject.waitForSearchResultByTitleAndDescription("JavaScript", "Programming language");
            SearchPageObject.waitForSearchResultByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        } else {
            SearchPageObject.waitForSearchResultByTitleAndDescription("Java", "Indonesian island");
            SearchPageObject.waitForSearchResultByTitleAndDescription("JavaScript", "High-level programming language");
            SearchPageObject.waitForSearchResultByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        }
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check not empty search result")
    @Description("We search 'Linkin Park Discography' and make sure that search result not empty")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue("We found too few results!",amount_of_search_results > 0);
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check empty search result")
    @Description("We search non-existent request and make sure that search result is empty")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "hffjfjyfjf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
