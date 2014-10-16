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
	private String senhaCartao;
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
	public String getSenhaCartao() {
		return senhaCartao;
	}
	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}
	public String getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
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
	
	public String transfToCcOld(){
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		remetente = (Cliente) session.getAttribute("cliente");
		
		remetente.getContaCorrente().setSaldo(remetente.getContaCorrente().getSaldo() + transacao.getValor());
		remetente.getContaPoupanca().setSaldo(remetente.getContaPoupanca().getSaldo() - transacao.getValor());
		
		TransacaoDAO dao = new TransacaoDAO();
		try {
			remetente = dao.transfToCc(remetente);
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
				
		if(remetente.getSenhaCartao().equals(senhaCartao)){		
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

	public String transacaoConfirm(){
		if(tipoTransacao.equals("TransfToCc"))
			return "TransToCc";
		

		return "";
	}
	


}
