package edu.neu.cs5200.finalproj.model;

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
public class KIndividualRoom extends KFacility implements Serializable {
	private static final long serialVersionUID = 8161892843717738432L;

	/**
	 * 
	 */

	private String name;
	
//	@ManyToOne
//	@JoinColumn(name = "FacilityID")
//	public KFacility FacilityID;

	public KIndividualRoom(int id,String name, KFacility facility) {
		this.name = name;
	}

	public KIndividualRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KIndividualRoom(int capacity, KFacilityType type, String name) {
		super(capacity, type);
		this.name = name;
	}

	@Column(length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
