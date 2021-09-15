package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer div.license";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:a#ca-watch[title='Watch']";
        RETURN_TO_MAIN_PAGE_BUTTON = "css:branding-box";
        OPTIONS_REMOVE_MY_LIST_BUTTON = "css:a#ca-watch[title='Unwatch']";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
