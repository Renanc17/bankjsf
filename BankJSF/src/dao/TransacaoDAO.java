package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Cliente;
import bean.Transacao;
import factory.ConnectionFactory;

public class TransacaoDAO {
	
public Cliente transfToPoupanca(Cliente c) throws SQLException{		
		
		Connection conn = ConnectionFactory.getConnection();
		String sql = "UPDATE cliente SET saldoc=?, saldop=? where id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setDouble(1, c.getContaCorrente().getSaldo());
		stmt.setDouble(2, c.getContaPoupanca().getSaldo());
		stmt.setInt(3, c.getId());
		
		stmt.executeUpdate();
		
		ClienteDAO dao = new ClienteDAO();
		c = dao.getClienteById(c.getId());
		
		
		return c;
		
		
	}

	public Cliente transfToCc(Cliente c) throws SQLException{	
	
	Connection conn = ConnectionFactory.getConnection();
	String sql = "UPDATE cliente SET saldoc=?, saldop=? where id=?";
	
	PreparedStatement stmt = conn.prepareStatement(sql);
	
	stmt.setDouble(1, c.getContaCorrente().getSaldo());
	stmt.setDouble(2, c.getContaPoupanca().getSaldo());
	stmt.setInt(3, c.getId());
	
	stmt.executeUpdate();
	
	ClienteDAO dao = new ClienteDAO();
	c = dao.getClienteById(c.getId());
	
	
	return c;
	
	
	}
	
	public void transferencia(Transacao t, Double saldoR) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		Double saldoD = null;
		
		String sql = "SELECT * FROM cliente where contac=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, t.getContaD());
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			saldoD = rs.getDouble("saldoc");
		}
		
		saldoD += t.getValor();
		saldoR -= t.getValor();
		
		String sql2 = "UPDATE cliente set saldoc = ? where contac = ?";
		
		for (int i = 0; i < 2; i++) {
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			if (i == 0) {
				stmt2.setDouble(1, saldoD);
				stmt2.setInt(2, t.getContaD());
				stmt2.executeUpdate();
			} else {
				stmt2.setDouble(1, saldoR);
				stmt2.setInt(2, t.getContaR());
				stmt2.executeUpdate();
			}
		}
		
	}

}
