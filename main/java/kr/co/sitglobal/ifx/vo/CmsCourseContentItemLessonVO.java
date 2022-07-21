package kr.co.sitglobal.ifx.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsCourseContentItemLessonVO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4440967534594729360L;
//	private String last_modified_by_user_name ="";
//	private String registered_from_country ="";
//	private String last_modified_date ="";
//	private String last_modified_by_user_idx ="";
//	private String last_modified_by_user_id ="";
	
	private String title ="";
//	private String registered_by_user_id ="";
//	private String is_available ="";
//	private String last_modified_from_device_type_code ="";
//	private String is_deleted ="";
//	private String registered_from_ip_address ="";
//	private String last_modified_from_ip_address ="";
//	
//	private String registered_from_device_type_code ="";
	private String id ="";
//	private String registered_date ="";
//	private String registered_by_user_name ="";
//	private String last_modified_from_country ="";
	
	private List<CmsCourseContentItemItemListVO> cmsCourseContentItemItemListVO =null;
	
	
	
	public CmsCourseContentItemLessonVO() {
		super();
		cmsCourseContentItemItemListVO = new ArrayList<CmsCourseContentItemItemListVO>();
		// TODO Auto-generated constructor stub
	}
	public List<CmsCourseContentItemItemListVO> getCmsCourseContentItemItemListVO() {
		return cmsCourseContentItemItemListVO;
	}
	public void setCmsCourseContentItemItemListVO(List<CmsCourseContentItemItemListVO> cmsCourseContentItemItemListVO) {
		this.cmsCourseContentItemItemListVO = cmsCourseContentItemItemListVO;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CmsCourseContentItemLessonVO [title=" + title + ", id=" + id
				+ ", cmsCourseContentItemItemListVO="
				+ cmsCourseContentItemItemListVO + "]";
	}

	
	
	
}
