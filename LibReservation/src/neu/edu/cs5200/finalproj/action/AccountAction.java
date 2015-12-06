package neu.edu.cs5200.finalproj.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;
import neu.edu.cs5200.finalproj.dao.KFacilityDao;
import neu.edu.cs5200.finalproj.dao.KComputerDao;
import neu.edu.cs5200.finalproj.dao.KGroupRoomDao;
import neu.edu.cs5200.finalproj.dao.KIndividualRoomDao;
import neu.edu.cs5200.finalproj.dao.KReservationDao;
import neu.edu.cs5200.finalproj.dao.KStudygroupDao;
import neu.edu.cs5200.finalproj.dao.KUser_StudygroupDao;
import neu.edu.cs5200.finalproj.dao.KUserDao;
import neu.edu.cs5200.finalproj.model.KFacilityType;
import neu.edu.cs5200.finalproj.model.KReservation;
import neu.edu.cs5200.finalproj.model.KStudygroup;
import neu.edu.cs5200.finalproj.model.KUser;
import neu.edu.cs5200.finalproj.util.SpringContextUtil;

public class AccountAction implements Action {

	///////////////// Varialbles /////////////////
	// Log in
	private String loginName;
	private String loginPassword;

	private List<KReservation> allResv;
	private Map<KStudygroup, List<KUser>> allStudygroup;

	private Map<String, String> studygroupAjax;
	private Map<String, String> facilityAjax;

	// Make a reservation
	private String facilityDdl_makeResv;
	private String studygroupDdl_makeResv;
	private String datePicker_makeResv;
	private String startHour_makeResv;
	private String endHour_makeResv;

	// Delete a reservation
	private int resvID_delResv;

	// Update a reservation
	private String resvID_updateResv;
	private String datePicker_updateResv;
	private String startHour_updateResv;
	private String endHour_updateResv;

	private ActionContext context;
	private Map request;
	private Map session;

	private int Groupid;
	private int userid;

	private String newGroupName;
	private int newGroupid;
	private String Groupname;

	private int insertUserid;
	private int insertUserRole;
	private String insertUserName;
	private String insertUserLoginid;
	private int insertUserNuid;
	private String updateUserLoginid;
	private int updateUserRole;
	private String deleteUserLoginid;
	private int insertComputerCapacity;
	private String insertComputerName;
	private String deleteComputerName;
	private int insertGroupRoomCapacity;
	private String insertGroupRoomName;
	private String deleteGroupRoomName;
	private int insertIndividualRoomCapacity;
	private String insertIndividualRoomName;
	private String deleteIndividualRoomName;
	private int Facilityid;
	private String Maintainstatus;

	private int user1;

	///////////////// Getters and Setters /////////////////

	public String getResvID_updateResv() {
		return resvID_updateResv;
	}

	public int getResvID_delResv() {
		return resvID_delResv;
	}

	public void setResvID_delResv(int resvID_delResv) {
		this.resvID_delResv = resvID_delResv;
	}

	public void setResvID_updateResv(String resvID_updateResv) {
		this.resvID_updateResv = resvID_updateResv;
	}

	public String getDatePicker_updateResv() {
		return datePicker_updateResv;
	}

	public void setDatePicker_updateResv(String datePicker_updateResv) {
		this.datePicker_updateResv = datePicker_updateResv;
	}

	public String getStartHour_updateResv() {
		return startHour_updateResv;
	}

	public void setStartHour_updateResv(String startHour_updateResv) {
		this.startHour_updateResv = startHour_updateResv;
	}

	public String getEndHour_updateResv() {
		return endHour_updateResv;
	}

	public void setEndHour_updateResv(String endHour_updateResv) {
		this.endHour_updateResv = endHour_updateResv;
	}

	public Map<String, String> getStudygroupAjax() {
		return studygroupAjax;
	}

	public void setStudygroupAjax(Map<String, String> groups) {
		this.studygroupAjax = groups;
	}

	public Map<String, String> getFacilityAjax() {
		return facilityAjax;
	}

	public void setFacilityAjax(Map<String, String> facilityAjax) {
		this.facilityAjax = facilityAjax;
	}

