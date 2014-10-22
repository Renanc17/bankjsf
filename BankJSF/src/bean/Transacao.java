package bean;

import java.util.Date;

public class Transacao {

	private Integer id;
	private Date data; //Data da transação
	private String tipoTransacao;
	private String descricao;
	private Double valor;
	private Integer idR;	
	private Integer contaR; //Numero da conta do remetente
	private Integer agenciaR; //Agencia do remetente
	private Integer idD;	
	private Integer contaD; //Numero da conta do destinatario
	private Integer agenciaD; //Agencia do destinatario
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getContaR() {
		return contaR;
	}
	public void setContaR(Integer contaR) {
		this.contaR = contaR;
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
	public String getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	public Integer getAgenciaR() {
		return agenciaR;
	}
	public void setAgenciaR(Integer agenciar) {
		this.agenciaR = agenciar;
	}
	public Integer getIdR() {
		return idR;
	}
	public void setIdR(Integer idR) {
		this.idR = idR;
	}
	public Integer getIdD() {
		return idD;
	}
	public void setIdD(Integer idD) {
		this.idD = idD;
	}
	
	
}
