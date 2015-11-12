/**
 * 
 */
package kickdata;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author physicsboy
 *
 */
public class KUser_Studygroup {
	private KUser user;
	private KStudygroup group;
	/**
	 * @param user
	 * @param group
	 */
	public KUser_Studygroup(KUser user, KStudygroup group) {
		this.user = user;
		this.group = group;
	}
	/**
	 * @return the user
	 */
	@ManyToOne@JoinColumn(name="userid")
	public KUser getReserver() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setReserver(KUser user) {
		this.user = user;
	}
	/**
	 * @return the group
	 */
	@ManyToOne@JoinColumn(name="groupid")
	public KStudygroup getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(KStudygroup group) {
		this.group = group;
	}
	
}
