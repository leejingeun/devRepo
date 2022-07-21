package kr.co.sitglobal.ifx.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsCourseContentItemItemListVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 797951040702510324L;
	private String mobile_compatibility_code ="";
	private String display_order ="";
	private String width ="";
	private String id ="";
	private String title ="";
	private String lesson_id ="";
	private String height ="";
	
	private List<CmsCourseContentItemSubItemListVO> cmsCourseContentItemSubItemListVO  = null; 
			// new ArrayList<CmsCourseContentItemSubItemListVO>();
	
	public CmsCourseContentItemItemListVO() {
		super();
		cmsCourseContentItemSubItemListVO = new ArrayList<CmsCourseContentItemSubItemListVO>();
		// TODO Auto-generated constructor stub
	}
	
	public String getMobile_compatibility_code() {
		return mobile_compatibility_code;
	}

	public List<CmsCourseContentItemSubItemListVO> getCmsCourseContentItemSubItemListVO() {
		return cmsCourseContentItemSubItemListVO;
	}
	public void setCmsCourseContentItemSubItemListVO(List<CmsCourseContentItemSubItemListVO> cmsCourseContentItemSubItemListVO) {
			this.cmsCourseContentItemSubItemListVO = cmsCourseContentItemSubItemListVO;
	}
	public void setMobile_compatibility_code(String mobile_compatibility_code) {
		this.mobile_compatibility_code = mobile_compatibility_code;
	}
	
	public String getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(String display_order) {
		this.display_order = display_order;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "CmsCourseContentItemItemListVO [mobile_compatibility_code="
				+ mobile_compatibility_code 
				+ ", display_order=" + display_order + ", width=" + width
				+ ", id=" + id + ", title=" + title + ", lesson_id="
				+ lesson_id + ", height=" + height
				+ ", cmsCourseContentItemSubItemListVO="
				+ cmsCourseContentItemSubItemListVO + "]"
				+ "\n"
				+ cmsCourseContentItemSubItemListVO.toString()
				;
	}

}
