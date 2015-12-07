/**
 * 
 */
package edu.neu.cs5200.finalproj.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

/**
 * @author physicsboy
 *
 */
@Entity
@Access(AccessType.PROPERTY)
public class KReservation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5520309104330511950L;
	public static enum RsvStatEnum {RESERVED, INUSE, FIFS};
	public static enum MatStatEnum {MAINTAINING, AVAILABLE};
	private int id;
	private String description;
	private Timestamp starttime;
	private Timestamp endtime;
	private KFacility facility;
	private KUser reserver;
	private KStudygroup group;
	private RsvStatEnum resvstatus;
	private MatStatEnum maintainstatus;
	/**
	 * @param id
	 * @param description
	 * @param starttime
	 * @param endtime
	 * @param facility
	 * @param reserver
	 * @param group
	 * @param resvstatus
	 * @param maintainstatus
	 */
	public KReservation(String description, Timestamp starttime, Timestamp endtime, KFacility facility, KUser reserver,
			KStudygroup group, RsvStatEnum resvstatus, MatStatEnum maintainstatus) {
		this.description = description;
		this.starttime = starttime;
		this.endtime = endtime;
		this.facility = facility;
		this.reserver = reserver;
		this.group = group;
		this.resvstatus = resvstatus;
		this.maintainstatus = maintainstatus;
	}
	
	public KReservation() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the description
	 */
	@Basic
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the starttime
	 */
	@Basic
	@Column(name = "starttime")
	public Timestamp getStarttime() {
		return starttime;
	}
	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	/**
	 * @return the endtime
	 */
	@Basic
	@Column(name = "endtime")
	public Timestamp getEndtime() {
		return endtime;
	}
	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	/**
	 * @return the facility
	 */
	@ManyToOne
	@JoinColumn(name="facility_id")
	public KFacility getFacility() {
		return facility;
	}
	/**
	 * @param facility the facility to set
	 */
	public void setFacility(KFacility facility) {
		this.facility = facility;
	}
	/**
	 * @return the reserver
	 */
	@ManyToOne
	@JoinColumn(name="reserver_id")
	public KUser getReserver() {
		return reserver;
	}
	/**
	 * @param reserver the reserver to set
	 */
	public void setReserver(KUser reserver) {
		this.reserver = reserver;
	}
	/**
	 * @return the group
	 */
	@ManyToOne
	@JoinColumn(name="group_id")
	public KStudygroup getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(KStudygroup group) {
		this.group = group;
	}
	/**
	 * @return the resvstatus
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	public RsvStatEnum getResvstatus() {
		return resvstatus;
	}
	/**
	 * @param resvstatus the resvstatus to set
	 */
	public void setResvstatus(RsvStatEnum resvstatus) {
		this.resvstatus = resvstatus;
	}
	/**
	 * @return the maintainstatus
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	public MatStatEnum getMaintainstatus() {
		return maintainstatus;
	}
	/**
	 * @param maintainstatus the maintainstatus to set
	 */
	public void setMaintainstatus(MatStatEnum maintainstatus) {
		this.maintainstatus = maintainstatus;
	}
	
}
