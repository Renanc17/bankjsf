package managedBean;

import java.io.IOException;
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

	public void logout() throws IOException{
		try{
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesContext.getCurrentInstance().getExternalContext().redirect("Login.jsf");
		}finally{}
	}


}
