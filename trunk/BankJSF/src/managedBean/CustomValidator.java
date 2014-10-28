package managedBean;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import bean.Cliente;
import dao.ClienteDAO;
import dao.FavoritoDAO;

@SessionScoped
@ManagedBean (name = "validator")
public class CustomValidator{
	
	Cliente user = new Cliente();
	private int tempAg = 0;
	
	//Variaveis de Mensagens Internacionalizadas
	
	ResourceBundle bundle = ResourceBundle.getBundle("language_" + FacesContext.getCurrentInstance().getViewRoot().getLocale());
	String favoritoExistente = "";
	String alteracaoInvalida = "";
	String terceiroNaoEncontrado = "";
	String transferenciaInvalida = "";
	String quatroDigitos = bundle.getString("quatroDigitos");
	

	public int getTempAg() {
		return tempAg;
	}

	public void setTempAg(int tempAg) {
		this.tempAg = tempAg;
	}

	
	public void verificarExistenciaFavorito(FacesContext context, UIComponent componentToValidate, Object value)
					throws ValidatorException {
		
		FavoritoDAO dao = new FavoritoDAO();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");
		
		int contaC = (int) value;

		try {
			if (dao.verificaExistencia(tempAg, contaC, user.getId())) {
				favoritoExistente = bundle.getString("favoritoExistente");
				alteracaoInvalida = bundle.getString("alteracaoInvalida");
				FacesMessage message = new FacesMessage(favoritoExistente + " " + alteracaoInvalida);
				throw new ValidatorException(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void verificarTerceiro(FacesContext context, UIComponent componentToValidate, Object value)
					throws ValidatorException{
		ClienteDAO dao = new ClienteDAO();
		
		int contaDest = (int) value;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Cliente) session.getAttribute("cliente");		
		
		
		try{
			//Verifica se o terceiro Existe.
			if(dao.getCliente(contaDest, tempAg) == null){ 
				terceiroNaoEncontrado = bundle.getString("terceiroNaoEncontrado");
				FacesMessage message = new FacesMessage(terceiroNaoEncontrado);
				throw new ValidatorException(message);
			}
			//Verifica se o Destinatário setado é a conta corrente ou poupança do próprio usuário ou não.
			if((tempAg == user.getAgencia() && contaDest == user.getContaCorrente().getConta()) ||
				tempAg == user.getAgencia() && contaDest == user.getContaPoupanca().getConta()){ 
				transferenciaInvalida = bundle.getString("transferenciaInvalida");
				FacesMessage message = new FacesMessage(transferenciaInvalida);
				throw new ValidatorException(message);
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void verificadorGuardarAg(FacesContext context, UIComponent componentToValidate, Object value)
									throws ValidatorException {
		tempAg = (int) value;

	}
	
	public void verificador4Digitos(FacesContext context, UIComponent componentToValidate, Object value)
									throws ValidatorException{
		
		
		if(value.toString().length() != 4){
			FacesMessage message = new FacesMessage(quatroDigitos);
			throw new ValidatorException(message);
		}
	}	

}
