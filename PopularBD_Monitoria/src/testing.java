import java.sql.Date;


public class testing {

	public static void main(String[] args) {
		
		final long dia = 3600000*24;
		final long mes = 30*dia;
		//final long ano = 12*mes;
		final long ano = 12*30*3600000*24L;
		System.out.println(new Date(ano+15*dia));
		System.out.println(ano);

	}

}
