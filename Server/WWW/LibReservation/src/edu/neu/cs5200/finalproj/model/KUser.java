package edu.neu.cs5200.finalproj.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Access(AccessType.PROPERTY)
public class KUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8764864179372814697L;

	private int id;
	
	private String loginid;
	
	private String loginPassword;
	
	private Collection<KUser_Studygroup> user_sg_lst;

	private KRole role;
	
	private String name;
	
	@Column(nullable=false, unique=true)
	private int nuid;

	
	public KUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KUser(int id, String loginid, KRole role, String name, int nuid) {
		super();
		this.id = id;
		this.loginid = loginid;
		this.role = role;
		this.name = name;
		this.nuid = nuid;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
	@JoinColumn(nullable=false)
	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	@Basic
	@JoinColumn(nullable=false)
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	@ManyToOne
	@JoinColumn(name="role", nullable=false)
	public KRole getRole() {
		return role;
	}

	public void setRole(KRole role) {
		this.role = role;
	}

	@Basic
	@JoinColumn(nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@JoinColumn(nullable=false)
	public int getNuid() {
		return nuid;
	}

	public void setNuid(int nuid) {
		this.nuid = nuid;
	}

	@OneToMany(cascade={CascadeType.REMOVE, CascadeType.REFRESH}, mappedBy="user")
	public Collection<KUser_Studygroup> getUser_sg_lst() {
		return user_sg_lst;
	}

	public void setUser_sg_lst(Collection<KUser_Studygroup> user_sg_lst) {
		this.user_sg_lst = user_sg_lst;
	}
		
	
}