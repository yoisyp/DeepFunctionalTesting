import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	//Ejemplo generico para seleccionar dinamicamente varios items en un carrito de compra

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Calidad de Software\\Automation\\FilesDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		//Implicit wait de 5 segundos, afectara a cada linea de codigo del test.
//		driver.manage()	.timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//WebDriverWait wait = new WebDriverWait(driver,5); esta variante da error, la de abajo solucion alternativa
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); //Creando objeto de la clase WebDriverWait para poder acceder a sus metodos.
		
	
	    //Creando un arreglo con todos los items a comprar
		String[] itemsNeeded = {"Cucumber", "Brocolli", "Beetroot", "Carrot"};
						
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");		
		Thread.sleep(3000);		
		addItems(driver, itemsNeeded);
		
		driver.findElement(By.cssSelector("img[alt='Cart']")).click(); //Boton carrito verde
		//Boton checkout, en este caso si se puede usar un texto del elemento como localizador porque dicho texto no es dinamico, no varia. 
		driver.findElement(By.xpath("//button[contains(text(), 'PROCEED TO CHECKOUT')]")).click();
		
		//Entrando codigo de promocion y usando Explicit wait		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promocode")));
		driver.findElement(By.cssSelector("input.promocode")).sendKeys("rahulshettyacademy");
		//Click en boton Apply del codigo de promocion
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		
		//Explicit wait		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		
		
		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
		
		
	}

	
	
	public static void addItems (WebDriver driver, String[] itemsNeeded)
	{
		int counterItemNeeded=0; 
		
		//Creando (variable productos) una lista de todos los nombres de productos que hay en la pagina 
		List<WebElement> products=driver.findElements(By.cssSelector("h4.product-name"));
			
		//Recorrer toda la lista y obtener el nombre de cada elemento hasta encontrar el que se esta buscando
		for (int i=0; i<products.size(); i++)
		{
			//Brocolli - 1 Kg
			//Brocolli, 1 Kg			
			String[] names=products.get(i).getText().split("-"); //guardo el nombre de cada elemento y se divide en dos por el caracter-, se almacena cada string en un arreglo de strig
			String formattedName=names[0].trim(); //Metodo trim recorta/elimina todos los espacios al final del primer string 
			
			//Convertir array itemsNeedes en una arrayList para facil busqueda
			List<String> itemsNeededList = Arrays.asList(itemsNeeded);
			
			//Chequear si el nombre extraido esta presente en arrayList o no			
			if (itemsNeededList.contains(formattedName))
			{
				counterItemNeeded++;
				//Click on add to cart. Primero obtengo el boton que pertenece al producto i y luego doy el click
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click(); // Si se utiliza este localizador //div[text()='ADD TO CART'] se marea porque busca dos veces seguidas el primer elemento por usar texto como localizador y como es dinamico este cambia a ADDED y no lo encuentra.
						
				if(counterItemNeeded==itemsNeeded.length) 
				{
					break;
				} 
			}
		}

		
	}
	
	
}
