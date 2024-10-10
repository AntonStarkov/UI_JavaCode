package ru.javacode.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainPage {
    private final WebDriver driver;
    public static final String MAINPAGE_URL = "https://www.demoblaze.com";
    public static final By SIGNUP_BUTTON_TO_OPEN_FORM = By.xpath("//*[@id='signin2']");
    public static final By SIGNUP_FORM = By.xpath("//*[@id='signInModal']/div/div/div[2]");
    public static final By SIGNUP_USERNAME_FIELD = By.xpath("//*[@id='sign-username']");
    public static final By SIGNUP_PASSWORD_FIELD = By.xpath("//*[@id='sign-password']");
    public static final By SIGNUP_BUTTON_TO_REGISTER = By.xpath("//*[@id='signInModal']/div/div/div[3]/button[2]");
    public static final By LOGIN_BUTTON_TO_OPEN_FORM = By.xpath("//*[@id='login2']");
    public static final By LOGIN_FORM = By.xpath("//*[@id='logInModal']/div/div/div[2]/form");
    public static final By LOGIN_USERNAME_FIELD = By.xpath("//*[@id='loginusername']");
    public static final By LOGIN_PASSWORD_FIELD = By.xpath("//*[@id='loginpassword']");
    public static final By LOGIN_BUTTON_TO_AUTH = By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]");
    public static final By FIRST_GADGET_PRICE_FROM_LIST = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='Samsung galaxy s6']/parent::*/parent::*/h5");
    public static final By SECOND_GADGET_PRICE_FROM_LIST = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='Sony vaio i5']/parent::*/parent::*/h5");
    public static final By THIRD_GADGET_PRICE_FROM_LIST = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='Apple monitor 24']/parent::*/parent::*/h5");
    public static final By GADGET_PRICE_FROM_CARD = By.xpath("//*[@id='tbodyid']/h3");
    public static final By FIRST_GADGET_OPEN_CARD = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='Samsung galaxy s6']");
    public static final By SECOND_GADGET_OPEN_CARD = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='Sony vaio i5']");
    public static final By THIRD_GADGET_OPEN_CARD = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a[text()='Apple monitor 24']");
    public static final By ADD_TO_CART_BUTTON = By.xpath("//*[@id='tbodyid']/div[2]/div/a");
    public static final By PHONES_CATEGORY_BUTTON = By.xpath("//*[@id='itemc' and text()='Phones']");
    public static final By LAPTOPS_CATEGORY_BUTTON = By.xpath("//*[@id='itemc' and text()='Laptops']");
    public static final By MONITORS_CATEGORY_BUTTON = By.xpath("//*[@id='itemc' and text()='Monitors']");
    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public void openPage(String testingPage){
        driver.get(testingPage);
    }
    public void clickToOpenSignUpForm(){
        driver.findElement(SIGNUP_BUTTON_TO_OPEN_FORM).click();
    }
    public void clickToRegister(){
        driver.findElement(SIGNUP_BUTTON_TO_REGISTER).click();
    }
    public void singUpFormFilling(String usernameField, String passwordField){
        driver.findElement(SIGNUP_USERNAME_FIELD).sendKeys(usernameField);
        driver.findElement(SIGNUP_PASSWORD_FIELD).sendKeys(passwordField);
    }
    public void waitingElementLoad(By xpathElement){
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(xpathElement));
    }
    public void waitingAlertToAccept(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    public void clickToOpenLogInForm(){
        driver.findElement(LOGIN_BUTTON_TO_OPEN_FORM).click();
    }
    public void logInFormFilling(String usernameField, String passwordField){
        driver.findElement(LOGIN_USERNAME_FIELD).sendKeys(usernameField);
        driver.findElement(LOGIN_PASSWORD_FIELD).sendKeys(passwordField);
    }
    public void clickToAuth(){
        driver.findElement(LOGIN_BUTTON_TO_AUTH).click();
    }
    public void clickToOpenGadgetCard(By gadgetCard){
        driver.findElement(gadgetCard).click();
    }
    public void clickToOpenCategory(By category){
        driver.findElement(category).click();
    }
    public int takeGadgetPrice(By xpathGadgetPrice){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(driver.findElement(xpathGadgetPrice).getText());
        int gadgetPriceToInt = 0;
        while (matcher.find()) {
            gadgetPriceToInt = Integer.parseInt(matcher.group());
        }
        return gadgetPriceToInt;
    }
    public void addToCartButtonClick(){
        driver.findElement(ADD_TO_CART_BUTTON).click();
    }
}
