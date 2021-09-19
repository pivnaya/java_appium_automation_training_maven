package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{

    private static final String
            STEP_LEARN_MORE_ABOUT_WIKIPEDIA_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
            NEXT_LINK = "id:Next",
            GET_STARTED_BUTTON = "id:Get started",
            SKIP_LINK = "xpath://XCUIElementTypeStaticText[@name='Skip']";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for 'Learn more about Wikipedia' link")
    public void waitForLearnMoreAboutWikipediaLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_WIKIPEDIA_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    @Step("Waiting for 'For new ways to explore' text")
    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' text", 10);
    }

    @Step("Waiting for 'Add or edit preferred lang' link")
    public void waitForAddOrEditPreferredLangLink() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Add or edit preferred languages' link", 10);
    }

    @Step("Waiting for 'Learn more about data collected' link")
    public void waitForLearnMoreAboutDataCollectedLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' link", 10);
    }

    @Step("Clicking 'Next' link")
    public void clickNextLink() {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link", 10);
    }

    @Step("Clicking 'Get Started' button")
    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' button", 10);
    }

    @Step("Clicking 'Skip' link")
    public void clickSkip() {
        this.waitForElementAndClick(SKIP_LINK, "Cannot find and click skip link", 5);
    }
}
