package kr.co.sitglobal.oklms.comm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Formatter;

//import oracle.sql.CLOB;

import org.springframework.util.StringUtils;

/**
 * 팝업 모델 클래스
 * @author 서비스 개발팀 이진근
 * @since 2016.07.15
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2016.07.15  이진근          최초 생성
 *
 * </pre>
 */
public final class TextStringUtil {
    private static final String NEWLINE_CHAR = "\r\n";                         //줄바꿈 처리
    private static final String NEWLINE_CHAR_2 = "\n";                          //줄바꿈 처리
    private static final String NEWLINE_REPLACE = "<BR>";                      //줄바꿈 처리

    /**
     * Remove special white space from both ends of this string.
     * <p>
     * All characters that have codes less than or equal to
     * <code>'&#92;u0020'</code> (the space character) are considered to be
     * white space.
     * <p>
     * java.lang.String의 trim()과 차이점은 일반적인 white space만 짜르는 것이 아니라 위에서와 같은 특수한
     * blank도 짤라 준다.<br>
     * 이 소스는 IBM HOST와 데이타를 주고 받을 때 유용하게 사용했었다. 일반적으로 많이 쓰이지는 않을 것이다.
     *
     * @param str
     *            대상 문자열
     * @return trimed string with white space removed from the front and end.
     */
    public static String trim(String str) {
        int idx = 0;
        char[] val = str.toCharArray();
        int count = val.length;
        int len = count;

        while ((idx < len) && ((val[idx] <= ' ')))
            idx++;
        while ((idx < len) && ((val[len - 1] <= ' ')))
            len--;
        /**
        while ((idx < len) && ((val[idx] <= ' ') || (val[idx] == '　')))
            idx++;
        while ((idx < len) && ((val[len - 1] <= ' ') || (val[len - 1] == '　')))
            len--;
        */
        // while ((idx < len) && (val[idx] <= ' ')) idx++;
        // while ((idx < len) && (val[len - 1] <= ' ')) len--;
        return ((idx > 0) || (len < count)) ? str.substring(idx, len) : str;
    }

    /**
     * 문자열 좌측의 공백을 제거하는 메소드
     *
     * @param str
     *            대상 문자열
     * @return trimed string with white space removed from the front.
     */
    public static String ltrim(String str) {
        int len = str.length();
        int idx = 0;

        while ((idx < len) && (str.charAt(idx) <= ' ')) {
            idx++;
        }
        return str.substring(idx, len);
    }

    /**
     * 문자열 우측의 공백을 제거하는 메소드
     *
     * @param str
     *            대상 문자열
     * @return trimed string with white space removed from the end.
     */
    public static String rtrim(String str) {
        int len = str.length();

        while ((0 < len) && (str.charAt(len - 1) <= ' ')) {
            len--;
        }
        return str.substring(0, len);
    }

    /**
     * 지정된 크기만큼 문자열 앞부분을 분리하는 메소드
     *
     * @param str
     *            대상 문자열
     * @param limit
     *            제한 크기
     * @return splitted string from the source string.
     */
    public static String splitHead(String str, int limit) {
        if (str == null || limit < 4)
            return str;

        int len = str.length();
        int cnt = 0, index = 0;

        while (index < len && cnt < limit) {
            if (str.charAt(index++) < 256) // 1바이트 문자라면...
                cnt++; // 길이 1 증가
            else
                // 2바이트 문자라면...
                cnt += 2; // 길이 2 증가
        }

        if (index < len)
            str = str.substring(0, index) + "...";

        return str;
    }

    /**
     * 지정된 크기만큼 문자열 뒷부분을 분리하는 메소드
     *
     * @param str
     *            대상 문자열
     * @param size
     *            제한 크기
     * @return splitted string from the source string.
     */
    public static String splitTail(String str, int size) {
        if (str == null)
            return "";
        String value = null;
        if (str.length() > size)
            value = "..." + str.substring(str.length() - size);
        else
            value = str;
        return value;
    }

