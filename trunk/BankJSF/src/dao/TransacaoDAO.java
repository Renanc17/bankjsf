package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import bean.Cliente;
import bean.Transacao;
import factory.ConnectionFactory;

public class TransacaoDAO {
	
public Cliente transfToPoupanca(Cliente c, Transacao t) throws SQLException{		
		
		Connection conn = ConnectionFactory.getConnection();
		String sql = "UPDATE cliente SET saldoc=?, saldop=? where id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setDouble(1, c.getContaCorrente().getSaldo());
		stmt.setDouble(2, c.getContaPoupanca().getSaldo());
		stmt.setInt(3, c.getId());
		
		stmt.executeUpdate();
		
		gravarHist(t);
		
		ClienteDAO dao = new ClienteDAO();
		c = dao.getClienteById(c.getId());
		
		conn.close();
		return c;
		
		
	}

	public Cliente transfToCc(Cliente c, Transacao t) throws SQLException{	
	
	Connection conn = ConnectionFactory.getConnection();
	String sql = "UPDATE cliente SET saldoc=?, saldop=? where id=?";
	
	PreparedStatement stmt = conn.prepareStatement(sql);
	
	stmt.setDouble(1, c.getContaCorrente().getSaldo());
	stmt.setDouble(2, c.getContaPoupanca().getSaldo());
	stmt.setInt(3, c.getId());
	
	stmt.executeUpdate();
	
	gravarHist(t);	
	
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
		if(t.getDescricao().equals(""))
		t.setDescricao("Transf. p/ cliente " + dest.getNome());
				
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
		
		t = fillTransacao(t, saldoR, saldoD);
		gravarHist(t);
		c = dao.getClienteById(c.getId());
		
		conn.close();
		return c;
		
	}
	
	public Transacao fillTransacao(Transacao t, Double saldoR, Double saldoD) throws SQLException{
		ClienteDAO dao = new ClienteDAO();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Cliente rem = (Cliente) session.getAttribute("cliente");
		Cliente dest = dao.getCliente(t.getContaD(), t.getAgenciaD());
		
		t.setIdR(rem.getId());
		t.setContaR(rem.getContaCorrente().getConta());
		t.setAgenciaR(rem.getAgencia());
		t.setIdD(dest.getId());
		t.setSaldoR(saldoR);
		t.setSaldoD(saldoD);
		
		return t;
	}
	
	public void gravarHist(Transacao t) throws SQLException{
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT into historico(data, tipoTransacao, descricao, valor, idR, contaR, agenciaR, saldoR, idD, contaD, agenciaD, saldoD) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		java.util.Date data = t.getData();
		java.sql.Date datasql = new java.sql.Date(data.getTime());
		
		stmt.setDate(1, datasql);
		stmt.setString(2, t.getTipoTransacao());
		stmt.setString(3, t.getDescricao());
		stmt.setDouble(4, t.getValor());
		stmt.setInt(5, t.getIdR());
		stmt.setInt(6, t.getContaR());
		stmt.setInt(7, t.getAgenciaR());
		stmt.setDouble(8, t.getSaldoR());
		stmt.setInt(9, t.getIdD());
		stmt.setInt(10, t.getContaD());
		stmt.setInt(11, t.getAgenciaD());
		stmt.setDouble(12, t.getSaldoD());
		
		stmt.executeUpdate();
		
		conn.close();
		
		
	}
	
	public List<Transacao> historico(Integer id, Date fromDate, Date untilDate) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		List<Transacao> lista = new ArrayList<Transacao>();
		
		String sql = "SELECT * FROM historico WHERE idR = ? or idD = ? AND data BETWEEN ? and ? "; 
		
		PreparedStatement stmt = conn.prepareStatement(sql);

		java.sql.Date fromDateSQL = new java.sql.Date(fromDate.getTime());		
		java.sql.Date untilDateSQL = new java.sql.Date(untilDate.getTime());

		stmt.setInt(1, id);
		stmt.setInt(2, id);
		stmt.setDate(3, fromDateSQL);
		stmt.setDate(4, untilDateSQL);
		
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
			
			if(t.getIdR() == id){			
				t.setSaldo(rs.getDouble("SaldoR"));
			}else
				if(t.getIdD() == id)
					t.setSaldo(rs.getDouble("SaldoD"));
					
			
			lista.add(t);
			
		}
		
		conn.close();
		return lista;
		
	}
	
	

}
