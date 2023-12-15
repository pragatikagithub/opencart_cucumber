package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.SearchPage;

public class AddToCartSteps

{
	WebDriver driver;
	HomePage hp;
	SearchPage sp;
	
	
	@Given("enter product in the search box")
	public void enter_product_in_the_search_box() 
	{
		BaseClass.getLogger().info("Enter Product name..... "); 
		hp=new HomePage(BaseClass.getDriver());
		hp.enterProductName("iphone");
	}

	@When("clicks on the Search button")
	public void clicks_on_the_search_button()
	{
		BaseClass.getLogger().info("clicked on Search button...");
		hp=new HomePage(BaseClass.getDriver());
		hp.clickSearch();
	}

	@Then("select the product,quantity and add to cart")
	public void select_the_product_quantity_and_add_to_cart() throws InterruptedException
	{
		BaseClass.getLogger().info("Select product,quantity to cart.....");
		sp=new SearchPage(BaseClass.getDriver());
		sp.selectProduct("iPhone");
		sp.setQuantity("2");
		sp.addToCart();
		Thread.sleep(5000);
	}

	@Then("validate the conformation massage")
	public void validate_the_conformation_massage()
	{
		try {
		Assert.assertEquals(sp.checkConfMsg(),true);

		} catch (Exception e) {
			Assert.fail();
		}
	
	BaseClass.getLogger().info("Finished AddtoCartTest...");
	}

}