    /**
     * null이나 공백검사
     *
     * @param str
     *            String target
     * @return boolean result
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals("") || str.length() == 0)
            return true;
        else
        	return false;
    }

    /**
     * 파라메터 값의 숫자형 검사
     *
     * @param str
     *            String target
     * @return boolean result
     */
    public static boolean isNumberValue(String str){
    	for(int i=0;i<str.length();i++)
    	{
    		if ( !(Integer.parseInt(str.charAt(i)+"") >= 0 && Integer.parseInt(str.charAt(i)+"") < 10) )
    		{
    			return false;
    		}
    	}
    	return true;
    }
    /**
     * Null인 경우 "" 처리 메소드
     *
     * @param str
     *            대상 문자열
     * @return fixed string from the source string.
     */
    public static String fixNull(String str) {
        String value = null;
        if (isEmpty(str))
            value = "";
        else
            value = trim(str);
        return value;
    }

    /**
     * Null인 경우 임의의 문자처리 메소드
     *
     * @param str
     *            대상 문자열
     *        val
     *            Null일 경우 리턴 값
     * @return fixed string from the source string.
     */
    public static String fixNull(String str, String val) {
        String value = null;
        if (isEmpty(str))
            value = val;
        else
            value = trim(str);
        return value;
    }

    /**
     * Null인 경우 -- 처리 메소드
     *
     * @param str
     *            대상 문자열
     * @return fixed string from the source string.
     */
    public static String fixBar(String str) {
        String value = null;
        if (str == null)
            value = "--";
        else
            value = trim(str);
        return value;
    }

    /**
     * Null 또는 "" 인 경우 "0" 처리 메소드
     *
     * @param str
     *            대상 문자열
     * @return fixed string from the source string.
     */
    public static String fixZero(String str) {
        String value = fixNull(str);
        if (value.equals(""))
            return "0";
        else
            return value;
    }

    /**
     * "0" 인 경우 "" 처리 메소드
     *
     * @param str
     *            대상 문자열
     * @return fixed string from the source string.
     */
    public static String fixNonZero(String str) {
        String value = fixNull(str);
        if (value.equals("0"))
            return "";
        else
            return value;
    }

    /**
     * DBCS 문자열 보정 메소드
     *
     * @param str
     *            대상 문자열
     * @return fixed string from the source string.
     */
    public static String fixDBCS(String str) {
        String value = null;
        if (str == null)
            value = "";
        else {
            str = str.replace('\r', ' ');
            str = str.replace('\n', ' ');
            str = str.replace('\t', ' ');
            value = trim(str);
        }
        return value;
    }

    /**
     * String to Double 메소드
     *
     * @param number
     *            숫자형 문자열
     * @return double.
     */
    public static double toDouble(String number) {
        if (number == null)
            number = "0";
        Double doDouble = Double.valueOf(number);

        return doDouble.doubleValue();
    }

    /**
     * Characreset 변환 메소드(8859_1 --> KSC5601)
     *
     * @param original
     *            변환할 문자열
     * @return KSC5601 Characreset으로 변환된 문자열
     */
    public static synchronized String convert(String original) {
        return convert(1, original);
    }

    /**
     * Characreset 변환 메소드
     *
     * @param type
     *            (변환할 유형 : 1=(8859_1 --> KSC5601), 2=(KSC5601 --> 8859_1)).
     * @param original
     *            (변환할 문자열).
     * @return converted (해당 Characreset으로 변환된 문자열).
     */
    public static String convert(int type, String original) {
        String converted = null;

        if (original == null)
            return "";

        try {
            if (type == 1)
                converted = new String(original.getBytes("8859_1"), "KSC5601");
            else
                converted = new String(original.getBytes("KSC5601"), "8859_1");
        } catch (java.io.UnsupportedEncodingException e) {
            converted = new String(original);
        }
        return converted;
    }

