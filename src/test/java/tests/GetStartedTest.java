package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
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
