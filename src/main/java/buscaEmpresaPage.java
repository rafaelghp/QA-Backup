import org.openqa.selenium.WebElement;

import Teste.core.DSL;

public class buscaEmpresaPage {
	
	private DSL dsl;
	public buscaEmpresaPage() {
		dsl= new DSL();
	}
	public void setEstado(String Estado) throws InterruptedException {
		dsl.escreveEnterId("busca-filtro-estados", Estado);
		
	}
	public void setCidade(String Cidade) throws InterruptedException {
		dsl.escreveEnterId("busca-filtro-cidade", Cidade);
		
	}
	public void setBairro(String bairro) throws InterruptedException{
		dsl.escreveEnterId("busca-filtro-bairro", bairro);
	}
	public void setLogradouro(String logradouro) throws InterruptedException {
		dsl.escreveId("busca-filtro-logradouro", logradouro);
	}
	public void setCep(String cep) throws InterruptedException{
		dsl.escreveId("busca-filtro-cep", cep);
	}
	public void setNumDe(String numero)throws InterruptedException{
		dsl.escreveId("busca-filtro-numero-de", numero);
	}
	public void setNumAte(String numero)throws InterruptedException{
		dsl.escreveId("busca-filtro-numero-ate", numero);
	}
	public void setCnae(String cnae) throws InterruptedException{
		dsl.escreveEnterId("busca-filtro-cnae", cnae);
	}
	public void atualizarPesquisa() {
		dsl.clicaId("btn-filtrar");
	}
	public void regiao() {
		dsl.clicaId("filtro-regiao");
	}
	public void setor() {
		dsl.clicaId("filtro-setor");
	}
	public String resultado() {
		return dsl.pegaValor("qtd-registros");
	}
	public boolean verificaEstado(String estado) {
       WebElement element = dsl.retornaXpath("//*[@id=\"main-table\"]/tbody/tr[1]/td[3]/div/div[3]");
       if(element != null) {
    	   String[] elementArray = element.getText().split(", ");
    	   return elementArray[1].toLowerCase().contains(estado.toLowerCase());
       }
	   return false;
	}
	public boolean verificaCidade(String cidade) {
	       WebElement element = dsl.retornaXpath("//*[@id=\"main-table\"]/tbody/tr[1]/td[3]/div/div[3]");
	       if(element != null) {
	    	   String[] elementArray = element.getText().split(", ");
	    	   return elementArray[0].toLowerCase().contains(cidade.toLowerCase());
	       }
		   return false;
		}
	public void detalheEmpresa() {
		dsl.clicaXpath("//*[@id=\"main-table\"]/tbody/tr[1]/td[7]/div/div/button");
	}
	public boolean verificalog(String logradouro) {
		WebElement element = dsl.retornaXpath("//*[@id=\"collapse-resumo\"]/div/div[2]/div[3]/span");
		if(element !=null) {
				String [] elementArray = element.getText().split(", ");
				return elementArray[0].toLowerCase().contains(logradouro.toLowerCase());	
		}
		return false;
	}
	public boolean verificaCep(String cep) {
		WebElement element = dsl.retornaXpath("//*[@id=\"collapse-resumo\"]/div/div[2]/div[3]/span");
		if(element !=null) {
				String [] elementArray = element.getText().split(", ");
				return elementArray[3].toLowerCase().contains(cep.toLowerCase());	
		}
		return false;
	}	
	public boolean verificaCnae(String cnae) {
		WebElement element = dsl.retornaXpath("//*[@id=\"main-table\"]/tbody/tr[1]/td[5]/div[1]");
		if(element !=null) {
				String elementTx = element.getText();
				return elementTx.toLowerCase().contains(cnae.toLowerCase());	
		}
		return false;
	}	
	
	
public boolean verificaBairro(String bairro) {
	WebElement element = dsl.retornaXpath("//*[@id=\"main-table\"]/tbody/tr[1]/td[5]/div[1]");
	if(element !=null) {
			String elementTx = element.getText();
			return elementTx.toLowerCase().contains(bairro.toLowerCase());	
	}
	return false;
}
	
	
}