    /**
     * SQL Query 용 value를 리턴 (Single quotation으로 감싸준다)
     *
     * @param source
     *            대상 문자열
     * @return String for SQL query value
     */
    public static String quoteValue(String source) {
        java.lang.StringBuffer sbfValue = new java.lang.StringBuffer("'");
        sbfValue.append(escapeQuote(source)).append("'");
        return sbfValue.toString();
    }

    /**
     * Single Quotation("'") 을 2 Single Quotation ("''") 로 교체
     *
     * @param source
     *            대상 문자열
     * @return Replaced String
     */
    public static String escapeQuote(String source) {
        return replace(source, "'", "''");
    }

    /**
     * Slash("/") 을 2 Slash("//") 로 교체
     *
     * @param source
     *            대상 문자열
     * @return Replaced String
     */
    public static String escapeSlash(String source) {
        return replace(source, "/", "//");
    }

    /**
     * 특정 Item String을 찾아 다른 String 으로 교체
     *
     * @param source
     *            원본 문자열
     * @param target
     *            교체 대상 문자열
     * @param replace
     *            교체 문자열
     * @return Replaced String
     */
    public static String replace(String source, String target, String replace) {
        if (source == null)
            return "";

        int iTargetLen = target.length();

        StringBuffer sbfReplace = new StringBuffer();
        int i = 0;
        int j = 0;

        while (j > -1) {
            j = source.indexOf(target, i);
            if (j > -1) {
                sbfReplace.append(source.substring(i, j)).append(replace);
                i = j + iTargetLen;
            }
        }
        sbfReplace.append(source.substring(i, source.length()));

        return sbfReplace.toString();
    }

    /**
     * 특정 문자 변환 - 대소문자 구분없슴
     *
     * @param source
     *            String 원본 문자열
     * @param target
     *            String 대상 문자열
     * @param replace
     *            String 교체할 문자열
     * @return String replace result
     */
    public static String replaceIgnorecase(String source, String target,
            String replace) {
        int intS = 0;
        int intE = 0;
        StringBuffer result = new StringBuffer();

        // while ( (intE = (source.indexOf(target,
        // intS)>=source.toUpperCase().indexOf(target.toUpperCase(), intS) ?
        // source.indexOf(target, intS) :
        // source.toUpperCase().indexOf(target.toUpperCase(), intS))) >= 0)
        // {
        // result.append(source.substring(intS, intE));
        // result.append(replace);
        // intS = intE + target.length();
        // }

        String sourceUpper = source.toUpperCase();
        String targetUpper = target.toUpperCase();

        while ((intE = (source.indexOf(target, intS) >= sourceUpper.indexOf(
                targetUpper, intS) ? source.indexOf(target, intS) : sourceUpper
                .indexOf(targetUpper, intS))) >= 0) {
            result.append(source.substring(intS, intE));
            result.append(replace);
            intS = intE + target.length();
        }

        result.append(source.substring(intS));
        return result.toString();
    }

    /**
     * 특정 문자 변환 - 대소문자 구분없슴
     *
     * @param source
     *            String 원본 문자열
     * @param target
     *            String 대상 문자열
     * @param replace
     *            String 교체할 문자열
     * @return String replace result
     */
    public static String replaceAll(String source, String target, String replace) {
        return source.replaceAll(target, replace);
    }

    /**
     * 문자열의 길이를 리턴하는 메소드 - 영문은 1 , 한글은 2 만큼 길이에 적용
     *
     * @param target
     *            대상 문자열
     * @return 문자열의 길이(Byte 단위)
     */
    public static int lengthOfDbString(String target) {
        char caracter;
        int length = 0;
        target = target.trim();
        for (int i = 0; i < target.length(); i++) {
            caracter = target.charAt(i);
            if (0 <= caracter && caracter <= 255)
                length += 1;
            else
                length += 2;
        }
        return length;
    }

