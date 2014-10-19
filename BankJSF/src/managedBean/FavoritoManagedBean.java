package managedBean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.FavoritoDAO;
import bean.Cliente;
import bean.Favorito;

@SessionScoped
@ManagedBean
public class FavoritoManagedBean {
	
	private Favorito f = new Favorito();
	private Cliente user = new Cliente();
	private String msg = "";
	
	public Favorito getF() {
		return f;
	}
	public void setF(Favorito f) {
		this.f = f;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Cliente getC() {
		return user;
	}
	public void setC(Cliente user) {
		this.user = user;
	}
	
	public String cadastrarFavorito(){
		
		String pagina = "";
		FavoritoDAO dao = new FavoritoDAO();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");
		
		try {
			f.setIdCliente(user.getId());
			dao.cadastrarFavorito(f);
			pagina = "sucesso";
		}catch(SQLException e){
			msg = "Erro ao cadastrar o favorito";
			pagina = "erro";
		}	
		
		return pagina + ".faces?faces-redirect=true";
	}
	

}
