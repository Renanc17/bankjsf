package managedBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.TransacaoDAO;
import bean.Cliente;
import bean.Favorito;
import bean.Transacao;

@SessionScoped
@ManagedBean
public class TransacaoManagedBean {
	
	private Transacao transacao = new Transacao();
	private Cliente remetente;
	private Favorito favorito;
	private int senhaCartao;
	private String tipoTransacao;
	private String msg;
	private Date fromDate = new Date();
	private Date untilDate = new Date();
	private List<Transacao> listaExtrato = new ArrayList<Transacao>();
	
	public Transacao getTransacao() {
		return transacao;
	}
	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}
	public Cliente getRemetente() {
		return remetente;
	}
	public Favorito getFavorito() {
		return favorito;
	}
	public void setFavorito(Favorito favorito) {
		this.favorito = favorito;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Transacao> getListaExtrato(){
		return listaExtrato;
	}
	public void setListaExtrato(List<Transacao> listaExtrato){
		this.listaExtrato = listaExtrato;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getUntilDate() {
		return untilDate;
	}
	public void setUntilDate(Date untilDate) {
		this.untilDate = untilDate;
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
				
				//Setar tudo pro proprio cliente - Preparar gravação de Historico
				transacao.setIdD(remetente.getId());
				transacao.setAgenciaD(remetente.getAgencia());
				transacao.setContaD(remetente.getContaPoupanca().getConta());
				transacao.setIdR(remetente.getId());
				transacao.setAgenciaR(remetente.getAgencia());
				transacao.setContaR(remetente.getContaCorrente().getConta());
				transacao.setData(new java.util.Date());
				transacao.setTipoTransacao(tipoTransacao);
				transacao.setSaldoR(remetente.getContaCorrente().getSaldo());
				transacao.setSaldoD(remetente.getContaPoupanca().getSaldo());
				if(transacao.getDescricao().equals(""))
				transacao.setDescricao("Transf. de Conta Corrente p/ Conta Poup.");
				
				TransacaoDAO dao = new TransacaoDAO();
				try {
					
					remetente = dao.transfToPoupanca(remetente, transacao);					
					pagina = "sucesso";			
				} catch (SQLException e) {
					pagina = "erro";
				}
			}else{
				pagina = "erro";
				setMsg("Senha incorreta!");
			}
			
				session.setAttribute("cliente", remetente);	
				transacao = new Transacao();
				return pagina + ".faces?faces-redirect=true";			
		}		
		else 
		if(tipoTransacao.equals("transfToCc")){
			
			if(remetente.getSenhaCartao() == senhaCartao){	
				
					remetente.getContaCorrente().setSaldo(remetente.getContaCorrente().getSaldo() + transacao.getValor());
					remetente.getContaPoupanca().setSaldo(remetente.getContaPoupanca().getSaldo() - transacao.getValor());
					
					//Setar tudo pro proprio cliente - Preparar gravação de Historico
					transacao.setIdD(remetente.getId());
					transacao.setAgenciaD(remetente.getAgencia());
					transacao.setContaD(remetente.getContaCorrente().getConta());
					transacao.setIdR(remetente.getId());
					transacao.setAgenciaR(remetente.getAgencia());
					transacao.setContaR(remetente.getContaPoupanca().getConta());
					transacao.setData(new java.util.Date());
					transacao.setTipoTransacao(tipoTransacao);
					transacao.setSaldoR(remetente.getContaPoupanca().getSaldo());
					transacao.setSaldoD(remetente.getContaCorrente().getSaldo());
					if(transacao.getDescricao().equals(""))
					transacao.setDescricao("Transf. de Conta Poup. p/ Conta Corrente");
					
					TransacaoDAO dao = new TransacaoDAO();
					try {	
							remetente = dao.transfToCc(remetente, transacao);
							pagina = "sucesso";
					}catch (SQLException e) {
						pagina = "erro";
						setMsg("SQL!");
					}
			}else{
					pagina = "erro";
					setMsg("Senha incorreta!");
			}
					
			session.setAttribute("cliente", remetente);	
			transacao = new Transacao();
			return pagina + ".faces?faces-redirect=true";			
		}		
		else
		if(tipoTransacao.equals("transfToTerc")){

			if(remetente.getSenhaCartao() == senhaCartao){	
								
				transacao.setData(new java.util.Date());
				transacao.setTipoTransacao(tipoTransacao);
				
				TransacaoDAO dao = new TransacaoDAO();
				try {
					remetente = dao.transferencia(remetente, transacao);
					pagina = "sucesso";
				} catch (SQLException e) {
					pagina = "erro";
					setMsg("SQLException!");
				} catch (NullPointerException e) {
					pagina = "erro";
					setMsg("Null Pointer!");
				}
				
				session.setAttribute("cliente", remetente);
				session.setAttribute("saldototal", remetente.getContaCorrente().getSaldo() + remetente.getContaPoupanca().getSaldo());
			
			}else{
				pagina = "erro";
				setMsg("Senha incorreta!");
			}			

			transacao = new Transacao();
			return pagina + ".faces?faces-redirect=true";
		}
				
		setMsg("Transacao desconhecida!");
		return pagina + ".faces?faces-redirect=true";	
	}
	
	
	public String selecionarFavorito(){
		String pagina = "transferenciaTerceiro";
		
		transacao.setAgenciaD(favorito.getAgencia());
		transacao.setContaD(favorito.getContaC());
		
		return pagina + ".faces?faces-redirect=true";
		
	}
	
	
	public String listarExtrato(){
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		remetente = (Cliente) session.getAttribute("cliente");
			
		TransacaoDAO dao = new TransacaoDAO();
		try {
			listaExtrato = dao.historico(remetente.getId(), fromDate, untilDate);
			pagina = "Extrato";
		} catch (SQLException e) {
			pagina = "erro";
			msg += e;
		}
		
		
		return pagina;
	}
	

}
