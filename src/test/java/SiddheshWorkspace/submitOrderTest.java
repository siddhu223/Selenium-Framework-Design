package SiddheshWorkspace;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SiddheshWorkspace.pageObjects.OrderPage;
import SiddheshWorkspace.pageObjects.billingPage;
import SiddheshWorkspace.pageObjects.cartPage;
import SiddheshWorkspace.pageObjects.confirmationPage;
import SiddheshWorkspace.pageObjects.productCatalogue;
import siddheshselenium.TestComponents.baseTest;

public class submitOrderTest extends baseTest {

	public String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = "purchase")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException {

		productCatalogue catalogue = landingpage.signIn(input.get("email"), input.get("password"));
		catalogue.loginMessagePopUp();
		List<WebElement> products = catalogue.getProductList();
		catalogue.getProductByName(input.get(productName));
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

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		// ADIDAS ORIGINAL verify in order history present or not
		productCatalogue catalogue = landingpage.signIn("siddheshmalusare0113@gmail.com", "Mal_sidh@0113");
		OrderPage orderpage = catalogue.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	}
	
	//method for taking screenshot
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ss = (TakesScreenshot)driver;
		File source=ss.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+"testCaseName"+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+"testCaseName"+".png";
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\projectSelenium\\Data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "siddheshmalusare0113@gmail.com");
//	map.put("password", "Mal_sidh@0113");
//	map.put("productName", "ADIDAS ORIGINAL");
//
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "malusarexyz@gmail.com");
//	map1.put("password", "Avinash@0113");
//	map1.put("productName", "ZARA COAT 3");

	// This can be done with this way also and by hashmap
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "siddheshmalusare0113@gmail.com", "Mal_sidh@0113", "ADIDAS ORIGINAL" },
//				{ "malusarexyz@gmail.com", "Avinash@0113", "ZARA COAT 3" } };
//	}

}
