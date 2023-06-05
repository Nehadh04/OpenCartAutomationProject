package opencartpackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Login {
	EdgeOptions options = new EdgeOptions();
	EdgeDriver driver;

	
	  public void setUp() throws InterruptedException 
	  {
		  options.addArguments("--remote-allow-origins=*");
		  driver = new EdgeDriver(options);	
		  driver.get("https://awesomeqa.com/ui/index.php?route=account/login");
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  }
	 
	   @Test(enabled=false)
	   public void performLogin()
	   {    //Click on login
	    	driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
	    	List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li"));
	    	
	 	   for(WebElement option : options){
	 		   String optionText = option.getText();//username814@gmail.com-email
	 		   if(optionText.equals("Login"))
	 		   {
	 			   System.out.println(optionText);
	 			   option.click();
	 			   break;
	 			   
	 		   }
	 		    
	 		   
	 		}
	   }
	 
	// Login page with Valid and in valid credentials(
	  
	    @Test(priority=3,dataProvider = "logindata",dataProviderClass=OpenCartDataProvider.class )
		public void Getinfo(String type, String email, String  password) throws InterruptedException
		{
	    	this.setUp();
	    	System.out.println(email+ " "+password);
				//Thread.sleep(5000);
			
				//Click on email tab
				WebElement emailField  = driver.findElement(By.xpath("//*[@id=\"input-email\"]"));
				emailField.sendKeys(email);
				// Click on password tab
				  WebElement passwordField =driver.findElement(By.id("input-password"));
				  passwordField.sendKeys(password);
				//Click on Submit tab
				  WebElement loginButton = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
				  loginButton.click();
				  Thread.sleep(2000);
				  if(type == "ValidEmail")
					  ValidLoginTest();
				  else
					  InvalidLoginTest();
				
			
			}		
			
					
		public void  ValidLoginTest() {
			
			try {
				String expectedText = driver.findElement(By.xpath("//*[@id=\"content\"]/h2[1]")).getText();
		        Assert.assertTrue(expectedText.contains("My Account"));
		        System.out.println("Login successfull");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				//System.out.println("Login failed");
			}
		}		
		
		public void  InvalidLoginTest() {
			
			try {
				String expectedText = driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]")).getText();
		        Assert.assertTrue(expectedText.contains("Warning: Your account") || expectedText.contains("Warning: No"));
		        System.out.println("Login Failed");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				//System.out.println("Login failed");
			}
		}		
		
		
	
		
		public void closeBrowser(){
		driver.quit();
		 }
	 
  
}
