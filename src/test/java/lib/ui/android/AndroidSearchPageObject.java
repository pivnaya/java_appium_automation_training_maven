package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
                SEARCH_INIT_ELEMENT_TEXT = "xpath://*[@resource-id='org.wikipedia:id/search_container']//*[@class='android.widget.TextView']";
                SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_EQUALS_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text= '{SUBSTRING}']";
                SEARCH_RESULT_BY_SUBSTRING_CONTAINS_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING}')]";
                SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container' " +
                        "and .//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text = '{TITLE}'] " +
                        "and .//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text = '{DESCRIPTION}']]";
                SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
                EMPTY_RESULT_LABEL = "xpath://*[@text='No results found']";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