    /**
     * Convert to String from java.io.Reader <br>
     * DB LONG type data 처리시 사용 <br>
     * (ex) StringManager.convertString(rowset.getCharacterStream(1))
     *
     * @param reader
     *            java.io.Reader
     * @return String
     */
    public static String convertString(java.io.Reader reader)
            throws java.io.IOException {
        java.io.StringWriter oStringWriter = new java.io.StringWriter();
        int ch;

        while ((ch = reader.read()) != -1) {
            oStringWriter.write(ch);
        }

        return oStringWriter.toString();
    }

    /**
     * String 앞 또는 뒤를 특정문자로 지정한 길이만큼 채워주는 함수 <BR>
     * (예) pad("1234","0", 6, 1) --> "123400" <BR>
     *
     * @param src
     *            Source string
     * @param pad
     *            pad string
     * @param totLen
     *            total length
     * @param mode
     *            앞/뒤 구분 (-1:front, 1:back)
     * @return String
     */
    public static String pad(String src, String pad, int totLen, int mode) {
        String paddedString = "";

        if (src == null)
            return "";
        int srcLen = src.length();

        if ((totLen < 1) || (srcLen >= totLen))
            return src;

        for (int i = 0; i < (totLen - srcLen); i++) {
            paddedString += pad;
        }

        if (mode == -1)
            paddedString += src; // front padding
        else
            paddedString = src + paddedString; // back padding

        return paddedString;
    }

    /**
     * 주어진 길이(iLength)만큼 주어진 문자(cPadder)를 strSource의 왼쪽에 붙혀서 보내준다. ex)
     * lpad("abc", 5, '^') ==> "^^abc" lpad("abcdefghi", 5, '^') ==> "abcde"
     * lpad(null, 5, '^') ==> "^^^^^"
     *
     * @param strSource
     * @param iLength
     * @param cPadder
     */
    public static String lpad(String strSource, int iLength, char cPadder) {
        StringBuffer sbBuffer = null;

        if (!isEmpty(strSource)) {
            int iByteSize = getByteSize(strSource);
            if (iByteSize > iLength) {
                return strSource.substring(0, iLength);
            } else if (iByteSize == iLength) {
                return strSource;
            } else {
                int iPadLength = iLength - iByteSize;
                sbBuffer = new StringBuffer();
                for (int j = 0; j < iPadLength; j++) {
                    sbBuffer.append(cPadder);
                }
                sbBuffer.append(strSource);
                return sbBuffer.toString();
            }
        }

        // int iPadLength = iLength;
        sbBuffer = new StringBuffer();
        for (int j = 0; j < iLength; j++) {
            sbBuffer.append(cPadder);
        }

        return sbBuffer.toString();
    }

    /**
     * 주어진 길이(iLength)만큼 주어진 문자(cPadder)를 strSource의 오른쪽에 붙혀서 보내준다. ex)
     * lpad("abc", 5, '^') ==> "abc^^" lpad("abcdefghi", 5, '^') ==> "abcde"
     * lpad(null, 5, '^') ==> "^^^^^"
     *
     * @param strSource
     * @param iLength
     * @param cPadder
     */
    public static String rpad(String strSource, int iLength, char cPadder) {
        StringBuffer sbBuffer = null;

        if (!isEmpty(strSource)) {
            int iByteSize = getByteSize(strSource);
            if (iByteSize > iLength) {
                return strSource.substring(0, iLength);
            } else if (iByteSize == iLength) {
                return strSource;
            } else {
                int iPadLength = iLength - iByteSize;
                sbBuffer = new StringBuffer(strSource);
                for (int j = 0; j < iPadLength; j++) {
                    sbBuffer.append(cPadder);
                }
                return sbBuffer.toString();
            }
        }

        // int iPadLength = iLength;
        sbBuffer = new StringBuffer();
        for (int j = 0; j < iLength; j++) {
            sbBuffer.append(cPadder);
        }

        return sbBuffer.toString();
    }

