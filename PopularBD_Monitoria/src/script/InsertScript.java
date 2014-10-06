package script;

import java.util.Date;

import model.Departamento;
import model.Disciplina;
import model.Horario;
import model.Professor;
import model.ProfessorLecionaTurma;
import model.Semestre;
import model.Turma;
import model.TurmaPossuiHorario;
import model.persistence.DepartamentoDAO;
import model.persistence.DisciplinaDAO;
import model.persistence.HorarioDAO;
import model.persistence.ProfessorDAO;
import model.persistence.ProfessorLecionaTurmaDAO;
import model.persistence.SemestreDAO;
import model.persistence.TurmaDAO;
import model.persistence.TurmaPossuiHorarioDAO;

public class InsertScript {
	
	private static final int QTDE_INSERT = 200;
	private static final String NOME = "nome";
	private static final String MATA = "MATA";
	private static final String RDE = "DE";
	private static final String R40 = "40H";
	private static final String R20 = "20H";
	private static final long HOUR = 3600000;
	private static final long DIA = 3600000L*24;
	private static final long MES = 3600000L*24*30L;
	private static final long ANO = 3600000L*24*30*12;
	private static final int QTDE_DIAS = 15;
	
	
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
		
		for(int i=1; i<QTDE_INSERT; i++){
			
			disc = new Disciplina();
			disc.setCod(MATA+i);
			disc.setDepartamento_cod(i);
			disc.setNome(NOME+i);
			discDAO.insert(disc);

		}
	
	}
	
	private static void insertHorario(){
		
		HorarioDAO hd =  new HorarioDAO();
		Horario h;
		Date d = new Date();
		
		for(int i=1; i<QTDE_INSERT; i++){
			
			h = new Horario();
			
			if(i%10 != 0){
				
				h.setHora_inicio(d);
				h.setHora_fim(new Date(d.getTime()+HOUR*2));
				h.setSeg(true);
				h.setQua(true);
				h.setSex(true);
				hd.insert(h);
			
			}else{
				
				d = new Date(d.getTime()+HOUR);
				h.setHora_inicio(d);
				h.setHora_fim(new Date(d.getTime()+HOUR*2));
				h.setTer(true);
				h.setQui(true);
				hd.insert(h);
				
			}
			
		}
	
	}
	
	
private static void insertSemestre(){
		
		SemestreDAO sd =  new SemestreDAO();
		Semestre s;
		long atual = new Date(DIA*QTDE_DIAS).getTime(); //starting on 1970-01-15
		char se = '1';
		int i=1970;
		
		while(i<2015){
			
			s = new Semestre();
			
			if(se == '1'){ //primeiro semestre
				
				s.setAno_nominal(String.valueOf(i));
				s.setData_fim(null);
				s.setData_inicio(new Date(atual));
				s.setPeriodo_nominal(String.valueOf(se));
				sd.insert(s);
				se = '2';
			
			}else{
				
				s.setAno_nominal(String.valueOf(i));
				s.setData_fim(null);
				s.setData_inicio(new Date(atual+MES*7));
				s.setPeriodo_nominal(String.valueOf(se));
				sd.insert(s);
				i++;
				se = '1';
				atual += ANO + 5*DIA;
				
			}
			
		}
	
	}

	

private static void insertTurma(){
	
	TurmaDAO td = new TurmaDAO();
	Turma t;
	
	long atual = new Date(DIA*QTDE_DIAS).getTime(); //starting on 1970-01-15
	char se = '1';
	
	for(int i=1970, n=1; i<2015; n++){
		
		t = new Turma();
		
		if(se == '1'){ //primeiro semestre
			
			t.setSemestre_data_inicio(new Date(atual));
			t.setNumero(String.valueOf(n));
			t.setDisciplina_cod(MATA+n);
			td.insert(t);
			se = '2';
		
		}else{
			
			t.setSemestre_data_inicio(new Date(atual+MES*7));
			t.setNumero(String.valueOf(n));
			t.setDisciplina_cod(MATA+n);
			td.insert(t);
			i++;
			se = '1';
			atual += ANO + 5*DIA;
			
		}
		
	}

}


private static void insertTurmaPossuiHorario(){
	
	TurmaPossuiHorarioDAO tphDAO = new TurmaPossuiHorarioDAO();
	TurmaPossuiHorario tph;
	
	long atual = new Date(DIA*QTDE_DIAS).getTime(); //starting on 1970-01-15
	char se = '1';
	
	for(int i=1970, n=1; i<2015; n++){
		
		tph = new TurmaPossuiHorario();
		
		if(se == '1'){ //primeiro semestre
			
			tph.setNumero(String.valueOf(n));
			tph.setDisciplina_cod(MATA+n);
			tph.setSemestre_data_inicio(new Date(atual));
			tph.setCod(n);
			
			tphDAO.insert(tph);
			se = '2';
		
		}else{
			
			tph.setNumero(String.valueOf(n));
			tph.setDisciplina_cod(MATA+n);
			tph.setSemestre_data_inicio(new Date(atual+MES*7));
			tph.setCod(n);
			
			tphDAO.insert(tph);
			i++;
			se = '1';
			atual += ANO + 5*DIA;
			
		}
		
	}

}


private static void insertProfessor(){
	
	ProfessorDAO pDAO = new ProfessorDAO();
	Professor p = new Professor();
	String regimes[] = {RDE, R40, R20};
	
	for(int i=1; i<QTDE_INSERT; i++){
		
		p = new Professor();
		
		p.setMatricula(String.valueOf(i));
		p.setCpf(String.valueOf(i));
		p.setNome(NOME+i);
		
		if(i % 2 == 0)
			p.setRegime(regimes[0]);
		else if(i % 3 == 0)
			p.setRegime(regimes[1]);
		else
			p.setRegime(regimes[2]);
		
		p.setDepartamento_cod(i);
		
		pDAO.insert(p);
		
	}

}


private static void insertProfessorLecionaTurma(){
	
	ProfessorLecionaTurmaDAO pltDAO = new ProfessorLecionaTurmaDAO();
	ProfessorLecionaTurma plt;
	
	long atual = new Date(DIA*QTDE_DIAS).getTime(); //starting on 1970-01-15
	char se = '1';
	
	for(int i=1970, n=1; i<2015; n++){
		
		plt = new ProfessorLecionaTurma();
		
		if(se == '1'){ //primeiro semestre
			
			plt.setTurma_numero(String.valueOf(n));
			plt.setTurma_disciplina_cod(MATA+n);
			plt.setTurma_semestre_data_inicio(new Date(atual));
			plt.setProfessor_matricula(String.valueOf(n));
			
			pltDAO.insert(plt);
			se = '2';
		
		}else{
			
			plt.setTurma_numero(String.valueOf(n));
			plt.setTurma_disciplina_cod(MATA+n);
			plt.setTurma_semestre_data_inicio(new Date(atual+MES*7));
			plt.setProfessor_matricula(String.valueOf(n));
			
			pltDAO.insert(plt);
			i++;
			se = '1';
			atual += ANO + 5*DIA;
			
		}
		
	}

}


	
	public static void main(String args[]){
		
		/*insertDepartamento();
		insertDisciplina();
		insertHorario();
		insertSemestre();
		insertTurma();*/
		insertTurmaPossuiHorario();
		insertProfessor();
		insertProfessorLecionaTurma();
		
		
	}

	
}
