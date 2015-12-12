package edu.neu.cs5200.finalproj.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.TreeMap;

import javax.persistence.EntityTransaction;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import edu.neu.cs5200.finalproj.model.KComputer;
import edu.neu.cs5200.finalproj.model.KFacility;
import edu.neu.cs5200.finalproj.model.KGroupRoom;
import edu.neu.cs5200.finalproj.model.KIndividualRoom;
import edu.neu.cs5200.finalproj.model.KReservation;
import edu.neu.cs5200.finalproj.model.KRole;
import edu.neu.cs5200.finalproj.model.KStudygroup;
import edu.neu.cs5200.finalproj.model.KUser;
import edu.neu.cs5200.finalproj.model.KReservation.MatStatEnum;
import edu.neu.cs5200.finalproj.model.KReservation.RsvStatEnum;

public class KReservationDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<KReservation> getMyResv(KUser user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "select distinct r from KReservation r, KUser_Studygroup us " + "where r.reserver = :curUser "
				+ "or (us.user = :curUser and r.group = us.group) ";

		Query query = session.createQuery(hql);
		query.setParameter("curUser", user);
		List<KReservation> resvs = (List<KReservation>) query.list();

		session.getTransaction().commit();
		session.close();
		return resvs;
	}

	public Map<String, Map<Integer, String>> getResvData(String attendence, String date) throws ParseException {
		// Prepare parameters
		int month, day, year;
		if (date == null) {
			Calendar now = Calendar.getInstance();
			year = now.get(Calendar.YEAR);
			month = now.get(Calendar.MONTH);
			day = now.get(Calendar.DAY_OF_MONTH);
		} else {
			String[] splits = date.split("/");
			month = Integer.parseInt(splits[0]) - 1;
			day = Integer.parseInt(splits[1]);
			year = Integer.parseInt(splits[2]);	
		}
		Calendar startCal = new GregorianCalendar(year, month, day, 0, 0, 0);
		Calendar endCal = new GregorianCalendar(year, month, day, 23, 59, 59);
		Timestamp startTimestamp = new Timestamp(startCal.getTime().getTime());
		Timestamp endTimestamp = new Timestamp(endCal.getTime().getTime());

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Transaction 1: get all facilities
		String hql = "from KFacility where capacity >= :capacity";
		Query query1 = session.createQuery(hql);
		query1.setInteger("capacity", attendence==null ? 0 : Integer.parseInt(attendence));
		List<KFacility> facilities = (List<KFacility>) query1.list();		
		
		// Transaction 2: get this date's reservations
		hql = "from KReservation " + "where starttime >= :starttime "
					+ "and endtime <= :endtime "
					+ "and facility.capacity >= :capacity ";
		Query query2 = session.createQuery(hql);
		query2.setTimestamp("starttime", startTimestamp);
		query2.setTimestamp("endtime", endTimestamp);
		query2.setInteger("capacity", attendence==null ? 0 : Integer.parseInt(attendence));
		List<KReservation> resvs = (List<KReservation>) query2.list();

		session.getTransaction().commit();
		session.close();

		Map<String, Map<Integer, String>> result = new TreeMap<String, Map<Integer, String>>();
		for (KFacility fac : facilities) {	
			String facName = "";
			if(fac instanceof KComputer) {
				facName = ((KComputer)fac).getName();
			} else if(fac instanceof KGroupRoom) {
				facName = ((KGroupRoom)fac).getName();
			} else if(fac instanceof KIndividualRoom) {
				facName = ((KIndividualRoom)fac).getName();
			}
			if (!result.containsKey(facName)) {
				result.put(facName, new TreeMap<Integer, String> ());
			}
		}
		for (KReservation resv : resvs) {
			// Get facility name
			KFacility fac = resv.getFacility();
			String facName = "";
			if(fac instanceof KComputer) {
				facName = ((KComputer)fac).getName();
			} else if(fac instanceof KGroupRoom) {
				facName = ((KGroupRoom)fac).getName();
			} else if(fac instanceof KIndividualRoom) {
				facName = ((KIndividualRoom)fac).getName();
			}
			
			// Get all occupied hours
			Calendar retrievedStartCal = new GregorianCalendar();
			Calendar retrievedEndCal = new GregorianCalendar();
			retrievedStartCal.setTimeInMillis(resv.getStarttime().getTime());
			retrievedEndCal.setTimeInMillis(resv.getEndtime().getTime());
			int startHour = retrievedStartCal.get(Calendar.HOUR_OF_DAY);
			int endHour = retrievedEndCal.get(Calendar.HOUR_OF_DAY);
			
			for (int i=startHour; i<=endHour; i++) {
				if (resv.getMaintainstatus() == KReservation.MatStatEnum.MAINTAINING) {
					result.get(facName).put(i, "MAINTAINING");
				} else if (result.get(facName).get(i) != "MAINTAINING") {
					result.get(facName).put(i, "RESERVED");
				}
			}
		}
	
		return result;
	}

	public void insertResv(String date, String startHour, String endHour, String facilityID, String studygroupID,
			int userID, KReservation.RsvStatEnum resvStatus, KReservation.MatStatEnum maintainStatus) {
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
		Transaction tx = session.getTransaction();
		tx.begin();

		KFacility facility = session.get(KFacility.class, Integer.parseInt(facilityID));
		KUser user = session.get(KUser.class, userID);
		KStudygroup studygroup = studygroupID.equals("null") ? null
				: session.get(KStudygroup.class, Integer.parseInt(studygroupID));

		String desc = new String(month+"/"+day+" "+startTimestamp.getHours()+" to "+endTimestamp.getHours());
		KReservation resv = new KReservation(desc, startTimestamp, endTimestamp, facility, user, studygroup, resvStatus,
				maintainStatus);

		session.save(resv);
		try {
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void deleteResv(int resvID) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		KReservation resv = session.get(KReservation.class, resvID);
		session.delete(resv);
		
		try {
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}	}

	public void updateResv(String date, String startHour, String endHour, String resvID) {
		// Prepare parameters
		String[] splits = date.split("/");
		int month = Integer.parseInt(splits[0]) - 1;
		int day = Integer.parseInt(splits[1]);
		int year = Integer.parseInt(splits[2]);
		Calendar startCal = new GregorianCalendar(year, month, day, Integer.parseInt(startHour), 0);
		Calendar endCal = new GregorianCalendar(year, month, day, Integer.parseInt(endHour), 0);
		Timestamp startTimestamp = new Timestamp(startCal.getTime().getTime());
		Timestamp endTimestamp = new Timestamp(endCal.getTime().getTime());

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		KReservation resv = session.get(KReservation.class, Integer.parseInt(resvID));
		resv.setStarttime(startTimestamp);
		resv.setEndtime(endTimestamp);
		session.update(resv);

		try {
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void SetFacilityState(int facilityid, String maintainstatus) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "update KReservation r set r.maintainstatus = :maintainstatus "
				+ "where r.facility.id = :facilityid ";

		Query query = session.createQuery(hql);
		query.setInteger("facilityid", facilityid);
		query.setString("maintainstatus", maintainstatus);
		int ret = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

}
