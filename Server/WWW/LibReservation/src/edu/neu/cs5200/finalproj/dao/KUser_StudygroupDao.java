package edu.neu.cs5200.finalproj.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.neu.cs5200.finalproj.model.KStudygroup;
import edu.neu.cs5200.finalproj.model.KUser;
import edu.neu.cs5200.finalproj.model.KUser_Studygroup;

public class KUser_StudygroupDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addpersoninStudygroup( int nuid,
			 int newGroupid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "select distinct u "
				+ "from KUser u "
				+ "where u.nuid = :nuid";
		Query query = session.createQuery(hql);
		query.setParameter("nuid", nuid);
		List<KUser> ls = (List<KUser>)query.list();
		if (ls ==null || ls.isEmpty())
			return;
		KUser user = ls.get(0);
		//KUser user = session.get(KUser.class,nuid);
		KStudygroup studygroup = session.get(KStudygroup.class, newGroupid);
		KUser_Studygroup newgroup = new KUser_Studygroup(user, studygroup);
		session.save(newgroup);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deletepeoplefromStudygroup(int Groupid,int usernuid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int userid;
		String hql = "select distinct u "
				+ "from KUser u "
				+ "where u.nuid = :nuid";
		Query query = session.createQuery(hql);
		query.setParameter("nuid", usernuid);
		List<KUser> ls = (List<KUser>)query.list();
		if (ls ==null || ls.isEmpty())
			return;
		KUser user = ls.get(0);
		userid = user.getId();
		hql= "delete KUser_Studygroup ksg "
			
				+"where ksg.group = :Groupid "
				
				+"and ksg.user = :Userid ";
		query=session.createQuery(hql);
		
		query.setInteger("Groupid",Groupid);
		query.setInteger("Userid",userid);
	    int ret=query.executeUpdate();
        session.getTransaction().commit();
		session.close();
	}
	
}
