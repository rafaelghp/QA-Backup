package Teste.core;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DSL {


	


	public void escreveId(String id_campo, String texto) throws InterruptedException {
		DriverFactory.getDriver().findElement(By.id(id_campo)).sendKeys(texto);
	}

	public void clicId(String campo) throws InterruptedException {
		DriverFactory.getDriver().findElement(By.id(campo)).click();
	}
	


	public void clickXpath(String endereco) {
		DriverFactory.getDriver().findElement(By.xpath(endereco)).click();
	}

	public void escreveEnterId(String campo, String texto) throws InterruptedException {
		DriverFactory.getDriver().findElement(By.id(campo)).sendKeys(texto);
		DriverFactory.getDriver().findElement(By.id(campo)).sendKeys(Keys.ENTER);
	
	}

	public void clicaId(String id_campo) {
		DriverFactory.getDriver().findElement(By.id(id_campo)).click();

	}

	public String pegaValor(String campo) {
		return DriverFactory.getDriver().findElement(By.id(campo)).getText();
		

	}
	
	public void clicaSidebar(String menu) {
		DriverFactory.getDriver().findElement(By.linkText(menu)).click();
	}
	
	public WebElement retornaXpath(String caminho) {
		return  DriverFactory.getDriver().findElement(By.xpath(caminho));
	}
	
	public void clicaXpath(String caminho) {
		DriverFactory.getDriver().findElement(By.xpath(caminho)).click();
	}

	

}
