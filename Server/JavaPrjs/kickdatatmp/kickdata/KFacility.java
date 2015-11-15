package kickdata;


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
public class KFacility {
	
	private int id;
	private int capacity;
	private KFacilityType type;
	
	public KFacility(int id,int capacity, KFacilityType type) {
	    this.id=id;
		this.capacity = capacity;
		this.type = type;
	}
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column (nullable=false)
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@ManyToOne 
	@JoinColumn(name="type",nullable=false)
	public KFacilityType getType() {
		return type;
	}
	public void setType(KFacilityType type) {
		this.type = type;
	}
	@OneToMany(mappedBy="Facilityid")
	private Set<KIndividualRoom> KIndividualRoom;
	private Set<KGroupRoom> KGroupRoom;
	private Set<KComputer> KCmputer;
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
	
	
	
	
	

}
