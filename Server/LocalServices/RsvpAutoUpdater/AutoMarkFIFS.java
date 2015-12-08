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

/**
 * 
 */

/**
 * @author physicsboy
 *
 */
public class AutoMarkFIFS implements Runnable {
	private EntityManager emanager = null;

	/**
	 * @param emanager
	 */
	public AutoMarkFIFS(EntityManager emanager) {
		super();
		this.emanager = emanager;
	}

	@Override
	public void run() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat datefmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parsedDate;
		try {
			parsedDate = datefmt.parse("2015-12-10 15:30:00");
			ts = new Timestamp(parsedDate.getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		EntityTransaction tx = emanager.getTransaction();
		tx.begin();
		String qry = "SELECT r "
				+ "FROM KReservation r "
				+ "WHERE (r.starttime<=?1 AND r.resvstatus=?2)";
		Query q1 = this.emanager.createQuery(qry);
		q1.setParameter(1, ts);
		q1.setParameter(2, KReservation.RsvStatEnum.RESERVED);
		@SuppressWarnings("unchecked")
		List<KReservation> resvs = q1.getResultList();
		for(KReservation rsv4usr:resvs){
			rsv4usr.setResvstatus(KReservation.RsvStatEnum.FIFS);
			this.emanager.merge(rsv4usr);
		}
		try {
			tx.commit();
		}
		catch(RollbackException e) {
			e.printStackTrace();
			tx.rollback();
		}

	}
	
}
