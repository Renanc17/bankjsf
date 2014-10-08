package managedBean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import bean.Cliente;
import bean.Transacao;

@SessionScoped
@ManagedBean
public class TransacaoManagedBean {
	
	private Transacao transacao = new Transacao();
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
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		remetente = (Cliente) session.getAttribute("cliente");
		
		remetente.getContaCorrente().setSaldo(remetente.getContaCorrente().getSaldo() - transacao.getValor());
		remetente.getContaPoupanca().setSaldo(remetente.getContaPoupanca().getSaldo() + transacao.getValor());
		
		session.setAttribute("cliente", remetente);
		
		pagina = "Home";
		
		
		return pagina;
	}

}
