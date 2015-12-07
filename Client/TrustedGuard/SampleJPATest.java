import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.*;

import edu.neu.cs5200.finalproj.model.KStudygroup;


public class SampleJPATest {

	public static void main(String[] args) {
		//connect to MySQL server
		EntityManagerFactory emf;
		EntityManager em;
		/*
		emf = Persistence.createEntityManagerFactory("SwipeCard");
		em = emf.createEntityManager();
		try {
			//Sample:
			//TODO:
			SampleJPQL ag = new SampleJPQL(new SampleJPQL.InputParameter(3), em);
			List<KStudygroup> l = ag.oneSample();
			for(KStudygroup o:l){
				System.out.println(o.toString());
			}
			em.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		emf = Persistence.createEntityManagerFactory("SwipeCard");
		em = emf.createEntityManager();
		try {
			try{
				SwipeCard sw = new SwipeCard(new SwipeCard.InputParameter(1779701, 4), em);
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
		
		emf = Persistence.createEntityManagerFactory("SwipeCard");
		em = emf.createEntityManager();
		try {
			AutoMarkFIFS amf = new AutoMarkFIFS(em);
			amf.run();
		} catch (NoResultException e){
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("NO RESERVATION FOUNDED");
			em.getTransaction().commit();
		}
		em.close();
		
	}
}
