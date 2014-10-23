package managedBean;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.ClienteDAO;
import bean.Cliente;

@SessionScoped
@ManagedBean
public class ClienteManagedBean {

	private Cliente cliente = new Cliente();
	private Double saldoTotal;
	private String msg = "";

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
			
		ClienteDAO dao = new ClienteDAO();
		Cliente user = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
				
		try{
			user = dao.passConfirm(cliente);
			if(user != null){
				cliente = user;
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				session.setAttribute("cliente", cliente);
				saldoTotal();
				resultado = "Home";
			}
			
			else{
				resultado = "erro";
				msg += "Senha incorreta.";
			}
		}catch(SQLException e){
			resultado = "erro";
			msg += "Erro de Banco de dados.";
		}
		
		return resultado + ".faces?faces-redirect=true";		
		
	}
		
	public void saldoTotal(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Cliente clienteAtualizado = (Cliente) session.getAttribute("cliente");
		
		session.setAttribute("saldototal", clienteAtualizado.getContaCorrente().getSaldo()+clienteAtualizado.getContaPoupanca().getSaldo());
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
