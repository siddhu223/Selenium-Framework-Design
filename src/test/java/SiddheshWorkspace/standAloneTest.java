package SiddheshWorkspace;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SiddheshWorkspace.pageObjects.billingPage;
import SiddheshWorkspace.pageObjects.cartPage;
import SiddheshWorkspace.pageObjects.confirmationPage;
import SiddheshWorkspace.pageObjects.productCatalogue;
import siddheshselenium.TestComponents.baseTest;
import siddheshselenium.TestComponents.retryAnalyzer;

public class standAloneTest  extends baseTest{
	
	@Test(retryAnalyzer = retryAnalyzer.class)
	public void submitorder() throws IOException, InterruptedException {



		String productName = "ADIDAS ORIGINAL";
    //  landingPage landingpage = launchApplication();  no need to write this it is write in best test before method
		productCatalogue catalogue = landingpage.signIn("siddheshmalusare0113@gmail.com", "Mal_sidh@0113");

		catalogue.loginMessagePopUp();
		
//		WebDriverWait toasterwait = new WebDriverWait(driver, Duration.ofSeconds(4));
//		toasterwait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));

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

		
	}
	
}


