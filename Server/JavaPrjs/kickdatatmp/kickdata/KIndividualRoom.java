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
public class KIndividualRoom {
	private int id;
	private String name;
	private KFacility FacilityID;
	public KIndividualRoom(int id,String name, KFacility facility) {
        this.id=id;
		this.name = name;
		this.FacilityID = facility;
	}
	
	
	@Id
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name",length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name = "FacilityID")
	public KFacility getFacility() {
		return FacilityID;
	}

	public void setFacility(KFacility facility) {
		this.FacilityID = facility;
	}

}
