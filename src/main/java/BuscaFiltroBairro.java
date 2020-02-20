import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import Teste.core.DSL;
import Teste.core.DriverFactory;
@RunWith(Parameterized.class)
public class BuscaFiltroBairro{
	private DSL dsl;
	private buscaEmpresaPage page;
	@Parameter
	public String estado;
	@Parameter(value=1)
	public String cidade;
	@Parameter(value=2)
	public String bairro;
	@Parameter(value=3)
	public String resultado;
	@Parameter(value=4)
	public String bairroRes;
	@Before
	public void inicializa() throws InterruptedException{
		DriverFactory.getDriver().get("https://hmlplat.econodata.com.br/#/login");
		dsl = new DSL();
		page = new buscaEmpresaPage();
		dsl.escreveId("plat-login-email","rafael.s.lima92@hotmail.com");
		dsl.escreveId("plat-login-senha","frango");
		dsl.clicId("plat-login-botao-entrar");
		dsl.clicaSidebar("Empresas");
		page.regiao();
	}
	@After	
	public void finaliza() throws InterruptedException {
			DriverFactory.KillDriver();
			}
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][]{
			{"AM-AMAZONAS","MANAUS - AM","Manaus - Centro","10.470","Centro"},							
			{"BA-BAHIA","SALVADOR - BA","Salvador - Centro","1.987","Centro"},							
			{"CE-CEARÁ","FORTALEZA - CE","Fortaleza - Centro","20.507","centro"},						
			{"ES-ESPÍRITO SANTO","VITÓRIA - ES","Vitória - Centro","5.538","centro"},											
			{"MS-MATO GROSSO DO SUL","CAMPO GRANDE - MS","Campo Grande - Centro","8.384","centro"},		
			{"MG-MINAS GERAIS","BELO HORIZONTE - MG","Belo Horizonte - Centro","23.297","centro"},																		
			{"PE-PERNAMBUCO","RECIFE - PE","Recife -  Aflitos","711","aflitos"},												
			{"RJ-RIO DE JANEIRO","RIO DE JANEIRO - RJ","Rio De Janeiro - Centro","65.361","centro"},		
			{"RN-RIO GRANDE DO NORTE","NATAL - RN","Natal - Nordeste","544","nordeste"},													
			{"SC-SANTA CATARINA","FLORIANÓPOLIS - SC","Florianópolis - Centro","17.469","centro"},		
			{"SP-SÃO PAULO","SÃO PAULO - SP","São Paulo - centro","26.613","centro"},															
		});
	}
		@Test
		public void testeFiltroBairro() throws InterruptedException {
			page.setEstado(estado);
			page.setCidade(cidade);
			page.setBairro(bairro);
			page.atualizarPesquisa();
			Assert.assertNotEquals(page.resultado(), "0");
			Assert.assertNotEquals(page.resultado(), "19.538.383");
			Assert.assertEquals(page.resultado(), resultado);
			page.detalheEmpresa();
			Assert.assertTrue(page.verificaBairro(bairroRes));
		}
		
	}
		

