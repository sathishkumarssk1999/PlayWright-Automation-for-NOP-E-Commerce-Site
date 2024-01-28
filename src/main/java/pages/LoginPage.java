package pages;

import com.microsoft.playwright.Page;
import org.testng.Assert;

import java.nio.file.Paths;

public class LoginPage {

    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void signIn() {
        page.locator(".ico-login").click();
    }

    public void username(String emailId) {
        page.locator("id=Email").fill(emailId);
    }

    public void password(String password) {
        page.locator("id=Password").fill(password);
    }

    public void loginButton(String sno) {
        page.locator("//button[@class='button-1 login-button' and text()='Log in']").click();
        page.waitForTimeout(3000);
        try {
            Assert.assertEquals(page.url(), "https://demo.nopcommerce.com/", "User enter the Invalid Username and Password");
        } catch (AssertionError e) {
            // Take a screenshot of Login page once user enter the invalid username or password
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-output/screenshots/Login/loginFailed_" + sno + ".png")));
            Assert.fail("Invalid username or password");
        }
    }

}
