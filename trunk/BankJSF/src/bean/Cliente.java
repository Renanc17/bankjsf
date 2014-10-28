package bean;

import java.sql.Date;


public class Cliente {
	
	private int id;
	private String nome;
	private Long cpf;
	private Integer agencia;
	private String senha;
	private String tipoCliente;
	private ContaCorrente ContaCorrente = new ContaCorrente(); 
	private ContaPoupanca ContaPoupanca = new ContaPoupanca();	
	private Date data;
	private String senhaCartao;
	
	
	public ContaCorrente getContaCorrente() {
		return ContaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		ContaCorrente = contaCorrente;
	}

	public ContaPoupanca getContaPoupanca() {
		return ContaPoupanca;
	}

	public void setContaPoupanca(ContaPoupanca contaPoupanca) {
		ContaPoupanca = contaPoupanca;
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
