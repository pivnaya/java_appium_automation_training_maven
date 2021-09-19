package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for lists")
public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "pivnaya",
            password = "pivnayapivnaya";

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article"),@Feature(value="Lists")})
    @DisplayName("Save and delete article from list")
    @Description("We open article, save it to the list, open this list and delete article from it")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToNewList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject AuthorizationPageObject = new AuthorizationPageObject(driver);
            AuthorizationPageObject.clickAuthButton();
            AuthorizationPageObject.enterLoginData(login, password);
            AuthorizationPageObject.clickSubmitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            MyListsPageObject.closeInformationPopup();
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article"),@Feature(value="Lists")})
    @DisplayName("Delete one of two articles from the list")
    @Description("We save two articles to the list, open this list and delete one of articles from it")
    @Step("Starting test testSaveTwoArticlesToMyList")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticlesToMyList() {
        String search_line = "Java";
        String article_title_for_save = "JavaScript";
        String article_description_for_save = "High-level programming language";
        String article_title_for_delete = "Java (programming language)";

        //Adding first article to list

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_title_for_save);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToNewList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject AuthorizationPageObject = new AuthorizationPageObject(driver);
            AuthorizationPageObject.clickAuthButton();
            AuthorizationPageObject.enterLoginData(login, password);
            AuthorizationPageObject.clickSubmitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login", article_title_for_save, ArticlePageObject.getArticleTitle());
        }

        ArticlePageObject.closeArticle();

        //Adding second article to list

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_title_for_delete);

        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        //Deleting article from list

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            MyListsPageObject.closeInformationPopup();
        }

        MyListsPageObject.swipeByArticleToDelete(article_title_for_delete);

        int amount_of_articles = MyListsPageObject.getAmountOfArticlesInList();
        Assert.assertEquals("We see unexpected count of article after deleting", 1, amount_of_articles);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMw()) {
            MyListsPageObject.openArticleFromList();
            String article_title = ArticlePageObject.getArticleTitle();
            Assert.assertEquals("We see unexpected title", article_title_for_save, article_title);
        } else {
            MyListsPageObject.assertArticlePresentInList(article_title_for_save, article_description_for_save);
        }
    }
}