	public String getFacilityDdl_makeResv() {
		return facilityDdl_makeResv;
	}

	public void setFacilityDdl_makeResv(String facilityDdl_makeResv) {
		this.facilityDdl_makeResv = facilityDdl_makeResv;
	}

	public String getStudygroupDdl_makeResv() {
		return studygroupDdl_makeResv;
	}

	public void setStudygroupDdl_makeResv(String studygroupDdl_makeResv) {
		this.studygroupDdl_makeResv = studygroupDdl_makeResv;
	}

	public String getDatePicker_makeResv() {
		return datePicker_makeResv;
	}

	public void setDatePicker_makeResv(String datePicker_makeResv) {
		this.datePicker_makeResv = datePicker_makeResv;
	}

	public String getStartHour_makeResv() {
		return startHour_makeResv;
	}

	public void setStartHour_makeResv(String startHour_makeResv) {
		this.startHour_makeResv = startHour_makeResv;
	}

	public String getEndHour_makeResv() {
		return endHour_makeResv;
	}

	public void setEndHour_makeResv(String endHour_makeResv) {
		this.endHour_makeResv = endHour_makeResv;
	}

	public int getGroupid() {
		return Groupid;
	}

	public void setGroupid(int groupid) {
		this.Groupid = groupid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getGroupname() {
		return Groupname;
	}

	public void setGroupname(String Groupname) {
		this.Groupname = Groupname;
	}

	public int getNewGroupid() {
		return newGroupid;
	}

	public void setNewGroupid(int newGroupid) {
		this.newGroupid = newGroupid;
	}

	public String getNewGroupName() {
		return newGroupName;
	}

	public void setNewGroupName(String newGroupName) {
		this.newGroupName = newGroupName;
	}

	public int getUser1() {
		return user1;
	}

	public void setUser1(int user1) {
		this.user1 = user1;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public List<KReservation> getAllResv() {
		return allResv;
	}

	public void setAllResv(List<KReservation> allResv) {
		this.allResv = allResv;
	}

	public int getInsertUserid() {
		return insertUserid;
	}

	public void setInsertUserid(int insertUserid) {
		this.insertUserid = insertUserid;
	}

	public int getInsertUserRole() {
		return insertUserRole;
	}

	public void setInsertUserRole(int insertUserRole) {
		this.insertUserRole = insertUserRole;
	}

	public String getInsertUserName() {
		return insertUserName;
	}

	public void setInsertUserName(String insertUserName) {
		this.insertUserName = insertUserName;
	}

	public String getInsertUserLoginid() {
		return insertUserLoginid;
	}

	public void setInsertUserLoginid(String insertUserLoginid) {
		this.insertUserLoginid = insertUserLoginid;
	}

	public int getInsertUserNuid() {
		return insertUserNuid;
	}

	public void setInsertUserNuid(int insertUserNuid) {
		this.insertUserNuid = insertUserNuid;
	}

	public String getUpdateUserLoginid() {
		return updateUserLoginid;
	}

	public void setUpdateUserLoginid(String updateUserLoginid) {
		this.updateUserLoginid = updateUserLoginid;
	}

	public int getUpdateUserRole() {
		return updateUserRole;
	}

	public void setUpdateUserRole(int updateUserRole) {
		this.updateUserRole = updateUserRole;
	}

	public String getDeleteUserLoginid() {
		return deleteUserLoginid;
	}

	public void setDeleteUserLoginid(String deleteUserLoginid) {
		this.deleteUserLoginid = deleteUserLoginid;
	}

	public int getInsertComputerCapacity() {
		return insertComputerCapacity;
	}

	public void setInsertComputerCapacity(int insertComputerCapacity) {
		this.insertComputerCapacity = insertComputerCapacity;
	}

	public String getInsertComputerName() {
		return insertComputerName;
	}

	public void setInsertComputerName(String insertComputerName) {
		this.insertComputerName = insertComputerName;
	}

	public String getDeleteComputerName() {
		return deleteComputerName;
	}

	public void setDeleteComputerName(String deleteComputerName) {
		this.deleteComputerName = deleteComputerName;
	}

	public int getInsertGroupRoomCapacity() {
		return insertGroupRoomCapacity;
	}

	public void setInsertGroupRoomCapacity(int insertGroupRoomCapacity) {
		this.insertGroupRoomCapacity = insertGroupRoomCapacity;
	}

	public String getInsertGroupRoomName() {
		return insertGroupRoomName;
	}

	public void setInsertGroupRoomName(String insertGroupRoomName) {
		this.insertGroupRoomName = insertGroupRoomName;
	}

	public String getDeleteGroupRoomName() {
		return deleteGroupRoomName;
	}

	public void setDeleteGroupRoomName(String deleteGroupRoomName) {
		this.deleteGroupRoomName = deleteGroupRoomName;
	}

	public int getInsertIndividualRoomCapacity() {
		return insertIndividualRoomCapacity;
	}

	public void setInsertIndividualRoomCapacity(int insertIndividualRoomCapacity) {
		this.insertIndividualRoomCapacity = insertIndividualRoomCapacity;
	}

	public String getInsertIndividualRoomName() {
		return insertIndividualRoomName;
	}

	public void setInsertIndividualRoomName(String insertIndividualRoomName) {
		this.insertIndividualRoomName = insertIndividualRoomName;
	}

	public String getDeleteIndividualRoomName() {
		return deleteIndividualRoomName;
	}

	public void setDeleteIndividualRoomName(String deleteIndividualRoomName) {
		this.deleteIndividualRoomName = deleteIndividualRoomName;
	}

	public int getFacilityid() {
		return Facilityid;
	}

	public void setFacilityid(int facilityid) {
		Facilityid = facilityid;
	}

	public String getMaintainstatus() {
		return Maintainstatus;
	}

	public void setMaintainstatus(String maintainstatus) {
		Maintainstatus = maintainstatus;
	}

	public Map<KStudygroup, List<KUser>> getAllStudygroup() {
		return allStudygroup;
	}

	public void setAllStudygroup(Map<KStudygroup, List<KUser>> allStudygroup) {
		this.allStudygroup = allStudygroup;
	}

	public AccountAction() {
		super();
		this.context = ActionContext.getContext();
		this.request = (Map) this.context.get("request");
		this.session = (Map) this.context.get("session");
	}

	///////////////// Actions /////////////////
	public String signin() throws Exception {
		KUserDao userDao = SpringContextUtil.getService("kUserDao");
		KUser curUser = userDao.signin(loginName, loginPassword);
		if (curUser != null) {
			this.session.put("user", curUser);
			if (curUser.getRole() == 6)
				return "admin";
			else
				return "kuser";
		} else {
			return LOGIN;
		}
	}

	public String signout() throws Exception {
		this.session.put("user", null);
		return SUCCESS;
	}

	public String getMyResv() throws Exception {
		KReservationDao resvDao = SpringContextUtil.getService("kReservationDao");

		this.allResv = resvDao.getMyResv((KUser) this.session.get("user"));

		return SUCCESS;
	}

	public String getMyStudygroup() throws Exception {
		KStudygroupDao studygroupDao = SpringContextUtil.getService("kStudygroupDao");
		this.allStudygroup = studygroupDao.getAllStudygroup(10);

		return SUCCESS;
	}

	public String getMyStudygroupAjax() throws Exception {
		this.getMyStudygroup();

		studygroupAjax = new HashMap<String, String>();
		for (Entry<KStudygroup, List<KUser>> entry : this.allStudygroup.entrySet()) {
			studygroupAjax.put(Integer.toString(entry.getKey().getId()), entry.getKey().getName());
		}

		return SUCCESS;
	}

	public String getAllFacilitiesAjax() throws Exception {
		KFacilityDao facilityDao = SpringContextUtil.getService("kFacilityDao");
		this.facilityAjax = facilityDao.getAllFacilities();

		return SUCCESS;
	}

	public String makeResv() throws Exception {
		KReservationDao resvDao = SpringContextUtil.getService("kReservationDao");
		String transactionResult = "Insertion Succeeded.";
		try {
			resvDao.insertResv(datePicker_makeResv, startHour_makeResv, endHour_makeResv, facilityDdl_makeResv,
					studygroupDdl_makeResv, ((KUser) this.session.get("user")).getId());
		} catch (Exception e) {
			transactionResult = "Insertion Failed.";
		}

		this.request.put("isMakeResvSucceed", transactionResult);

		return SUCCESS;

	}

	public String addStudygroup() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KStudygroupDao studygroupDao = context.getBean("kStudygroupDao", KStudygroupDao.class);
		studygroupDao.addStudygroup(newGroupName);

		return SUCCESS;
	}

	public String deleteStudygroup() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KStudygroupDao studygroupDao = context.getBean("kStudygroupDao", KStudygroupDao.class);
		studygroupDao.deleteStudygroup(Groupname);

		return SUCCESS;
	}

