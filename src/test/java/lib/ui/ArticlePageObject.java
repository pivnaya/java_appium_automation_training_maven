package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends  MainPageObject{
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            FOLDER_BY_NAME_TPL,
            CLOSE_ARTICLE_BUTTON,
            RETURN_TO_MAIN_PAGE_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    /* TEMPLATES METHODS */

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title", 15);
    }

    @Step("Getting article title")
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getText();
        }
    }

    @Step("Swiping to footer on article page")
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find the end of the article",40);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of the article", 40);
        } else {
            this.scrollWebPageUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of the article",40);
        }
    }

    @Step("Adding the article to new list")
    public void addArticleToNewList(String name_of_folder) {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot find button to open article options", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Cannot find 'Got it' tip overlay", 5);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT, "Cannot find input to set name of articles folder", 5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder, "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot press OK button", 5);
    }

    @Step("Adding the article to '{name_of_folder}' list")
    public void addArticleToList(String name_of_folder) {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot find button to open article options", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
        String folder_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_xpath, "Cannot find and click folder by name " + name_of_folder, 5);
    }

    @Step("Adding the article to saved articles")
    public void addArticleToMySaved() {
        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find and click option to add article to reading list", 5);
    }

    @Step("Removing the article from saved if it has been added")
    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(OPTIONS_REMOVE_MY_LIST_BUTTON, "Cannot click button to remove an article from saved", 5);
        }
        this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot to find button to saved list after removing it from this list before", 5);
    }

    @Step("Closing the article")
    public void closeArticle() {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Cannot close article, cannot find X link",5);
        } else if (Platform.getInstance().isIOS()){
            this.waitForElementAndClick(RETURN_TO_MAIN_PAGE_BUTTON, "Cannot close article, cannot find W link",5);
        } else {
            System.out.println("Method closeArticle() do nothing for platform" +  Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Making sure article has title")
    public void assertArticleHasTitle() {
        this.assertElementPresent(TITLE, "Cannot find article title");
    }
}
