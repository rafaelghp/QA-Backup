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
public class buscaFiltroendereco {
	FirefoxDriver driver =  new FirefoxDriver();
	private DSL dsl;
	private buscaEmpresaPage page;
	@Parameter
	public  String estado;
	@Parameter(value=1)
	public String cidade;
	@Parameter(value=2)
	public String bairro;
	@Parameter(value=3)
	public String logradouro;
	@Parameter(value=4)
	public String cep;
	@Parameter(value=5)
	public String de;
	@Parameter(value=6)
	public String ate;
	@Parameter(value=7)
	public String resultado;
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
	@After	
	public void finaliza() throws InterruptedException {
			driver.quit();
			}
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][]{
			{"RS","PORTO ALEGRE","auxiliadora","carlos gomes","90.480-003","0","500","382"},
		});
	}
		
		@Test
		public void testeFiltroBairro() throws InterruptedException {
			page.setEstado(estado);
			page.setCidade(cidade);
			page.setBairro(bairro);
			page.setLogradouro(logradouro);
			page.setCep(cep);
			page.setNumDe(de);
			page.setNumAte(ate);
			page.atualizarPesquisa();
			Assert.assertNotEquals(page.resultado(), "0");
			Assert.assertNotEquals(page.resultado(), "19.538.383");
			Assert.assertEquals(page.resultado(), resultado);
			page.detalheEmpresa();
			Assert.assertTrue(page.verificalog(logradouro));
			Assert.assertTrue(page.verificaCep(cep));
		}
	
	
	

}
