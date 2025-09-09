package SKAutomation.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckoutPage {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button[normalize-space()='Checkout'])")
	WebElement Checkout;

	@FindBy(xpath = "(//input[@placeholder='Select Country'])")
	WebElement SelectCountry;

	@FindBy(xpath = "(//section[@class='ta-results list-group ng-star-inserted'])")
	List<WebElement> CountryList;

	@FindBy(xpath = "(//a[@class='btnn action__submit ng-star-inserted'])")
	WebElement SubmitButton;

	@FindBy(xpath = "(//label[@class='ng-star-inserted'])")
	WebElement Order_id;

	@FindBy(xpath = "(//label[@routerlink='/dashboard/myorders'])")
	WebElement OrderPage;

	@FindBy(css = "tbody tr:nth-child(1) th:nth-child(1)")
	WebElement History_order_id;

	

	public void AssertionCheck() {
		String order_id = Order_id.getText();
		OrderPage.click();
		String history_order_id = History_order_id.getText();
		Assert.assertEquals(order_id.replace("|", "").trim(), history_order_id);

	}

	public void SelectCountryList(String countryname) {
		Checkout.click();
		SelectCountry.sendKeys("ind");
		List<WebElement> UpdatedList = CountryList.stream().filter(country -> country.getText().contains(countryname))
				.collect(Collectors.toList());
		UpdatedList.get(0).click();
		SubmitButton.click();
	}

}
