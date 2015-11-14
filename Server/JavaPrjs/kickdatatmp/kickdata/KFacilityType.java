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


@Entity
@Access(AccessType.PROPERTY)
public class KFacilityType implements Serializable {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(length=50,name="typename", nullable=false)
	private String typename;
	
	@Column(length=50,name="tablename", nullable=false)
	private String tablename;
	
	@OneToMany(mappedBy="KFacilityType")
	public Collection<Facility> Facilities;
	
	@OneToMany(mappedBy="KFacilityType")
	public Collection<Rights> Rights;


	public KFacilityType() {
		// TODO Auto-generated constructor stub
	}
	
	public KFacilityType(int id, String typename, String tablename) {
		this.id = id;
		this.typename = typename;
		this.tablename = tablename;
	}
	
	public int getid() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTableName() {
		return tablename;
	}
	
	public void setTableName(String tablename) {
		this.tablename = tablename;
	}
	
	public String getTypeName() {
		return typename;
	}
	public void setTypeName(String typename) {
		this.typename = typename;
	}

}
