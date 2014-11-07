package bean;

import java.sql.Date;

import enums.TipoConta;


public class Cliente {
	
	private int id;
	private String nome;
	private Long cpf;
	private Integer agencia;
	private String senha;
	private String tipoCliente;
	private Conta contaCorrente = new Conta(TipoConta.CONTA_CORRENTE); 
	private Conta contaPoupanca = new Conta(TipoConta.CONTA_POUPANCA); 
	private Date data;
	private String senhaCartao;
	
	

	public Conta getContaCorrente() {
		return contaCorrente;
	}
	public Conta getContaPoupanca() {
		return contaPoupanca;
	}
	public void setContaCorrente(Conta contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	public void setContaPoupanca(Conta contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}
	
	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaCartao() {
		return senhaCartao;
	}

	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}
	

}
