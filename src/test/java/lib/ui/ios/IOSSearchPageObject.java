package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INIT_ELEMENT_TEXT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_EQUALS_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
        SEARCH_RESULT_BY_SUBSTRING_CONTAINS_TPL = "xpath://XCUIElementTypeOther[.XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeOther[./XCUIElementTypeStaticText[@name='{TITLE}'] " +
                "and ./XCUIElementTypeStaticText[@name='{DESCRIPTION}']]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell[./XCUIElementTypeOther and " +
                "./XCUIElementTypeOther[./XCUIElementTypeStaticText and ./XCUIElementTypeStaticText and ./XCUIElementTypeOther] " +
                "and not(.//*[contains(@type, 'XCUIElementTypeButton')])]";
        EMPTY_RESULT_LABEL = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
