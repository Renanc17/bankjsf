package bean;

import java.util.Calendar;

public class Transacao {

	private Integer id;
	private String descricao;
	private Double valor;
	private Calendar dataR; //Data de realização da transação
	private Calendar dataP; //Data de pagamento da transação
	private Integer contaR; //Numero da conta do remetente
	private Integer contaD; //Numero da conta do destinatario
	
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
	public Calendar getDataR() {
		return dataR;
	}
	public void setDataR(Calendar dataR) {
		this.dataR = dataR;
	}
	public Calendar getDataP() {
		return dataP;
	}
	public void setDataP(Calendar dataP) {
		this.dataP = dataP;
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
	
}
