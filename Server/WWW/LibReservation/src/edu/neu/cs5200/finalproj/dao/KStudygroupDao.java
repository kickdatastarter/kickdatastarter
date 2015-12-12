package edu.neu.cs5200.finalproj.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.neu.cs5200.finalproj.model.KFacility;
import edu.neu.cs5200.finalproj.model.KReservation;
import edu.neu.cs5200.finalproj.model.KStudygroup;
import edu.neu.cs5200.finalproj.model.KUser;
import edu.neu.cs5200.finalproj.model.KUser_Studygroup;

public class KStudygroupDao {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Map<KStudygroup, List<KUser> > getMyStudygroup(int userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "select sg, u from KStudygroup sg, KUser_Studygroup usg1, KUser_Studygroup usg2, KUser u "
				+ "where usg1.user = :userId "
				+ "and usg1.group = usg2.group "
				+ "and usg2.group = sg "
				+ "and usg2.user = u ";
//				+ "and u != :userId";
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		List<Object[]> list = (List<Object[]>)query.list();
		
		Map<KStudygroup, List<KUser> > result = new TreeMap<KStudygroup, List<KUser> >();
		for(Object[] object : list) {
			KStudygroup sg = (KStudygroup)object[0];
			KUser usr = (KUser)object[1];
			if(result.containsKey(sg)) {
				result.get(sg).add(usr);
			} else {
				result.put(sg, new ArrayList<KUser>());
				result.get(sg).add(usr);
			}
		}
		
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public int addStudygroup(String newGroupName) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		KStudygroup group = new KStudygroup(newGroupName);
		int insertedID = (Integer)session.save(group);
     	session.getTransaction().commit();
		session.close();
		return insertedID;
	}
	
	public void deleteStudygroup(String Groupname) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql= "from KStudygroup "
				+"where name = :Groupname ";
		Query query=session.createQuery(hql);
		
		query.setString("Groupname",Groupname);
		List<KStudygroup> lst = (List<KStudygroup>) query.list();
		session.delete(lst.get(0));
        session.getTransaction().commit();
		session.close();
	}
	
}
