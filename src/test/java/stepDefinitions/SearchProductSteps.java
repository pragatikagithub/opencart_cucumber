package stepDefinitions;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;

public class SearchProductSteps
{
	WebDriver driver;
	HomePage hp;
	SearchPage sp;
	
	
	@Given("the user is on the opencart Home page")
	public void the_user_is_on_the_opencart_home_page()
	{
		BaseClass.getLogger().info("Goto my Homepage..... ");
    	hp=new HomePage(BaseClass.getDriver());  
	}

	@Given("the user enter valid product in the search input box")
	public void the_user_enter_valid_product_in_the_search_input_box() throws IOException
	{
		BaseClass.getLogger().info("Enter Product name..... "); 
		hp.enterProductName("mac");
		
	}

	@When("the user clicks on the Search button")
	public void the_user_clicks_on_the_search_button()
	{ 
		BaseClass.getLogger().info("clicked on Search button...");
		hp.clickSearch();
	}

	@Then("the user should be redirected to the Product Page")
	public void the_user_should_be_redirected_to_the_product_page() throws InterruptedException 
	{
		BaseClass.getLogger().info("Search for the product...");
		sp=new SearchPage(BaseClass.getDriver());
		sp.isProductExist("MacBook");
		Thread.sleep(5000);
		
			
			
	}

	@Then("validate display of the product")
	public void validate_display_of_the_product()
	{
		try {
			Assert.assertEquals(sp.isProductExist("MacBook"),true);

			} catch (Exception e) {
				Assert.fail();
			}
		
		BaseClass.getLogger().info("Finished SearchProductTest...");
	}


}
