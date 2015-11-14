package ss;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class KIndividualRoom {
@Facilityid
private int Facilityid;
@Column(length=50)
private String name;
@ManyToOne 
@JoinColumn(name="Facility")
public KFacility Facility;
public int getFacilityid() {
	return Facilityid;
}
public void setFacilityid(int facilityid) {
	this.Facilityid = facilityid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


public KIndividualRoom( String name, int facilityid) {
	this.name = name;
	this.Facilityid = facilityid;
}

}
