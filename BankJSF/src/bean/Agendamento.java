package bean;

import java.util.Date;

public class Agendamento {

	private Integer id;
	private Integer idUsuario;
	private Date data; //Data da transação
	private String tipoAgendamento;
	private String descricao;
	private Double valor;
	private Integer idD;	
	private Integer contaD; //Numero da conta do destinatario
	private Integer agenciaD; //Agencia do destinatario
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getIdD() {
		return idD;
	}
	public void setIdD(Integer idD) {
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
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	 

}
