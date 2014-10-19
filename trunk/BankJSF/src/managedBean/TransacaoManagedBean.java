package managedBean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.ClienteDAO;
import dao.TransacaoDAO;
import bean.Cliente;
import bean.Transacao;

@SessionScoped
@ManagedBean
public class TransacaoManagedBean {
	
	private Transacao transacao = new Transacao();
	private Cliente remetente;
	private int senhaCartao;
	private String tipoTransacao;
	String msg;
	
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
	public int getSenhaCartao() {
		return senhaCartao;
	}
	public void setSenhaCartao(int senhaCartao) {
		this.senhaCartao = senhaCartao;
	}
	public String getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	
	
	public String transacao(){
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		remetente = (Cliente) session.getAttribute("cliente");
		
		if(tipoTransacao.equals("transfToPoupanca")){
			
			if(remetente.getSenhaCartao() == senhaCartao){	
				remetente.getContaCorrente().setSaldo(remetente.getContaCorrente().getSaldo() - transacao.getValor());
				remetente.getContaPoupanca().setSaldo(remetente.getContaPoupanca().getSaldo() + transacao.getValor());
			
				TransacaoDAO dao = new TransacaoDAO();
				try {
					remetente = dao.transfToPoupanca(remetente);					
					pagina = "sucesso";			
				} catch (SQLException e) {
					pagina = "erro";
				}
			}else{
				pagina = "erro";
				msg="Senha incorreta!";
			}
			
				session.setAttribute("cliente", remetente);					
				return pagina + ".faces?faces-redirect=true";			
		}
		
		else 
		if(tipoTransacao.equals("transfToCc")){
			
			if(remetente.getSenhaCartao() == senhaCartao){		
					remetente.getContaCorrente().setSaldo(remetente.getContaCorrente().getSaldo() + transacao.getValor());
					remetente.getContaPoupanca().setSaldo(remetente.getContaPoupanca().getSaldo() - transacao.getValor());
					
					TransacaoDAO dao = new TransacaoDAO();
					try {	
							remetente = dao.transfToCc(remetente);
							pagina = "sucesso";
					}catch (SQLException e) {
						pagina = "erro";
					}
			}else{
					pagina = "erro";
					msg="Senha incorreta!";
			}
					
			session.setAttribute("cliente", remetente);							
			return pagina + ".faces?faces-redirect=true";			
		}
		
		else
		if(tipoTransacao.equals("transfToTerc")){

			if(remetente.getSenhaCartao() == senhaCartao){	
				TransacaoDAO dao = new TransacaoDAO();
				try {
					remetente = dao.transferencia(remetente, transacao);
					pagina = "sucesso";
				} catch (SQLException e) {
					pagina = "erro";
				}
				
				session.setAttribute("cliente", remetente);
				session.setAttribute("saldototal", remetente.getContaCorrente().getSaldo() + remetente.getContaPoupanca().getSaldo());
			
			}else{
				pagina = "erro";
				msg="Senha incorreta!";
			}			

			
			return pagina + ".faces?faces-redirect=true";
		}
				
		msg="Transacao desconhecida!";
		return pagina + ".faces?faces-redirect=true";	
	}
	
	
	
	
	
	
	
	
	
	
	public String transfToPoupanca(){
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		remetente = (Cliente) session.getAttribute("cliente");
		
		remetente.getContaCorrente().setSaldo(remetente.getContaCorrente().getSaldo() - transacao.getValor());
		remetente.getContaPoupanca().setSaldo(remetente.getContaPoupanca().getSaldo() + transacao.getValor());
		
		TransacaoDAO dao = new TransacaoDAO();
		try {
			remetente = dao.transfToPoupanca(remetente);
		} catch (SQLException e) {
			pagina = "erro";
		}
		
		session.setAttribute("cliente", remetente);
		
		pagina = "Home";
		
		
		return pagina;
	}
	
	public String transfToCc(){
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		remetente = (Cliente) session.getAttribute("cliente");
				
		if(remetente.getSenhaCartao() == senhaCartao){		
				remetente.getContaCorrente().setSaldo(remetente.getContaCorrente().getSaldo() + transacao.getValor());
				remetente.getContaPoupanca().setSaldo(remetente.getContaPoupanca().getSaldo() - transacao.getValor());
				
				TransacaoDAO dao = new TransacaoDAO();
				try {	
						remetente = dao.transfToCc(remetente);
						pagina = "Home";
				}catch (SQLException e) {
					pagina = "erro";
				}
		}else{
				pagina = "erro";
				msg="Senha incorreta!";
			}
				
		session.setAttribute("cliente", remetente);
		
				
		return pagina;
	}
	
	public String transferencia() {
		String pagina = "";

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		remetente = (Cliente) session.getAttribute("cliente");

		

			TransacaoDAO dao = new TransacaoDAO();
			try {
				remetente = dao.transferencia(remetente, transacao);
				pagina = "Home";
			} catch (SQLException e) {
				pagina = "erro";
			}
		

		session.setAttribute("cliente", remetente);
		session.setAttribute("saldototal", remetente.getContaCorrente().getSaldo() + remetente.getContaPoupanca().getSaldo());

		return pagina;
	}

}
