package NopCommerce;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import utils.excelUtils;

import java.nio.file.Paths;

public class NewUserTest extends Base {


    @Test(dataProvider = "testData", dataProviderClass = excelUtils.class)
    public void registerTest(String sno, String Gender, String Firstname, String Lastname, String Email, String CompanyName, String Password, String ConfirmPassword) {

        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
             Page page = browser.newPage()) {

            // Creating object for the home page
            HomePage homePage = new HomePage(page);
            homePage.navigate();
            homePage.registerButton();

            // Creating object for the Register page
            RegisterPage registerpage = new RegisterPage(page);

            // Take a screenshot once user enters into the register user page
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/Register/NewUser Register Page.png")));

            // Call the personalDetails-function with arguments
            registerpage.enterPersonalDetails(Gender, Firstname, Lastname, Email, CompanyName);

            // Take a screenshot once user enters personal details
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/Register/personalDetails_" + sno + ".png")));

            // Call the enterCredentials function with arguments
            registerpage.enterCredentials(Password, ConfirmPassword);

            // Take a screenshot once registration process is completed
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/Register/registrationCompletion_" + sno + ".png")));

            // Call the continue button
            registerpage.continueRegistration();

        }

    }

}
