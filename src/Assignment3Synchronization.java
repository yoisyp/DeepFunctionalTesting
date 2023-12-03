
//Este assignment incluye manejo de Radiobuttons, Dropbox, Checkbox, Carrito de compras y Sincronizacion

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment3Synchronization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Calidad de Software\\Automation\\FilesDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 	
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		
		driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
		driver.findElement(By.id("password")).sendKeys("learning");
		driver.findElement(By.cssSelector("input[value=user]")).click();
		
		//manejo de popup
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(7000)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
		driver.findElement(By.id("okayBtn")).click();
		
		//Seleccion en dropdown
		driver.findElement(By.cssSelector("option[value=consult]")).click();
		driver.findElement(By.id("terms")).click();
		driver.findElement(By.cssSelector(".btn.btn-info.btn-md")).click();
		
		//Seleccion de productos al carrito  
		//Explicit wait hasta que cargue la nueva pagina de compra	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[contains(@class,'card-title')]"))); //se uso como parametro el ultimo de los items de la pagina para probar otra via diferente
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Checkout"))); //Este elemento boton Checkout seria el idoneo a usar para la espera
		addItems(driver);
		
	}
	
	public static void addItems (WebDriver driver)
	{
		//Creando (variable productos) una lista de todos los botones Add que hay en la pagina (existe uno para cada producto, pero se pide anadirlos todos)
		List<WebElement> products=driver.findElements(By.xpath("//button[@class='btn btn-info']"));
		
		//Recorrer toda la lista y dar un click en cada boton Add
		for (int i=0; i<products.size(); i++)
			{
					products.get(i).click(); //Click on add. 
					
			}
		driver.findElement(By.partialLinkText("Checkout")).click();	
		//Otros locators: By.cssSelector("a.btn-primary"), By.cssSelector(".nav-link.btn.btn-primary"), By.xpath("//a[@class='nav-link btn btn-primary']"), By.xpath("//li[@class='nav-item active']")
							
	}
	

}
