package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Cliente;
import factory.ConnectionFactory;

public class ClienteDAO {
	
	public Cliente login(Cliente c) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "SELECT cpf, senha FROM cliente";
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();

			
			while(rs.next()){
				c.setCpf(rs.getLong("cpf"));
				c.setSenha(rs.getString("senha"));
				
			}
			
		}
		catch(SQLException ex){ 
			ex.printStackTrace();
		}
		finally{
			
		}
		
		return c;
		
	}
}