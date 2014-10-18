import java.sql.Date;

import model.Projeto;


public class testing {

	public static void main(String[] args) {
		long atual = 0L;
		long sem = 60000L*60L*24L*30L*6L;
		long anual = 2L*sem;
		/*long sem = 36000000L*180;
		long anual = 2L*sem;*/
		
		java.util.Date d = new java.util.Date(528768000000L);
		System.out.println(d);

	}

}
