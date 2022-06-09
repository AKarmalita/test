import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class cardTransfer {
    // Определение UI элементов на странице

    By cardNumber1 = By.xpath("//input [@data-qa-node='numberdebitSource']");
    By validDate1 = By.xpath("//input [@data-qa-node='expiredebitSource']");
    By cvv1 = By.xpath("//input [@data-qa-node='cvvdebitSource']");
    By cardholderName1 = By.xpath("//input[@data-qa-node='firstNamedebitSource']");
    By cardholderSurname1 = By.xpath("//input[@data-qa-node='lastNamedebitSource']");
    By cardNumber2 = By.xpath("//input[@data-qa-node='numberreceiver']");
    By cardholderName2 = By.xpath("//input[@data-qa-node='firstNamereceiver']");
    By cardholderSurname2 = By.xpath("//input[@data-qa-node='lastNamereceiver']");
    By amount = By.xpath("//input[@data-qa-node='amount']");
    By currency = By.xpath( "//div[@class='currency_LIup4nXfM7']");
    By currencyUSD = By.xpath( "//button[@data-qa-value='USD']");
    By buttonSubmit = By.xpath( "//button[@type='submit']");



    // Определение элементов на странице корзины

    By cardCard1 = By.xpath("//div/span[@data-qa-node='payer-card']");
    By cardCard2 = By.xpath("//div/span[@data-qa-node='receiver-card']");
    By amountPayer = By.xpath("//div[@data-qa-node='payer-amount']");
    By amountComission = By.xpath("//div[@data-qa-node='payer-currency']");
    By receiverName = By.xpath("//div[@data-qa-node='receiver-name']");
    By receiverAmount = By.xpath("//div[@data-qa-node='receiver-amount']");
    By totalAmount = By.xpath("//div[@class='sc-chPdSV iiqwfv']");


    // Написание тестов
    @Test
    public void checkMinPaymentSum () throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://next.privat24.ua/money-transfer/card");
        driver.findElement(cardNumber1).sendKeys("4552331448138217");
        driver.findElement(validDate1).sendKeys("1234");
        driver.findElement(cvv1).sendKeys("321");
        Thread.sleep(1000);
        driver.findElement(cardholderName1).sendKeys("Shayne");
        driver.findElement(cardholderSurname1).sendKeys("McConnell");
        driver.findElement(cardNumber2).sendKeys("4004159115449003");
        Thread.sleep(1000);
        driver.findElement(cardholderName2).sendKeys("Philipp");
        driver.findElement(cardholderSurname2).sendKeys("McCauley");
        driver.findElement(amount).sendKeys("10");
        driver.findElement(currency).click();
        driver.findElement(currencyUSD).sendKeys("USD");
        driver.findElement(currencyUSD).click();
        Thread.sleep(1000);
        driver.findElement(buttonSubmit).submit();



        // Проверка ожидаемого и фактического результатов

        Assert.assertEquals("4552 3314 4813 8217", driver.findElement(cardCard1).getText());
        Assert.assertEquals("4004 1591 1544 9003", driver.findElement(cardCard2).getText());
        Assert.assertEquals("10 USD", driver.findElement(amountPayer).getText());
        Assert.assertEquals("3.15 USD", driver.findElement(amountComission).getText());
        Assert.assertEquals("PHILIPP MCCAULEY", driver.findElement(receiverName).getText());
        Assert.assertEquals("10 USD", driver.findElement(receiverAmount).getText());
        Assert.assertEquals("13.16 USD", driver.findElement(totalAmount).getText());
    }

}
