package SiddheshWorkspace;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.util.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SiddheshWorkspace.pageObjects.billingPage;
import SiddheshWorkspace.pageObjects.cartPage;
import SiddheshWorkspace.pageObjects.confirmationPage;
import SiddheshWorkspace.pageObjects.landingPage;
import SiddheshWorkspace.pageObjects.productCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import siddheshselenium.TestComponents.baseTest;

public class copyOfStandAloneTest extends baseTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
        


		String productName = "ADIDAS ORIGINAL";
		productCatalogue catalogue = landingpage.signIn("siddheshmalusare0113@gmail.com", "Mal_sidh@0113");
		catalogue.loginMessagePopUp();
		List<WebElement> products = catalogue.getProductList();
     	catalogue.getProductByName(productName);
		cartPage CartPage = catalogue.addProductToCart(productName);
		CartPage.goToCartPage(productName);

		Boolean match = CartPage.verifyProducts(productName);
		Assert.assertTrue(match);
		billingPage finalPage = CartPage.checkout();

		finalPage.SelectCountry("india");
		Assert.assertTrue(true);
		confirmationPage orderDetails = finalPage.submitorder();
		String message = orderDetails.getConfirmationMessage();
		System.out.println(message);
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		String orderinfo = orderDetails.shippingInformation();
		System.out.println(orderinfo);
		
		
		
		
		
		
		
		driver.quit();
	}

}
