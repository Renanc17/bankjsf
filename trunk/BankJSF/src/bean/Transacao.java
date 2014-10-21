package bean;

import java.util.Date;

public class Transacao {

	private Integer id;
	private String descricao;
	private Double valor;
	private Date data; //Data da transação
	private Integer contaR; //Numero da conta do remetente
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
	public Date getDataR() {
		return data;
	}
	public void setDataR(Date dataR) {
		this.data = dataR;
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
	
	
}
