package managedBean;

import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.ClienteDAO;
import dao.TransacaoDAO;
import bean.Cliente;

@SessionScoped
@ManagedBean
public class ClienteManagedBean {

	private Cliente cliente = new Cliente();
	private Double saldoTotal;
	private String msg = "";
	
	ResourceBundle bundle = ResourceBundle.getBundle("language_" + FacesContext.getCurrentInstance().getViewRoot().getLocale());
	String senhaErrada = bundle.getString("senhaErrada");


	public Double getSaldoTotal() {
		return saldoTotal;
	}
	
	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String logar() {
		String resultado = "";
		

		ClienteDAO dao = new ClienteDAO();
		boolean user;
		try {
			user = dao.login(cliente);
			if (user == true){
				resultado = "Login_Confirm";
			}
			else{
				resultado = "erro";
				msg = "Conta não encontrada.";
			}

		} catch (SQLException e) {
			resultado = "erro";
			msg = " Erro de Banco de dados.";
		}

		return resultado + ".faces?faces-redirect=true";
	}
	
	public String passConfirm(){

		String resultado = "";
		msg = "";
		Date fromDate = new Date();
		Date untilDate = new Date();
			
		ClienteDAO dao = new ClienteDAO();
		Cliente user = null;
		TransacaoDAO tdao = new TransacaoDAO();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		
				
		try{
			user = dao.passConfirm(cliente);
			if(user != null){
				cliente = user;
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				session.setAttribute("cliente", cliente);
				session.setAttribute("saldototal", cliente.getContaCorrente().getSaldo()+cliente.getContaPoupanca().getSaldo());
				session.setAttribute("listaUltimosLanc", tdao.ultimosLanc(cliente.getId()));
				session.setAttribute("listaExtrato", tdao.historico(cliente.getId(), fromDate, untilDate));
				resultado = "Home";
			}
			
			else{
				resultado = "erro";
				msg = senhaErrada;
			}
		}catch(SQLException e){
			resultado = "erro";
			msg += "Erro de Banco de dados.";
		}
		
		return resultado + ".faces?faces-redirect=true";		
		
	}
		
	
	public String logout(){
		String pagina = "Login";
		cliente = null;
		try{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		pagina = "Login";
		}finally{
		pagina ="Login";
		}
		return pagina + ".faces?faces-redirect=true";
	}


}
