import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import edu.neu.cs5200.finalproj.model.KReservation;
import edu.neu.cs5200.finalproj.model.KUser;

/**
 * 
 */

/**
 * @author physicsboy
 *
 */
public class SwipeCard {
	private static final String timeparten = "yyyy-MM-dd/hh:mm:ss";
	public static enum AccessCodeEnum {OK, MAINTAINING, LATE, RESERVEDBYOTHERS, OBJECTUSED};
	public static class InputParameter {
		private int nuid;
		private int facilityId;
		private Timestamp datetime;
		/**
		 * @param id
		 */
		public InputParameter(int id, int fid) {
			super();
			this.nuid = id;
			this.facilityId = fid;
			String datetime = "2015-12-11/07:30:00";
			SimpleDateFormat datefmt = new SimpleDateFormat(timeparten);
			Date parsedDate;
			Timestamp ts=null;
			try {
				parsedDate = datefmt.parse(datetime);
				ts = new Timestamp(parsedDate.getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.datetime = ts;
		}
		/**
		 * @param nuid
		 * @param facilityId
		 * @param datetime
		 */
		public InputParameter(int nuid, int facilityId, String datetime) {
			super();
			this.nuid = nuid;
			this.facilityId = facilityId;
			Timestamp ts=null;
			if(datetime.compareTo("NOW") == 0){
				ts = new Timestamp(System.currentTimeMillis());
			} else {
				SimpleDateFormat datefmt = new SimpleDateFormat(timeparten);
				Date parsedDate;
				try {
					parsedDate = datefmt.parse(datetime);
					ts = new Timestamp(parsedDate.getTime());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.datetime = ts;
		}
		
	}
	private InputParameter ip = null;
	private EntityManager emanager = null;
	private boolean used = false;
	/**
	 * @param ip
	 * @param emanager
	 */
	public SwipeCard(InputParameter ip, EntityManager emanager) {
		super();
		this.ip = ip;
		this.emanager = emanager;
	}

	public AccessCodeEnum tryAccessFacility () throws IOException, SQLException{
		AccessCodeEnum accesscode = AccessCodeEnum.OK;
		if(used==true)
			return AccessCodeEnum.OBJECTUSED;
		used=true;
		SimpleDateFormat datefmt = new SimpleDateFormat(timeparten);
		String time_s = datefmt.format(new Date(ip.datetime.toGMTString()));
		System.out.println("The Time you indicated(simulate) is: " + time_s);
		System.out.println("The Facility id you indicated(simulate) is: " + ip.facilityId);
		System.out.println("The NUID of your card(simulate) is: "+ip.nuid);
		
		EntityTransaction tx = emanager.getTransaction();
		tx.begin();
		String qry = "SELECT r "
				+ "FROM KReservation r "
				+ "WHERE (r.starttime<=?1 AND r.endtime>=?1) AND "
				+ "r.facility.id = ?2";
		Query q = this.emanager.createQuery(qry);
		q.setParameter(1, ip.datetime);
		q.setParameter(2, ip.facilityId);
		@SuppressWarnings("unchecked")
		List<KReservation> resvs = q.getResultList();
CheckReservation:
		for(KReservation rsv4usr:resvs){
			System.out.println("Found a reservation, " +
					"Reserver NUID: " +rsv4usr.getReserver().getNuid() +
					"\r\nStartTime: " + datefmt.format(rsv4usr.getStarttime()) +
					", EndTime: " + datefmt.format(rsv4usr.getEndtime()) + 
					"\r\nReservation Status: " + rsv4usr.getResvstatus() +
					", Maintaining Status: " + rsv4usr.getMaintainstatus());
			if(rsv4usr.getMaintainstatus() == KReservation.MatStatEnum.MAINTAINING){
				accesscode = AccessCodeEnum.MAINTAINING;
				break CheckReservation;
			}
			//Find users allowed by this reservation record
			qry = "SELECT u "
				+ "FROM KReservation r, KUser u, KUser_Studygroup us "
				+ "WHERE ((us.group=r.group AND us.user=u) OR (r.reserver=u)) AND "
				+ "r=?1 AND u.nuid=?2 "
				+ "GROUP BY u";
			q = this.emanager.createQuery(qry);
			q.setParameter(1, rsv4usr);
			q.setParameter(2, ip.nuid);
			try{
				q.getSingleResult();
				if(rsv4usr.getResvstatus() == KReservation.RsvStatEnum.FIFS){
					return AccessCodeEnum.LATE;
				} else if(rsv4usr.getResvstatus() == KReservation.RsvStatEnum.RESERVED){
		 		//update Reservation record
					rsv4usr.setResvstatus(KReservation.RsvStatEnum.INUSE);
					this.emanager.merge(rsv4usr);
					continue;
				}
			} catch (javax.persistence.NoResultException e){
				accesscode = AccessCodeEnum.RESERVEDBYOTHERS;
				continue;
			}
		}
		if(resvs.isEmpty()==true){
			System.out.println("No ongoding Reservation found");
		}
		try {
			tx.commit();
		}
		catch(RollbackException e) {
			e.printStackTrace();
			tx.rollback();
		}
		return accesscode;
	}
}
