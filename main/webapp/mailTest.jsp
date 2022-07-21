<%@page import="javax.mail.*"%>
<%@page import="javax.mail.internet.*"%>
<%@page import="java.util.*"%>
<%@page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
 
 
 <%
 
 //String sql =" SELECT REPLACE(FN_GET_SSN(ssn),'-','') AS ssn, USER_NO as userNo FROM TB_USR_USER_INFO WHERE USER_NO NOT IN ( 'USR000002758' ,'USR000004548') AND new_ssn IS NULL ";
// String sql1 =" UPDATE TB_USR_USER_INFO SET NEW_SSN = ? WHERE USER_NO = ? ";
 
//mail.smtp.host = 211.232.26.122
//mail.admin.mailadd = no-reply@wizi.co.kr
//mail.admin.name = 위지런
//String aunuriUrl =  EgovProperties.getProperty("Globals.AunuriUrl");		
//String aunuriUserName =  EgovProperties.getProperty("Globals.AunuriUserName");
//String smtpHost	 =   EgovProperties.getProperty("mail.smtp.host");	

String smtpHost	 ="email.koreatech.ac.kr";

String subject = "재목 운영";

String content = "내용 운영";

String from = "no-reply@wizi.co.kr"; //보내는 사람


String to = "suno99@wizi.co.kr"; //받는 사람 



System.out.println("=============== smtpHost : "+ smtpHost);



/*  if(from.equals("") || to.equals("") || content.equals("") || subject.equals("")){

     System.out.println("메일 전송 실패");

 }else{ */

	 // 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)

	 Properties props = new Properties();
	 
	 props.put("mail.smtp.host", smtpHost);
	 props.put("mail.smtp.port", "25");

	 Session sess= Session.getDefaultInstance(props, null);

	 Message msg = new MimeMessage(sess);

	 msg.setFrom(new InternetAddress(from));//보내는 사람 설정

	 InternetAddress address = new InternetAddress(to);

	 msg.setRecipient(Message.RecipientType.TO, address);//받는 사람설정

	 msg.setSubject(subject);//제목 설정

	 msg.setSentDate(new java.util.Date());//보내는 날짜 설정
	 msg.setContent(content,"text/html;charset=euc-kr"); // 내용 설정 (HTML 형식)
	 Transport.send(msg);//메일 보내기

 //}
 
 %>
 
 


 
 
