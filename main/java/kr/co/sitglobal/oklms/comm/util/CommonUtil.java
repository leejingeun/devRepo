package kr.co.sitglobal.oklms.comm.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.*;
import javax.crypto.spec.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

public class CommonUtil
{
    protected final Log logger = LogFactory.getLog(getClass());

	public static long strToLong(String sData)
	{
		try
		{
			if (sData == null)
			{
				return 0;
			}
			else
			{
				return (Long.parseLong(sData));
			}
		}
        catch (Exception ex)
        {
            return 0;
        }
	}

    public static int strToInt(String sData)
    {
        try
        {
            if (sData == null)
            {
                return 0;
            }
            else
            {
                return Integer.parseInt(sData);
            }
        }
        catch (Exception ex)
        {
            return 0;
        }
    }

    public static String intToStr(int iData)
    {
        try
        {
            return Integer.toString(iData);
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public static String booleanToStr(boolean bool)
    {
        String sResult = "";
        try
        {
            sResult = Boolean.toString(bool);
        }
        catch (Exception e)
        {
            sResult = "";
        }
        return sResult;
    }

    public static String longToStr(long lg)
    {
        String sResult = "";
        try
        {
            sResult = Long.toString(lg);
        }
        catch (Exception e)
        {
            sResult = "";
        }
        return sResult;
    }

	public static String dblTostr(double dbl)
	{
		String sResult = "";
		try
		{
			sResult = Double.toString(dbl);
		}
		catch (Exception e)
		{
			sResult = "";
		}
		return sResult;
	}

	public static double strTodbl(String sData)
	{
        if (sData == null)
        {
            return 0;
        }
        else
		{
			return (Double.parseDouble(sData));
		}
	}

	public static double intTodbl(int iData)
	{
		return (new Integer(iData).doubleValue());
	}

//    public static String transKor(String sData) throws java.io.
//        UnsupportedEncodingException
//    {
//        String sResult = "";
//        try
//        {
//            sResult = (new String(sData.getBytes("8859_1"), "EUC-KR"));
//        }
//        catch (Exception e)
//        {
//            //logger.info("transKor method of CommonUtil error : " + e.toString());
//        }
//        return sResult;
//    }

    //줄바꿈 처리 (\n을 <br>로 변경)
    public static String toBR(String str) {
        if(str == null) return "";
    int pos = 0;

    while ((pos = str.indexOf("\n")) != -1) {
        String left  = str.substring(0, pos);
        String right = str.substring(pos+1, str.length());
        str = left + "<br>&nbsp;" + right;
    }

    return str;
    }
    //option client -> server true
    //option server <- client false
   
    public static String convertLineFeed(String content, boolean option)
    {
      String mConvertString = "";
      if(option)
      {
        mConvertString = content.replaceAll("\n\n", "\n");
      }else{
        mConvertString = content.replaceAll("\n\n", "\n");
      }
      return mConvertString;
    }

    /**
     * 2007.01.18일  최봉균
     * 전달받은 문자열을 금전형식으로  바꿔주기
     * 사용예 : cngMoney("1000000")
     * 결과 : 1,000,000
     * @param : str String
     * @return : reMoney;
     */
    // TextString class에 똑같은 함수가 존재함.
/*
    public String cngMoney(String str){	 
    	int coin = Integer.parseInt(str);
    	DecimalFormat df = new DecimalFormat("###,###");   
    	   
		
		return df.format(coin).toString();	
    }
*/
    
    /**
     * 2007.01.22 최봉균
	* 입력된 문자열을 날짜 표현형식으로 돌려준다. 
	* 사용예) getFmDate("200102042345","yyyyMMddhhmm","yyyy/MM/dd hh:mm a")
	* 결 과 ) 2001/02/04 11:45 오후   
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.	*
	* @return java.lang.String
	* @param inStr String
	* @param pInformat String
	* @param pOutformat String
	*/
	public static String getFmDate( String inStr, String pInformat, String pOutformat ){
		SimpleDateFormat pInformatter =  new SimpleDateFormat (pInformat, java.util.Locale.KOREA);
		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);
	
		String rDateString = "";
		Date vDate = null;
	
		try{
			if(Double.parseDouble(inStr) == 0 ){
				rDateString	=	inStr;
			}else{
				vDate = pInformatter.parse(inStr);
				rDateString = pOutformatter.format(vDate);
			}
		}catch( Exception e ){
			rDateString = inStr;
		}
		return rDateString;
	}

    /**
     * 기존의 경로에 새로운 경로를 추가시키기 위한 util method
     * @param pathStr 기존 경로
     * @param appendStr 추가될 경로
     * @return 새로운 경로가 추가된 경로
     */
    public static String appendPath( String pathStr, String appendStr ) {
        
        if( !pathStr.endsWith( "/" ) && !pathStr.endsWith( "\\" ) ) {
            pathStr += File.separator;
        }
        
        if( appendStr.startsWith( "/" ) || appendStr.startsWith( "\\" ) ) {
            appendStr = appendStr.substring( 1 );
        }
        
        return StringUtils.cleanPath( pathStr + appendStr );
    }
    
    /**
     * 주민등록번호 뒷자리를 숨긴다
     * @param Str 주민번호 (123456-1234567 형식)      
     * @return 123456-******* 형식의 str
     */
    public static String hiddenRegiNo( String regiNo ) {    
    	String inStr= "";
		 if(!regiNo.equals("") && (regiNo.length() > 12)){
			 inStr = regiNo.substring(0,6) + "-*******";
		 }else{
			 inStr = "--";
		 }
		 return inStr;
    }
    
    /**
     * 반올림 util method
     * @param doubleValue 반올림하고자 하는 수
     * @param scale 반올림 자릿수
     * @return 반올림 값
     */
	public static double round(double doubleValue, int scale) throws ArithmeticException, IllegalArgumentException
	{
		double dblReturn = 0;

		if (TextStringUtil.isEmpty(dblTostr(doubleValue)) || doubleValue == 0)
		{
			return 0;
		}
		else
		{
			dblReturn = Math.round(doubleValue * 10 * scale) / (double) (10 * scale);
		}

		return dblReturn;
	}

    /**
     * 말줄임 util method
     * @param str 줄이고자 하는 문자열
     * @param limit 최대 표현 문자수
     * @return 줄임 문자열
     */
	public static String reduceString(String str, int limit) {
        if( str != null )
            if ( str.length() > limit ) {
                return str.substring( 0, Math.min( str.length(), limit ) ) + "..";
            } else {
                return str.substring( 0, Math.min( str.length(), limit ) );
            }
        else
            return "";
	}

    /**
     * 문자열을 경로 문자열로 변환
     * @param strValue 경로 변환 대상 문자열
     * @return 경로 문자열
     */
    public static String toPathStr(String strValue) {
        if( StringUtils.hasText(strValue) ) {
            if( !strValue.startsWith("/") ) {
                strValue = "/" + strValue;
            }
            
            if( strValue.endsWith("/") ) {
                strValue = strValue.substring(0, strValue.length()-1);
            }
            
            strValue = StringUtils.cleanPath( strValue );
        }

        return strValue;
    }
    
    /**
     * Encrypt된 텍스트를 Descrypt
     * @param theText 변환할 텍스트
     * @return 복호화된String
     */
    public static String unEncrypt(String theText) {
    	
    	String output = "";
    	
    	if (!StringUtils.hasText(theText))
    		return output;
    		
    	ArrayList<Integer> Temp = new ArrayList<Integer>();
    	ArrayList<Integer> Temp2 = new ArrayList<Integer>();

    	int TextSize = theText.length();
    	
    	for (int i = 0; i < TextSize; i++) {
    		Temp.add(theText.codePointAt(i));
    		if (i != TextSize-1)
    		Temp2.add(theText.codePointAt(i+1));
    	}
    	for (int i = 0; i < TextSize; i = i+2) {
    		output += fromCharCode(Temp.get(i) - Temp2.get(i));
    	}
    	
    	return output;
    }
    
    /**
     * ascii값을 string으로 치환
     * @param int... ascii code
     * @return 치환된 문자열
     */
    public static String fromCharCode(int... codePoints) {
        return new String(codePoints, 0, codePoints.length);
    }
    
    /*public static String base64Encode(byte[] src) {
	    Base64.Encoder enc = Base64.getEncoder();
		byte[] encodedBytes = enc.encode(src);
	    return new String(encodedBytes, Charset.forName("UTF-8"));
	}


	public static String base64Decode(byte[] src) {
		Base64.Decoder dec = Base64.getDecoder();
		byte[] decodedBytes = dec.decode(src);
	    return new String(decodedBytes, Charset.forName("UTF-8"));
	}*/
    
    
    public static String AESenc(String text) throws Exception
    {
    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    	byte[] keyBytes = { 0x6b,0x6f,0x72,0x65,0x41,0x35,0x74,0x45,0x63,0x68,0x34,0x6e,0x63,0x53,0x33,0x25 };
    	byte[] ivBytes  = { 0x77,0x69,0x7a,0x69,0x61,0x6e,0x6e,0x63,0x73,0x73,0x79,0x73,0x74,0x65,0x6d,0x31 };
    	SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
    	IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
    	cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);
    	
    	byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
    	return Base64.encodeBase64String(results);
    }

