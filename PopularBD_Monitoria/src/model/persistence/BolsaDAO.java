package model.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Bolsa;
import model.DB;

public class BolsaDAO {
	
public int insert(Bolsa b){
		
		int count = 0;

		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".bolsa values (default, ?, ?, ?)");
		
			preparedStatement.setDate(1, new Date(b.getInicio_vigencia().getTime()));
			preparedStatement.setDate(2, new Date(b.getFim_vigencia().getTime()));
			preparedStatement.setInt(3, b.getProjeto_cod());
			
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
