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

	public Cliente login(Cliente c) throws SQLException {

		Boolean resultado = false;
		Cliente cl = null;

		Connection conn = ConnectionFactory.getConnection();

		String sql = "SELECT * FROM cliente where cpf=? and senha=? and agencia=?";
		for (int i = 0; i < 2; i++) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setLong(1, c.getCpf());
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
				resultado = true;

			}
			if (resultado == false) {
				sql = "SELECT * FROM cliente where contac=? and senha=? and agencia=?";
			}

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