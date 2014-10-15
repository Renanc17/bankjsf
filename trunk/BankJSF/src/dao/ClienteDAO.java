package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Cliente;
import bean.ContaCorrente;
import bean.ContaPoupanca;
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
				cl = new Cliente();
				ContaCorrente cc = new ContaCorrente();
				ContaPoupanca cp = new ContaPoupanca();
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
		}

		return cl;

	}
	

	public Cliente getClienteById(Integer id) throws SQLException {

		Cliente cl = null;

		Connection conn = ConnectionFactory.getConnection();

		String sql = "SELECT * FROM cliente where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			cl = new Cliente();
			ContaCorrente cc = new ContaCorrente();
			ContaPoupanca cp = new ContaPoupanca();
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
		}
		return cl;

	}

}