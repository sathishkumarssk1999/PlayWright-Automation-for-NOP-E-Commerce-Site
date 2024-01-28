package pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://demo.nopcommerce.com/");
    }

    public void registerButton() {
        page.locator(".ico-register").click();
    }

}
