package kickdata;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.AccessType;
@Entity
@Access(AccessType.PROPERTY)
public class KComputer extends KFacility implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -109185852638880878L;
	private String name;
	private KFacility Facilityid;
	public KComputer(){}
	public KComputer(String name, KFacility facilityid) {
		this.name = name;
		Facilityid = facilityid;
	}
	@Column(name="name",length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Id
	@ManyToOne 
	@JoinColumn(name="Facilityid")
	
	public KFacility getFacilityid() {
		return Facilityid;
	}
	public void setFacilityid(KFacility facilityid) {
		this.Facilityid = facilityid;
	}
	
	
}
