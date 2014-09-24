package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Cliente;
import factory.ConnectionFactory;

public class ClienteDAO {
	
	public Boolean login(Cliente c) throws SQLException{
		
		Boolean resultado = false;
		
		Connection conn = ConnectionFactory.getConnection();
		
		
		String sql = "SELECT * FROM cliente where cpf=? and senha=? and agencia=?";
		for(int i = 0; i < 2; i++){
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, c.getCpf());
			stmt.setString(2, c.getSenha());
			stmt.setInt(3, c.getAgencia());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
				resultado = true;
			
			if(resultado == false){
				sql = "SELECT * FROM cliente where contac=? and senha=? and agencia=?";
			}
			
		}
			
		return resultado;
		
	}
	
/*public Cliente login(Cliente c) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "SELECT * from clientes WHERE cpf ";
		
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
		
	}*/
	
}