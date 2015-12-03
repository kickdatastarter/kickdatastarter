package neu.edu.cs5200.finalproj.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import neu.edu.cs5200.finalproj.model.KFacility;
import neu.edu.cs5200.finalproj.model.KReservation;
import neu.edu.cs5200.finalproj.model.KRole;
import neu.edu.cs5200.finalproj.model.KStudygroup;
import neu.edu.cs5200.finalproj.model.KUser;
import neu.edu.cs5200.finalproj.model.KReservation.MatStatEnum;
import neu.edu.cs5200.finalproj.model.KReservation.RsvStatEnum;

public class KReservationDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<KReservation> getAllResv(int userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "from KReservation where id = :userId ";
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		List<KReservation> resvs = (List<KReservation>) query.list();
		
		session.getTransaction().commit();
		session.close();
		return resvs;
	}
	
	public Map<String, Set<Integer> > getResvData() throws ParseException {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
//		Calendar startCal = Calendar.getInstance();
//		startCal.set(Calendar.HOUR_OF_DAY, 0);
//		startCal.set(Calendar.MINUTE, 0);
//		startCal.set(Calendar.SECOND, 0);
//		Date startOfToday = startCal.getTime();
//		
//		Calendar endCal = Calendar.getInstance();
//		endCal.set(Calendar.HOUR_OF_DAY, 23);
//		endCal.set(Calendar.MINUTE, 59);
//		endCal.set(Calendar.SECOND, 59);
//		Date endOfToday = endCal.getTime();			 
		
		String hql = "from KReservation "
				+ "where starttime = :startOfToday ";
				//+ "and endtime between :startOfToday and :endOfToday ";
		Query query = session.createSQLQuery(hql);
		
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//query.setDate("startOfToday", new Date(1000000000));
		//query.setParameter("endOfToday", "2015-11-24 07:00:00");

		query.setTimestamp("startOfToday", new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		List<KReservation> resvs = (List<KReservation>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		Map<String, Set<Integer> > result = new TreeMap<String, Set<Integer>> ();
		result.put("K001", new HashSet<Integer> (Arrays.asList(1,3,5,6)));
		result.put("K002", new HashSet<Integer> (Arrays.asList(2,4,7,9)));
		result.put("K003", new HashSet<Integer> (Arrays.asList(1,5,10,12)));
				
		return result;
	}
	
	public void insertResv(String date, String startHour, String endHour, String facilityID, String studygroupID, int userID) {
		// Prepare parameters
		String[] splits = date.split("/");
		int month = Integer.parseInt(splits[0]) - 1;
		int day = Integer.parseInt(splits[1]);
		int year = Integer.parseInt(splits[2]);
		Calendar startCal = new GregorianCalendar(year, month, day, Integer.parseInt(startHour), 0);
		Calendar endCal = new GregorianCalendar(year, month, day, Integer.parseInt(endHour), 0);
		Timestamp startTimestamp = new Timestamp(startCal.getTime().getTime());
		Timestamp endTimestamp = new Timestamp(endCal.getTime().getTime());
		
		// Session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		KFacility facility = session.get(KFacility.class, Integer.parseInt(facilityID));
		KUser user = session.get(KUser.class, userID);
		KStudygroup studygroup = studygroupID==null ? null : session.get(KStudygroup.class, Integer.parseInt(studygroupID));
		
		KReservation resv = new KReservation("", 
											 startTimestamp, 
											 endTimestamp,
				                             facility, user, studygroup, KReservation.RsvStatEnum.FIFS, KReservation.MatStatEnum.AVAILABLE);
		
		session.save(resv);
		session.getTransaction().commit();
		session.close();
	}
	
	public void SetFacilityState(int facilityid, String maintainstatus){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql="update KReservation r set r.maintainstatus = :maintainstatus " 
				  + "where r.facility.id = :facilityid ";
		//String hql1="save KReservation r set r.maintainstatus = :maintainstatus ";
		
		Query query=session.createQuery(hql);
		query.setInteger("facilityid", facilityid);
		query.setString("maintainstatus", maintainstatus);
		int ret=query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

}
