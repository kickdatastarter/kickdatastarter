package edu.neu.cs5200.finalproj.model;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author physicsboy
 *
 */
public class KUser_StudygroupKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8179216876424464223L;

	private int user;
	
	private int group;
	
	/**
	 * 
	 */
	public KUser_StudygroupKey() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @param user
	 * @param group
	 */
	public KUser_StudygroupKey(int user, int group) {
		super();
		this.user = user;
		this.group = group;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + group;
		result = prime * result + user;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KUser_StudygroupKey other = (KUser_StudygroupKey) obj;
		if (group != other.group)
			return false;
		if (user != other.user)
			return false;
		return true;
	}
	
}
