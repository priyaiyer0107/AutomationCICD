package SKAutomation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SKAutomation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
		// TODO Auto-generated method stub
		
		@FindBy(id="userEmail")
		WebElement UserEmail;
		
		@FindBy(id="userPassword")
		WebElement Userpswd;
		
		@FindBy(id="login")
		WebElement Loginbttn;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement ErrorMsg;
		
		
		public ProductCatalogue LoginApp(String email,String password)
		{
			UserEmail.sendKeys(email);
			Userpswd.sendKeys(password);
			Loginbttn.click();
			
			ProductCatalogue productcatalogue=new ProductCatalogue(driver);
			
			return productcatalogue;
			
			
		}
		public String getErrormsg()
		{
			WebElementVisibile(ErrorMsg);
			return(ErrorMsg.getText());
			
		}
		public void gotourl()
		
		{
			driver.get("https://rahulshettyacademy.com/client/");
			
		}
		
		
		
	

}
