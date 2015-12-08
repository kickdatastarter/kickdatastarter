package edu.neu.cs5200.finalproj.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;


@Entity
@Access(AccessType.PROPERTY)
public class KRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7456834736614394911L;
	private int id;
	private String name;
	
	@OneToMany(mappedBy="role")
	private List<KRights> kRights = new ArrayList<KRights>();

	public KRole() {
		// TODO Auto-generated constructor stub
	}
	
	public KRole(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	@Id @GeneratedValue
	public int getid() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	@OneToMany(mappedBy = "KRole")
	public Collection<KUser> KUsers;

}
