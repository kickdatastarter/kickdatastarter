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
	public static class InputParameter {
		private int nuid;
		private int facilityId;
		/**
		 * @param id
		 */
		public InputParameter(int id, int fid) {
			this.nuid = id;
			this.facilityId = fid;
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

	public boolean tryAccessFacility() throws IOException, SQLException{
		if(used==true)
			throw new IOException("ObjectAlreadyUsed");
		used=true;
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat datefmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parsedDate;
		try {
			parsedDate = datefmt.parse("2015-12-11 07:30:00");
			ts = new Timestamp(parsedDate.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EntityTransaction tx = emanager.getTransaction();
		tx.begin();
		String qry = "SELECT r "
				+ "FROM KReservation r, KUser_Studygroup us, KUser u "
				+ "WHERE (r.starttime<=?1 AND r.endtime>=?1) AND "
				+ "r.facility.id = ?2";
		Query q = this.emanager.createQuery(qry);
		q.setParameter(1, ts);
		q.setParameter(2, ip.facilityId);
		@SuppressWarnings("unchecked")
		List<KReservation> resvs = (List<KReservation>) q.getResultList();
		for(KReservation rsv4usr:resvs){
			if(rsv4usr.getMaintainstatus() == KReservation.MatStatEnum.MAINTAINING){
				throw new IOException("Managing");
			}
			qry = "SELECT u "
				+ "FROM KReservation r, KUser u, KUser_Studygroup us "
				+ "WHERE ((us.group=r.group AND us.user=u) OR (r.reserver=u)) AND "
				+ "r=?1 AND u.nuid=?2"
				+ "GROUP BY u";
			q = this.emanager.createQuery(qry);
			q.setParameter(1, rsv4usr);
			q.setParameter(2, ip.nuid);
			@SuppressWarnings("unchecked")
			List<KUser> usrs = (List<KUser>)q.getResultList();
			if(!(usrs.isEmpty()==false ^ rsv4usr.getResvstatus() == KReservation.RsvStatEnum.FIFS)){
				throw new IOException("NotAllowedAccess");
			}
			if(rsv4usr.getResvstatus() == KReservation.RsvStatEnum.RESERVED){
				rsv4usr.setResvstatus(KReservation.RsvStatEnum.INUSE);
				this.emanager.merge(rsv4usr);
			}
		}
		try {
			tx.commit();
		}
		catch(RollbackException e) {
			e.printStackTrace();
			tx.rollback();
		}
		return true;
	}
}
