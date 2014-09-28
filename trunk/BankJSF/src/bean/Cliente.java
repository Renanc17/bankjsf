package bean;

import java.util.Calendar;


public class Cliente {
	
	private Integer id;
	private String nome;
	private Long cpf;
	private Integer agencia;
	private String senha;
	private String tipoCliente;
	private ContaCorrente ContaCorrente = new ContaCorrente(); 
	private ContaPoupanca ContaPoupanca = new ContaPoupanca();	
	private Calendar data;
	
	
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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	

}