    public static String nullArg(String arg_str)
    {
    	if (arg_str == null)
	  {
	    return "";
	  }
	  else
	  {
	    return arg_str;
	  }
    }

    public static  String check_charset(String arg_chk) {
      if ("".equals(arg_chk)) {
    	    return "";
      }

      if ("한글".equals(arg_chk)) {
        return "";
      }

      String chk1 = "";
      String chk2 = "";

      try {
        chk1 = new String(arg_chk.getBytes("ISO8859-1"), "utf-8");
        chk2 = new String(arg_chk.getBytes("ISO8859-1"), "euc-kr");
      }
      catch (Exception ex) {
        return "euc-kr";
      }

      if ("한글".equals(chk1)) {
        return "utf-8";
      }
      else if ("한글".equals(chk2)) {
        return "euc-kr";
      }

      return "";
    }
    
    /**
     * 배열값을 in 조건문에 사용하기 위한 'xxx', 'aaa' .. 형식의 String으로 변환
     * @param arr 대상 배열
     * @return 변환된 String 값
     */
    public static String toArrStr( String[] arr ) {
        String delim = ",";
        String arrStr = "";
        String str;
        for( int idx = 0 ; idx < arr.length ; idx++ ) {
            str = arr[idx];
            
            if( str != null && !str.equals( "" )) {
                arrStr += ( "'" + str + "'" );

                if( idx + 1 < arr.length ) {
                    arrStr += delim;
                }
            }
        }
        
        if( arrStr.endsWith( delim ) ) {
            arrStr = arrStr.substring( 0, arrStr.lastIndexOf( delim ) );
        }
        
        return arrStr;
    }
    
    
    public static String getLaterDay(String originDay, String format, int laterCnt) {

      String laterDay = ""; 
      SimpleDateFormat df = new SimpleDateFormat(format); //날짜 형식에 따라서 달리 할 수 있다.

       try {
          
           Date date = df.parse(originDay);
           
           Calendar cal = Calendar.getInstance();
           cal.setTime(date);

           cal.add(Calendar.DATE, laterCnt);

           laterDay = df.format(cal.getTime());

       } catch (ParseException ex) {
       }

       return laterDay;
   }

}