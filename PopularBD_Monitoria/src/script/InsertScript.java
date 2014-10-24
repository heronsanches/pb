package script;

import java.util.Date;

import model.Aluno;
import model.Bolsa;
import model.BolsaPertenceBolsista;
import model.Bolsista;
import model.Departamento;
import model.Disciplina;
import model.Edital;
import model.Horario;
import model.Inscricao;
import model.Professor;
import model.ProfessorLecionaTurma;
import model.Projeto;
import model.Relatorio;
import model.Semestre;
import model.Turma;
import model.TurmaPossuiHorario;
import model.Voluntario;
import model.VoluntarioProjeto;
import model.persistence.AlunoDAO;
import model.persistence.BolsaDAO;
import model.persistence.BolsaPertenceBolsistaDAO;
import model.persistence.BolsistaDAO;
import model.persistence.DepartamentoDAO;
import model.persistence.DisciplinaDAO;
import model.persistence.EditalDAO;
import model.persistence.HorarioDAO;
import model.persistence.InscricaoDAO;
import model.persistence.ProfessorDAO;
import model.persistence.ProfessorLecionaTurmaDAO;
import model.persistence.ProjetoDAO;
import model.persistence.RelatorioDAO;
import model.persistence.SemestreDAO;
import model.persistence.TurmaDAO;
import model.persistence.TurmaPossuiHorarioDAO;
import model.persistence.VoluntarioDAO;
import model.persistence.VoluntarioProjetoDAO;

public class InsertScript {
	
