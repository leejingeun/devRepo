package kr.co.sitglobal.ifx.vo;

import java.io.Serializable;

public class CmsCourseBaseVO  implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 219994424037896728L;
	private String year                                  ="";
	private String category_name = "";
	private String institutionId                                  ="";
	
	private String courseCodeId                  		 ="";
	private String courseContentId                  	 ="";
	private String addURL								 ="";
	private String lessonId								 ="";
	private String lessonItemId							 ="";
	private String lessonSubItemId						 ="";
	private String id							 ="";
	private String total_count = "";
	
	private String searchInstitutionId  = "";
	private String searchYear  = "";
	private String searchStDate  = "";
	private String searchEdDate  = "";
	private String searchWrd  = "";
	
	private int count = 0;
	private int page = 0;
	private int orderByCode = 0;
	private int isAvailable = 0;
	
	
	
	/** 현재페이지 */
    private int pageIndex = 1;

	/** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;
    
    public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}


	/** select total count */
    private String totalCount = "";
    
    public String getSearchInstitutionId() {
		return searchInstitutionId;
	}

	public void setSearchInstitutionId(String searchInstitutionId) {
		this.searchInstitutionId = searchInstitutionId;
	}

	public String getSearchYear() {
		return searchYear;
	}

	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}

	public String getSearchStDate() {
		return searchStDate;
	}

	public void setSearchStDate(String searchStDate) {
		this.searchStDate = searchStDate;
	}

	public String getSearchEdDate() {
		return searchEdDate;
	}

	public void setSearchEdDate(String searchEdDate) {
		this.searchEdDate = searchEdDate;
	}

	public String getSearchWrd() {
		return searchWrd;
	}

	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}
	
    public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getTotal_count() {
		return total_count;
	}

	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}
	
	public int getOrderByCode() {
		return orderByCode;
	}

	public void setOrderByCode(int orderByCode) {
		this.orderByCode = orderByCode;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getLessonSubItemId() {
		return lessonSubItemId;
	}

	public void setLessonSubItemId(String lessonSubItemId) {
		this.lessonSubItemId = lessonSubItemId;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCourseCodeId() {
		return courseCodeId;
	}

	public void setCourseCodeId(String courseCodeId) {
		this.courseCodeId = courseCodeId;
	}

	public String getCourseContentId() {
		return courseContentId;
	}

	public void setCourseContentId(String courseContentId) {
		this.courseContentId = courseContentId;
	}

	public String getAddURL() {
		return addURL;
	}

	public void setAddURL(String addURL) {
		this.addURL = addURL;
	}

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
	
	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}


	@Override
	public String toString() {
		return "CmsCourseBaseVO [year=" + year + ", courseCodeId="
				+ courseCodeId + ", courseContentId=" + courseContentId
				+ ", addURL=" + addURL + ", lessonId=" + lessonId
				+ ", lessonItemId=" + lessonItemId + ", id=" + id
				+ ", total_count=" + total_count + "]";
	}


	
	
}
