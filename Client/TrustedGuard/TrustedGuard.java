import java.io.IOException;
import java.sql.SQLException;
import javax.persistence.*;


public class TrustedGuard {

	public static void main(String[] args) {
		//connect to MySQL server
		EntityManagerFactory emf;
		EntityManager em;
		int nuid, facilityid;
		emf = Persistence.createEntityManagerFactory("SwipeCard");
		em = emf.createEntityManager();
		try {
			try{
				if(args.length==2){
					nuid = Integer.parseUnsignedInt(args[0].trim());
					facilityid = Integer.parseUnsignedInt(args[1].trim());
				}else{
					return;
				}
				SwipeCard sw = new SwipeCard(new SwipeCard.InputParameter(nuid, facilityid), em);
				if(sw.tryAccessFacility()){
					System.out.println("Please Enjoy the room");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				switch (e.getMessage()) {
					case "NotAllowedAccess":
						System.out.println("YOU ARE NOT ALLOWED TO ACCESS THIS FACILITY NOW!");
						break;
					case "Managing":
						System.out.println("THIS ROOM IS MANAGING NOW!");
						break;
					default:
						e.printStackTrace();
				}
				em.getTransaction().commit();
			}
			em.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
