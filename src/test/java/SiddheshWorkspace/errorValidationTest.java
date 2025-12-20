package SiddheshWorkspace;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SiddheshWorkspace.pageObjects.cartPage;
import SiddheshWorkspace.pageObjects.productCatalogue;
import siddheshselenium.TestComponents.baseTest;
import siddheshselenium.TestComponents.retryAnalyzer;

public class errorValidationTest extends baseTest {


	public String productName = "ADIDAS ORIGINAL";
	
	@Test(groups = {"errorhandling"},retryAnalyzer=retryAnalyzer.class)
	public void endtoendTest() throws IOException, InterruptedException {

		landingpage.signIn("siddheshmalusare0113@gmail.com", "Mal_sidh@");
		landingpage.getErrorMessage();
		Assert.assertEquals(landingpage.getErrorMessage(),"Incorrect email  password.");
		System.out.println("here your credential failed");
			

	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		productCatalogue catalogue = landingpage.signIn("siddheshmalusare0113@gmail.com", "Mal_sidh@0113");
		catalogue.loginMessagePopUp();
		List<WebElement> products = catalogue.getProductList();
		catalogue.getProductByName(productName);
		cartPage CartPage = catalogue.addProductToCart(productName);
		CartPage.goToCartPage(productName);
		Boolean match = CartPage.verifyProducts("ADIDAS ORIGINAL SUPER");
		Assert.assertTrue(match);
	}
}
