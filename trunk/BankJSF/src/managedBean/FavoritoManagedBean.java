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
	private int pk;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getPk(){
		return pk;
	}
	public void setPk(int pk){
		this.pk = pk;
	}
	
	
	public String cadastrarFavorito(){
		
		String pagina = "";
		FavoritoDAO dao = new FavoritoDAO();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");
		
		if(user == null)
			return "Login" + ".faces?faces-redirect=true";
		
		try {
			f.setIdCliente(user.getId());
			dao.cadastrarFavorito(f);
			session.setAttribute("listaFavoritos", dao.listarFavoritos(user.getId()));
			pagina = "sucesso";
		}catch(SQLException e){
			msg = "Erro ao cadastrar o favorito";
			pagina = "erro";
		}	
		
		f = new Favorito();
		return pagina + ".faces?faces-redirect=true";
	}
	
	public String listarFavoritos(){
		FavoritoDAO dao = new FavoritoDAO();
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");
		
		if(user == null)
			return "Login" + ".faces?faces-redirect=true";
		
		try{
			//listaFavoritos = dao.listarFavoritos(user.getId());
			session.setAttribute("listaFavoritos", dao.listarFavoritos(user.getId()));
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
		
		if(user == null)
			return "Login" + ".faces?faces-redirect=true";
		
		try{			
			dao.alterarFavorito(f);
			//listaFavoritos = dao.listarFavoritos(user.getId());
			session.setAttribute("listaFavoritos", dao.listarFavoritos(user.getId()));
			pagina = "consultarFavoritos";
		}catch(SQLException e){
			pagina="erro";
		}
		
		f = new Favorito();
		return pagina + ".faces?faces-redirect=true";
	}
	
	public String excluirFavorito(){
		
		String pagina ="";
		FavoritoDAO dao = new FavoritoDAO();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");
		
		if(user == null)
			return "Login" + ".faces?faces-redirect=true";
		
		try{			
			dao.excluirFavorito(pk);
			//listaFavoritos = dao.listarFavoritos(user.getId());
			session.setAttribute("listaFavoritos", dao.listarFavoritos(user.getId()));
			listarFavoritos();
		}catch(SQLException e){
			pagina = "erro";
		}
		
		return pagina;
		
	}

}
