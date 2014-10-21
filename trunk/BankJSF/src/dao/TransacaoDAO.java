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
	
	public Cliente transferencia(Cliente c, Transacao t) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		ClienteDAO dao = new ClienteDAO();
		Cliente dest = dao.getCliente(t.getContaD(), t.getAgenciaD());
		Double saldoD = dest.getContaCorrente().getSaldo();
		Double saldoR = c.getContaCorrente().getSaldo();
				
		saldoD += t.getValor();
		saldoR -= t.getValor();
		
		String sql = "UPDATE cliente set saldoc = ? where contac = ? and agencia = ?";
		
		for (int i = 0; i < 2; i++) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (i == 0) {
				stmt.setDouble(1, saldoD);
				stmt.setInt(2, t.getContaD());
				stmt.setInt(3, t.getAgenciaD());
				stmt.executeUpdate();
			} else {
				stmt.setDouble(1, saldoR);
				stmt.setInt(2, c.getContaCorrente().getConta());
				stmt.setInt(3, c.getAgencia());
				stmt.executeUpdate();
			}
		}
		
		
		c = dao.getClienteById(c.getId());
		
		return c;
		
	}

}
