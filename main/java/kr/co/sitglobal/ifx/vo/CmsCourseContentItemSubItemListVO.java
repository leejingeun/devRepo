package kr.co.sitglobal.ifx.vo;

import java.io.Serializable;
import java.util.List;

public class CmsCourseContentItemSubItemListVO implements Serializable {

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1397922614527613517L;
		private String display_order ="";//
	private String id ="";
	private String title ="";
	private String required_learning_time_in_seconds ="";
	private String lesson_item_id ="";
	private String url ="";
	private String width ="";
	private String height ="";
	private String package_type_code ="";
	private String sub_item_length ="";
	private String lesson_sub_item ="";
	private String lessonProgress = "";
	
	public String getLessonProgress() {
		return lessonProgress;
	}
	public void setLessonProgress(String lessonProgress) {
		this.lessonProgress = lessonProgress;
	}
	private List<CmsCourseContentItemSubItemListVO> list  = null;
	
	public List<CmsCourseContentItemSubItemListVO> getList() {
		return list;
	}
	public void setList(List<CmsCourseContentItemSubItemListVO> list) {
		this.list = list;
	}
	public String getLesson_sub_item() {
		return lesson_sub_item;
	}
	public void setLesson_sub_item(String lesson_sub_item) {
		this.lesson_sub_item = lesson_sub_item;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getPackage_type_code() {
		return package_type_code;
	}
	public void setPackage_type_code(String package_type_code) {
		this.package_type_code = package_type_code;
	}
	public String getSub_item_length() {
		return sub_item_length;
	}
	public void setSub_item_length(String sub_item_length) {
		this.sub_item_length = sub_item_length;
	}
	public String getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(String display_order) {
		this.display_order = display_order;
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
	public String getRequired_learning_time_in_seconds() {
		return required_learning_time_in_seconds;
	}
	public void setRequired_learning_time_in_seconds(
			String required_learning_time_in_seconds) {
		this.required_learning_time_in_seconds = required_learning_time_in_seconds;
	}
	public String getLesson_item_id() {
		return lesson_item_id;
	}
	public void setLesson_item_id(String lesson_item_id) {
		this.lesson_item_id = lesson_item_id;
	}
	@Override
	public String toString() {
		return "CmsCourseContentItemSubItemListVO [display_order="
				+ display_order + ", id=" + id + ", title=" + title
				+ ", required_learning_time_in_seconds="
				+ required_learning_time_in_seconds + ", lesson_item_id="
				+ lesson_item_id + "]";
	}
	
	;
}
