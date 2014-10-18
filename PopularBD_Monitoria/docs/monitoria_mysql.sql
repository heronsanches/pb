CREATE DATABASE IF NOT EXISTS monitoria;
USE monitoria;

CREATE TABLE horario (
                cod INT AUTO_INCREMENT NOT NULL,
                hora_inicio TIME NOT NULL,
                hora_fim TIME NOT NULL,
                seg BOOLEAN,
                ter BOOLEAN,
                qua BOOLEAN,
                qui BOOLEAN,
                sex BOOLEAN,
                sab BOOLEAN,
                PRIMARY KEY (cod)
);


CREATE TABLE edital (
                cod INT AUTO_INCREMENT NOT NULL,
                data_inicio DATE NOT NULL,
                data_fim DATE NOT NULL,
                informacoes_adicionais VARCHAR(300) NOT NULL,
                documentos_necessarios VARCHAR(150) NOT NULL,
                PRIMARY KEY (cod)
);


CREATE TABLE semestre (
                data_inicio DATE NOT NULL,
                ano_nominal VARCHAR(4) NOT NULL,
                data_fim DATE,
                periodo_nominal VARCHAR(1) NOT NULL,
                PRIMARY KEY (data_inicio)
);


CREATE TABLE aluno (
                matricula VARCHAR(9) NOT NULL,
                cpf VARCHAR(11) NOT NULL,
                nome VARCHAR(100) NOT NULL,
                PRIMARY KEY (matricula)
);


CREATE UNIQUE INDEX aluno_idx USING BTREE
 ON aluno
 ( cpf );

CREATE TABLE bolsista (
                matricula VARCHAR(9) NOT NULL,
                conta_corrente VARCHAR(14) NOT NULL,
                agencia VARCHAR(9) NOT NULL,
                banco VARCHAR(30) NOT NULL,
                PRIMARY KEY (matricula)
);


CREATE TABLE voluntario (
                matricula VARCHAR(9) NOT NULL,
                PRIMARY KEY (matricula)
);


CREATE TABLE departamento (
                cod INT AUTO_INCREMENT NOT NULL,
                nome VARCHAR(150) NOT NULL,
                PRIMARY KEY (cod)
);


CREATE UNIQUE INDEX departamento_idx USING BTREE
 ON departamento
 ( nome ASC );

CREATE TABLE professor (
                matricula VARCHAR(15) NOT NULL,
                cpf VARCHAR(11) NOT NULL,
                nome VARCHAR(100) NOT NULL,
                regime VARCHAR(3) NOT NULL,
                departamento_cod INT NOT NULL,
                PRIMARY KEY (matricula)
);


CREATE UNIQUE INDEX professor_idx_cpf USING BTREE
 ON professor
 ( cpf );

CREATE TABLE disciplina (
                cod VARCHAR(10) NOT NULL,
                nome VARCHAR(200) NOT NULL,
                departamento_cod INT NOT NULL,
                PRIMARY KEY (cod)
);


CREATE TABLE projeto (
                cod INT AUTO_INCREMENT NOT NULL,
                edital_cod INT,
                status BOOLEAN NOT NULL,
                atividades_gerais VARCHAR(400) NOT NULL,
                descricao VARCHAR(150) NOT NULL,
                data_aprovacao DATE,
                ata_aprovacao VARCHAR(300),
                inicio_vigencia DATE,
                fim_vigencia DATE,
                qtde_vagas INT NOT NULL,
                tipo VARCHAR(1) NOT NULL,
                disciplina_cod VARCHAR(10) NOT NULL,
                professor_matricula VARCHAR(15) NOT NULL,
                PRIMARY KEY (cod)
);


CREATE TABLE relatorio (
                cod INT AUTO_INCREMENT NOT NULL,
                data_criacao DATE NOT NULL,
                data_aprovacao DATE,
                arquivo_relatorio VARCHAR(300) NOT NULL,
                tipo VARCHAR(1) NOT NULL,
                nota_conceito DECIMAL(4,2),
                descricao VARCHAR(200) NOT NULL,
                ata_aprovacao VARCHAR(300),
                projeto_cod INT NOT NULL,
                PRIMARY KEY (cod)
);


CREATE TABLE voluntario_projeto (
                projeto_cod INT NOT NULL,
                voluntario_matricula VARCHAR(9) NOT NULL,
                data_inicio DATE NOT NULL,
                data_fim DATE NOT NULL,
                PRIMARY KEY (projeto_cod, voluntario_matricula)
);


CREATE TABLE bolsa (
                cod INT AUTO_INCREMENT NOT NULL,
                inicio_vigencia DATE NOT NULL,
                fim_vigencia DATE NOT NULL,
                valor DECIMAL(6,2) NOT NULL,
                projeto_cod INT NOT NULL,
                PRIMARY KEY (cod)
);


CREATE TABLE bolsa_pertence_bolsista (
                bolsa_cod INT NOT NULL,
                bolsista_matricula VARCHAR(9) NOT NULL,
                data_inicio DATE NOT NULL,
                data_fim DATE NOT NULL,
                PRIMARY KEY (bolsa_cod, bolsista_matricula)
);


