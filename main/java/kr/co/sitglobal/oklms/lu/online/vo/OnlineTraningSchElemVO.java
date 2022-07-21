package kr.co.sitglobal.oklms.lu.online.vo;

import java.io.Serializable;







import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OnlineTraningSchElemVO extends BaseVO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2481114028940676114L;
	/**
	 * 
	 */
	
	private String elemId   = "";
	private String weekId        = "";
	private String weekCnt      = "";
	private String subjSchId     = ""; 
	private String subjStep	= "";
	private String lesson_id        = "";
	private String lesson_item_id       = "";
	private String lesson_sub_item_id   = "";
	private String required_learning_time_in_seconds  = "";
	
	private String lessonId        = "";
	private String lessonItemId       = "";
	private String lessonSubItemId   = "";
	private String requiredLearningTimeInSeconds  = "";
	
	public String getLessonId() {
		return lessonId;
	}


	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}


	public String getLessonItemId() {
		return lessonItemId;
	}


	public void setLessonItemId(String lessonItemId) {
		this.lessonItemId = lessonItemId;
	}


	public String getLessonSubItemId() {
		return lessonSubItemId;
	}


	public void setLessonSubItemId(String lessonSubItemId) {
		this.lessonSubItemId = lessonSubItemId;
	}


	public String getRequiredLearningTimeInSeconds() {
		return requiredLearningTimeInSeconds;
	}


	public void setRequiredLearningTimeInSeconds(
			String requiredLearningTimeInSeconds) {
		this.requiredLearningTimeInSeconds = requiredLearningTimeInSeconds;
	}
	
	public String getElemId() {
		return elemId;
	}


	public void setElemId(String elemId) {
		this.elemId = elemId;
	}


	public String getWeekId() {
		return weekId;
	}


	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}


	public String getWeekCnt() {
		return weekCnt;
	}


	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}


	public String getSubjSchId() {
		return subjSchId;
	}


	public void setSubjSchId(String subjSchId) {
		this.subjSchId = subjSchId;
	}


	public String getSubjStep() {
		return subjStep;
	}


	public void setSubjStep(String subjStep) {
		this.subjStep = subjStep;
	}


	public String getLesson_id() {
		return lesson_id;
	}


	public void setLesson_id(String lesson_id) {
		this.lesson_id = lesson_id;
	}


	public String getLesson_item_id() {
		return lesson_item_id;
	}


	public void setLesson_item_id(String lesson_item_id) {
		this.lesson_item_id = lesson_item_id;
	}


	public String getLesson_sub_item_id() {
		return lesson_sub_item_id;
	}


	public void setLesson_sub_item_id(String lesson_sub_item_id) {
		this.lesson_sub_item_id = lesson_sub_item_id;
	}


	public String getRequired_learning_time_in_seconds() {
		return required_learning_time_in_seconds;
	}


	public void setRequired_learning_time_in_seconds(
			String required_learning_time_in_seconds) {
		this.required_learning_time_in_seconds = required_learning_time_in_seconds;
	}
	

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
	

}
