package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Cliente;
import bean.Conta;
import enums.TipoConta;
import factory.ConnectionFactory;

public class ClienteDAO {

	public boolean login(Cliente c) throws SQLException {

		boolean cl = false;

		Connection conn = ConnectionFactory.getConnection();

		String sql = "SELECT * FROM cliente where contac=? and agencia=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, c.getContaCorrente().getConta());
			stmt.setInt(2, c.getAgencia());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				cl = true;
		}
		conn.close();
		return cl;

	}
	
	public Cliente passConfirm(Cliente c) throws SQLException {

		Cliente cl = null;

		Connection conn = ConnectionFactory.getConnection();

		String sql = "SELECT * FROM cliente where contac=? and senha=? and agencia=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setLong(1, c.getContaCorrente().getConta());
			stmt.setString(2, c.getSenha());
			stmt.setInt(3, c.getAgencia());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				cl = createCliente(rs);
		}
		
		conn.close();
		return cl;

	}
	

	public Cliente getClienteById(int id) throws SQLException {

		Cliente cl = null;

		Connection conn = ConnectionFactory.getConnection();

		String sql = "SELECT * FROM cliente where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			cl = createCliente(rs);
		}
		
		conn.close();
		return cl;

	}
	
	public Cliente getCliente (int contac, int agencia) throws SQLException {

		Cliente cl = null;

		Connection conn = ConnectionFactory.getConnection();

		String sql = "SELECT * FROM cliente where contac=? and agencia=?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, contac);
		stmt.setInt(2, agencia);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			cl = createCliente(rs);
		}
		
		conn.close();
		return cl;

	}

	private Cliente createCliente(ResultSet rs) throws SQLException {
		Cliente cl = new Cliente();
		Conta cc = new Conta(TipoConta.CONTA_CORRENTE);
		Conta cp = new Conta(TipoConta.CONTA_POUPANCA);				
		cl.setId(rs.getInt("id"));
		cl.setNome(rs.getString("nome"));
		cl.setCpf(rs.getLong("cpf"));
		cl.setAgencia(rs.getInt("agencia"));
		cl.setSenha(rs.getString("senha"));
		cc.setConta(rs.getInt("contac"));
		cc.setSaldo(rs.getDouble("saldoc"));
		cl.setContaCorrente(cc);
		cp.setConta(rs.getInt("contap"));
		cp.setSaldo(rs.getDouble("saldop"));
		cl.setContaPoupanca(cp);
		cl.setSenhaCartao(rs.getString("senhaCartao"));
		return cl;
	}
	

}