package kickdata;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Access(AccessType.PROPERTY)
public class KRights implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3517860063594773274L;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date max_time_per_resv;
	
	@Column(nullable=false)
	private int max_resv_per_day;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date max_total_ongoing_resv;
	
	@Id
	@ManyToOne
	@JoinColumn(name="role")
	private int role;
	
	@Id
	@ManyToOne
	@JoinColumn(name="facilityType")
	private int facilityType;

	
	public KRights(Date max_time_per_resv, int max_resv_per_day, Date max_total_ongoing_resv, int role,
			int facilityType) {
		super();
		this.max_time_per_resv = max_time_per_resv;
		this.max_resv_per_day = max_resv_per_day;
		this.max_total_ongoing_resv = max_total_ongoing_resv;
		this.role = role;
		this.facilityType = facilityType;
	}

	public Date getMax_time_per_resv() {
		return max_time_per_resv;
	}

	public void setMax_time_per_resv(Date max_time_per_resv) {
		this.max_time_per_resv = max_time_per_resv;
	}

	public int getMax_resv_per_day() {
		return max_resv_per_day;
	}

	public void setMax_resv_per_day(int max_resv_per_day) {
		this.max_resv_per_day = max_resv_per_day;
	}

	public Date getMax_total_ongoing_resv() {
		return max_total_ongoing_resv;
	}

	public void setMax_total_ongoing_resv(Date max_total_ongoing_resv) {
		this.max_total_ongoing_resv = max_total_ongoing_resv;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(int facilityType) {
		this.facilityType = facilityType;
	}
	
	
}