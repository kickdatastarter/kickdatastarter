package edu.neu.cs5200.finalproj.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.neu.cs5200.finalproj.model.KComputer;
import edu.neu.cs5200.finalproj.model.KFacility;
import edu.neu.cs5200.finalproj.model.KFacilityType;
import edu.neu.cs5200.finalproj.model.KUser;

import java.util.*;

public class KComputerDao {
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void InsertComputer(int capacity, String name){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
        
		String hql = "from KFacilityType type where type.id = 0";
        Query query = session.createQuery(hql);
        List list = query.list();
        KFacilityType type = (KFacilityType) list.get(0);
	   
	    KComputer c = new KComputer(capacity, type, name);
	    session.save(c);
	    session.getTransaction().commit();
	    session.close();
}
	
	public void DeleteComputer(String name){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql= "delete KComputer c "
				  + "where c.name = :name ";
				  
		Query query=session.createQuery(hql);
		query.setString("name", name);
		int ret=query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
	}

}
