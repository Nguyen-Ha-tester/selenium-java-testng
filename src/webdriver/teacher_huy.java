package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.Alert;

public class teacher_huy<Alert> {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		}
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver; 
		alert = (Alert) driver.switchTo().alert();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_TEMPLATE() {
	
	//Vòng lặp for => tránh 1 kiểu code lặp lại nhiều dòng
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement eachCourseName : courseName) {
			Assert.assertTrue(eachCourseName.getText().contains("Excel"));	}
		
	//Handle dropdown:
		Select select; //Khai báo
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH"); // khởi tạo kèm thao tác
		
	//Iframe/Frame:
		//Switch qua frame chưa login textbox
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		//Switch back to main page
		driver.switchTo().defaultContent();

	
	//Accept alert
	//alert.accept();
	}

	//private void scrollToElement(String string) {
		
	//}
	// Hàm scroll trên browser
	//scrollToElement("img.img-dtcp.d-block ");
	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
		// Nếu true thì scroll lên mép trên
		// Nếu false thì scroll xuống mép dưới
	}
	
	// 1- Load 1 lần 1 file
		//	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath1);
		//	sleepInSecond(3);
		//	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath2);
		//	sleepInSecond(3);
	
	//HÀM WAIT
		// 1- Implicit wait
			//driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		// 2- Explicit Wait:
			//WebDriverWait explicitWait; // Khai báo
			//explicitWait = new WebDriverWait(driver, 15); // Khởi tạo
			//explicitWait = new WebDriverWait(driver, 1); //Apply chỉ đợi 1s
		// 3- Static Wait:  sleepInSecond(3);
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
  
		}
	}
}
