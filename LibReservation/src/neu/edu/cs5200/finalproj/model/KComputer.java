package neu.edu.cs5200.finalproj.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.AccessType;

@Entity
@Access(AccessType.PROPERTY)
@PrimaryKeyJoinColumn(name = "Facilityid")
public class KComputer extends KFacility implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -109185852638880878L;
	private String name;
	//private KFacility Facilityid;
	public KComputer(){}
	public KComputer(int capacity, KFacilityType type, String name) {
		super(capacity, type);
		this.name = name;
	}
	
	@Column(name="name",length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	@Id
//	@ManyToOne 
//	@JoinColumn(name="Facilityid")
	
	
}
