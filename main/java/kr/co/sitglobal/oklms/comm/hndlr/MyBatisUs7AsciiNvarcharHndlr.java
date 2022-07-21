package kr.co.sitglobal.oklms.comm.hndlr;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

 /**
 * DB 문자셋이 US7ASCII 로 셋팅되어있는경우 한글이 깨지는 현상에대한 용도로 mybatis 쿼리 작성시에 사용됨.
 * SQL 예 : #{prototypeContents2 , typeHandler=kr.co.sitglobal.oklms.comm.hndlr.MyBatisUs7AsciiNvarcharHndlr}
 * @author <a href="mailto:reo_me@sitglobal.co.kr">AA</a>
 * @since 2016. 7. 16.
 */
public class MyBatisUs7AsciiNvarcharHndlr implements TypeHandler {

    /**
     * 파라메터 셋팅할때
     * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) 
           throws SQLException {
		String str = "";
		try{
			str = new String(((String)parameter).getBytes("EUC_KR"), "8859_1");
		}catch(Exception e){
//			LOGGER.debug("ERROR" , e);
			str = (String)parameter;
		}
		ps.setNString(i, str);
    }
 
    /**
     * SQL 호출해서 ResultSet 으로 컬럼값을 읽어올때
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
     */
    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
		try{
			return new String(rs.getNString(columnName).getBytes("8859_1"), "EUC_KR");
		}catch(Exception e){
//			LOGGER.debug("ERROR" , e);
			return rs.getNString(columnName);
		}
    }
 
    /**
     * CallableStatement 로 SQL 호출해서 컬럼값 읽어올때
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
     */
    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		try{
			return new String(cs.getNString(columnIndex).getBytes("8859_1"), "EUC_KR");
		}catch(Exception e){
//			LOGGER.debug("ERROR" , e);
			return cs.getNString(columnIndex);
		}
    }

	/**
	 * SQL 호출해서 ResultSet 으로 컬럼값을 읽어올때
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)
	 */
	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		try{

			return new String(rs.getNString(columnIndex).getBytes("8859_1"), "EUC_KR");
		}catch(Exception e){
//			LOGGER.debug("ERROR" , e);
			return rs.getNString(columnIndex);
		}
	}
}
