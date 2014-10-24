package model.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DB;
import model.VoluntarioProjeto;

public class VoluntarioProjetoDAO {
	
public int insert(VoluntarioProjeto vp){
		
		int count = 0;

		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".voluntario_projeto values (?, ?, ?, ?)");
		
			preparedStatement.setInt(1, vp.getProjeto_cod());
			preparedStatement.setString(2, vp.getVoluntario_matricula());
			preparedStatement.setDate(3, new Date(vp.getData_inicio().getTime()));
			preparedStatement.setDate(4, new Date(vp.getData_fim().getTime()));

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
