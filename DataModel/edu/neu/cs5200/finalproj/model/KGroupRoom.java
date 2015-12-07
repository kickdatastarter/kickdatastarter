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
public class KGroupRoom extends KFacility implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1730031831409739513L;

	public KGroupRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(length=50)
	private String name;
//	@ManyToOne 
//	@JoinColumn(name="Facilityid")
//	public KFacility Facilityid;
	public KGroupRoom(int capacity, KFacilityType type, String name) {
		super(capacity, type);
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}