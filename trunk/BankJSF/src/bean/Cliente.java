package bean;


public class Cliente {
	
	private Integer id;
	private String nome;
	private Long cpf;
	private Integer agencia;
	private String senha;
	private String tipoConta;
	private ContaCorrente contac;
	private ContaPoupanca contap;
	private Calendar data;
	
	
	public ContaCorrente getContac() {
		return contac;
	}
	public void setContac(ContaCorrente contac) {
		this.contac = contac;
	}
	public ContaPoupanca getContap() {
		return contap;
	}
	public void setContap(ContaPoupanca contap) {
		this.contap = contap;
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
