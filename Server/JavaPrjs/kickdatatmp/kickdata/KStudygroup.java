/**
 * 
 */
package kickdata;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author physicsboy
 *
 */
@Entity
@Access(AccessType.PROPERTY)
public class KStudygroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4250982436029967725L;
	private int id;
	private String name;
	
	/**
	 * @param id
	 * @param name
	 */
	public KStudygroup(int id, String name) {
		this.id = id;
		this.name = name;
	}
	/**
	 * @return the id
	 */
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	@Basic
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
