package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "css:li[title='{TITLE}']";
        REMOVE_FROM_SAVED_BUTTON_TPL = "xpath://li[@title='{TITLE}']/a[contains(@class, 'watched')]";
        ARTICLE_ELEMENT = "css:li.with-watchstar";
        ADD_TO_SAVED_BUTTON= "css:li.with-watchstar a[title='Add this page to your watchlist']";
    }

    public MWMyListsPageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
