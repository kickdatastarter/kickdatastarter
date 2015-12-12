/**
 * 
 */
package edu.neu.cs5200.finalproj.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.neu.cs5200.finalproj.model.KFacilityType;
import edu.neu.cs5200.finalproj.model.KIndividualRoom;
import edu.neu.cs5200.finalproj.model.KRole;

/**
 * @author physicsboy
 *
 */
public class KRoleDao {
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void InsertRole(String name){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		KRole i = new KRole(name);
		session.save(i);
		session.getTransaction().commit();
		session.close();
	}
	
	public void DeleteRole(String name){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql="delete KRole r "
				  + "where r.name = :name ";
		Query query=session.createQuery(hql);
		query.setString("name", name);
		int ret=query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	public KRole getRoleFromID(int id){
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql="select KRole r "
				  + "where r.id = :id ";
		Query query=session.createQuery(hql);
		query.setInteger("id", id);
        List<KRole> list = query.list();
        KRole role = (KRole) list.get(0);
		session.getTransaction().commit();
		session.close();
		return role;
	}
}
