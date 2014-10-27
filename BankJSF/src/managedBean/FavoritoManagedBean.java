package managedBean;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
		
		try {
			f.setIdCliente(user.getId());
			dao.cadastrarFavorito(f);
			session.setAttribute("listaFavoritos", dao.listarFavoritos(user.getId()));
			pagina = "sucesso";
		}catch(SQLException e){
			msg = "Erro ao cadastrar o favorito";
			pagina = "erro";
		}	
		
		return pagina + ".faces?faces-redirect=true";
	}
	
	public String listarFavoritos(){
		FavoritoDAO dao = new FavoritoDAO();
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");
		
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
		
		try{			
			dao.alterarFavorito(f);
			session.setAttribute("listaFavoritos", dao.listarFavoritos(user.getId()));
			pagina = "consultarFavoritos";
		}catch(SQLException e){
			pagina="erro";
		}
		
		return pagina + ".faces?faces-redirect=true";
	}
	
	public String excluirFavorito(){
		
		String pagina ="";
		FavoritoDAO dao = new FavoritoDAO();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");
		
		try{			
			dao.excluirFavorito(pk);
			session.setAttribute("listaFavoritos", dao.listarFavoritos(user.getId()));
			listarFavoritos();
		}catch(SQLException e){
			pagina = "erro";
		}
		
		return pagina;
		
	}
	
	public void verificarExistencia(FacesContext context,
									UIComponent componentToValidate,
									Favorito f)
									throws ValidatorException{
		FavoritoDAO dao = new FavoritoDAO();
		
		ResourceBundle bundle = ResourceBundle.getBundle("language_" + FacesContext.getCurrentInstance().getViewRoot().getLocale());
		String mensagem = bundle.getString("favoritoExistente");
		
		try {
			if(dao.verificaExistencia(f)){
				FacesMessage message = new FacesMessage(mensagem);
				throw new ValidatorException(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	

}
