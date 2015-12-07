/**
 * 
 */
package edu.neu.cs5200.finalproj.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author physicsboy
 *
 */
@Entity
//@IdClass(KUser_StudygroupKey.class)
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
	//@Id
	@ManyToOne
	@JoinColumn(name="userid")
	
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
	//@Id
	@ManyToOne
	@JoinColumn(name="groupid")
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
