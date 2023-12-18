package stepDefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;

public class EndToEndSteps
{

	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage macc;
	AccountRegistrationPage regpage;
	SearchPage sp;
	
	String email=BaseClass.randomAlphaNumeric() + "@gmail.com";

	@Given("on homepage click on Register Account page")
	public void on_homepage_click_on_register_account_page()
	{

		BaseClass.getLogger().info("Start EndToEnd Test...");
		hp=new HomePage(BaseClass.getDriver());
    	hp.clickMyAccount();
    	hp.clickRegister(); 
	}

	@When("enters the details and selects Privacy Policy")
	public void enters_the_details_and_selects_privacy_policy() 
	{
		BaseClass.getLogger().info("Enter customer Details...");
		
		regpage=new AccountRegistrationPage(BaseClass.getDriver());
		regpage.setFirstName(BaseClass.randomeString().toUpperCase());
		regpage.setLastName(BaseClass.randomeString().toUpperCase());
		
		//String email=BaseClass.randomAlphaNumeric() + "@gmail.com";
		regpage.setEmail(email);
		System.out.println(email);
		regpage.setTelephone(BaseClass.randomeNumber());
		regpage.setPassword("test@123");
		regpage.setConfirmPassword("test@123"); 
		regpage.setPrivacyPolicy();
	}

	@When("clicks on Continue button")
	public void clicks_on_continue_button() 
	{
		regpage.clickContinue();
	}

	@Then("check account should get created successfully")
	public void check_account_should_get_created_successfully()
	{
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}

	@Then("click on logout button")
	public void click_on_logout_button() throws InterruptedException 
	{
		macc=new  MyAccountPage(BaseClass.getDriver());
		macc.clickLogout();
		Thread.sleep(3000);

	}

	@Given("go to login page")
	public void go_to_login_page()
	{
		
		BaseClass.getLogger().info("Goto my account-->Click on Login.. ");
    	hp=new HomePage(BaseClass.getDriver());
    	hp.clickMyAccount();
    	hp.clickLogin();  
	    
	}

	@When("enters  username as email and password as {string}")
	public void enters_username_as_email_and_password_as(String pwd) 
	{
		BaseClass.getLogger().info("Entering email and password.. ");
    	lp=new LoginPage(BaseClass.getDriver());
       	lp.setEmail(email);
       	lp.setPassword(pwd);
		
		
	}

	@When("clicks on the Login button")
	public void clicks_on_the_login_button()
	{
		 lp.clickLogin();
	     BaseClass.getLogger().info("clicked on login button...");  
	}

	@Then("validate the MyAccount Page")
	public void validate_the_my_account_page()
	{
	
		macc=new MyAccountPage(BaseClass.getDriver());
		boolean targetpage=macc.isMyAccountPageExists();
				
		Assert.assertEquals(targetpage, true);
		
	}
	@When("enter productname and click search button")
	public void enter_productname_and_click_search_button() throws IOException
	{
		BaseClass.getLogger().info("search & add product to cart...............");
		hp.enterProductName(BaseClass.getProperties().getProperty("searchProductName"));
		hp.clickSearch();
	}

	@Then("add product to cart")
	public void add_product_to_cart() throws InterruptedException, IOException
	{
		BaseClass.getLogger().info("Select product,quantity to cart.....");
		sp=new SearchPage(BaseClass.getDriver());
		sp.selectProduct("iPhone");
		sp.setQuantity("2");
		sp.addToCart();
		Thread.sleep(5000);
		try {
			Assert.assertEquals(sp.checkConfMsg(),true);

			} catch (Exception e) {
				Assert.fail();
			}
	}


}
