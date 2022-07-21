package kr.co.sitglobal.ifx.vo;
import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AunuriMemberVO extends AunuriBaseVO implements Serializable {
	private static final long serialVersionUID = -8761117337352093048L;
	
	private String memId="";     //아이디(학번) 
	private String memNm="";     //이름      
	private String memType="";   //사용자유형   
	private String college="";   //학부      
	private String major="";     //전공      
	private String phone="";     //연락처     
	private String email="";     //이메일     
	private String address="";   //주소      
	private String desc="";      //설명

	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemNm() {
		return memNm;
	}
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	public String getMemType() {
		return memType;
	}
	public void setMemType(String memType) {
		this.memType = memType;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "AunuriMemberVO [memId=" + memId + ", memNm=" + memNm
				+ ", memType=" + memType + ", college=" + college + ", major="
				+ major + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", desc=" + desc + "]";
	}

}
