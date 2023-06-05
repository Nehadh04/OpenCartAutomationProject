package opencartpackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OpenCartRegistration {
	EdgeDriver driver;
	EdgeOptions options = new EdgeOptions();

	@Test(priority = 1, enabled = true)
	public void setUp() throws InterruptedException {

		options.addArguments("--remote-allow-origins=*");
		driver = new EdgeDriver(options);
		driver.get("https://awesomeqa.com/ui/index.php?");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 2, enabled = true)
	public void verifyTitle() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Your Store";
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println(actualTitle);

	}

	@Test(priority = 3, enabled = true, dataProvider = "registrationdata", dataProviderClass = OpenCartDataProvider.class)
	public void clickRegister(String firstname, String lastname, String email, String phone, String password) {

		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		
		List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li"));
		for (WebElement option : options) {
			String optionText = option.getText();
			if (optionText.equals("Register")) {
				System.out.println(optionText);
				option.click();
				break;

			}

			System.out.println(optionText);

		}
		enterRegistrationdetails(firstname, lastname, email, phone, password);

	}

	public void enterRegistrationdetails(String firstname, String lastname, String email, String phone,
			String password) {

		// First name
		driver.findElement(By.id("input-firstname")).sendKeys(firstname);
		// Last name
		driver.findElement(By.id("input-lastname")).sendKeys(lastname);
		System.out.println(email);
		driver.findElement(By.xpath("//input[@type=\"email\"]")).sendKeys(email);
		// Telephone
		driver.findElement(By.xpath("//input[@type=\"tel\"]")).sendKeys(phone);
		// Password
		driver.findElement(By.id("input-password")).sendKeys(password);
		// Confirm Password
		driver.findElement(By.id("input-confirm")).sendKeys(password);
		// Select Radio button - Yes
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input")).click();
		// Privacy Policy Checkbox
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[1]")).click();
		// Click Continue
		driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]")).click();
		// Click Continue on Continue (account has been created)
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
		logout();

	}

	
	public void logout() {
		// Click on My Account dropdown and selct Logout
		driver.findElement(By.xpath("//*[@title=\"My Account\"]")).click();
		List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[5]/a"));
		for (WebElement option : options) {
			String optionText = option.getText();
			if (optionText.equals("Logout")) {
				System.out.println(optionText);
				option.click();
				break;
			}
		}
		// Click in Continue button
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();

	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
