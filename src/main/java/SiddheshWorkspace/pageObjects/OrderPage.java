package SiddheshWorkspace.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import siddhesh.abstractComponents.reusablecode;


public class OrderPage extends reusablecode {
 
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		

	}

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> CartProduct;

	@FindBy(css = ".totalRow button")
	WebElement checkOut;

	public  Boolean verifyOrderDisplay(String productName) {
         Boolean match =CartProduct.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
	     return match;
		 System.out.println("cartProducts");

	}



}
