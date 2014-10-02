package model.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import model.DB;
import model.Horario;

public class HorarioDAO {

public int insert(Horario horario){
		
		int count = 0;

		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".horario values (default, ?, ?, ?, ?, ?, ?, ?, ?)");
		
			preparedStatement.setTime(1, new Time(horario.getHora_inicio().getTime()));
			preparedStatement.setTime(2, new Time(horario.getHora_fim().getTime()));
			preparedStatement.setBoolean(3, horario.isSeg());
			preparedStatement.setBoolean(4, horario.isTer());
			preparedStatement.setBoolean(5, horario.isQua());
			preparedStatement.setBoolean(6, horario.isQui());
			preparedStatement.setBoolean(7, horario.isSex());
			preparedStatement.setBoolean(8, horario.isSab());
			
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
