package kr.co.sitglobal.ifx.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsCourseContentItemListVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1261807248081798372L;
//	private String last_modified_by_user_name ="";
	private String display_order ="";
//	private String registered_from_country ="";
//	private String last_modified_date ="";
//	private String last_modified_by_user_idx ="";
	
//	private String last_modified_by_user_id ="";
	private String title ="";
	private String lesson_id ="";
//	private String registered_by_user_id ="";
//	private String is_available ="";
//	private String last_modified_from_device_type_code ="";
	private String course_content_id ="";
//	private String registered_from_ip_address ="";
//	private String registered_by_user_idx ="";
//	private String last_modified_from_ip_address ="";
	
//	private String registered_from_device_type_code ="";
	private String id ="";
//	private String registered_date ="";
//	private String registered_by_user_name ="";
//	private String last_modified_from_country ="";
	private String type_code ="";
	
	private List<CmsCourseContentItemLessonVO> cmsCourseContentItemLessonVO =null;
	
	
	public CmsCourseContentItemListVO() {
		super();
		cmsCourseContentItemLessonVO = new ArrayList<CmsCourseContentItemLessonVO>();	
		// TODO Auto-generated constructor stub
	}
	
	public List<CmsCourseContentItemLessonVO> getCmsCourseContentItemLessonVO() {
		return cmsCourseContentItemLessonVO;
	}
	public void setCmsCourseContentItemLessonVO(List<CmsCourseContentItemLessonVO> cmsCourseContentItemLessonVO) {
		this.cmsCourseContentItemLessonVO = cmsCourseContentItemLessonVO;
	}

	public String getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(String display_order) {
		this.display_order = display_order;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLesson_id() {
		return lesson_id;
	}
	public void setLesson_id(String lesson_id) {
		this.lesson_id = lesson_id;
	}
	
	public String getCourse_content_id() {
		return course_content_id;
	}
	public void setCourse_content_id(String course_content_id) {
		this.course_content_id = course_content_id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	@Override
	public String toString() {
		return "CmsCourseContentItemListVO [display_order=" + display_order
				+ ", title=" + title + ", lesson_id=" + lesson_id
				+ ", course_content_id=" + course_content_id + ", id=" + id
				+ ", type_code=" + type_code
				+ ", cmsCourseContentItemLessonVO="
				+ cmsCourseContentItemLessonVO + "]";
	}
	
}
