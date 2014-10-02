package script;

import java.util.Date;

import model.Departamento;
import model.Disciplina;
import model.Horario;
import model.Semestre;
import model.persistence.DepartamentoDAO;
import model.persistence.DisciplinaDAO;
import model.persistence.HorarioDAO;
import model.persistence.SemestreDAO;

public class InsertScript {
	
	private static final int QTDE_INSERT = 200;
	private static final String NOME = "nome";
	
	
	private static void insertDepartamento(){
		
		DepartamentoDAO depDAO = new DepartamentoDAO();
		Departamento dep;
		
		for(int i=1; i<QTDE_INSERT; i++){
			
			dep = new Departamento();
			dep.setNome(NOME+i);
			depDAO.insert(dep);
		
		}
	
	}
	
	private static void insertDisciplina(){
		
		DisciplinaDAO discDAO = new DisciplinaDAO();
		Disciplina disc;
		String mat = "MAT";
		
		for(int i=1; i<QTDE_INSERT; i++){
			
			disc = new Disciplina();
			disc.setCod(mat+i);
			disc.setDepartamento_cod(i);
			disc.setNome(NOME+i);
			discDAO.insert(disc);

		}
	
	}
	
	private static void insertHorario(){
		
		HorarioDAO hd =  new HorarioDAO();
		Horario h;
		long hour = 3600000;
		Date d = new Date();
		
		for(int i=1; i<QTDE_INSERT; i++){
			
			h = new Horario();
			
			if(i%10 != 0){
				
				h.setHora_inicio(d);
				h.setHora_fim(new Date(d.getTime()+hour*2));
				h.setSeg(true);
				h.setQua(true);
				h.setSex(true);
				hd.insert(h);
			
			}else{
				
				d = new Date(d.getTime()+hour);
				h.setHora_inicio(d);
				h.setHora_fim(new Date(d.getTime()+hour*2));
				h.setTer(true);
				h.setQui(true);
				hd.insert(h);
				
			}
			
		}
	
	}
	
	
private static void insertSemestre(){
		
		SemestreDAO sd =  new SemestreDAO();
		Semestre s;
		long ano = 3600000*24*30*12;
		long mes = 3600000*24*30;
		long dia = 3600000*24;
		
		Date d = new Date(0);
		byte se = 1;
		
		for(int i=1970; i<2015;){
			
			s = new Semestre();
			
			if(se%2 != 0){ //primeiro semestre
				
				s.setAno_nominal(String.valueOf(i));
				s.setData_fim(null);
				s.setData_inicio(new Date(d.getTime()+(mes+dia)*2));
				s.setPeriodo_nominal(se);
				sd.insert(s);
				se++;
			
			}else{
				
				s.setAno_nominal(String.valueOf(i));
				s.setData_fim(null);
				s.setData_inicio(new Date(d.getTime()+(mes+dia)*7));
				s.setPeriodo_nominal(se);
				sd.insert(s);
				i++;
				se = 1;
				d = new Date(d.getTime()+ano+dia);
				
			}
			
		}
	
	}
	

	
	public static void main(String args[]){
		
		//insertDepartamento();
		//insertDisciplina();
		//insertHorario();
		//insertSemestre();
		
	}

	
}