CREATE TABLE inscricao (
                numero INT AUTO_INCREMENT NOT NULL,
                projeto_cod INT NOT NULL,
                aluno_matricula VARCHAR(9) NOT NULL,
                data_inscricao DATE NOT NULL,
                nota_processo_seletivo DECIMAL(4, 2),
                PRIMARY KEY (numero, projeto_cod, aluno_matricula)
);


CREATE TABLE turma (
                numero VARCHAR(6) NOT NULL,
                disciplina_cod VARCHAR(10) NOT NULL,
                semestre_data_inicio DATE NOT NULL,
                data_fim DATE,
                data_inicio DATE,
                PRIMARY KEY (numero, disciplina_cod, semestre_data_inicio)
);


CREATE TABLE turma_possui_horario (
                numero VARCHAR(6) NOT NULL,
                disciplina_cod VARCHAR(10) NOT NULL,
                semestre_data_inicio DATE NOT NULL,
                cod INT NOT NULL,
                PRIMARY KEY (numero, disciplina_cod, semestre_data_inicio, cod)
);


CREATE TABLE professor_leciona_turma (
                turma_numero VARCHAR(6) NOT NULL,
                turma_disciplina_cod VARCHAR(10) NOT NULL,
                turma_semestre_data_inicio DATE NOT NULL,
                professor_matricula VARCHAR(15) NOT NULL,
                PRIMARY KEY (turma_numero, turma_disciplina_cod, turma_semestre_data_inicio, professor_matricula)
);


ALTER TABLE turma_possui_horario ADD CONSTRAINT horario_turma_possui_horario_fk
FOREIGN KEY (cod)
REFERENCES horario (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE projeto ADD CONSTRAINT edital_projeto_fk
FOREIGN KEY (edital_cod)
REFERENCES edital (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE turma ADD CONSTRAINT semestre_turma_fk
FOREIGN KEY (semestre_data_inicio)
REFERENCES semestre (data_inicio)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE inscricao ADD CONSTRAINT aluno_inscricao_fk
FOREIGN KEY (aluno_matricula)
REFERENCES aluno (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE voluntario ADD CONSTRAINT aluno_voluntario_fk
FOREIGN KEY (matricula)
REFERENCES aluno (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE bolsista ADD CONSTRAINT aluno_bolsista_fk
FOREIGN KEY (matricula)
REFERENCES aluno (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE bolsa_pertence_bolsista ADD CONSTRAINT bolsista_bolsa_pertence_bolsista_fk
FOREIGN KEY (bolsista_matricula)
REFERENCES bolsista (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE voluntario_projeto ADD CONSTRAINT voluntario_voluntario_projeto_fk
FOREIGN KEY (voluntario_matricula)
REFERENCES voluntario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE disciplina ADD CONSTRAINT departamento_disciplina_fk
FOREIGN KEY (departamento_cod)
REFERENCES departamento (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE professor ADD CONSTRAINT departamento_professor_fk
FOREIGN KEY (departamento_cod)
REFERENCES departamento (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE professor_leciona_turma ADD CONSTRAINT professor_professorleciona_turma_fk
FOREIGN KEY (professor_matricula)
REFERENCES professor (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE projeto ADD CONSTRAINT professor_projeto_fk
FOREIGN KEY (professor_matricula)
REFERENCES professor (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE turma ADD CONSTRAINT disciplina_turma_fk
FOREIGN KEY (disciplina_cod)
REFERENCES disciplina (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE projeto ADD CONSTRAINT disciplina_projeto_fk
FOREIGN KEY (disciplina_cod)
REFERENCES disciplina (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE inscricao ADD CONSTRAINT projeto_inscricao_fk
FOREIGN KEY (projeto_cod)
REFERENCES projeto (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE bolsa ADD CONSTRAINT projeto_bolsa_fk
FOREIGN KEY (projeto_cod)
REFERENCES projeto (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE voluntario_projeto ADD CONSTRAINT projeto_voluntario_projeto_fk
FOREIGN KEY (projeto_cod)
REFERENCES projeto (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE relatorio ADD CONSTRAINT projeto_relatorio_fk
FOREIGN KEY (projeto_cod)
REFERENCES projeto (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE bolsa_pertence_bolsista ADD CONSTRAINT bolsa_bolsa_pertence_bolsista_fk
FOREIGN KEY (bolsa_cod)
REFERENCES bolsa (cod)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE professor_leciona_turma ADD CONSTRAINT turma_professorleciona_turma_fk
FOREIGN KEY (turma_numero, turma_disciplina_cod, turma_semestre_data_inicio)
REFERENCES turma (numero, disciplina_cod, semestre_data_inicio)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE turma_possui_horario ADD CONSTRAINT turma_turma_possui_horario_fk
FOREIGN KEY (numero, disciplina_cod, semestre_data_inicio)
REFERENCES turma (numero, disciplina_cod, semestre_data_inicio)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
