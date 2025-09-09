package SKAutomation.Tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import SKAutomation.PageObjects.CheckoutPage;
import SKAutomation.PageObjects.LandingPage;
import SKAutomation.PageObjects.ProductCatalogue;
import SKAutomation.TestComponents.BaseTest;
import SKAutomation.TestComponents.Retry;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.*;

public class ErrorValidation extends BaseTest {

	//@Test(retryAnalyzer=Retry.class)
	@Test
	public void LoginTestCases() throws InterruptedException,IOException
	{
      
			
        landingpage.LoginApp("abc1233456@gmail.com", "Ac@123456");
        Assert.assertEquals("Incorrect email password.", landingpage.getErrormsg());
		

	}
	
	@Test
	public void ProductTestCases() throws InterruptedException,IOException
	{
      
		String product1 = "ZARA COAT 3";
		//String product2 = "ADIDAS ORIGINAL";
		String countryname="India";
		
			
	    landingpage.LoginApp("abc1233456@gmail.com", "Abc@123456");
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		List<WebElement> Products = productcatalogue.ProductDisplay();
		// productcatalogue.GetProductsByName(product1);
		productcatalogue.Addtocart(product1);
		Thread.sleep(1000);
		productcatalogue.CartSection(driver);

		CheckoutPage Chkoutpage = new CheckoutPage(driver);

		Chkoutpage.SelectCountryList(countryname);
		Chkoutpage.AssertionCheck();

	}
}
