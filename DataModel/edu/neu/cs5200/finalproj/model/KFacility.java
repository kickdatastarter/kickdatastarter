package edu.neu.cs5200.finalproj.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.AccessType;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.JOINED)
public class KFacility implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4956573759662634954L;
	
	private int id;
	
	private int capacity;

	public KFacilityType type;
	
	@Id @GeneratedValue
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
	
	public KFacility(){}
	public KFacility(int capacity, KFacilityType type) {
		this.capacity = capacity;
		this.type = type;
	}
	
	
	
	

}
