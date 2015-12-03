package neu.edu.cs5200.finalproj.action;

import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.Action;

import neu.edu.cs5200.finalproj.dao.KReservationDao;
import neu.edu.cs5200.finalproj.util.SpringContextUtil;

public class HomeAction implements Action {

	private Map<String, Set<Integer> > resvData;

	public void setResvData(Map<String, Set<Integer>> resvData) {
		this.resvData = resvData;
	}
	/*
	public Map<String, Set<Integer> > getResvData() {
		return this.resvData;
	}
	*/
	
	@Override
	public String execute() throws Exception {
		return null;
	}
	
	public String getResvData() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KReservationDao resvDao = context.getBean("kReservationDao", KReservationDao.class);		
		resvDao.getResvData();
		
		//this.allResv = resvDao.getAllResv((int)(this.session.get("userId")));
		
		return SUCCESS;
	}

	public String getHomeResvData() throws Exception {
		KReservationDao resvDao = SpringContextUtil.getService("kReservationDao");
		
		//resvDao.insertResv();
		resvData = resvDao.getResvData();
		
		return SUCCESS;
	}

}
