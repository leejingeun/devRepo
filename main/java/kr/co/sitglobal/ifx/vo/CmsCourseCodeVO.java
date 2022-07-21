package kr.co.sitglobal.ifx.vo;

import java.io.Serializable;

public class CmsCourseCodeVO extends CmsCourseBaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8040887806352745822L;
	
	private String code="";
	private String operating_course_content_count="";
	private String last_modified_by_user_name="";
	private String last_modified_date="";
	private String last_modified_by_user_idx="";
	private String last_modified_by_user_id="";
	private String title="";
	private String subtitle="";
	private String registered_by_user_id="";
	private String institution_id="";
	private String institution_name="";
	private String last_modified_from_device_type_code="";
	private String last_modified_from_ip_address="";
	private String registered_from_device_type_code="";
	private String row_number="";
	private String credit="";
	private String last_modified_from_country="";
	private String category_code="";
	private String course_content_count="";
	private String registered_from_country="";
	private String registered_from_ip_address="";
	private String is_modifiable="";
	private String registered_by_user_idx="";
	private String registered_date="";
	private String registered_by_user_name="";
	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOperating_course_content_count() {
		return operating_course_content_count;
	}

	public void setOperating_course_content_count(
			String operating_course_content_count) {
		this.operating_course_content_count = operating_course_content_count;
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
	
	public String getInstitution_name() {
		
		if("FORMAL".equals(institution_id)){
			institution_name = "평생능력개발";
		} else if("INFORMAL".equals(institution_id)){
			institution_name = "비형식";
		} else if("LAB".equals(institution_id)){
			institution_name = "온라인실습실";
		} else if("MARKET".equals(institution_id)){
			institution_name = "e-koreatech마켓";
		}
		
		return institution_name;
	}



	public String getLast_modified_from_device_type_code() {
		return last_modified_from_device_type_code;
	}

	public void setLast_modified_from_device_type_code(
			String last_modified_from_device_type_code) {
		this.last_modified_from_device_type_code = last_modified_from_device_type_code;
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

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getCourse_content_count() {
		return course_content_count;
	}

	public void setCourse_content_count(String course_content_count) {
		this.course_content_count = course_content_count;
	}

	public String getRegistered_from_country() {
		return registered_from_country;
	}

	public void setRegistered_from_country(String registered_from_country) {
		this.registered_from_country = registered_from_country;
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
		return "CmsCourseCodeVO [ code="
				+ code
				+ ", operating_course_content_count="
				+ operating_course_content_count
				+ ", last_modified_by_user_name=" + last_modified_by_user_name
				+ ", last_modified_date=" + last_modified_date
				+ ", last_modified_by_user_idx=" + last_modified_by_user_idx
				+ ", last_modified_by_user_id=" + last_modified_by_user_id
				+ ", title=" + title + ", registered_by_user_id="
				+ registered_by_user_id + ", institution_id=" + institution_id
				+ ", last_modified_from_device_type_code="
				+ last_modified_from_device_type_code
				+ ", last_modified_from_ip_address="
				+ last_modified_from_ip_address
				+ ", registered_from_device_type_code="
				+ registered_from_device_type_code + ", row_number="
				+ row_number + ", credit=" + credit
				+ ", last_modified_from_country=" + last_modified_from_country
				+ ", category_code=" + category_code
				+ ", course_content_count=" + course_content_count
				+ ", registered_from_country=" + registered_from_country
				+ ", registered_from_ip_address=" + registered_from_ip_address
				+ ", is_modifiable=" + is_modifiable
				+ ", registered_by_user_idx=" + registered_by_user_idx
				+ ", registered_date=" + registered_date
				+ ", registered_by_user_name=" + registered_by_user_name + "]";
	}

	
	

	
	
	
	

	
	
}
