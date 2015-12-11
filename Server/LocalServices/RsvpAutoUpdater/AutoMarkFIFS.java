import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimerTask;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import edu.neu.cs5200.finalproj.model.KReservation;

/**
 * 
 */

/**
 * @author physicsboy
 *
 */
public class AutoMarkFIFS extends TimerTask {
	public static final String timeparten = "yyyy-MM-dd/hh:mm:ss";
	public final String presettime = "2015-12-10/15:30:00";
	private Timestamp ts = null;
	private boolean isDaemon = false;
	private EntityManager emanager = null;
	/**
	 * @param emanager
	 */
	public AutoMarkFIFS(EntityManager emanager) {
		super();
		this.emanager = emanager;
		Date parsedDate;
		try {
			SimpleDateFormat datefmt = new SimpleDateFormat(timeparten);
			parsedDate = datefmt.parse("presettime");
			ts = new Timestamp(parsedDate.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * @param isDaemon
	 * @param emanager
	 */
	public AutoMarkFIFS(boolean isDaemon, EntityManager emanager) {
		super();
		this.isDaemon = isDaemon;
		this.emanager = emanager;
		Date parsedDate;
		try {
			SimpleDateFormat datefmt = new SimpleDateFormat(timeparten);
			parsedDate = datefmt.parse(presettime);
			ts = new Timestamp(parsedDate.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @param ts
	 * @param isDaemon
	 * @param emanager
	 */
	public AutoMarkFIFS(Timestamp ts, boolean isDaemon, EntityManager emanager) {
		super();
		this.ts = ts;
		this.isDaemon = isDaemon;
		this.emanager = emanager;
	}

	@Override
	public void run() {
		System.out.println("Triggerd");
		SimpleDateFormat datefmt = new SimpleDateFormat(timeparten);
		if(isDaemon){
			ts = new Timestamp(System.currentTimeMillis());
			String cur = ts.toGMTString();
			System.out.println("Run in deamon mode, currentTime is"
					+ cur);
			if (System.currentTimeMillis() - scheduledExecutionTime() >= 3000){
				System.out.println("" + scheduledExecutionTime());
				return;
			}
		} else {
			System.out.println("Run in simulated mode, test trigger time is "
					+ ts.toGMTString());
		}
		EntityTransaction tx = emanager.getTransaction();
		tx.begin();
		//find reservation still on "reserved" status
		String qry = "SELECT r "
				+ "FROM KReservation r "
				+ "WHERE (r.starttime<=?1 AND r.resvstatus=?2) "
				+ "AND r.maintainstatus IS null";
		Query q1 = this.emanager.createQuery(qry);
		q1.setParameter(1, ts);
		q1.setParameter(2, KReservation.RsvStatEnum.RESERVED);
		List<KReservation> resvs = null;
		try {
			resvs = (List<KReservation>) q1.getResultList();
		} catch (NoResultException e){
			// TODO Auto-generated catch block
			System.out.println("no reservation with status 'RESERVED' founded");
		}
		for(KReservation rsv4usr:resvs){
			System.out.println("Found an NOSHOW reservation, " +
					"Reserver NUID: " +rsv4usr.getReserver().getNuid() +
					"\r\nStartTime: " + datefmt.format(rsv4usr.getStarttime()) +
					", EndTime: " + datefmt.format(rsv4usr.getEndtime()) + 
					"\r\nReservation Status: " + rsv4usr.getResvstatus() +
					", Maintaining Status: " + rsv4usr.getMaintainstatus());
			rsv4usr.setResvstatus(KReservation.RsvStatEnum.FIFS);
			this.emanager.merge(rsv4usr);
			System.out.println("Set this reservation to FIFS");
		}
		try {
			tx.commit();
		}
		catch(RollbackException e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public boolean isDaemon() {
		return isDaemon;
	}

	public void setDaemon(boolean isDaemon) {
		this.isDaemon = isDaemon;
	}
	
}
