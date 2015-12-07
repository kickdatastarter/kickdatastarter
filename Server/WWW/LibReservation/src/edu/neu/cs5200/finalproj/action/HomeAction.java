package edu.neu.cs5200.finalproj.action;

import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.Action;

import edu.neu.cs5200.finalproj.dao.KReservationDao;
import edu.neu.cs5200.finalproj.util.SpringContextUtil;

public class HomeAction implements Action {

	private Map<String, Map<Integer, String>> resvData;
	
	private String attendence;
	private String datePicker_searchFacility;

	
	public String getAttendence() {
		return attendence;
	}

	public void setAttendence(String attendence) {
		this.attendence = attendence;
	}

	public String getDatePicker_searchFacility() {
		return datePicker_searchFacility;
	}

	public void setDatePicker_searchFacility(String datePicker_searchFacility) {
		this.datePicker_searchFacility = datePicker_searchFacility;
	}

	public void setResvData(Map<String, Map<Integer, String>> resvData) {
		this.resvData = resvData;
	}
	
	public Map<String, Map<Integer, String>> getResvData() {
		return this.resvData;
	}
	
	
	@Override
	public String execute() throws Exception {
		return null;
	}

	public String getHomeResvData() throws Exception {
		KReservationDao resvDao = SpringContextUtil.getService("kReservationDao");
		resvData = resvDao.getResvData(attendence, datePicker_searchFacility);
		
		return SUCCESS;
	}

}
