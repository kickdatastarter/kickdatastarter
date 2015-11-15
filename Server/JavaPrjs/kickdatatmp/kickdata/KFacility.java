package kickdata;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.AccessType;


@Entity
@Access(AccessType.PROPERTY)
public class KFacility implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4956573759662634954L;
	@Id
	private int id;
	@Column (nullable=false)
	private int capacity;
	@ManyToOne 
	@JoinColumn(name="type",nullable=false)
	public KFacilityType type;
	
	@OneToMany(mappedBy="Facilityid")
	private Set<KIndividualRoom> KIndividualRoom;
	private Set<KGroupRoom> KGroupRoom;
	private Set<KComputer> KCmputer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public KFacilityType getType() {
		return type;
	}
	public void setType(KFacilityType type) {
		this.type = type;
	}
	public Set<KIndividualRoom> getKIndividualRoom() {
		return KIndividualRoom;
	}
	public void setKIndividualRoom(Set<KIndividualRoom> kIndividualRoom) {
		this.KIndividualRoom = kIndividualRoom;
	}
	public Set<KGroupRoom> getKGroupRoom() {
		return KGroupRoom;
	}
	public void setKGroupRoom(Set<KGroupRoom> kGroupRoom) {
		this.KGroupRoom = kGroupRoom;
	}
	public Set<KComputer> getKCmputer() {
		return KCmputer;
	}
	public void setKCmputer(Set<KComputer> kCmputer) {
		this.KCmputer = kCmputer;
	}
	public KFacility(int id,int capacity, KFacilityType type) {
	    this.id=id;
		this.capacity = capacity;
		this.type = type;
	}
	
	
	
	

}
