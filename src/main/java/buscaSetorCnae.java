import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.firefox.FirefoxDriver;

import Teste.core.DSL;

public class buscaSetorCnae {
	FirefoxDriver driver =  new FirefoxDriver();
	private DSL dsl;
	private buscaEmpresaPage page;
	@Parameter(value=0)
	public String cnae;
	@Parameter(value=1)
	public String resultado;
	@Parameter(value=2)
	public String cnaeres;
	
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
		page.setor();
		
		
	}
	
	@After	
	public void finaliza() throws InterruptedException {
		driver.quit();
	}
	
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][]{
			{"A-01","644.574","A-01"},
		});
	}
	
	
	@Test
	public void testeFiltroCnae() throws InterruptedException {
		page.setCnae(cnae);
		page.atualizarPesquisa();
		Assert.assertNotEquals(page.resultado(), "0");
		Assert.assertNotEquals(page.resultado(), "19.538.383");
		Assert.assertEquals(page.resultado(), resultado);
		Assert.assertTrue(page.verificaCnae(cnaeres));
		
	}
	
	
	
}
