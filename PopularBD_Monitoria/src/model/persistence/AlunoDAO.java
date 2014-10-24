package model.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Aluno;
import model.DB;

public class AlunoDAO {
	
public int insert(Aluno a){
		
		int count = 0;

		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".aluno values (?, ?, ?)");
		
			preparedStatement.setString(1, a.getMatricula());
			preparedStatement.setString(2, a.getCpf());
			preparedStatement.setString(3, a.getNome());
			
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
