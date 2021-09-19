package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertEquals;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INIT_ELEMENT_TEXT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_EQUALS_TPL,
            SEARCH_RESULT_BY_SUBSTRING_CONTAINS_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_ELEMENT,
            EMPTY_RESULT_LABEL;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElementWithSubstringEquals(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_EQUALS_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementWithSubstringContains(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_CONTAINS_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    @Step("Initializing the search field")
    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find input after clicking search init element", 5);
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    @Step("Waiting for search cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    @Step("Typing '{search_line}' to the search line")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    @Step("Waiting for search result")
    public void waitForSearchResultBySubstring(String substring) {
        String search_result_xpath = getResultSearchElementWithSubstringContains(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 15);
    }

    public void waitForSearchResultByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with title " + title + " and description " + description, 15);
    }

    public void waitForSearchResultDisappear() {
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT, "Search result is still present", 15);
    }

    @Step("Waiting for search result and select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElementWithSubstringEquals(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 15);
    }

    @Step("Getting amount of articles")
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);
        return getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public int getAmountOfFoundArticlesWithSubstringContains(String substring) {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);
        String search_result_xpath = getResultSearchElementWithSubstringContains(substring);
        return getAmountOfElements(search_result_xpath);
    }

    @Step("Waiting for empty search result label")
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(EMPTY_RESULT_LABEL, "Cannot find empty result label by the request",15);
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void assertSearchInputTextEquals(String text) {
        if (Platform.getInstance().isMw()) {
            WebElement element = waitForElementPresent(SEARCH_INIT_ELEMENT_TEXT, "Cannot find search input element", 15);
            String placeholder = element.getAttribute("placeholder");
            assertEquals("We see unexpected text in input", text, placeholder);
        } else{
            this.assertElementHasText(SEARCH_INIT_ELEMENT_TEXT, text, "We see unexpected text in input");
        }
    }
}
