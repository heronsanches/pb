package model;

import java.util.Date;

/**
 * Database's relational model class
 */

public class Horario {
	
	/** pk auto_increment*/
	private int cod;
	
	/** not null*/
	private Date hora_inicio;
	/** not null*/
	private Date hora_fim;

	private boolean seg;
	private boolean ter;
	private boolean qua;
	private boolean qui;
	private boolean sex;
	private boolean sab;
	
	
	//setters and getters
	public int getCod() {
		return cod;
	}
	public Date getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public Date getHora_fim() {
		return hora_fim;
	}
	public void setHora_fim(Date hora_fim) {
		this.hora_fim = hora_fim;
	}
	public boolean isSeg() {
		return seg;
	}
	public void setSeg(boolean seg) {
		this.seg = seg;
	}
	public boolean isTer() {
		return ter;
	}
	public void setTer(boolean ter) {
		this.ter = ter;
	}
	public boolean isQua() {
		return qua;
	}
	public void setQua(boolean qua) {
		this.qua = qua;
	}
	public boolean isQui() {
		return qui;
	}
	public void setQui(boolean qui) {
		this.qui = qui;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public boolean isSab() {
		return sab;
	}
	public void setSab(boolean sab) {
		this.sab = sab;
	}
	
	
	
}
