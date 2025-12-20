package siddheshselenium.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SiddheshWorkspace.pageObjects.billingPage;
import SiddheshWorkspace.pageObjects.cartPage;
import SiddheshWorkspace.pageObjects.confirmationPage;
import SiddheshWorkspace.pageObjects.landingPage;
import SiddheshWorkspace.pageObjects.productCatalogue;
import siddheshselenium.TestComponents.TestContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinationImplementation {

    private TestContext context;   // shared context
    private productCatalogue catalogue;
    private cartPage CartPage;
    private confirmationPage orderDetails;

    // Constructor injection by PicoContainer
    public stepDefinationImplementation(TestContext context) {
        this.context = context;
    }

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        // Initialize driver and landing page only once
        if (context.driver == null) {
            context.driver = new siddheshselenium.TestComponents.baseTest().intializeDriver();
        }
        context.landingpage = new landingPage(context.driver);
        context.landingpage.goTo();

        System.out.println("LandingPage in step definition: " + context.landingpage);
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password) {
        catalogue = context.landingpage.signIn(username, password);
    }

    @When("^Add the product (.+_) to cart$")
    public void Add_the_product_to_cart(String productname) throws InterruptedException {
        List<WebElement> products = catalogue.getProductList();
        catalogue.getProductByName(productname);
        CartPage = catalogue.addProductToCart(productname);
    }

    @And("^ Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productname) {
        CartPage.goToCartPage(productname);
        Boolean match = CartPage.verifyProducts(productname);
        Assert.assertTrue(match);
        billingPage finalPage = CartPage.checkout();
        finalPage.SelectCountry("india");
        orderDetails = finalPage.submitorder();
    }

    @Then("{string} message is displayed in confirmation cart ")
    public void message_is_displayed_in_confirmation_page(String string) {
        String message = orderDetails.getConfirmationMessage();
        System.out.println(message);
        Assert.assertTrue(message.equalsIgnoreCase(string));
    }

    @Then("shipping information")
    public void shipping_infromation() {
        String orderinfo = orderDetails.shippingInformation();
        System.out.println(orderinfo);
    }
}



