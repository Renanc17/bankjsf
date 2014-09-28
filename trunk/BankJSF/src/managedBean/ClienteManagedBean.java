package managedBean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/*import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;*/

import dao.ClienteDAO;
import bean.Cliente;

@SessionScoped
@ManagedBean
public class ClienteManagedBean {

	private Cliente cliente = new Cliente();

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
				resultado = "logado";
				
				/*
				 * COMO MONTAR UMA SESSION EM JSF ???
				 * FacesContext context = FacesContext.getCurrentInstance();
			     * HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			     */

			}
			else
				resultado = "erro";

		} catch (SQLException e) {
			resultado = "erro";
		}

		return resultado;
	}

}
