/**
 * 
 */
package edu.neu.cs5200.finalproj.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * @author physicsboy
 *
 */
@Entity
@Access(AccessType.PROPERTY)
public class KStudygroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4250982436029967725L;
	private int id;
	private String name;
	private Collection<KUser_Studygroup> user_sg_lst;
	
	
	/**
	 * @param id
	 * @param name
	 */
	public KStudygroup(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	@Basic
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade={CascadeType.REMOVE, CascadeType.REFRESH}, mappedBy="group")
	public Collection<KUser_Studygroup> getUser_sg_lst() {
		return user_sg_lst;
	}
	public void setUser_sg_lst(Collection<KUser_Studygroup> user_sg_lst) {
		this.user_sg_lst = user_sg_lst;
	}
	
	public KStudygroup() {
		super();
		// TODO Auto-generated constructor stub
	}

}
