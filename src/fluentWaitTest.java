import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class fluentWaitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Calidad de Software\\Automation\\FilesDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); //ChromeDriver es la clase que implementa WebDriver Interface	
		//driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		driver.findElement(By.cssSelector("[id='start'] button")).click();
		
		//FluentWait es la clase que implementa Wait interface, 
		//se indica el tiempo total de espera, 
		//se indica el intervalo de tiempo en el cual se chequea el elemento durante el tiempo total de espera
		//se indica que si no encuentra el elemento durante el tiempo de espera que lo ignore
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class); 	
		
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
		   
			public WebElement apply(WebDriver driver) {
		       if (driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed())
		       {
		    	   return driver.findElement(By.cssSelector("[id='finish'] h4"));
		       }
		       else
		       {
		    	   return null;
		       }
		     }
		   });
		System.out.println(driver.findElement(By.cssSelector("[id='finish'] h4")).getText());

	}

}
