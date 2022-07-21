<%@page import="egovframework.com.cmm.service.EgovProperties"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
 
 
 <%
 
 //String sql =" SELECT REPLACE(FN_GET_SSN(ssn),'-','') AS ssn, USER_NO as userNo FROM TB_USR_USER_INFO WHERE USER_NO NOT IN ( 'USR000002758' ,'USR000004548') AND new_ssn IS NULL ";
// String sql1 =" UPDATE TB_USR_USER_INFO SET NEW_SSN = ? WHERE USER_NO = ? ";
 
	Connection conn =null;
	String ssn ="";
	String new_ssn = "";
	String userNo = "";
	
			
	String aunuriUrl =  EgovProperties.getProperty("Globals.AunuriUrl");		
	String aunuriUserName =  EgovProperties.getProperty("Globals.AunuriUserName");
	String aunuriPassword	 =   EgovProperties.getProperty("Globals.AunuriPassword");	
	
	System.out.println("================================ aunuriUrl : "+aunuriUrl);
	System.out.println("================================ aunuriUserName : "+aunuriUserName);
	System.out.println("================================ aunuriPassword : "+aunuriPassword);
	
	int user_co =0;
	int totcnt =0;
	try{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
	 	conn = DriverManager.getConnection(aunuriUrl, aunuriUserName, aunuriPassword);
	 	//jdbc:oracle:thin:@211.232.121:1521:WIZIDB
	 	//conn = DriverManager.getConnection("jdbc:oracle:thin:@211.232.121:1521:WIZIDB", "KUT_NCS", "wizi000");
	// 	conn.setAutoCommit(false);
	 	System.out.println("=============================== conn : "+conn);
	// 	pstmt = conn.prepareStatement(sql);
	 	
//	 	pstmt1 = conn.prepareStatement(sql1);
	 	
	 	
	 /* 	rs = pstmt.executeQuery();
	 	
	 	while(rs.next()){
	 		
	 		ssn = rs.getString("ssn");
	 		userNo = rs.getString("userNo");
	 		
	 		System.out.println("====== new_ssn start : "+ssn);
	 		System.out.println("====== userNo : "+userNo);
	 		new_ssn = test.getNewSsn(ssn);
	 		System.out.println("====== new_ssn end : "+new_ssn);
	 		
	 		System.out.println("====== ssn : "+ssn);
	 		System.out.println("====== new_ssn : "+new_ssn);
	 		//System.out.println("====== userNo : "+userNo);
	 		System.out.println("====== cnt : "+totcnt++);
	 		
	 		pstmt1.setString(1, new_ssn);
	 		pstmt1.setString(2, userNo);
	 		
	 		pstmt1.addBatch();
	 	}
	 	
	 	int v_rtn [] = pstmt1.executeBatch();
	 	
	 	System.out.println("============= v_rtn : "+v_rtn.length);
	 	
	 conn.commit(); */
	 	
	}catch(Exception e){
		e.printStackTrace();
		//conn.rollback();
	}finally{
		if(conn != null){
			conn.close();
		}
		/* if(pstmt != null){
			pstmt.close();
		}
		if(pstmt1 != null){
			pstmt1.close();
		}
		if(rs != null){
			rs.close();
		} */
	}
 
 %>
 
 
 
