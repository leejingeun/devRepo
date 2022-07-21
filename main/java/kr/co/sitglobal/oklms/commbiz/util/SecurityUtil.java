/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 12. 09.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.commbiz.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

 /**
 * 암호화 관련 Util
 * @since 2016. 07. 01.
 */
@Component("securityUtil")
@Scope("prototype")
@SuppressWarnings( {"rawtypes", "unchecked"} )
public class SecurityUtil {
	
	private static byte[] IV_BY = new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	
	/**
	 * AES 128 암호화 
	 * @param salt   대칭키 문자열
	 * @param encStr 암호화 시킬 문자열
	 * @return 암호화된 문자열
	 * @throws EgovBizException 
	 */
	public static String encryptAes128(final String salt, final String content) throws Exception{
		
		if(StringUtils.isEmpty(content))
			return StringUtils.EMPTY;
		
		try{
			String encryptString = salt + content;
			String keyString = salt.substring(0, 16);
			AlgorithmParameterSpec aSpec = new IvParameterSpec(IV_BY);
			SecretKeySpec skeySpec = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, aSpec);
			byte[] cipherByte = cipher.doFinal(encryptString.getBytes("UTF-8"));
			return Base64.encodeBase64String(cipherByte);
		}catch(Exception e){
			e.printStackTrace();
			throw new EgovBizException("암호화 처리중 오류 발생");
		}
	}
	
	/**
	 * AES 128 복호화 
	 * @param salt  	 대칭키 문자열
	 * @param content 	 복호화 시킬 문자열
	 * @return 복호화된 문자열
	 * 
	 * AES 암호화 시킬 때 문자를 salt + content 합쳐서 문자를 암호화 하기 때문에 salt 문자를 "" 처리 함 
	 * @throws EgovBizException 
	 * 
	 */
	public static String decryptAes128(final String salt, final String content) throws Exception{
		
		if(StringUtils.isEmpty(content))
			return StringUtils.EMPTY;
		
		try {
			byte[] textBy = Base64.decodeBase64(content.getBytes("UTF-8"));
			String keyString = salt.substring(0, 16);
			AlgorithmParameterSpec aSpec = new IvParameterSpec(IV_BY);
			SecretKeySpec skeySpec = new SecretKeySpec(keyString.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, aSpec);
			byte[] cipherByte = cipher.doFinal(textBy);
			String resultString = new String(cipherByte, "UTF-8").replaceAll(salt, ""); 
			
			return resultString;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EgovBizException("복호화 처리중 오류 발생");
		}
	}
	
	/**
	 * MD5 암호화 
	 * @param content 암호화 시킬 문자열
	 * @return 암호화된 문자열
	 */
	public static String encryptMd5(final String content) {
		
		if(StringUtils.isEmpty(content))
			return StringUtils.EMPTY;
		
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(content.getBytes());
			byte[] by = digest.digest();
			for(byte b : by){
				sb.append(  Integer.toHexString((int)b & 0x00ff)  );
			}
			return Base64.encodeBase64String(sb.toString().getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * SHA-256 암호화 
	 * @param content 암호화 시킬 문자열
	 * @return 암호화된 문자열
	 */
	public static String encryptSha256(final String content){
		
		if(StringUtils.isEmpty(content))
			return StringUtils.EMPTY;
		
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(content.getBytes("UTF-8"));
			byte[] by = digest.digest();
			for(byte b : by){
				sb.append( Integer.toString((b & 0xff) + 0x100 , 16).substring(1) );
			}
			return Base64.encodeBase64String(sb.toString().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}
	
	
	
	public static void main(String[] args) {
//		String key = "1234567890abcdef";
		String key = "123456789012345678901234567890ab11";
		String str = "평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1평문1";
		SecurityUtil su = new SecurityUtil();
		try {
			String encStr = su.encryptAes128( key, str );
			String decStr = su.decryptAes128( key, encStr );
			System.out.println( encStr );
			System.out.println( decStr );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
