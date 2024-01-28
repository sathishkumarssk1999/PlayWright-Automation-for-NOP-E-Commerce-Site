package pages;

import com.microsoft.playwright.*;
import utils.excelUtils;
import java.nio.file.Paths;

public class SearchPage {

    private final Page page;

    public SearchPage(Page page) {
        this.page = page;
    }

    public void searchProduct(String searchText) {
        page.locator("id=small-searchterms").fill(searchText);
        page.locator("//*[@class='button-1 search-box-button' and text()='Search']").click();
    }

    public void searchResult(String sno) {

        int resultCount = page.locator("//div[@class='item-box']").count();
        for (int i = 1; i <= resultCount; i++) {
            String productTitle = page.locator("(//h2[@class='product-title'])[" + i + "]").textContent();
            excelUtils.excelWriter(sno, "title " + i, productTitle);
            String productDescription = page.locator("(//div[@class='description'])[" + i + "]").textContent();
            excelUtils.excelWriter(sno, "description " + i, productDescription);
            String productPrize = page.locator("(//span[@class='price actual-price'])[" + i + "]").textContent();
            excelUtils.excelWriter(sno, "prize " + i, productPrize);
        }
    }

    public void addToCart() {

        int resultCount = page.locator("//div[@class='item-box']").count();
        for (int j = 1; j <= resultCount; j++) {
            page.locator("(//button[@class='button-2 product-box-add-to-cart-button'])[" + j + "]").click();
        }

        // click the close button in top right corner
        page.locator("//span[@class='close']").click();

        // click the shopping Cart button
        page.locator("//span[@class='cart-label']").click();

        // Take a screenshot once user enters into shopping cart page
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/ShoppingCart/beforeShoppingCart.png")));

        // User update the shopping Cart Page
        page.locator("(//input[@class='qty-input'])[1]").fill("2");

        // user click the update shopping Cart Button
        page.locator("//button[@id='updatecart']").click();

        // Take a screenshot once user After updated the products shopping Cart Page
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/ShoppingCart/afterUpdatedShoppingCart.png")));

        // Take scrollBy one page
        page.evaluate("window.scrollBy(0, 700);");

        // Take a screenshot once user enters into check out page
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/SearchResult/checkOutPage.png")));

        // To check the user agreement
        page.locator("//input[@id='termsofservice']").click();

        // User click the checkOut button in shopping Cart Page
        page.locator("//button[@id='checkout']").click();

        // Take a screenshot once user enters into billingInformation page
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/DifferentBrowser/chromeSession.png")));

    }

    public void sameTab(BrowserContext context, String url) {

        Page newPage = context.newPage();
        newPage.navigate(url);

        newPage.waitForTimeout(3000);
        newPage.evaluate("window.scrollBy(0, -500);");

        // Take a screenshot once user enters into new tab in sameBrowser
        newPage.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/sameBrowser/newTabSession.png")));

    }


    public void differentBrowser(String url) {

        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
             Page page1 = browser.newPage()) {

            // Create a new page in a pristine context.
            page1.navigate(url);

            // Take a screenshot once user enters into search result page
            page1.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/DifferentBrowser/firefoxSession.png")));

        }

    }

}
