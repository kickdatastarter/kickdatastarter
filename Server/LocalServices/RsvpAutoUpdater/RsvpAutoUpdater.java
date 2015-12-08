import javax.persistence.*;


public class RsvpAutoUpdater {

	public static void main(String[] args) {
		//connect to MySQL server
		EntityManagerFactory emf;
		EntityManager em;
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
