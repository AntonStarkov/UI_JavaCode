package ru.javacode.pageobject;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;

public class CartPage {
    private final WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver = driver;
    }
    public static final String CARTPAGE_URL = "https://www.demoblaze.com/cart.html";
    public static final By TOTAL_PRICE = By.xpath("//*[@id='totalp']");
    public static final By FIRST_GADGET_PRICE_FROM_CARTLIST = By.xpath("//*[@id='tbodyid']/tr[1]/td[3]");
    public static final By SECOND_GADGET_PRICE_FROM_CARTLIST = By.xpath("//*[@id='tbodyid']/tr[2]/td[3]");
    public static final By THIRD_GADGET_PRICE_FROM_CARTLIST = By.xpath("//*[@id='tbodyid']/tr[3]/td[3]");
    public static final By PLACE_ORDER_BUTTON_TO_OPEN_FORM = By.xpath("//*[@id='page-wrapper']/div/div[2]/button");
    public static final By PLACE_ORDER_FORM = By.xpath("//*[@id='orderModal']/div/div/div[2]/form");
    public static final By PURCHASE_BUTTON = By.xpath("//*[@id='orderModal']/div/div/div[3]/button[2]");
    public static final By MESSAGE_AFTER_CREATE_ORDER = By.xpath("/html/body/div[10]/p");
    public void clickToOpenPlaceOrderForm(){
        driver.findElement(PLACE_ORDER_BUTTON_TO_OPEN_FORM).click();
    }
    public void clickToPurchaseOrder(){
        driver.findElement(PURCHASE_BUTTON).click();
    }
    public void placeOrderFormFilling(String name, String country, String city, String creditCard, String month, String year){
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='country']")).sendKeys(country);
        driver.findElement(By.xpath("//*[@id='city']")).sendKeys(city);
        driver.findElement(By.xpath("//*[@id='card']")).sendKeys(creditCard);
        driver.findElement(By.xpath("//*[@id='month']")).sendKeys(month);
        driver.findElement(By.xpath("//*[@id='year']")).sendKeys(year);
    }
    public String[] purchaseDateCheck() throws ParseException {
        String[] array = new String[2];
        String purchaseInfo = driver.findElement(MESSAGE_AFTER_CREATE_ORDER).getText();
        String subStr = purchaseInfo.substring(purchaseInfo.indexOf("Date: ") + 6);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        array[0] = formatter.format(formatter.parse(subStr));
        array[1] = formatter.format(new Date());
        return array;
    }
    public HashMap<String, String> generateCreditCardDate(){
        Faker faker = new Faker();
        HashMap<String, String> monthAndYear = new HashMap<>();
        Month monthEnum = LocalDate.now().getMonth();
        int currentYear = Year.now().getValue();
        int currentMonth = monthEnum.getValue();
        int generateMonth = faker.number().numberBetween(1,13);
        monthAndYear.put("Month", Integer.toString(generateMonth));
        if(currentMonth >= generateMonth){
            monthAndYear.put("Year", Integer.toString(faker.number().numberBetween(currentYear, currentYear + 3)));
        }else{
            monthAndYear.put("Year", Integer.toString(faker.number().numberBetween(currentYear + 1, currentYear + 3)));
        }
        return monthAndYear;
    }
}
