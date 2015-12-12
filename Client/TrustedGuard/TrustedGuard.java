import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;


public class TrustedGuard {

	public static void main(String[] args) {
		//connect to MySQL server
		EntityManagerFactory emf;
		EntityManager em;
		SwipeCard sw = null;
		int nuid, facilityid;
		emf = Persistence.createEntityManagerFactory("SwipeCard");
		em = emf.createEntityManager();
		try {
			try{
				if (args.length==2) {
					nuid = Integer.parseUnsignedInt(args[0].trim());
					facilityid = Integer.parseUnsignedInt(args[1].trim());
					sw = new SwipeCard(new SwipeCard.InputParameter(nuid, facilityid), em);
				} else if (args.length==3) {
					nuid = Integer.parseUnsignedInt(args[0].trim());
					facilityid = Integer.parseUnsignedInt(args[1].trim());
					sw = new SwipeCard(new SwipeCard.InputParameter(nuid, facilityid, args[2]), em);
				} else {
					return;
				}
				switch(sw.tryAccessFacility()){
				case OK:
					System.out.println("Please Enjoy the room");
					break;
				case MAINTAINING:
					System.out.println("THIS ROOM IS MAINTAINING NOW!");
					break;
				case LATE:
					System.out.println("Sorry, either you and your group member are late!");
					break;
				case RESERVEDBYOTHERS:
					System.out.println("Sorry, this room reserved by others at this time.");
					break;
				case OBJECTUSED:
					System.out.println("YOU ARE NOT ALLOWED TO ACCESS THIS FACILITY NOW!");
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			em.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
