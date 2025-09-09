package SKAutomation.Tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SKAutomation.PageObjects.CheckoutPage;
import SKAutomation.PageObjects.LandingPage;
import SKAutomation.PageObjects.OrderPageHeader;
import SKAutomation.PageObjects.ProductCatalogue;
import SKAutomation.TestComponents.BaseTest;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class StandAloneTest extends BaseTest {

	
	String product1 = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void TestCases(HashMap<String,String>input) throws InterruptedException,IOException
	{
      
		
		//String product2 = "ADIDAS ORIGINAL";
		String countryname="India";
		
			
		ProductCatalogue productcatalogue=landingpage.LoginApp(input.get("email"),input.get("password"));
		
		List<WebElement> Products = productcatalogue.ProductDisplay();
		// productcatalogue.GetProductsByName(product1);
		productcatalogue.Addtocart(input.get("productname"));
		Thread.sleep(1000);
		CheckoutPage Chkoutpage=productcatalogue.CartSection(driver);

	    Chkoutpage.SelectCountryList(countryname);
		Chkoutpage.AssertionCheck();

	}
	
	
	
	@Test(dependsOnMethods= {"TestCases"})
	
	public void OrderHistoryTest()
	{
		ProductCatalogue productcatalogue=landingpage.LoginApp("abc1233456@gmail.com", "Abc@123456");
		OrderPageHeader orheader=productcatalogue.Ordr_history();
		
		Assert.assertTrue(orheader.VerifyorderDisplay(product1));
		
		
	}
	
	@DataProvider
	
	public Object[][] getData() throws IOException
	{
	
		
		List<HashMap<String,String>>data=Convertjsontomap(System.getProperty("user.dir")+"\\src\\test\\java\\SKAutomation\\TestData\\PurchaseOrder.json");
		
	//	return new Object[][] {{"abc1233456@gmail.com","Abc@123456","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	}


