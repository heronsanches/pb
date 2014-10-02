package script;

import model.Departamento;
import model.DepartamentoDAO;

public class InsertScript {
	
	private static Departamento dep;
	private static DepartamentoDAO depDAO;
	
	private static String nome = "nome";
	
	public static void main(String args[]){
		
		depDAO = new DepartamentoDAO();
		
		for(int i=0; i<10; i++){
			dep = new Departamento();
			dep.setNome(nome+i);
			depDAO.insert(dep);
		}
		
	}

}
