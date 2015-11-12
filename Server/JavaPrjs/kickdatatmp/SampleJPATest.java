import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.*;

import kickdata.KStudygroup;


public class SampleJPATest {

	public static void main(String[] args) {
		//connect to MySQL server
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("ProductRepair");
		EntityManager em = emf.createEntityManager();
		SampleJPQL.InputParameter ip = new SampleJPQL.InputParameter(3);
		SampleJPQL ag = new SampleJPQL(ip, em);
		try {
			//Sample:
			//TODO:
			List<KStudygroup> l = ag.oneSample();
			em.close();
			for(Object o:l){
				System.out.println(o.toString());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