	public int addpersoninStudygroup() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KUser_StudygroupDao studygroupDao = context.getBean("kUser_StudygroupDao", KUser_StudygroupDao.class);
		studygroupDao.addpersoninStudygroup(user1, newGroupid);

		return 1;
	}

	public int deletepeoplefromStudygroup() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KUser_StudygroupDao studygroupDao = context.getBean("kUser_StudygroupDao", KUser_StudygroupDao.class);
		studygroupDao.deletepeoplefromStudygroup(Groupid, userid);
		return 1;
	}

	public String insertUser() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KUserDao kuserinsert = context.getBean("kUserDao", KUserDao.class);
		kuserinsert.InsertKuser(insertUserid, insertUserRole, insertUserName, insertUserLoginid, insertUserNuid);

		return SUCCESS;
	}

	public String updateUser() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KUserDao kuserupdate = context.getBean("kUserDao", KUserDao.class);
		kuserupdate.UpdateKUser(updateUserLoginid, updateUserRole);

		return SUCCESS;
	}

	public String deleteUser() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KUserDao kuserdelete = context.getBean("kUserDao", KUserDao.class);
		kuserdelete.DeleteKUser(deleteUserLoginid);

		return SUCCESS;
	}

	public String insertComputer() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KComputerDao kcomputerinsert = context.getBean("kComputerDao", KComputerDao.class);
		kcomputerinsert.InsertComputer(insertComputerCapacity, insertComputerName);

		return SUCCESS;
	}

	public String deleteComputer() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KComputerDao kcomputerdelete = context.getBean("kComputerDao", KComputerDao.class);
		kcomputerdelete.DeleteComputer(deleteComputerName);

		return SUCCESS;
	}

	public String insertGroupRoom() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KGroupRoomDao kgrouproominsert = context.getBean("kGroupRoomDao", KGroupRoomDao.class);
		kgrouproominsert.InsertGroupRoom(insertGroupRoomCapacity, insertGroupRoomName);

		return SUCCESS;
	}

	public String deleteGroupRoom() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KGroupRoomDao kgrouproomdelete = context.getBean("kGroupRoomDao", KGroupRoomDao.class);
		kgrouproomdelete.DeleteGroupRoom(deleteGroupRoomName);

		return SUCCESS;
	}

	public String insertIndividualRoom() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KIndividualRoomDao kindividualroominsert = context.getBean("kIndividualRoomDao", KIndividualRoomDao.class);
		kindividualroominsert.InsertIndividualRoom(insertIndividualRoomCapacity, insertIndividualRoomName);

		return SUCCESS;
	}

	public String deleteIndividualRoom() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KIndividualRoomDao kindividualroomdelete = context.getBean("kIndividualRoomDao", KIndividualRoomDao.class);
		kindividualroomdelete.DeleteIndividualRoom(insertIndividualRoomName);

		return SUCCESS;
	}

	public String setfacilitystate() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		KReservationDao setfacilitystate = context.getBean("kReservationDao", KReservationDao.class);
		setfacilitystate.SetFacilityState(Facilityid, Maintainstatus);

		return SUCCESS;
	}

	public String deleteResv() throws Exception {
		KReservationDao resvDao = SpringContextUtil.getService("kReservationDao");
		resvDao.deleteResv(resvID_delResv);

		return SUCCESS;
	}

	public String updateResv() throws Exception {
		KReservationDao resvDao = SpringContextUtil.getService("kReservationDao");
		resvDao.updateResv(datePicker_updateResv, startHour_updateResv, endHour_updateResv, resvID_updateResv);

		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}
}
