package SKAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SKAutomation.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}



	@FindBy(className="mb-3")
	List<WebElement>Productlist;
	By product_list= By.className("mb-3");
	By Toastmsg=By.xpath("(//div[@id='toast-container'])");
	@FindBy(className="ng-animating")
	WebElement PageLoad;
	@FindBy(xpath="(//button[@routerlink='/dashboard/cart'])")
	WebElement Cart;
	
	
	
	public List<WebElement> ProductDisplay() {
		
		ElementVisibile( product_list);
		
		return Productlist;
		
	}
	public WebElement GetProductsByName(String ProductName)
	{
		
		WebElement Product1 = ProductDisplay().stream()
				.filter(s -> s.findElement(By.tagName("b")).getText().equalsIgnoreCase(ProductName)).findFirst()
				.orElse(null);
		return Product1;
	}
	
	public void Addtocart(String ProductName)
	{

	        WebElement Product1=GetProductsByName(ProductName);
			Product1.findElement(By.xpath("//button[contains (text(),'Add To Cart')]")).click();
			ElementVisibile( Toastmsg);
			ElementInvisible(PageLoad);
			
			
	}
	
	
	public CheckoutPage CartSection(WebDriver driver) {
		// TODO Auto-generated method stub
		Cart.click();
		CheckoutPage chkoutpage=new CheckoutPage(driver);
		return chkoutpage;
	}
	
	

}
