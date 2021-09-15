package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_ELEMENT,
            CLOSE_INFORM_POPUP,
            ARTICLE_BY_TITLE_AND_DESCRIPTION_TPL,
            REMOVE_FROM_SAVED_BUTTON_TPL;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getSavedArticleByTitleAndDescription(String title, String description) {
        return ARTICLE_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public void waitForFolderToAppearByName(String name_of_folder) {
        String folder_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresent(folder_xpath, "Cannot find folder by name " + name_of_folder, 15);
    }

    public void openFolderByName(String name_of_folder) {
        this.waitForFolderToAppearByName(name_of_folder);
        String folder_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_xpath, "Cannot find and click folder by name " + name_of_folder, 5);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(article_xpath, "Cannot find saved article");

            if (Platform.getInstance().isIOS()) {
                this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
            }
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Cannot click button to remove article from saved", 10);

            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public int getAmountOfArticlesInList() {
        this.waitForElementPresent(ARTICLE_ELEMENT, "Cannot find any articles in list", 15);
        return getAmountOfElements(ARTICLE_ELEMENT);
    }

    public void openArticleFromList() {
        this.waitForElementAndClick(ARTICLE_ELEMENT, "Cannot find and click article", 5);
    }

    public void closeInformationPopup() {
        this.waitForElementAndClick(CLOSE_INFORM_POPUP, "Cannot find and click close button", 5);
    }

    public void assertArticlePresentInList(String article_title, String article_description) {
        String article_xpath = getSavedArticleByTitleAndDescription(article_title, article_description);
        this.assertElementPresent(article_xpath, "Cannot find article with title " + article_title + " and description " + article_description);
    }
}
