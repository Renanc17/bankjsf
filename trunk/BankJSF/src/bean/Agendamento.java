package bean;

import java.util.Date;

public class Agendamento {

	private int id;
	private int idUsuario;
	private Date data; //Data da transação
	private String tipoAgendamento;
	private String descricao;
	private Double valor;
	private int idD;	
	private int contaD; //Numero da conta do destinatario
	private int agenciaD; //Agencia do destinatario
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTipoAgendamento() {
		return tipoAgendamento;
	}
	public void setTipoAgendamento(String tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public int getIdD() {
		return idD;
	}
	public void setIdD(int idD) {
		this.idD = idD;
	}
	public int getContaD() {
		return contaD;
	}
	public void setContaD(int contaD) {
		this.contaD = contaD;
	}
	public int getAgenciaD() {
		return agenciaD;
	}
	public void setAgenciaD(int agenciaD) {
		this.agenciaD = agenciaD;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	 

}
