package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Agendamento;
import factory.ConnectionFactory;

public class AgendamentoDAO {
	
	
	
	public void setarAgendamento(Agendamento a) throws SQLException{
		Connection conn = ConnectionFactory.getConnection();
		String sql = "";
		
		if(a.getId() != 0){
			alterarAgendamento(a);
			conn.close();
		}else{
			
			if(a.getIdD() != 0 && a.getContaD() != 0 && a.getAgenciaD() != 0) //Tranferencias
				sql = "INSERT INTO agendamento (idUsuario, data, tipoAgendamento, descricao, valor, idD, contaD, agenciaD) values(?,?,?,?,?,?,?,?)";
			else //Pagamento
				sql = "INSERT INTO agendamento (idUsuario, data, tipoAgendamento, descricao, valor) values(?,?,?,?,?,?)";
					
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			java.util.Date data = a.getData();
			java.sql.Date datasql = new java.sql.Date(data.getTime());
			
			stmt.setInt(1, a.getIdUsuario());
			stmt.setDate(2, datasql);
			stmt.setString(3, a.getTipoAgendamento());
			stmt.setString(4, a.getDescricao());
			stmt.setDouble(5, a.getValor());
			
			if(a.getIdD() != 0 && a.getContaD() != 0 && a.getAgenciaD() != 0){
				stmt.setInt(6, a.getIdD());
				stmt.setInt(7, a.getContaD());
				stmt.setInt(8, a.getAgenciaD());
			}else if(!a.getCodBarras().equals(""))
				stmt.setString(6, a.getCodBarras());
			
			stmt.executeUpdate();
			conn.close();
		}
	}
	
	public ResultSet verificaExistencia(Agendamento a) throws SQLException{
		Connection conn = ConnectionFactory.getConnection();
		String sql = "";
		
		sql="SELECT * FROM agendamento WHERE id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, a.getId());
		
		ResultSet rs = stmt.executeQuery();
		
		return rs;
		
	}
	
	public void alterarAgendamento(Agendamento a) throws SQLException{
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "UPDATE agendamento SET idUsuario=?, data=?, tipoAgendamento=?, descricao=?, valor=?, idD=?, contaD=?, agenciaD=? WHERE id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		java.util.Date data = a.getData();
		java.sql.Date datasql = new java.sql.Date(data.getTime());
		
		stmt.setInt(1, a.getIdUsuario());
		stmt.setDate(2, datasql);
		stmt.setString(3, a.getTipoAgendamento());
		stmt.setString(4, a.getDescricao());
		stmt.setDouble(5, a.getValor());
		stmt.setInt(6, a.getIdD());
		stmt.setInt(7, a.getContaD());
		stmt.setInt(8, a.getAgenciaD());
		stmt.setInt(9, a.getId());
		
		stmt.executeUpdate();
		conn.close();
	}
	
	public List<Agendamento> pegarAgenda(int id) throws SQLException{
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "SELECT * FROM agendamento WHERE idUsuario=? ORDER BY data";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		
		ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
		
		while(rs.next()){
			Agendamento a = new Agendamento();
			
			a.setId(rs.getInt("id"));
			a.setIdUsuario(rs.getInt("idUsuario"));
			a.setData(rs.getDate("data"));
			a.setTipoAgendamento(rs.getString("tipoAgendamento"));
			a.setDescricao(rs.getString("descricao"));
			a.setValor(rs.getDouble("valor"));
			a.setIdD(rs.getInt("idD"));
			a.setContaD(rs.getInt("contaD"));
			a.setAgenciaD(rs.getInt("agenciaD"));
			
			if(rs.getString("codBarras") != null)
				a.setCodBarras(rs.getString("codBarras"));
			
			agendamentos.add(a);
		}		
		
		conn.close();
		
		return agendamentos;
	}
	
	public void excluirAgendamento (int pk) throws SQLException{
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "DELETE FROM agendamento WHERE id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, pk);
		
		stmt.executeUpdate();
		conn.close();
	}
	
	
}
