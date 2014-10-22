package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		
		conn.close();
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
	
	conn.close();
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
		
		conn.close();
		return c;
		
	}
	
	public void gravarHist(Transacao t) throws SQLException{
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT into historico(data, tipoTransacao, descricao, valor, idR, contaR, agenciaR, idD, contaD, agenciaD) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setDate(1, (Date) t.getData());
		stmt.setString(2, t.getTipoTransacao());
		stmt.setString(3, t.getDescricao());
		stmt.setDouble(4, t.getValor());
		stmt.setInt(5, t.getIdR());
		stmt.setInt(6, t.getContaR());
		stmt.setInt(7, t.getAgenciaR());
		stmt.setInt(8, t.getIdD());
		stmt.setInt(9, t.getContaD());
		stmt.setInt(10, t.getAgenciaD());
		
		stmt.executeUpdate();
		
		conn.close();
		
		
	}
	
	public List<Transacao> historico(Integer id) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		List<Transacao> lista = new ArrayList<Transacao>();
		
		String sql = "SELECT * FROM historico where idR = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			
			Transacao t = new Transacao();
			
			t.setData(rs.getDate("data"));
			t.setTipoTransacao(rs.getString("tipoTransacao"));
			t.setDescricao(rs.getString("descricao"));
			t.setValor(rs.getDouble("valor"));
			t.setIdR(rs.getInt("idR"));
			t.setContaR(rs.getInt("contaR"));
			t.setAgenciaR(rs.getInt("agenciaR"));
			t.setIdD(rs.getInt("idD"));
			t.setContaD(rs.getInt("contaD"));
			t.setAgenciaD(rs.getInt("agenciaD"));
			
			lista.add(t);
			
		}
		
		conn.close();
		return lista;
		
	}

}
