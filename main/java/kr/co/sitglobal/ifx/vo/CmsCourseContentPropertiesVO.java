package kr.co.sitglobal.ifx.vo;

import java.io.Serializable;

public class CmsCourseContentPropertiesVO extends CmsCourseBaseVO  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5477675590322746254L;

	private String title ="";
	private String course_image_url ="";
	private String subtitle ="";
	
	private String course_code_id ="";
	private String ncs_information ="";
	private String learning_target ="";
	private String reference_data ="";
	private String instructor_details ="";
	private String learning_goal ="";
	private String course_introduction ="";
	
	
	public String getCourse_code_id() {
		return course_code_id;
	}
	public void setCourse_code_id(String course_code_id) {
		this.course_code_id = course_code_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCourse_image_url() {
		return course_image_url;
	}
	public void setCourse_image_url(String course_image_url) {
		this.course_image_url = course_image_url;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getNcs_information() {
		return ncs_information;
	}
	public void setNcs_information(String ncs_information) {
		this.ncs_information = ncs_information;
	}
	public String getLearning_target() {
		return learning_target;
	}
	public void setLearning_target(String learning_target) {
		this.learning_target = learning_target;
	}
	public String getReference_data() {
		return reference_data;
	}
	public void setReference_data(String reference_data) {
		this.reference_data = reference_data;
	}
	public String getInstructor_details() {
		return instructor_details;
	}
	public void setInstructor_details(String instructor_details) {
		this.instructor_details = instructor_details;
	}
	public String getLearning_goal() {
		return learning_goal;
	}
	public void setLearning_goal(String learning_goal) {
		this.learning_goal = learning_goal;
	}
	public String getCourse_introduction() {
		return course_introduction;
	}
	public void setCourse_introduction(String course_introduction) {
		this.course_introduction = course_introduction;
	}
	@Override
	public String toString() {
		return "CmsCourseContentPropertiesVO [title=" + title
				+ ", course_image_url=" + course_image_url + ", subtitle="
				+ subtitle + ", course_code_id=" + course_code_id
				+ ", ncs_information=" + ncs_information + ", learning_target="
				+ learning_target + ", reference_data=" + reference_data
				+ ", instructor_details=" + instructor_details
				+ ", learning_goal=" + learning_goal + ", course_introduction="
				+ course_introduction + "]";
	}

	
	
}
