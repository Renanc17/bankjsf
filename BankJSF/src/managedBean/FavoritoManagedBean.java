package managedBean;

import java.sql.SQLException;
import java.util.List;

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
	private List<Favorito> listaFavoritos;
	private String msg = "";
	
	
	public Favorito getF() {
		return f;
	}
	public void setF(Favorito f) {
		this.f = f;
	}
	public Cliente getUser() {
		return user;
	}
	public void setUser(Cliente user) {
		this.user = user;
	}
	public List<Favorito> getListaFavoritos() {
		return listaFavoritos;
	}
	public void setListaFavoritos(List<Favorito> listaFavoritos) {
		this.listaFavoritos = listaFavoritos;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	
	public String listarFavoritos(){
		FavoritoDAO dao = new FavoritoDAO();
		String pagina = "Home";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");
		
		try{
			listaFavoritos = dao.listarFavoritos(user.getId());
			pagina = "consultarFavoritos";
		}catch(SQLException e){
			pagina = "erro";
		}

		return pagina + ".faces?faces-redirect=true";
	}
	
	public String alterarFavorito(){
		
		String pagina = "";
		FavoritoDAO dao = new FavoritoDAO();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");
		
		try{
			dao.cadastrarFavorito(f);
			
		}catch(SQLException e){
			pagina="erro";
		}
		
		return pagina + ".faces?faces-redirect=true";
	}
	

}
