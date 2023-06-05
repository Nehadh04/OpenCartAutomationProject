package opencartpackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class OpencartSearch {

	EdgeOptions options = new EdgeOptions();
	EdgeDriver driver;
	// EdgeDriver driver = new EdgeDriver();

	@DataProvider(name = "testdata")
	public Object[][] dataProvFun() {
		Object data[][] = { {"Computer"},{"11"},{ "Mac" } };

		return data;
	}

	// Launching Edge browser.
	public void setUp() throws InterruptedException {
		options.addArguments("--remote-allow-origins=*");
		driver = new EdgeDriver(options);
		System.out.println("Start Test");
		driver.navigate().to("https://awesomeqa.com/ui/index.php?route=account/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	// Performing login
	@BeforeClass
	public void login() throws InterruptedException {
		// Click on email tab
		this.setUp();
		driver.findElement(By.xpath("//*[@id=\"input-email\"]")).sendKeys("username824@gmail.com");

		// Click on password tab
		driver.findElement(By.id("input-password")).sendKeys("aaa222555");

		// Click on Submit tab
		WebElement loginButton = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
		loginButton.click();
		System.out.println("Login Sucessful");
		Thread.sleep(1000);
	}
	// Passing the dataProvider to the test method through @Test annotation

	@Test(dataProvider = "testdata", priority = 0)
	public void searchTab(String keyword) throws InterruptedException {

		WebElement txtbx = driver.findElement(By.xpath("//input[@type=\"text\"]"));

		txtbx.sendKeys(keyword);

		Reporter.log("Keyword entered is : " + keyword);
		txtbx.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		try {
			String expectedText = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[3]/div/label"))
					.getText();
			Assert.assertTrue(expectedText.contains("Sort By"));
			System.out.println("Products found");
			sortBy();
		} catch (Exception e) {
			System.out.println("Products not found");

		}

		Reporter.log("Search results are displayed.");
		WebElement txtbx1 = driver.findElement(By.xpath("//input[@type=\"text\"]"));
		txtbx1.sendKeys(Keys.CONTROL + "a");
		txtbx1.sendKeys(Keys.DELETE);

	}

	// Clicking on Sortby dropdown and selecting one of the option
	@Test(priority = 1)
	public void sortBy() {
		try {
			WebElement testDropDown = driver.findElement(By.id("input-sort"));
			Select dropdown = new Select(testDropDown);
			dropdown.selectByVisibleText("Price (Low > High)");
		} catch (Exception e) {

		}
	}

	// Click on Show dropdown and select number of products to display
	@Test(priority = 2)
	public void show() {
		try {
			WebElement ShowDropDown = driver.findElement(By.xpath("//*[@id=\"input-limit\"]"));
			Select dd = new Select(ShowDropDown);
			dd.selectByVisibleText("25");
		} catch (Exception e) {

		}

	}

	@Test(priority = 3)
	public void addItems() {

		try {
			WebElement prod = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div/div[1]/a/img"));
			prod.click();
		} catch (Exception e) {

		}
	}

	// select number of products to be added in the cart
	@DataProvider(name = "Qtyofitem")
	public Object[][] dataProvQty() {
		Object item[][] = { { "4" }, { "-1" }, { "1" }, { "100" } };

		return item;
	}

	@Test(dataProvider = "Qtyofitem", priority = 4)
	public void selectQty(String num) {

		WebElement Qty = driver.findElement(By.id("input-quantity"));
		Qty.sendKeys(Keys.CONTROL + "a");
		Qty.sendKeys(Keys.DELETE);
		Qty.sendKeys(num);
		driver.findElement(By.id("button-cart")).click();
		;
	}

	// Click on Add Cart

	// driver.navigate().back();
	@Test(priority = 5)
	public void clickCart() {
		driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
	}

	@Test(priority = 6)
	public void viewCart() {
		driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[1]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(priority = 7)
	public void couponCode() {
		driver.findElement(By.xpath("//*[@id=\"accordion\"]/div[1]/div[1]/h4/a")).click();
		// Enter Coupon Code
		driver.findElement(By.xpath("//*[@id=\"input-coupon\"]")).sendKeys("Sale");
		// Click on Apply Coupon
		driver.findElement(By.xpath("//*[@id=\"button-coupon\"]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(priority = 8)
	public void clickCheckout() {
		driver.findElement(By.linkText("Checkout")).click();

	}

	@Test(priority = 9)
	public void billingAddress() {

		// identify element with value
		WebElement rbNew = driver.findElement(By.xpath("//*[@id=\"collapse-payment-address\"]/div/form/div[3]/label/input"));
		
		// select radio button with click()
		rbNew.click();
		System.out.println("new address");
	}

	@Test(priority = 10)
	public void enterBillingdetails() throws InterruptedException {

		// First name
		driver.findElement(By.id("input-payment-firstname")).sendKeys("Niya");
		// Last name
		driver.findElement(By.id("input-payment-lastname")).sendKeys("Ji");
		// address
		driver.findElement(By.id("input-payment-address-1")).sendKeys("333,Sec -15");
		// city
		driver.findElement(By.id("input-payment-city")).sendKeys("Gurgaon");
		// postal code
		driver.findElement(By.id("input-payment-postcode")).sendKeys("122004");
		// Country dropdown
		// Create object of the Select class
		Select se = new Select(driver.findElement(By.xpath("//*[@id=\"input-payment-country\"]")));

		// Select the option by value
		se.selectByValue("99");
		// Select by Region
		Select se1 = new Select(driver.findElement(By.xpath("//*[@id=\"input-payment-zone\"]")));

		// Select the option by Visible text
		se1.selectByVisibleText("Haryana");
		// Click on Continue
		driver.findElement(By.xpath("//*[@id=\"button-payment-address\"]")).click();

		// driver.findElement(By.xpath("//*[@id=\"collapse-payment-method\"]/div/div[1]")).click();
		// Alert simpleAlert = driver.switchTo().alert();
		// simpleAlert.accept();

	}


// Select dropdown = new Select(driver.findElement(By.name("my-select")));
// dropdown.selectByVisibleText("Three");


  @AfterClass
  public void closeBrowser(){ driver.quit();
	}
}
 
