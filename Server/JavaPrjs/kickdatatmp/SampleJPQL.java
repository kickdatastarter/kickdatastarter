import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import kickdata.KStudygroup;

/**
 * 
 */

/**
 * @author physicsboy
 *
 */
public class SampleJPQL {
	public static class InputParameter {
		private int id;

		/**
		 * @param id
		 */
		public InputParameter(int id) {
			this.id = id;
		}
		
	}
	private InputParameter ip = null;
	private EntityManager emanager = null;
	/**
	 * @param ip
	 * @param emanager
	 */
	public SampleJPQL(InputParameter ip, EntityManager emanager) {
		super();
		this.ip = ip;
		this.emanager = emanager;
	}

	@SuppressWarnings("unchecked")
	public List<KStudygroup> oneSample() throws IOException, SQLException{
		ArrayList<KStudygroup> le = new ArrayList<KStudygroup>();
		EntityTransaction tx = emanager.getTransaction();
		tx.begin();
		String qry = "SELECT sg FROM KStudygroup sg WHERE sg.id>?1";
		Query q = this.emanager.createQuery(qry);
		q.setParameter(1, ip.id);
		le.addAll((List<KStudygroup>)q.getResultList()); //return a List, add them all to other list
		try {
			tx.commit();
		}
		catch(RollbackException e) {
			e.printStackTrace();
			tx.rollback();
		}
		return le;
	}
}
