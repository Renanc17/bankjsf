package managedBean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import bean.Cliente;
import bean.Transacao;

@SessionScoped
@ManagedBean
public class TransacaoManagedBean {
	
	private Transacao transacao;
	private Cliente remetente;
	
	public Transacao getTransacao() {
		return transacao;
	}
	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}
	public Cliente getRemetente() {
		return remetente;
	}
	public void setRemetente(Cliente remetente) {
		this.remetente = remetente;
	}
	
	public String transferir(){
		String pagina = "";
		
		/*
		 * Metodo de tranferencia.
		 * Criar transacaoDAO.
		 * Criar tabela transacao.
		 */
		
		return pagina;
	}

}
