package neu.edu.cs5200.finalproj.dao;

import neu.edu.cs5200.finalproj.model.KStudygroup;
import neu.edu.cs5200.finalproj.model.KUser;
import neu.edu.cs5200.finalproj.model.KUser_Studygroup;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class KUser_StudygroupDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addpersoninStudygroup( int user1,
			 int newGroupid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		KUser user = session.get(KUser.class,user1);
		KStudygroup studygroup = session.get(KStudygroup.class, newGroupid);
		KUser_Studygroup newgroup = new KUser_Studygroup(user, studygroup);
		session.save(newgroup);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deletepeoplefromStudygroup(int Groupid,int userid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql= "delete KUser_Studygroup ksg "
			
				+"where ksg.group = :Groupid "
				
				+"and ksg.user = :Userid ";
		Query query=session.createQuery(hql);
		
		query.setInteger("Groupid",Groupid);
		query.setInteger("Userid",userid);
	    int ret=query.executeUpdate();
        session.getTransaction().commit();
		session.close();
	}
	
}
