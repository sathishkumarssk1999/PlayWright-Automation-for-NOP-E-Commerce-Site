package NopCommerce;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.excelUtils;

import java.nio.file.Paths;
import java.util.List;

public class LoginTest extends Base {

    @Test(dataProvider = "testData", dataProviderClass = excelUtils.class)
    public void loginTest(String sno, String username, String password) {

        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(List.of("--start-maximize")).setSlowMo(2000));
             Page page = browser.newPage()) {

            // Creating object for the home page
            HomePage homePage = new HomePage(page);
            homePage.navigate();

            //Take a screenshot of landingPage - nopcommerce site
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/Login/homePage.png")));

            // Creating object for the Login page
            LoginPage loginpage = new LoginPage(page);

            loginpage.signIn();
            loginpage.username(username);
            loginpage.password(password);
            loginpage.loginButton(sno);

            // Take a screenshot of Login page once user enter the valid username and password
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/Login/LoginSuccess_" + sno + ".png")));

        }

    }

}
