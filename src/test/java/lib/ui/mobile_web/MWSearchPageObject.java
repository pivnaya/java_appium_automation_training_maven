package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INIT_ELEMENT_TEXT = "css:form>input.search";
        SEARCH_INPUT = "css:form>input.search";
        SEARCH_CANCEL_BUTTON = "css:div.header-action button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_EQUALS_TPL = "xpath://li[./a[@data-title='{SUBSTRING}'] or ./a/div[text()='{SUBSTRING}']]";
        SEARCH_RESULT_BY_SUBSTRING_CONTAINS_TPL = "xpath://li[./a[contains(@data-title,'{SUBSTRING}')] or ./a/div[contains(text(),'{SUBSTRING}')]]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[./a[contains(@data-title,'{TITLE}')]" +
                "[./div[contains(@class, 'wikidata-description')][text()='{DESCRIPTION}']]]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        EMPTY_RESULT_LABEL = "css:p.without-results";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
