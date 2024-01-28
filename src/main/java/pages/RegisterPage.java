package pages;

import com.microsoft.playwright.Page;

public class RegisterPage {

    private final Page page;

    public RegisterPage(Page page) {
        this.page = page;
    }

    public void enterPersonalDetails(String gender, String firstName, String lastName, String email, String company) {
        page.locator("input[type='radio'][id='gender-" + gender + "']").click();
        page.locator("id=FirstName").fill(firstName);
        page.locator("id=LastName").fill(lastName);
        page.locator("id=Email").fill(email);
        page.locator("id=Company").fill(company);
    }

    public void enterCredentials(String password, String confirmPassword) {
        page.locator("id=Password").fill(password);
        page.locator("id=ConfirmPassword").fill(confirmPassword);
        page.locator("button[id=register-button]").click();
    }

    public void continueRegistration() {
        page.locator("//a[@class='button-1 register-continue-button' and text()='Continue']").click();
    }

}
