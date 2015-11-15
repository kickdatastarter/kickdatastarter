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
public class KIndividualRoom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8161892843717738432L;

	@Column(length = 50)
	private String name;
	
	@Id
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "FacilityID")
	public KFacility FacilityID;

	public KIndividualRoom(int id,String name, KFacility facility) {
        this.id=id;
		this.name = name;
		this.FacilityID = facility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KFacility getFacility() {
		return FacilityID;
	}

	public void setFacility(KFacility facility) {
		this.FacilityID = facility;
	}

}
