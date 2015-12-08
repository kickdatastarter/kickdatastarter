/**
 * 
 */
package edu.neu.cs5200.finalproj.model;

import java.io.Serializable;

/**
 * @author physicsboy
 *
 */
public class KRightsKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2666307638860623289L;

	/**
	 * 
	 */
	public KRightsKey() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	private int role;
	
	private int facilityType;


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + facilityType;
		result = prime * result + role;
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
		KRightsKey other = (KRightsKey) obj;
		if (facilityType != other.facilityType)
			return false;
		if (role != other.role)
			return false;
		return true;
	}

	

}
