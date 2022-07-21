package kr.co.sitglobal.ifx.vo;

import java.io.Serializable;

public class CmsCourseContentVO extends CmsCourseBaseVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7668805133744644902L;
	private String course_code_id ="";
	private String last_modified_by_user_name ="";
	private String last_modified_date ="";
	private String last_modified_by_user_idx ="";
	private String last_modified_by_user_id ="";
	private String title ="";
	private String registered_by_user_id ="";
	private String institution_id ="";
	private String last_modified_from_device_type_code ="";
	private String course_code ="";
	private String is_operating ="";
	private String last_modified_from_ip_address ="";
	private String registered_from_device_type_code ="";
	
	private String row_number ="";
	private String credit ="";
	private String last_modified_from_country ="";
	private String study_time_in_hours ="";
	private String registered_from_country ="";
	private String is_available ="";
	private String subtitle ="";
	private String registered_from_ip_address ="";
	private String is_modifiable ="";
	private String registered_by_user_idx ="";
	private String registered_date ="";
	private String registered_by_user_name ="";
	private String deviceName = "";
	private String institutionName = "";
	private String cateName = "";
	private String registeredDate = "";
	private String courseCode = "";
	
	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	public String getCourse_code_id() {
		return course_code_id;
	}

	public void setCourse_code_id(String course_code_id) {
		this.course_code_id = course_code_id;
	}

	public String getLast_modified_by_user_name() {
		return last_modified_by_user_name;
	}

	public void setLast_modified_by_user_name(String last_modified_by_user_name) {
		this.last_modified_by_user_name = last_modified_by_user_name;
	}

	public String getLast_modified_date() {
		return last_modified_date;
	}

	public void setLast_modified_date(String last_modified_date) {
		this.last_modified_date = last_modified_date;
	}

	public String getLast_modified_by_user_idx() {
		return last_modified_by_user_idx;
	}

	public void setLast_modified_by_user_idx(String last_modified_by_user_idx) {
		this.last_modified_by_user_idx = last_modified_by_user_idx;
	}

	public String getLast_modified_by_user_id() {
		return last_modified_by_user_id;
	}

	public void setLast_modified_by_user_id(String last_modified_by_user_id) {
		this.last_modified_by_user_id = last_modified_by_user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegistered_by_user_id() {
		return registered_by_user_id;
	}

	public void setRegistered_by_user_id(String registered_by_user_id) {
		this.registered_by_user_id = registered_by_user_id;
	}

	public String getInstitution_id() {
		return institution_id;
	}

	public void setInstitution_id(String institution_id) {
		this.institution_id = institution_id;
	}

	public String getLast_modified_from_device_type_code() {
		return last_modified_from_device_type_code;
	}

	public void setLast_modified_from_device_type_code(
			String last_modified_from_device_type_code) {
		this.last_modified_from_device_type_code = last_modified_from_device_type_code;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String getIs_operating() {
		return is_operating;
	}

	public void setIs_operating(String is_operating) {
		this.is_operating = is_operating;
	}

	public String getLast_modified_from_ip_address() {
		return last_modified_from_ip_address;
	}

	public void setLast_modified_from_ip_address(
			String last_modified_from_ip_address) {
		this.last_modified_from_ip_address = last_modified_from_ip_address;
	}

	public String getRegistered_from_device_type_code() {
		return registered_from_device_type_code;
	}

	public void setRegistered_from_device_type_code(
			String registered_from_device_type_code) {
		this.registered_from_device_type_code = registered_from_device_type_code;
	}

	public String getRow_number() {
		return row_number;
	}

	public void setRow_number(String row_number) {
		this.row_number = row_number;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getLast_modified_from_country() {
		return last_modified_from_country;
	}

	public void setLast_modified_from_country(String last_modified_from_country) {
		this.last_modified_from_country = last_modified_from_country;
	}

	public String getStudy_time_in_hours() {
		return study_time_in_hours;
	}

	public void setStudy_time_in_hours(String study_time_in_hours) {
		this.study_time_in_hours = study_time_in_hours;
	}

	public String getRegistered_from_country() {
		return registered_from_country;
	}

	public void setRegistered_from_country(String registered_from_country) {
		this.registered_from_country = registered_from_country;
	}

	public String getIs_available() {
		return is_available;
	}

	public void setIs_available(String is_available) {
		this.is_available = is_available;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getRegistered_from_ip_address() {
		return registered_from_ip_address;
	}

	public void setRegistered_from_ip_address(String registered_from_ip_address) {
		this.registered_from_ip_address = registered_from_ip_address;
	}

	public String getIs_modifiable() {
		return is_modifiable;
	}

	public void setIs_modifiable(String is_modifiable) {
		this.is_modifiable = is_modifiable;
	}

	public String getRegistered_by_user_idx() {
		return registered_by_user_idx;
	}

	public void setRegistered_by_user_idx(String registered_by_user_idx) {
		this.registered_by_user_idx = registered_by_user_idx;
	}

	public String getRegistered_date() {
		return registered_date;
	}

	public void setRegistered_date(String registered_date) {
		this.registered_date = registered_date;
	}

	public String getRegistered_by_user_name() {
		return registered_by_user_name;
	}

	public void setRegistered_by_user_name(String registered_by_user_name) {
		this.registered_by_user_name = registered_by_user_name;
	}

	@Override
	public String toString() {
		return "CmsCourseContentVO [course_code_id=" + course_code_id
				+ ", last_modified_by_user_name=" + last_modified_by_user_name
				+ ", last_modified_date=" + last_modified_date
				+ ", last_modified_by_user_idx=" + last_modified_by_user_idx
				+ ", last_modified_by_user_id=" + last_modified_by_user_id
				+ ", title=" + title + ", registered_by_user_id="
				+ registered_by_user_id + ", institution_id=" + institution_id
				+ ", last_modified_from_device_type_code="
				+ last_modified_from_device_type_code + ", course_code="
				+ course_code + ", is_operating=" + is_operating
				+ ", last_modified_from_ip_address="
				+ last_modified_from_ip_address
				+ ", registered_from_device_type_code="
				+ registered_from_device_type_code + ", row_number="
				+ row_number + ", credit=" + credit
				+ ", last_modified_from_country=" + last_modified_from_country
				+ ", study_time_in_hours=" + study_time_in_hours
				+ ", registered_from_country=" + registered_from_country
				+ ", is_available=" + is_available + ", subtitle=" + subtitle
				+ ", registered_from_ip_address=" + registered_from_ip_address
				+ ", is_modifiable=" + is_modifiable
				+ ", registered_by_user_idx=" + registered_by_user_idx
				+ ", registered_date=" + registered_date
				+ ", registered_by_user_name=" + registered_by_user_name
				+ "]";
	}
	
	
	
}
