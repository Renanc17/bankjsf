package bean;

import enums.TipoConta;

public class Conta {
	
	public Conta(TipoConta tipo){
		this.tipo = tipo;
	}
	
	private Integer conta;
	private Double saldo;
	private TipoConta tipo;
	
	public Integer getConta() {
		return conta;
	}
	public void setConta(Integer conta) {
		this.conta = conta;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public TipoConta getTipo() {
		return tipo;
	}
	
	
	

}
