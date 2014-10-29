package managedBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.AgendamentoDAO;
import bean.Agendamento;
import bean.Cliente;


@SessionScoped
@ManagedBean
public class AgendamentoManagedBean {
	
	private Agendamento a = new Agendamento();
	private Cliente usuario = new Cliente();
	private List<Agendamento> listaAgenda = new ArrayList<Agendamento>();
	private String senhaCartao;
	private String codBarras1;
	private String codBarras2;
	private String codBarras3;
	private String codBarras4;
	private String msg;
	
	
	public Agendamento getA() {
		return a;
	}
	public void setA(Agendamento a) {
		this.a = a;
	}
	public Cliente getUsuario() {
		return usuario;
	}
	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}
	public List<Agendamento> getListaAgenda() {
		return listaAgenda;
	}
	public void setListaAgenda(List<Agendamento> listaAgenda) {
		this.listaAgenda = listaAgenda;
	}
	public String getSenhaCartao() {
		return senhaCartao;
	}
	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCodBarras1() {
		return codBarras1;
	}
	public void setCodBarras1(String codBarras1) {
		this.codBarras1 = codBarras1;
	}
	public String getCodBarras2() {
		return codBarras2;
	}
	public void setCodBarras2(String codBarras2) {
		this.codBarras2 = codBarras2;
	}
	public String getCodBarras3() {
		return codBarras3;
	}
	public void setCodBarras3(String codBarras3) {
		this.codBarras3 = codBarras3;
	}
	public String getCodBarras4() {
		return codBarras4;
	}
	public void setCodBarras4(String codBarras4) {
		this.codBarras4 = codBarras4;
	}
	
	public String agendar(){
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		usuario = (Cliente) session.getAttribute("cliente");
		
		if(usuario.getSenhaCartao().equals(senhaCartao)){	
		
			AgendamentoDAO dao = new AgendamentoDAO();
			
			a.setIdUsuario(usuario.getId());
			
			if(a.getTipoAgendamento().equals("Payment")) //Tem que setar o tipo agendamento
				a.setCodBarras(codBarras1+codBarras2+codBarras3+codBarras4);
			
			try {			
				dao.setarAgendamento(a);
				listaAgenda = dao.pegarAgenda(usuario.getId());
				session.setAttribute("listaAgenda", listaAgenda);
				pagina = "sucesso";			
			} catch (SQLException e) {
				pagina = "erro";
				msg = e.getMessage();
			}
		}else{
			pagina = "erro";
			msg = "Senha incorreta";
		}
		
		a = new Agendamento();
		return pagina + ".faces?faces-redirect=true";
	}
	
	/*public String alterar(){
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		usuario = (Cliente) session.getAttribute("cliente");
		
		AgendamentoDAO dao = new AgendamentoDAO();
		
		a.setIdUsuario(usuario.getId());	
		
		try{			
			if(a.getContaD()!= 0 && a.getAgenciaD() != 0){ //Só seta ID quando já tiver Ag e CC
				ClienteDAO tempDAO = new ClienteDAO();
				Cliente c= tempDAO.getCliente(a.getContaD(), a.getAgenciaD());
				a.setIdD(c.getId());
			}
			
			dao.alterarAgendamento(a);
			pagina = "sucesso";
		}catch(SQLException e){
			pagina = "erro";
			msg = e.getMessage();
		}
		
		a = new Agendamento();
		return pagina + ".faces?faces-redirect=true";
	}*/
	
	public String listarAgenda(){
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		usuario = (Cliente) session.getAttribute("cliente");
			
		AgendamentoDAO dao = new AgendamentoDAO();
		
		try {
			listaAgenda = dao.pegarAgenda(usuario.getId());
			session.setAttribute("listaAgenda", listaAgenda);
			pagina = "consultarAgendamentos";
		} catch (SQLException e) {
			pagina = "erro";
			msg += e;
		}
		
		return pagina + ".faces?faces-redirect=true";
	}
	
	public String excluir(){
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		usuario = (Cliente) session.getAttribute("cliente");
		
		AgendamentoDAO dao = new AgendamentoDAO();
		
		try{
			dao.excluirAgendamento(a.getId());
			listarAgenda();
		}catch(SQLException e){
			pagina="erro";
			msg = e.getMessage();
		}
		
		return pagina + ".faces?faces-redirect=true";
	}
	
	public String alterarAgendamento(){
		
		String pagina = "Ag_" + a.getTipoAgendamento();		
		
		return pagina + ".faces?faces-redirect=true";
		
	}
	

}
