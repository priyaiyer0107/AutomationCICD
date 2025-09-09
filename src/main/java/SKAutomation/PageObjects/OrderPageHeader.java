package SKAutomation.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SKAutomation.AbstractComponents.AbstractComponent;

public class OrderPageHeader extends AbstractComponent {

	WebDriver driver;
	
	public OrderPageHeader(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="td:nth-child(3)")
	List<WebElement>Productlist;
	
	
	public Boolean VerifyorderDisplay(String product1)
	{
	Boolean match=Productlist.stream().anyMatch(product->product.getText().equalsIgnoreCase(product1));
	return match;
	
	
	}

}
