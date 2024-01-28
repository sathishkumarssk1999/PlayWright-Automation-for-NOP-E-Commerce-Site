package NopCommerce;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;
import utils.excelUtils;

import java.nio.file.Paths;

public class SearchTest extends Base {

    @Test(dataProvider = "testData", dataProviderClass = excelUtils.class)
    public void searchTest(String sno, String username, String password, String searchText) {

        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
             BrowserContext browserContext = browser.newContext();
             Page page = browserContext.newPage()) {

            // Creating object for the home page
            HomePage homePage = new HomePage(page);
            homePage.navigate();

            // Creating object for the Login page
            LoginPage loginpage = new LoginPage(page);

            loginpage.signIn();
            loginpage.username(username);
            loginpage.password(password);
            loginpage.loginButton(sno);

            // Creating object for the SearchPage page
            SearchPage searchPage = new SearchPage(page);

            // Call the searchProduct-function with arguments
            searchPage.searchProduct(searchText);

            // Take scrollBy one page
            page.evaluate("window.scrollBy(0, 500);");

            // Take a screenshot once user enters into search result page
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/SearchResult/" + searchText + "_" + sno + ".png")));

            // Call the searchResult-function with arguments
            searchPage.searchResult(sno);

            // Add a product to cart
            if (searchText.equals("Cell phones")) {
                searchPage.addToCart();
                searchPage.sameTab(browserContext, page.url());
                searchPage.differentBrowser(page.url());
            }

        }

    }

}
