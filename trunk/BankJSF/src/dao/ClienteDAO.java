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
			{
				Cliente cl = new Cliente();
				cl.setId(rs.getInt("id"));
				cl.setNome(rs.getString("nome"));
				cl.setCpf(rs.getLong("cpf"));
				cl.setAgencia(rs.getInt("agencia"));
				cl.setSenha(rs.getString("senha"));
				//modificar tabela e bean cliente
				
				resultado = true;
			}
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