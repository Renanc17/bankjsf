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
	private Integer contaD; //Numero da conta do destinatario
	private Integer agenciaD; //Agencia do destinatario
	private String codBarras;
	
	
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
	public Integer getContaD() {
		return contaD;
	}
	public void setContaD(Integer contaD) {
		this.contaD = contaD;
	}
	public Integer getAgenciaD() {
		return agenciaD;
	}
	public void setAgenciaD(Integer agenciaD) {
		this.agenciaD = agenciaD;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
	 

}
