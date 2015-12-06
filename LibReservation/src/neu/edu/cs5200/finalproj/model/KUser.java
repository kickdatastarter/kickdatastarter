package neu.edu.cs5200.finalproj.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	public KUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManyToOne
	@JoinColumn(name="role", nullable=false)
	private int role;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false, unique=true)
	private int nuid;

	
	public KUser(int id, String loginid, int role, String name, int nuid) {
		super();
		this.id = id;
		this.loginid = loginid;
		this.role = role;
		this.name = name;
		this.nuid = nuid;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNuid() {
		return nuid;
	}

	public void setNuid(int nuid) {
		this.nuid = nuid;
	}
		
	
}