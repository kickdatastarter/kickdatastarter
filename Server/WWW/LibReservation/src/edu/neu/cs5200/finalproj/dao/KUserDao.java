package edu.neu.cs5200.finalproj.dao;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.neu.cs5200.finalproj.model.KRole;
import edu.neu.cs5200.finalproj.model.KUser;

public class KUserDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public KUser signin(String loginName, String loginPassword) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "from KUser where loginid = :loginid and loginPassword = :loginPassword";
		Query query = session.createQuery(hql);
		query.setString("loginid", loginName);
		query.setString("loginPassword", loginPassword);
		List<KUser> list = (List<KUser>) query.list();

		return list.isEmpty() ? null : list.get(0);
	}
	
	public KUser findidUseNUid(int nuid){
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "from KUser where nuid = :nuid";
		Query query = session.createQuery(hql);
		query.setInteger("nuid", nuid);
		List<KUser> list = (List<KUser>) query.list();
		return list.isEmpty() ? null : list.get(0);
	}

	public void InsertKuser(KRole Role, String name, String loginid, int nuid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		KUser u = new KUser();
		u.setRole(Role);
		u.setName(name);
		u.setLoginid(loginid);
		u.setNuid(nuid);
		session.save(u);
		session.getTransaction().commit();
		session.close();
	}

	public void DeleteKUser(String loginid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "delete KUser u " + "where u.loginid=:loginid ";
		Query query = session.createQuery(hql);
		query.setString("loginid", loginid);
		int ret = query.executeUpdate();
		session.getTransaction().commit();
		session.close();

	}

	public void UpdateKUser(String loginid, int role) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "update KUser u set u.role=:role " + "where u.loginid=:loginid ";
		Query query = session.createQuery(hql);
		query.setString("loginid", loginid);
		query.setInteger("role", role);
		int ret = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
}
