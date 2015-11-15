package dbproject;


import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dbhw2.Qualification;

@Entity
@Access(AccessType.PROPERTY)
public class KRole implements Serializable {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(length=500,name="name", nullable=false)
	private String name;
	
	@OneToMany(mappedBy="KRole")
	public Collection<KUser> KUsers;

	public KRole() {
		// TODO Auto-generated constructor stub
	}
	
	public KRole(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getid() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
