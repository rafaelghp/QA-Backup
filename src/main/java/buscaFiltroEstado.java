import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.firefox.FirefoxDriver;

import Teste.core.DSL;
@RunWith(Parameterized.class)
public class buscaFiltroEstado {
	FirefoxDriver driver =  new FirefoxDriver();
	private DSL dsl;
	private buscaEmpresaPage page;
	@Parameter
	public String estado;
	@Parameter(value=1)
	public String resultado;
	@Parameter(value=2)
	public String UF;
	
	
	@Before
	public void inicializa() throws InterruptedException{
		driver.get("https://hmlplat.econodata.com.br/#/login");
		dsl = new DSL(driver);
		page = new buscaEmpresaPage(driver);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		dsl.escreveId("plat-login-email","rafael.s.lima92@hotmail.com");
		dsl.escreveId("plat-login-senha","frango");
		dsl.clicId("plat-login-botao-entrar");
		dsl.clicaSidebar("Empresas");
		page.regiao();
		
		
	}
	
	@After	public void finaliza() throws InterruptedException {
		driver.quit();
		Thread.sleep(1000);}
	
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][]{
			{"AM-AMAZONAS","180.059","AM"},					//0
			{"BA-BAHIA","996.266","BA"},					//1
			{"DF-DISTRITO FEDERAL","338.530","DF"},			//2
			{"ES-ESP�RITO SANTO","423.120","ES"},			//3
			{"MT-MATO GROSSO","342.602","MT"},				//4
			{"MG-MINAS GERAIS","2.036.723","MG"},			//5
			{"PR-PARAN�","1.334.241","PR"},					//6
			{"RJ-RIO DE JANEIRO","1.801.729","RJ"},			//7
			{"RS-RIO GRANDE DO SUL","1.331.716","RS"},		//8
			{"SC-SANTA CATARINA","887.186","SC"},			//9
			{"SP-S�O PAULO","5.895.429","SP"},				//10
		});
		
	}
	
	@Test
	public void testeFiltroEstado() throws InterruptedException {
		page.setEstado(estado);
		page.atualizarPesquisa();
		Assert.assertNotEquals(page.resultado(), "0");
		Assert.assertNotEquals(page.resultado(), "19.538.383");
		Assert.assertEquals(page.resultado(), resultado);
		Assert.assertTrue(page.verificaEstado(UF));

	}
}
