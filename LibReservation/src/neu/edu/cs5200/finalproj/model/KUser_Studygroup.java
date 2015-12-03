/**
 * 
 */
package neu.edu.cs5200.finalproj.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author physicsboy
 *
 */
@Entity
public class KUser_Studygroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3201108187000406172L;
	private KUser user;
	private KStudygroup group;
	
	public KUser_Studygroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KUser_Studygroup other = (KUser_Studygroup) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
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
	@Id
	@ManyToOne@JoinColumn(name="userid")
	public KUser getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(KUser user) {
		this.user = user;
	}
	/**
	 * @return the group
	 */
	@Id
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
