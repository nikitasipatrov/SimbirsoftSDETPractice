package Pages;

import Utils.ConfProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@class = 'Textinput-Control']")
    private WebElement loginField;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement enterMailBtn;

    @FindBy(xpath = "//input[@data-t = 'field:input-passwd']")
    private WebElement passwordField;

    @FindBy(xpath ="//a[contains(@class,'HeadBanner-Button-Enter')]")
    private WebElement mailBtn;

    @Step
    public LoginPage clickMailBtn() {
        mailBtn.click();
        return this;
    }

    @Step
    public LoginPage clickEnterMailBtn() {
        enterMailBtn.click();
        return this;
    }

    @Step
    public LoginPage enterMailLogin(String inputLogin) {
        loginField.sendKeys(inputLogin);
        return this;
    }

    @Step
    public LoginPage enterPassword(String inputPassword){
        passwordField.sendKeys(inputPassword);
        return this;
    }
}
