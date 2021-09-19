package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for iOS only")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="WelcomePage")})
    @DisplayName("Pass trough welcome page")
    @Description("We open welcome page and pass all it steps")
    @Step("Starting test testPassTroughWelcome")
    @Severity(value = SeverityLevel.NORMAL)
    public void testPassTroughWelcome() {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMw()){
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForLearnMoreAboutWikipediaLink();
        WelcomePageObject.clickNextLink();

        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextLink();

        WelcomePageObject.waitForAddOrEditPreferredLangLink();
        WelcomePageObject.clickNextLink();

        WelcomePageObject.waitForLearnMoreAboutDataCollectedLink();
        WelcomePageObject.clickGetStartedButton();
    }
}
