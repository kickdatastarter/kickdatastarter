package kickdata;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.AccessType;
@Entity
@Access(AccessType.PROPERTY)
public class KComputer {
	private int id;
	private String name;
	private KFacility Facilityid;
	public KComputer(int id,String name, KFacility facilityid) {
		this.id=id;
		this.name = name;
		Facilityid = facilityid;
	}
	@Id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name",length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne 
	@JoinColumn(name="Facilityid")
	
	public KFacility getFacilityid() {
		return Facilityid;
	}
	public void setFacilityid(KFacility facilityid) {
		this.Facilityid = facilityid;
	}
	
	
}
