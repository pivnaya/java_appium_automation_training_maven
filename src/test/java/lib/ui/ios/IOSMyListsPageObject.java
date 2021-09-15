package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        CLOSE_INFORM_POPUP = "xpath://XCUIElementTypeButton[@name='Close']";
        ARTICLE_ELEMENT = "xpath://XCUIElementTypeCell[./XCUIElementTypeOther and ./XCUIElementTypeOther[./XCUIElementTypeStaticText and ./XCUIElementTypeStaticText and ./XCUIElementTypeOther]]";
        ARTICLE_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeOther[./XCUIElementTypeStaticText[@name='{TITLE}'] " +
                "and ./XCUIElementTypeStaticText[@name='{DESCRIPTION}']]";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