	private static final int QTDE_INSERT = 5000;
	private static final String NOME = "NOME";
	private static final String MATA = "MATA";
	private static final String MATRICULA = "MAT";
	private static final String CPF = "CPF";
	private static final String RDE = "DE";
	private static final String R40 = "40H";
	private static final String R20 = "20H";
	private static final String ATIVIDADES_GERAIS = "ATIVIDADES GERAIS ";
	private static final String DESCRICAO = "DESCRICAO ";
	private static final String TIPO_PROJETO_SEMESTRAL = "S";
	private static final String TIPO_PROJETO_ANUAL = "A";
	private static final String ARQUIVO_RELATORIO = "RELATORIO ";
	private static final String RELATORIO_PROFESSOR = "P";
	private static final String RELATORIO_MONITOR= "M";
	private static final String INFORMACOES_ADICIONAIS= "INFORMACOES ADICIONAIS ";
	private static final String DOCUMENTOS_NECESSARIOS= "DOCUMENTOS NECESSARIOS ";
	private static final String CONTA= "CONTA";
	private static final String AGENCIA= "AGEN";
	private static final String BANCO= "BANCO DO BRASIL";

	
	private static final long HOUR = 3600000;
	private static final long DIA = 3600000L*24;
	private static final long MES = 3600000L*24*30L;
	private static final long ANO = 3600000L*24*30*12;
	private static final int QTDE_DIAS = 15;
	public static final double SEM_NOTA = -1;
	
	
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


private static void insertProjeto(){
	
	ProjetoDAO pd = new ProjetoDAO();
	Projeto p;
	long atual = 0L;
	long sem = 60000L*60L*24L*30L*6L;
	long anual = 2L*sem;
	long anual44 = 43L*anual;
	
	for(int i=1; i<(QTDE_INSERT/2); i++){
		
		p = new Projeto();
		p.setAtividades_gerais(ATIVIDADES_GERAIS+i);
		p.setDescricao(DESCRICAO+i);
		p.setTipo(TIPO_PROJETO_ANUAL);
		p.setDisciplina_cod(MATA+i);
		p.setInicio_vigencia(new Date(atual));
		p.setData_aprovacao(new Date(atual));
		p.setFim_vigencia(new Date(atual+anual));
		p.setStatus(true);
		p.setProfessor_matricula(String.valueOf(i));
		
		pd.insert(p);
		
		if(atual > anual44)
			atual = 0L;
		else
			atual += anual;

	}
	
	//atual = 0L;
	
	for(int i=QTDE_INSERT/2; i<QTDE_INSERT; i++){
		
		p = new Projeto();
		p.setAtividades_gerais(ATIVIDADES_GERAIS+i);
		p.setDescricao(DESCRICAO+i);
		p.setTipo(TIPO_PROJETO_SEMESTRAL);	
		p.setInicio_vigencia(new Date(atual));
		p.setData_aprovacao(new Date(atual));
		p.setFim_vigencia(new Date(atual+sem));
		p.setStatus(true);
		p.setDisciplina_cod(MATA+i);
		p.setProfessor_matricula(String.valueOf(i));
		
		pd.insert(p);
		
		if(atual > anual44)
			atual = 0L;
		else
			atual += sem;

	}

}

private static void insertRelatorio(){
	
	RelatorioDAO rd = new RelatorioDAO();
	Relatorio r;
	long atual = 0L;
	long sem = 60000L*60L*24L*30L*6L;
	long anual = 2L*sem;
	long anual44 = 43L*anual;
	
	for(int i=1; i<QTDE_INSERT; i++){
		
		r = new Relatorio();
		r.setData_criacao(new Date(atual+anual+sem));
		r.setArquivo_relatorio(ARQUIVO_RELATORIO+i);
		r.setTipo(RELATORIO_MONITOR);		
		r.setDescricao(DESCRICAO+i);
		r.setProjeto_cod(i);
		
		rd.insert(r);
		
		if(atual > anual44)
			atual = 0L;
		else
			atual += anual;

	}

	for(int i=QTDE_INSERT/2; i<QTDE_INSERT; i++){
		
		r = new Relatorio();
		r.setData_criacao(new Date(atual+anual));
		r.setArquivo_relatorio(ARQUIVO_RELATORIO+i);
		r.setDescricao(DESCRICAO+i);
		r.setProjeto_cod(i);
		r.setTipo(RELATORIO_PROFESSOR);
		
		if(i%2 == 0)
			r.setNota_conceito(7.0);
		else
			r.setNota_conceito(9.4);
		
		rd.insert(r);
		
		if(atual > anual44)
			atual = 0L;
		else
			atual += sem;
		
	}

}


private static void insertEdital(){
	
	EditalDAO ed = new EditalDAO();
	Edital e;
	long atual = 0L;
	long sem = 60000L*60L*24L*30L*6L;
	long anual = 2L*sem;
	long dia10 = 1000L*60L*60L*24L*10L;
	long anual44 = 43L*anual;

	
	for(int i=1; i<QTDE_INSERT/2; i++){
		
		e = new Edital();
		e.setData_inicio(new Date(atual+=dia10));
		e.setData_fim(new Date(atual+dia10));
		e.setInformacoes_adicionais(INFORMACOES_ADICIONAIS+i);
		e.setDocumentos_necessarios(DOCUMENTOS_NECESSARIOS+i);
		
		ed.insert(e);
		
		if(atual > anual44)
			atual = 0L;
		else
			atual += anual;

	}

	for(int i=QTDE_INSERT/2; i<QTDE_INSERT; i++){
		
		e = new Edital();
		e.setData_inicio(new Date(atual+=dia10));
		e.setData_fim(new Date(atual+dia10));
		e.setInformacoes_adicionais(INFORMACOES_ADICIONAIS+i);
		e.setDocumentos_necessarios(DOCUMENTOS_NECESSARIOS+i);
		
		ed.insert(e);
		
		if(atual > anual44)
			atual = 0L;
		else
			atual += sem;
		
	}
}

private static void insertBolsa(){

	BolsaDAO bd = new BolsaDAO();
	Bolsa b;
	long atual = 0L;
	long sem = 60000L*60L*24L*30L*6L;
	long anual = 2L*sem;
	long anual44 = 43L*anual;
	
for(int i=1; i<(QTDE_INSERT/2); i++){
		
	b = new Bolsa();
	b.setInicio_vigencia(new Date(atual));
	b.setFim_vigencia(new Date(atual += anual));
	b.setProjeto_cod(i);
	b.setValor(400.00);
	
	bd.insert(b);
	
	if(atual > anual44)
		atual = 0L;

	}
	
	for(int i=QTDE_INSERT/2; i<QTDE_INSERT; i++){
		
		b = new Bolsa();
		b.setInicio_vigencia(new Date(atual));
		b.setFim_vigencia(new Date(atual += sem));
		b.setProjeto_cod(i);
		b.setValor(400.00);
		
		bd.insert(b);
		
		if(atual > anual44)
			atual = 0L;

	}

}

private static void insertAluno(){
	
	Aluno a;
	AlunoDAO ad = new AlunoDAO();
	
	for(int i=1; i<QTDE_INSERT; i++){
		
		a = new Aluno();
		a.setCpf(CPF+i);
		a.setMatricula(MATRICULA+i);
		a.setNome(NOME+i);
		ad.insert(a);
		
	}
	
}

private static void insertInscricao(){
	
	Inscricao i;
	InscricaoDAO id = new InscricaoDAO();
	long atual = 0L;
	long sem = 60000L*60L*24L*30L*6L;
	long anual = 2L*sem;
	long anual44 = 43L*anual;
	
for(int j=1; j<(QTDE_INSERT/2); j++){
		
	i = new Inscricao();
	i.setAluno_matricula(MATRICULA+j);
	i.setData_inscricao(new Date(atual));
	atual += anual;
	i.setNota_processo_seletivo(SEM_NOTA);
	i.setProjeto_cod(j);
	id.insert(i);
	
	if(atual > anual44)
		atual = 0L;

	}
	
	for(int j=QTDE_INSERT/2; j<QTDE_INSERT; j++){
		
		i = new Inscricao();
		i.setAluno_matricula(MATRICULA+j);
		i.setData_inscricao(new Date(atual));
		atual += sem;
		i.setNota_processo_seletivo(SEM_NOTA);
		i.setProjeto_cod(j);
		id.insert(i);
		
		if(atual > anual44)
			atual = 0L;

	}
	
}

//insert in the table voluntario and in the table voluntario_projeto
private static void insertVoluntaioProjeto(){
	
	
	long atual = 0L;
	long sem = 60000L*60L*24L*30L*6L;
	long anual = 2L*sem;
	long anual44 = 43L*anual;
	Voluntario v;
	VoluntarioProjeto vp;
	VoluntarioDAO vd = new VoluntarioDAO();
	VoluntarioProjetoDAO vpd = new VoluntarioProjetoDAO();
	
for(int j=1; j<(QTDE_INSERT/2); j++){
		
	v = new Voluntario();
	vp = new VoluntarioProjeto();
	
	v.setMatricula(MATRICULA+j);
	vd.insert(v);
	
	vp.setData_inicio(new Date(atual+15*DIA));
	vp.setData_fim(new Date((atual+15*DIA)+sem));
	vp.setProjeto_cod(j);
	vp.setVoluntario_matricula(MATRICULA+j);
	vpd.insert(vp);
	
	atual += anual;
	
	if(atual > anual44)
		atual = 0L;

	}
	
	for(int j=QTDE_INSERT/2; j<QTDE_INSERT; j++){
		
		v = new Voluntario();
		vp = new VoluntarioProjeto();
		
		v.setMatricula(MATRICULA+j);
		vd.insert(v);
		
		vp.setData_inicio(new Date(atual+15*DIA));
		vp.setData_fim(new Date((atual+15*DIA)+sem));
		vp.setProjeto_cod(j);
		vp.setVoluntario_matricula(MATRICULA+j);
		vpd.insert(vp);
		
		atual += sem;
		
		if(atual > anual44)
			atual = 0L;

	}
	
}

private static void insertBolsista(){
	Bolsista b;
	BolsistaDAO bd = new BolsistaDAO();
	
	for(int i=1; i<QTDE_INSERT; i++){
		
		b = new Bolsista();
		b.setAgencia(AGENCIA+i);
		b.setBanco(BANCO);
		b.setConta_corrente(CONTA+i);
		b.setMatricula(MATRICULA+i);
		bd.insert(b);
		
	}
	
}

private static void insertBolsaPertenceBolsista(){
	
	BolsaPertenceBolsista bpb;
	BolsaPertenceBolsistaDAO bpbd = new BolsaPertenceBolsistaDAO();
	
	long atual = 0L;
	long sem = 60000L*60L*24L*30L*6L;
	long anual = 2L*sem;
	long anual44 = 43L*anual;
	
for(int i=1; i<(QTDE_INSERT/2); i++){
	
	bpb = new BolsaPertenceBolsista();
	bpb.setBolsa_cod(i);
	bpb.setBolsista_matricula(MATRICULA+i);
	bpb.setData_fim(new Date(atual+sem));
	bpb.setData_inicio(new Date(atual));
	bpbd.insert(bpb);
	atual += anual;
	
	if(atual > anual44)
		atual = 0L;

	}
	
	for(int i=QTDE_INSERT/2; i<QTDE_INSERT; i++){
		
		bpb = new BolsaPertenceBolsista();
		bpb.setBolsa_cod(i);
		bpb.setBolsista_matricula(MATRICULA+i);
		bpb.setData_fim(new Date(atual+sem));
		bpb.setData_inicio(new Date(atual));
		bpbd.insert(bpb);
		atual += sem;
		
		if(atual > anual44)
			atual = 0L;

	}
	
}
	
	public static void main(String args[]){
		
		/*insertDepartamento();
		insertDisciplina();
		insertHorario();
		insertSemestre();
		insertTurma();
		insertTurmaPossuiHorario();
		insertProfessor();
		insertProfessorLecionaTurma();
		insertProjeto();
		insertRelatorio();
		insertEdital();
		insertBolsa();
		insertAluno();
		insertInscricao();
		insertVoluntaioProjeto();//insere voluntario e voluntario_projeto*/
		insertBolsista();
		insertBolsaPertenceBolsista();
		
	}

	
}
