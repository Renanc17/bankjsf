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
public class LoginController {

	public String logout(){
		String pagina = "Login";
		cliente = null;
		try{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}


}
