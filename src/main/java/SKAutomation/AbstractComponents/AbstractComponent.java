package SKAutomation.AbstractComponents;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SKAutomation.PageObjects.OrderPageHeader;

public class AbstractComponent {
	
WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath = "(//button[@routerlink='/dashboard/myorders'])")
	WebElement OrderPage;
	
	
	
	public OrderPageHeader Ordr_history()
	{
		OrderPage.click();
		OrderPageHeader orhead=new OrderPageHeader(driver);
		return orhead;	
		
	}

	public void ElementVisibile(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void WebElementVisibile(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
   
    public void ElementInvisible(WebElement findBy)
    {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	 wait.until(ExpectedConditions.invisibilityOf(findBy));
    	 
    }
}
