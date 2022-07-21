package kr.co.sitglobal.oklms.commbiz.file.vo;


/**
 * WebStudio Config/ extra image info Data Transfer Object Class 
 * @author 황상원 (hsw@anamit.com)
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *       수정일                         수정자                              수정내용
 *  ----------------    ------------    ---------------------------
 *  2014.09.24			황상원			최초 생성
 * 
 * </pre>
 */
@SuppressWarnings("serial")
public class ExtraImage {
	private String maxWidth = null;
	private String maxHeight = null;
	private String postFixName = null;
	private String fileExt = null;
	
	public String getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
	}
	public String getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}
	public String getPostFixName() {
		return postFixName;
	}
	public void setPostFixName(String postFixName) {
		this.postFixName = postFixName;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
}
