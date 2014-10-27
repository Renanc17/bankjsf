package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Favorito;
import factory.ConnectionFactory;

public class FavoritoDAO {
	
	public void cadastrarFavorito(Favorito f) throws SQLException{

		Connection conn = ConnectionFactory.getConnection();

		String sql = "insert into favorito (idCliente, nome, agencia, contac) values(?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, f.getIdCliente());
		stmt.setString(2, f.getNome());
		stmt.setInt(3, f.getAgencia());
		stmt.setInt(4, f.getContaC());

		stmt.executeUpdate();
		conn.close();
		
	}
	
	public List<Favorito> listarFavoritos(int idCliente) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "select * from favorito where idCliente=? order by nome";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, idCliente);
		
		ResultSet rs = stmt.executeQuery();
		
		
		ArrayList<Favorito> favoritos = new ArrayList<Favorito>();
		
		while(rs.next()){
			Favorito f = new Favorito();
			f.setId(rs.getInt("id"));
			f.setIdCliente(idCliente);
			f.setNome(rs.getString("nome"));
			f.setAgencia(rs.getInt("agencia"));
			f.setContaC(rs.getInt("contaC"));
			
			favoritos.add(f);
		}
		
		
		conn.close();
		
		return favoritos;
	}
	
	public void alterarFavorito(Favorito f) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "UPDATE favorito SET nome=?, agencia=?, contac=? WHERE id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, f.getNome());
		stmt.setInt(2, f.getAgencia());
		stmt.setInt(3, f.getContaC());
		stmt.setInt(4, f.getId());
		
		stmt.executeUpdate();
		conn.close();
	}

	public void excluirFavorito(int pk) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "DELETE FROM favorito WHERE id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, pk);
		stmt.executeUpdate();
		conn.close();
	}
	
	public boolean verificaExistencia(Favorito f) throws SQLException{
		Connection conn = ConnectionFactory.getConnection();
		String sql = "";
		
		sql="SELECT * FROM favorito WHERE idCliente=? and agencia=? and contaC=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, f.getIdCliente());
		stmt.setInt(2, f.getAgencia());
		stmt.setInt(3, f.getContaC());
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs != null)
			return true;
		else
			return false;
		
	}

}
