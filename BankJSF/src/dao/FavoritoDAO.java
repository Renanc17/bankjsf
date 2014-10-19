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
	
	public List<Favorito> listarFavoritos(Integer idCliente) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "select * from favorito where idCliente=? order by nome";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, idCliente);
		
		ResultSet rs = stmt.executeQuery();
		
		
		ArrayList<Favorito> favoritos = new ArrayList<Favorito>();
		
		while(rs.next()){
			Favorito f = new Favorito();
			f.setId(rs.getInt("id"));
			f.setNome(rs.getString("nome"));
			f.setAgencia(rs.getInt("agencia"));
			f.setContaC(rs.getInt("contaC"));
			
			favoritos.add(f);
		}
		
		
		conn.close();
		
		return favoritos;
	}

}
