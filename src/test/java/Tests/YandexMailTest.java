package Tests;

import Pages.LoginPage;
import Pages.MailPage;
import Utils.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class YandexMailTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mailLink"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void mailTest()  {
        int beforeNum;
        int afterNum;
        LoginPage loginpage = new LoginPage(driver);
        loginpage.clickMailBtn()
                .enterMailLogin(ConfProperties.getProperty("mail"))
                .clickEnterMailBtn()
                .enterPassword(ConfProperties.getProperty("password"))
                .clickEnterMailBtn();
        MailPage mailpage = new MailPage(driver);
        mailpage.findMailsByTheme();
        beforeNum = mailpage.getNumberOfMails();
        mailpage.clickSendBtn()
                .inputAddressMail(ConfProperties.getProperty("mail"))
                .inputMailTheme(ConfProperties.getProperty("mailTheme"))
                .inputMailText("Найдено " + mailpage.getNumberOfMails() + " писем\\ьма")
                .clickSendMail()
                .clickBackInboxBtn();
        driver.navigate().refresh();
        mailpage.findMailsByTheme();
        afterNum = mailpage.getNumberOfMails();
        Assert.assertEquals(++beforeNum,afterNum);
    }
}
