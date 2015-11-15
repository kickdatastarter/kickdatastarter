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
public class KComputer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -109185852638880878L;
	@Id
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length=50)
	private String name;
	@ManyToOne 
	@JoinColumn(name="Facilityid")
	public KFacility Facilityid;
	public KComputer(int id,String name, KFacility facilityid) {
		this.id=id;
		this.name = name;
		Facilityid = facilityid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public KFacility getFacilityid() {
		return Facilityid;
	}
	public void setFacilityid(KFacility facilityid) {
		this.Facilityid = facilityid;
	}
	
	
}
