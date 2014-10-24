package model.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DB;
import model.Voluntario;

public class VoluntarioDAO {
	
public int insert(Voluntario v){
		
		int count = 0;

		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".voluntario values (?)");
		
			preparedStatement.setString(1, v.getMatricula());
			
			count = preparedStatement.executeUpdate(); // update
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
