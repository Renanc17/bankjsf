package bean;

import java.util.Date;

public class Transacao {

	private int id;
	private Date data; //Data da transação
	private String tipoTransacao;
	private String descricao;
	private Double valor;
	private int idR;	
	private int contaR; //Numero da conta do remetente
	private int agenciaR; //Agencia do remetente
	private int idD;	
	private int contaD; //Numero da conta do destinatario
	private int agenciaD; //Agencia do destinatario
	private Double saldoR;
	private Double saldoD;
	private Double saldo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getContaR() {
		return contaR;
	}
	public void setContaR(int contaR) {
		this.contaR = contaR;
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
	public String getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	public int getAgenciaR() {
		return agenciaR;
	}
	public void setAgenciaR(int agenciar) {
		this.agenciaR = agenciar;
	}
	public int getIdR() {
		return idR;
	}
	public void setIdR(int idR) {
		this.idR = idR;
	}
	public int getIdD() {
		return idD;
	}
	public void setIdD(int idD) {
		this.idD = idD;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getSaldoR() {
		return saldoR;
	}
	public void setSaldoR(Double saldoR) {
		this.saldoR = saldoR;
	}
	public Double getSaldoD() {
		return saldoD;
	}
	public void setSaldoD(Double saldoD) {
		this.saldoD = saldoD;
	}
	
	
}
