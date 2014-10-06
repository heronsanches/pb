package model.persistence;


import java.sql.PreparedStatement;
import java.sql.SQLException;


import model.DB;
import model.Departamento;


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
