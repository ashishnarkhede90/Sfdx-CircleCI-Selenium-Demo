package seleniumgluecode;


import java.util.*;  
import java.io.*;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//@CucumberOptions(tags={"@TestAccountcreation"})
public class selenium {
 private WebDriver driver;
 private String baseUrl;
 private boolean acceptNextAlert = true;
 private StringBuffer verificationErrors = new StringBuffer();
 
	@Given("^navigate to \"([^\"]*)\"$")
	 public void navigate_to(String baseUrl) throws Throwable {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_linux64");
		//System.setProperty("webdriver.chrome.driver", "/Users/ashishnarkhede/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/");
	 }
	  
	 @Given("^username \"([^\"]*)\" and password \"([^\"]*)\"$")
	 public void username_and_password(String username, String password) throws Throwable {
		 JSONParser parser=new JSONParser();

		 // Object obj = parser.parse(new FileReader("./data/credentials.json"));
		 // JSONObject jsonObject = (JSONObject) obj;
		 // username = (String) jsonObject.get("username");
		 // password = (String) jsonObject.get("password");

		Properties p = null;
		FileReader reader;

		try {
			reader = new FileReader("data/creds.properties");
			p = new Properties();  
		    p.load(reader);  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception ex) {
			
		}

		System.out.println(p.getProperty("USERNAME"));  
		System.out.println(p.getProperty("PASSWORD"));
		 
		 
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
	 }
	  
	 @Then("^select \"([^\"]*)\"$")
	 public void select(String arg1) throws Throwable {
		driver.findElement(By.id("tryLexDialogX")).click();
		driver.findElement(By.id("Account_Tab")).click();
		driver.findElement(By.name("new")).click();
		driver.findElement(By.id("acc2")).clear();
		driver.findElement(By.id("acc2")).sendKeys("Temp User");
		driver.findElement(By.name("save")).click();
	  
	 //driver.get("/" + arg1);
	 }
	  
	 @Then("^response should contain \"([^\"]*)\"$")
	 public void response_should_contain(String arg1) throws Throwable {
		 System.out.println(driver.getTitle());
		 
		 if(driver.findElement(By.id("errorDiv_ep")) != null) {
			 throw new AssertionError();
		 }
		 if (!driver.getTitle().contains("Temp User")) {
			 throw new AssertionError();
		 }
		 
		 driver.close();
	 }
	 
	 @After
	 public void tearDown(Scenario scenario) {
	     if (scenario.isFailed()) {
	       // Take a screenshot...
	       final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	       scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
	       driver.quit();
	     }
	 }
}