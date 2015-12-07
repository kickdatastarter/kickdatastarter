package edu.neu.cs5200.finalproj.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@IdClass(KRightsKey.class)
@Access(AccessType.PROPERTY)
public class KRights implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3517860063594773274L;


	private Date max_time_per_resv;
	
	private int max_resv_per_day;
	
	private Date max_total_ongoing_resv;
	
	private KRole role;
	
	private KFacilityType facilityType;

	
	public KRights(Date max_time_per_resv, int max_resv_per_day, Date max_total_ongoing_resv, KRole role,
			KFacilityType facilityType) {
		super();
		this.max_time_per_resv = max_time_per_resv;
		this.max_resv_per_day = max_resv_per_day;
		this.max_total_ongoing_resv = max_total_ongoing_resv;
		this.role = role;
		this.facilityType = facilityType;
	}

	@Column(nullable=false)
	@Temporal(TemporalType.TIME)
	public Date getMax_time_per_resv() {
		return max_time_per_resv;
	}

	public void setMax_time_per_resv(Date max_time_per_resv) {
		this.max_time_per_resv = max_time_per_resv;
	}

	@Column(nullable=false)
	public int getMax_resv_per_day() {
		return max_resv_per_day;
	}

	public void setMax_resv_per_day(int max_resv_per_day) {
		this.max_resv_per_day = max_resv_per_day;
	}

	@Column(nullable=false)
	@Temporal(TemporalType.TIME)
	public Date getMax_total_ongoing_resv() {
		return max_total_ongoing_resv;
	}

	public void setMax_total_ongoing_resv(Date max_total_ongoing_resv) {
		this.max_total_ongoing_resv = max_total_ongoing_resv;
	}

	//@Id
	@ManyToOne
	@JoinColumn(name="role")
	public KRole getRole() {
		return role;
	}

	public void setRole(KRole role) {
		this.role = role;
	}

	//@Id
	@ManyToOne
	@JoinColumn(name="facilityType")
	public KFacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(KFacilityType facilityType) {
		this.facilityType = facilityType;
	}

	public KRights() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}