package managedBean;

import java.sql.SQLException;
import javax.faces.bean.ManagedBean;

import dao.ClienteDAO;
import bean.Cliente;

@ManagedBean
public class ClienteManagedBean {

	private Cliente cliente = new Cliente();

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/*public String logar() {
		String resultado = "";

		ClienteDAO dao = new ClienteDAO();

		try {

			if (dao.login(cliente))
				resultado = "logado";
			else
				resultado = "erro";

		} catch (SQLException e) {
			resultado = "erro";
		}

		return resultado;
	}*/ 	//TESTE DE LOGIN

}