    /**
     * delimeter로 구분된 문자열의 배열만들기
     *
     * @param source
     *            token string
     * @return String[] Array
     */
    public static String[] makeToken2Array(String source) {
        // Default delimeter = "!"
        return makeToken2Array(source, "!");
    }

    /**
     * delimeter로 구분된 문자열의 배열만들기
     *
     * @param source
     *            toekn string
     * @param delimiter
     *            구분자
     * @return String[] Array <br>
     *         Token 사이의 값이 빈값이라도 해당 Token만큼 처리
     */
    public static String[] makeToken2Array(String source, String delimiter) {
        if (source == null)
            source = "";

        source = replace(source, delimiter, " " + delimiter + " ");

        java.util.StringTokenizer oStringTokenizer = new java.util.StringTokenizer(
                source, delimiter);
        String[] result = new String[oStringTokenizer.countTokens()];

        for (int i = 0; i < result.length; i++) {
            result[i] = trim(oStringTokenizer.nextToken());
        }
        return result;
    }

    /**
     * delimeter로 구분된 문자열의 지정 크기만큼 배열만들기
     *
     * @param source
     *            toekn string
     * @param delimiter
     *            구분자
     * @param size
     *            배열크기
     * @return String Array
     */
    public static String[] makeToken2Array(String source, String delimiter,
            int size) {
        if (source == null)
            source = "";

        source = replace(source, delimiter, " " + delimiter + " ");

        java.util.StringTokenizer oStringTokenizer = new java.util.StringTokenizer(
                source, delimiter);
        int realSize = oStringTokenizer.countTokens();
        String[] result = new String[size];

        for (int i = 0; i < size; i++) {
            if (i < realSize)
                result[i] = trim(oStringTokenizer.nextToken());
            else
                result[i] = "";
        }
        return result;
    }

    /**
     * Properties에 저장된 Elements를 Token String으로 변환 <br>
     * 주의 : delimiter = "|" , pair-delimiter = "^"
     *
     * @param prop
     *            대상 Properties
     * @return Token String (ex : "key1^123|key2^abc")
     */
    public static String toTokenString(java.util.Properties prop) {
        if (prop == null)
            return "";

        StringBuffer result = new StringBuffer();
        String key = "";
        java.util.Enumeration enumKeys = prop.keys();

        while (enumKeys.hasMoreElements()) {
            key = (String) enumKeys.nextElement();
            result.append(key).append("^").append(prop.getProperty(key))
                    .append("|");
        }
        return result.toString();
    }

    /**
     * Token String을 Properties 로 변환 <br>
     * 주의 : delimiter = "|" , pair-delimiter = "^"
     *
     * @param params
     *            Token String(ex : "key1^123|key2^abc")
     * @param prop
     *            Properties (기존 Properties에 계속 추가할 때 사용)
     * @return Properties
     */
    public static java.util.Properties toProperties(String params,
            java.util.Properties prop) {
        if (params == null || params.equals(""))
            return prop;
        if (prop == null)
            prop = new java.util.Properties();

        String[] arrRows = makeToken2Array(params, "|");
        for (int i = 0; i < arrRows.length; i++) {
            String[] arrCols = makeToken2Array(arrRows[i], "^");
            if (arrCols.length == 1)
                prop.setProperty(arrCols[0], "");
            else if (arrCols.length == 2)
                prop.setProperty(arrCols[0], arrCols[1]);
        }
        return prop;
    }

    /**
     * 우편번호, 전화번호 등과 같이 몇개 부분으로 나누어진 단어들을 배열로 받아 단어 사이에 구분자를 집어넣어 하나의 합쳐진 문자열을
     * 구현한다. ex : zipcode = makeArray2Token(new String[] {zipcode1, zipcode2},
     * "-"); ex : telephone = makeArray2Token(new String[] {tel1, tel2, tel3},
     * "-"); (주의 : 단어가 빈값이면 구분자를 넣지 않는다)
     *
     * @param arrString
     *            문자열 배열
     * @param delimiter :
     *            구분자
     * @return String
     */
    public static String makeArray2Token(String[] arrString, String delimiter) {
        String tokenString = "";

        for (int i = 0; i < arrString.length; i++) {
            if (!arrString[i].equals("")) {
                if (!tokenString.equals(""))
                    tokenString += delimiter;
                tokenString += arrString[i];
            }
        }
        return tokenString;
    }

