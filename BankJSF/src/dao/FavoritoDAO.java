package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
