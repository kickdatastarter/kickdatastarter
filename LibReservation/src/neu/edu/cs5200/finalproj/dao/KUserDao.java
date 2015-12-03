package neu.edu.cs5200.finalproj.dao;

import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import neu.edu.cs5200.finalproj.model.KUser;

public class KUserDao {



private SessionFactory sessionFactory;

public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

public void InsertKuser(int id, int Role, String name, String loginid, int nuid){
		Session session = sessionFactory.openSession();
		session.beginTransaction();

	    KUser u=new KUser();
	    u.setId(id);
	    u.setRole(Role);
	    u.setName(name);
	    u.setLoginid(loginid);
	    u.setNuid(nuid);
	    session.save(u);
	    session.getTransaction().commit();
	    session.close();
}

public void DeleteKUser(String loginid){
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	
	String hql="delete KUser u " 
			  + "where u.loginid=:loginid ";
	Query query=session.createQuery(hql);
	query.setString("loginid", loginid);
	int ret=query.executeUpdate();
	session.getTransaction().commit();
	session.close();
	
}


public void UpdateKUser(String loginid, int role){
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	
	String hql="update KUser u set u.role=:role " 
			  + "where u.loginid=:loginid ";
	Query query=session.createQuery(hql);
	query.setString("loginid", loginid);
	query.setInteger("role", role);
	int ret=query.executeUpdate();
	session.getTransaction().commit();
	session.close();
}
}


