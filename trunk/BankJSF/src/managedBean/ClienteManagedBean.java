package managedBean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.ClienteDAO;
import bean.Cliente;

@SessionScoped
@ManagedBean
public class ClienteManagedBean {

	private Cliente cliente = new Cliente();
	private String msg = "";

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
		Cliente user = new Cliente();

		try {
			user = dao.login(cliente);
			if (user != null){
				cliente = user;
				resultado = "logado";
			}
			else{
				resultado = "erro";
				msg += " Usuario inexistente.";
			}

		} catch (SQLException e) {
			resultado = "erro";
			msg += " Erro de Banco de dados.";
		}

		return resultado;
	}

}
