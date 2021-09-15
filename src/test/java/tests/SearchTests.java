package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultBySubstring("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCancelSearchResult() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("Count of articles less than 2", amount_of_search_results > 1);

        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultDisappear();
    }

    @Test
    public void testCompareSearchInputText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        if (Platform.getInstance().isMw()) {
            SearchPageObject.initSearchInput();
        }

        SearchPageObject.assertSearchInputTextEquals("Search Wikipedia");
    }

    @Test
    public void testCompareSearchResult() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String search_line = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        int amount_of_search_results_with_value = SearchPageObject.getAmountOfFoundArticlesWithSubstringContains(search_line);

        assertEquals("Not all items contains search value", amount_of_search_results, amount_of_search_results_with_value);
    }

    @Test
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
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("We found too few results!",amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "hffjfjyfjf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
