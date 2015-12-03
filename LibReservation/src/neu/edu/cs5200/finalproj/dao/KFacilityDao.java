package neu.edu.cs5200.finalproj.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import neu.edu.cs5200.finalproj.model.KComputer;
import neu.edu.cs5200.finalproj.model.KFacility;
import neu.edu.cs5200.finalproj.model.KFacilityType;
import neu.edu.cs5200.finalproj.model.KGroupRoom;
import neu.edu.cs5200.finalproj.model.KIndividualRoom;
import neu.edu.cs5200.finalproj.model.KReservation;

public class KFacilityDao {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Map<String, String> getAllFacilities() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from KFacility ";
		Query query = session.createQuery(hql);
		List<KFacility> facilities = (List<KFacility>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		Map<String, String> result = new HashMap<String, String>();
		for (KFacility fac : facilities) {
			
			String facName = "";
			if(fac instanceof KComputer) {
				facName = ((KComputer)fac).getName();
			} else if(fac instanceof KGroupRoom) {
				facName = ((KGroupRoom)fac).getName();
			} else if(fac instanceof KIndividualRoom) {
				facName = ((KIndividualRoom)fac).getName();
			}
			
			result.put(Integer.toString(fac.getId()), facName);
		}
		return result;
	}
}
