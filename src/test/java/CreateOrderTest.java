import net.datafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.javacode.pageobject.CartPage;
import ru.javacode.pageobject.MainPage;

import java.text.ParseException;

import static ru.javacode.pageobject.CartPage.*;
import static ru.javacode.pageobject.MainPage.*;

public class CreateOrderTest {
    private WebDriver driver;

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        //bugged in default resolution
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }
    @Test
    public void fullPathToCreateOrder() throws InterruptedException, ParseException {
        Faker faker = new Faker();
        String rndUsername = faker.name().username();
        String rndPassword = faker.internet().password(4, 8);
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage(MAINPAGE_URL);
        mainPage.waitingElementLoad(SIGNUP_BUTTON_TO_OPEN_FORM);
        mainPage.clickToOpenSignUpForm();
        mainPage.waitingElementLoad(SIGNUP_FORM);
        mainPage.singUpFormFilling(rndUsername, rndPassword);
        mainPage.clickToRegister();
        mainPage.waitingAlertToAccept();
        System.out.println(rndUsername + " " + rndPassword);
        mainPage.clickToOpenLogInForm();
        mainPage.waitingElementLoad(LOGIN_FORM);
        mainPage.logInFormFilling(rndUsername, rndPassword);
        mainPage.clickToAuth();
        Thread.sleep(2000);
        int[] gadgetPriceFromList = new int[3];
        int[] gadgetPriceFromCard = new int[3];
        gadgetPriceFromList[0] = mainPage.takeGadgetPrice(FIRST_GADGET_PRICE_FROM_LIST);
        mainPage.clickToOpenGadgetCard(FIRST_GADGET_OPEN_CARD);
        Thread.sleep(2000);
        gadgetPriceFromCard[0] = mainPage.takeGadgetPrice(GADGET_PRICE_FROM_CARD);
        mainPage.addToCartButtonClick();
        mainPage.waitingAlertToAccept();
        mainPage.openPage(MAINPAGE_URL);
        mainPage.clickToOpenCategory(LAPTOPS_CATEGORY_BUTTON);
        Thread.sleep(2000);
        gadgetPriceFromList[1] = mainPage.takeGadgetPrice(SECOND_GADGET_PRICE_FROM_LIST);
        mainPage.clickToOpenGadgetCard(SECOND_GADGET_OPEN_CARD);
        Thread.sleep(2000);
        gadgetPriceFromCard[1] = mainPage.takeGadgetPrice(GADGET_PRICE_FROM_CARD);
        mainPage.addToCartButtonClick();
        mainPage.waitingAlertToAccept();
        mainPage.openPage(MAINPAGE_URL);
        mainPage.clickToOpenCategory(MONITORS_CATEGORY_BUTTON);
        Thread.sleep(2000);
        gadgetPriceFromList[2] = mainPage.takeGadgetPrice(THIRD_GADGET_PRICE_FROM_LIST);
        mainPage.clickToOpenGadgetCard(THIRD_GADGET_OPEN_CARD);
        Thread.sleep(2000);
        gadgetPriceFromCard[2] = mainPage.takeGadgetPrice(GADGET_PRICE_FROM_CARD);
        mainPage.addToCartButtonClick();
        mainPage.waitingAlertToAccept();
        Assert.assertArrayEquals(gadgetPriceFromList, gadgetPriceFromCard);
        mainPage.openPage(CARTPAGE_URL);
        mainPage.waitingElementLoad(TOTAL_PRICE);
        int totalGadgetsPrice = mainPage.takeGadgetPrice(TOTAL_PRICE);
        int sumOfGadgetPriceFromCart = mainPage.takeGadgetPrice(FIRST_GADGET_PRICE_FROM_CARTLIST) +
                mainPage.takeGadgetPrice(SECOND_GADGET_PRICE_FROM_CARTLIST) +
                mainPage.takeGadgetPrice(THIRD_GADGET_PRICE_FROM_CARTLIST);
        Assert.assertEquals(totalGadgetsPrice, sumOfGadgetPriceFromCart);
        CartPage cartPage = new CartPage(driver);
        cartPage.clickToOpenPlaceOrderForm();
        mainPage.waitingElementLoad(PLACE_ORDER_FORM);
        cartPage.placeOrderFormFilling(faker.name().name(), faker.address().country(), faker.address().city(),
                faker.finance().creditCard(), cartPage.generateCreditCardDate().get("Month"), cartPage.generateCreditCardDate().get("Year"));
        cartPage.clickToPurchaseOrder();
        mainPage.waitingElementLoad(MESSAGE_AFTER_CREATE_ORDER);
        String[] datesToCompare = cartPage.purchaseDateCheck();
        Assert.assertEquals(datesToCompare[0], datesToCompare[1]);
    }
    @After
    public void driverClose(){
        driver.quit();
    }
}