    /**
     * 문자열의 size만큼 배열만들기
     *
     * @param source
     *            대상 문자열
     * @param arr_size
     *            만들 배열크기
     * @param str_len
     *            배열요소에 지정할 문자열 크기
     * @return String Array
     */
    public static String[] makeString2Array(String source, int arr_size,
            int str_len) {
        if (source == null)
            source = "";

        String[] arr_string = new String[arr_size];

        for (int idx = 0; idx < arr_size; idx++) {
            if (source.length() <= str_len) {
                arr_string[idx] = source;
                source = "";
            } else {
                arr_string[idx] = source.substring(0, str_len);
                source = source.substring(str_len);
            }
        }
        return arr_string;
    }

    /**
     * byte size를 가져온다.
     *
     * @param str
     *            String target
     * @return int bytelength
     */
    public static int getByteSize(String str) {
        if (str == null || str.length() == 0)
            return 0;

        byte[] byteArray = null;

        try {
            byteArray = str.getBytes("KSC5601");
        } catch (Exception ex) {
        }

        if (byteArray == null)
            return 0;

        return byteArray.length;
    }

    /**
     * XSS(Cross Site Scripting) 취약점 해결을 위한 처리
     *
     * @param sourceString
     *            String 원본문자열
     * @return String 변환문자열
     */
    public static String replaceXSS(String sourceString) {
        if (sourceString != null) {
            sourceString = sourceString.replaceAll(
                    "(j|J)(a|A)(v|V)(a|A)(s|S)(c|C)(r|R)(i|I)(p|P)(t|T)",
                    "x-javascript");
            sourceString = sourceString.replaceAll(
                    "(v|V)(b|B)(s|S)(c|C)(r|R)(i|I)(p|P)(t|T)", "x-vbscript");
            sourceString = sourceString.replaceAll(
                    "(s|S)(c|C)(r|R)(i|I)(p|P)(t|T)", "x-script");
            sourceString = sourceString.replaceAll(
                    "(i|I)(f|F)(r|R)(a|A)(m|M)(e|E)", "x-iframe");
            sourceString = sourceString.replaceAll("(f|F)(r|R)(a|A)(m|M)(e|E)",
                    "x-frame");
            sourceString = sourceString.replaceAll(
                    "(e|E)(x|X)(p|P)(r|R)(e|E)(s|S)(s|S)(i|I)(o|O)(n|N)",
                    "x-expression");
            sourceString = sourceString.replaceAll("(a|A)(l|L)(e|E)(r|R)(t|T)",
                    "x-alert");
            sourceString = sourceString.replaceAll("(o|O)(p|P)(e|E)(n|N)",
                    "x-open");
            sourceString = sourceString.replaceAll("&#", "&amp;#");
        }

        return sourceString;
    }

    /**
     * 2007.04.05일 최봉균 전달받은 문자열을 금전형식으로 바꿔주기 사용예 : cngMoney("1000000") 결과 :
     * 1,000,000
     *
     * TODO 입력받은 문자열이 숫자로만 구성되어 있는지 체크하는 로직 추가할것.
     *
     * @param :
     *            str String
     * @return : reMoney;
     */
    public static String cngMoney(String str) {
        return cngMoney(str, "");
    }

    public static String cngMoney(String str, String chagneStr) {
        String rtnStr = chagneStr;
        str = fixZero(str);

        try{
        	if (isNumberValue(str)){
		           Object[] testArgs = {new Long( str )};
		           java.text.MessageFormat form = new java.text.MessageFormat( "{0,number,###,###,###,##0}" );
		           rtnStr = form.format( testArgs );
	        }else{
	        	throw new NumberFormatException();
	        }
        }catch ( NumberFormatException nfe ){
        	nfe.printStackTrace();
        }catch ( Exception e){
        	e.printStackTrace();
        }

        return rtnStr;
    }

