package SKAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SKAutomation.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	
	public WebDriver initializetest() throws IOException
	{
		
		Properties props=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//SKAutomation//Resources//GlobalData.properties");
		
				
		

		ChromeOptions options = new ChromeOptions();
		FirefoxOptions optionsfirefox=new FirefoxOptions();
        props.load(fis);
        String browser=  System.getProperty("browser")!=null ? System.getProperty("browser"):props.getProperty("browser");//using ternary operator ? 
        
        if (browser.equalsIgnoreCase("Chrome"))
        {
        
        
		Map<String, Object> prefs = new HashMap<>();

		// Disable the password manager's "credentials_enable_service"
		prefs.put("credentials_enable_service", false);

		// Disable the "profile.password_manager_enabled" preference
		prefs.put("profile.password_manager_enabled", false);

		prefs.put("profile.password_manager_leak_detection", false);
		options.addArguments("--guest");

		// Apply the preferences to ChromeOptions
		options.setExperimentalOption("prefs", prefs);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
       
        }
        else if(browser.equalsIgnoreCase("Firefox"))
        {
        	Map<String, Object> prefs = new HashMap<>();

    		// Disable the password manager's "credentials_enable_service"
    		prefs.put("credentials_enable_service", false);

    		// Disable the "profile.password_manager_enabled" preference
    		prefs.put("profile.password_manager_enabled", false);

    		prefs.put("profile.password_manager_leak_detection", false);
    		options.addArguments("--guest");

    		// Apply the preferences to ChromeOptions
    		options.setExperimentalOption("prefs", prefs);
    		WebDriverManager.firefoxdriver().setup();
    		driver = new FirefoxDriver(optionsfirefox);
    		
        }
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	
	
	public  List<HashMap<String, String>> Convertjsontomap(String pathname) throws IOException
	{
		Path filePath = Paths.get(pathname);
		String fileContent = Files.readString(filePath);
        System.out.println(fileContent);
        
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String>>data=mapper.readValue(fileContent,new TypeReference<List<HashMap<String,String>>>(){
        });
        
        return data;
        
     }
	public String TakeScreenshot(String Screenshot,WebDriver driver) throws IOException
	{
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports"+Screenshot+".png");
		FileUtils.copyFile(src, file);
		return  System.getProperty("user.dir")+"//reports"+Screenshot+".png";
		
		
		
	}
	
	@BeforeMethod
	
	public LandingPage LaunchApplication() throws IOException
	{
		driver=initializetest();
		landingpage = new LandingPage(driver);
		landingpage.gotourl();
		return landingpage;
		
	}

   @AfterMethod
   
   public void CloseApplication()
   {
	   
	   driver.close();
   }
}
