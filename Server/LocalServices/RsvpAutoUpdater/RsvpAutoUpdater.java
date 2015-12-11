import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;

import javax.persistence.*;


public class RsvpAutoUpdater {

	public static void main(String[] args) {
		//connect to MySQL server
		EntityManagerFactory emf;
		EntityManager em;
		Timestamp ts = null;
		SimpleDateFormat datefmt = new SimpleDateFormat(AutoMarkFIFS.timeparten);
		Date parsedDate;
		emf = Persistence.createEntityManagerFactory("RsvpAutoUpdater");
		em = emf.createEntityManager();
		AutoMarkFIFS amf;
		if(args.length >=1){
			if(args[0].compareTo("daemon") == 0){
				amf = new AutoMarkFIFS(true, em);
				try {
					parsedDate = datefmt.parse("2015-12-01/00:00:00");
					ts = new Timestamp(parsedDate.getTime());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Timer t1 = new Timer();
				t1.scheduleAtFixedRate(amf, ts, 9000L);
			} else {
				try {
					parsedDate = datefmt.parse(args[0]);
					ts = new Timestamp(parsedDate.getTime());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				amf = new AutoMarkFIFS(ts, false, em);
				amf.run();
			}
		} else {
			amf = new AutoMarkFIFS(false, em);
			amf.run();
		}
		em.close();
		
	}
}