    /**
     * 2007.04.05 최봉균 Method : 문자열을 토큰에 따라 배열로 돌려준다.
     *
     * <pre>
     * String orgStr = &quot;한글;영어;중국어;게일어&quot;;
     * String splitStrAry[] = StringUtil.split(orgStr, &quot;;&quot;);
     * for (int i = 0; i &lt; orgStr.length; i++) {
     *     //logger.debug(splitStrAry[i]); // Print 한글     / 영어/ 중국어/ 게일어
     * }
     * </pre>
     *
     * @param String
     *            string 원본 문자열
     * @param Strining
     *            onToken 토큰
     * @return String[] 결과 배열
     */
    public static String[] split(final String string, final String onToken) {
        final StringTokenizer tokenizer = new StringTokenizer(string, onToken);
        final String[] result = new String[tokenizer.countTokens()];

        for (int i = 0; i < result.length; i++) {
            result[i] = tokenizer.nextToken();
        }

        return result;
    }

    /**
     * 문자열 사이에서 숫자만 추출
     * @param arg String [입력 문자열]
     * @return [출력 파라미터]
     */
    public static String getOnlyNum(String arg) {
        String bigyo = "0123456789";
        String retval = "";
        if (arg == null || arg.trim().equals(""))
            return "";
        for (int i = 0; i < arg.length(); i++) {
            if (bigyo.indexOf("" + arg.charAt(i)) >= 0) {
                retval += arg.charAt(i);
            }
        }
        return retval;
    }

    /**
     * Oracle DB에 CLOB 타입의 대용량 Column을 String 타입으로 가져옴.
     *
     * @param clob CLOB
     *            대상 문자열
     * @return string
     */
    /*public static String getClobContent(CLOB clob)throws SQLException{
        StringBuffer str = new StringBuffer();
        try{
        	// 2008-09-12 유지보수내역 56번 참조.
        	if ( clob != null ) {
	            Reader is = clob.getCharacterStream ();
	            BufferedReader in = new BufferedReader(is);
	            String line = null;
	            while((line = in.readLine()) != null){
	                str.append(line);
	                str.append(NEWLINE_CHAR);
	            }
	            in.close();
	            is.close();
        	} else {
        		str.append("");
        	}
        }catch (IOException ie) {
            ie.printStackTrace();
        }

        return str.toString();
    }*/

    /**
     * 줄바꿈 처리
     * @return the content
     */
    public static String getHtmlString( String strValue) {
        if( StringUtils.hasText(strValue) && strValue.indexOf( NEWLINE_CHAR ) > 0 ) {
            strValue = strValue.replaceAll( NEWLINE_CHAR, NEWLINE_REPLACE );
        }

        return strValue;
    }

    /**
     * 줄바꿈 처리
     * @return the content
     */
    public static String getHtmlString2( String strValue) {
        if( StringUtils.hasText(strValue) && strValue.indexOf( NEWLINE_CHAR_2 ) > 0 ) {
            strValue = strValue.replaceAll( NEWLINE_CHAR_2, NEWLINE_REPLACE );
        }

        return strValue;
    }

	/**
	 * 원하는 문자열을 제거하여 반환
	 * @author		: sawh
	 * @since		: 2005-09-27
	 * @param		: String, String ("ABCD", "AB") => "CD"
	 * @return		: String
	 */
	public static String remString(String strSource, String strRemStr)
	{
		for (;;)
		{
			int index = strSource.indexOf(strRemStr);

			if (index >=0)
			{
				strSource = strSource.substring(0, index) + strSource.substring(index + strRemStr.length());
			}
			else
			{
				break;
			}
		}

		return strSource;
	}
}