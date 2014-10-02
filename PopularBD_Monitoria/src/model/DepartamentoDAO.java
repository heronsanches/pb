package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DepartamentoDAO {
	
	


	public  int insert(Departamento departamento){

		int count = 0;
		PreparedStatement preparedStatement;
			
			try {
				
				preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
					DB.getDbName()+".departamento values (default, ?)");
				
				preparedStatement.setString(1, departamento.getNome());

				count = preparedStatement.executeUpdate();
				
				preparedStatement.close();
				
			} catch (SQLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (Exception e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			return count;
			
	}

	
}
