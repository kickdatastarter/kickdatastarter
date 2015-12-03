package neu.edu.cs5200.finalproj.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import neu.edu.cs5200.finalproj.model.KComputer;
import neu.edu.cs5200.finalproj.model.KFacility;
import neu.edu.cs5200.finalproj.model.KFacilityType;
import neu.edu.cs5200.finalproj.model.KGroupRoom;
import neu.edu.cs5200.finalproj.model.KIndividualRoom;

public class KGroupRoomDao {
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void InsertGroupRoom(int capacity, String name){
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "from KFacilityType type where type.id = 1";
        Query query = session.createQuery(hql);
        List list = query.list();
        KFacilityType type = (KFacilityType) list.get(0);

		KGroupRoom g = new KGroupRoom(capacity, type, name);
		session.save(g);
		session.getTransaction().commit();
		session.close();
}
	
	public void DeleteGroupRoom(String name){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql="delete KGroupRoom g "
				  + "where g.name = :name ";
		Query query=session.createQuery(hql);
		query.setString("name", name);
		int ret=query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
	}

}
