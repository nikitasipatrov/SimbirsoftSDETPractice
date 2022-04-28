package Pages;

import Utils.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailPage {
    private WebDriver driver;

    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Определение строки поиска писем
    @FindBy(xpath = "//input[@class='textinput__control']")
    private WebElement searchMailsField;

    //Определение количества писем
    @FindBy(xpath = "//span[@class = 'mail-MessagesSearchInfo-Title']//span")
    private WebElement mailsNumber;

    //Определение кнопки "Написать"
    @FindBy(xpath = "//span[contains (text(),'Написать')]//ancestor::a")
    private WebElement writeMailBtn;

    //Определение поля для ввода адреса получателя письма
    @FindBy(xpath = "//div[@data-class-bubble ='yabble-compose js-yabble']")
    private WebElement addressMailField;

    //Определение поля для ввода темы письма
    @FindBy(xpath = "//input[@name= 'subject']")
    private WebElement themeMailField;

    //Определение поля для ввода текста письма
    @FindBy (xpath = "//div[@role = 'textbox']")
    private WebElement mailTextField;

    //Определение кнопки "Отправить" письмо
    @FindBy(xpath = "*//span[contains (text(),'Отправить')]//ancestor::button")
    private WebElement sentMailBtn;

    //Определение ссылки "Вернуться во входящие" после отправки письма
    @FindBy (xpath = "//a[@href = '#inbox']")
    private WebElement backInboxBtn;

    //Метод фильтрации gисем по теме "Simbirsoft theme" в папке входяшие
    public MailPage findMailsByTheme() {
        searchMailsField.sendKeys(ConfProperties.getProperty("mailTheme") + " папка:Входящие");
        searchMailsField.submit();
        return this;
    }

    //Определение числа писем
    public Integer getNumberOfMails() {
        return Integer.parseInt(mailsNumber.getText().replaceAll("\\D+",""));
    }

    //Метод нажатия кнопки "Написать"
    public MailPage clickSendBtn() {
        writeMailBtn.click();
        return this;
    }

    //Метод ввода адреса получателя письма
    public MailPage inputAddressMail(String inputAddress) {
        addressMailField.sendKeys(inputAddress);
        return this;
    }

    //Метод ввода темы письма
    public MailPage inputMailTheme(String inputTheme){
        themeMailField.sendKeys(inputTheme);
        return this;
    }

    //Метод ввода текста письма
    public MailPage inputMailText(String inputText){
        mailTextField.sendKeys(inputText);
        return this;
    }

    //Метод нажатия кнопки "Отправить"
    public MailPage clickSendMail() {
        sentMailBtn.click();
        return this;
    }

    //Метод нажатия "Вернуться во входящие"
    public MailPage clickBackInboxBtn() {
        backInboxBtn.click();
        return this;
    }
}