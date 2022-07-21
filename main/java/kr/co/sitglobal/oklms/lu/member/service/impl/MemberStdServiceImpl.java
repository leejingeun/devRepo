
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 01. 06.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.member.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.util.Config;
import kr.co.sitglobal.oklms.comm.util.ExcelData;
import kr.co.sitglobal.oklms.comm.util.ExcelDataSet;
import kr.co.sitglobal.oklms.comm.util.ExcelHandler;
import kr.co.sitglobal.oklms.comm.util.FileUploadUtil;
//import kr.co.sitglobal.oklms.comm.util.FileUploadUtil;
//import kr.co.sitglobal.oklms.comm.util.UUID;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
//import kr.co.sitglobal.oklms.commbiz.util.IOUtills;
//import kr.co.sitglobal.oklms.lu.mail.service.MailService;
//import kr.co.sitglobal.oklms.lu.mail.vo.MailVO;
import kr.co.sitglobal.oklms.lu.member.service.MemberStdService;
import kr.co.sitglobal.oklms.lu.member.vo.ExcelMemberStdVO;
import kr.co.sitglobal.oklms.lu.member.vo.MemberStdVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
//import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("MemberStdService")
public class MemberStdServiceImpl extends EgovAbstractServiceImpl implements MemberStdService {

	/** ID Generation */
    @Resource(name="memberIdGnrService")
    private EgovIdGnrService memberIdGnrService;

    @Resource(name="subjTutMappingIdGnrService")
    private EgovIdGnrService subjTutMappingIdGnrService;

    @Resource(name="subjCcmMappingIdGnrService")
    private EgovIdGnrService subjCcmMappingIdGnrService;

    @Resource(name = "MemberStdMapper")
    private MemberStdMapper memberStdMapper;

    @Resource(name = "currProcService")
    private CurrProcService currProcService;

    /*@Resource(name = "mailService")
    private MailService mailService;*/

    @Override
	public List<ExcelMemberStdVO> listChangeSubjectMemberCot(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listChangeSubjectMemberCot(memberStdVO);
		return data;
	}

    @Override
	public List<ExcelMemberStdVO> listChangeMemberCcm(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listChangeMemberCcm(memberStdVO);
		return data;
	}

    @Override
	public List<ExcelMemberStdVO> listMemberSubjectCodeMapping(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listMemberSubjectCodeMapping(memberStdVO);
		return data;
	}

    @Override
	public List<ExcelMemberStdVO> listMemberSubjectCodeMappingDetail(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listMemberSubjectCodeMappingDetail(memberStdVO);
		return data;
	}

	@Override
	public List<ExcelMemberStdVO> listMemberCot(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listMemberCot(memberStdVO);
		return data;
	}

	@Override
	public List<ExcelMemberStdVO> listMemberCcm(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listMemberCcm(memberStdVO);
		return data;
	}

	@Override
	public List<ExcelMemberStdVO> listMemberStd(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listMemberStd(memberStdVO);
		return data;
	}

	@Override
	public List<ExcelMemberStdVO> listPlanResultMemberStd(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listPlanResultMemberStd(memberStdVO);
		return data;
	}

	@Override
	public List<ExcelMemberStdVO> listMemberPrt(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listMemberPrt(memberStdVO);
		return data;
	}

	@Override
	public List<ExcelMemberStdVO> listMemberChangeApplicationNew(ExcelMemberStdVO memberStdVO) throws Exception {
		List<ExcelMemberStdVO> data = memberStdMapper.listMemberChangeApplicationNew(memberStdVO);
		return data;
	}

	@Override
	public List<ExcelMemberStdVO> listMemberChangeApplication(ExcelMemberStdVO memberStdVO) throws Exception {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		List<ExcelMemberStdVO> data = memberStdMapper.listMemberChangeApplication(memberStdVO);
		return data;
	}

	@Override
	public String insertMemberAllExcel(ExcelMemberStdVO memberStdVO, MultipartHttpServletRequest multiRequest) throws Exception {
		String result = "";
		String memIdDualChkId ="";
		int sqlResultInt1 = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);
		MultipartFile mfile = multiRequest.getFile("uploadExcelFile");

		if (!mfile.isEmpty() ) {
			String realUploadPath = EgovProperties.getProperty("Globals.fileStorePath");
			String tmpFile = FileUploadUtil.uploadFormFile(mfile, realUploadPath );
			//물리경로
			String fullPath = realUploadPath + File.separator+tmpFile;
			String memId ="";
			int memIdDualchkCnt = 0;

			ExcelData 		row;
			ExcelHandler 	eh 		= new ExcelHandler(fullPath);
			ExcelDataSet 	dataSet 	= eh.parseExcelData();

			MemberStdVO memDualChkVO = new MemberStdVO();

			if (!CollectionUtils.isEmpty(dataSet)) {
				//엑셀업로드 파일에 중복회원 체크
				for (int j = 0; j < dataSet.size(); j++) {
					row = dataSet.getRow(j);

					memId = row.getColumn("회원아이디").trim();
					memDualChkVO.setMemId(memId);
					memIdDualchkCnt = memberStdMapper.getMemberCnt(memDualChkVO);

					if(0 < memIdDualchkCnt){
						memIdDualChkId += memId;
		                if (j < dataSet.size()-1){
		                	memIdDualChkId += ",";
		                }
					}
				}

				result = memIdDualChkId;
				System.out.println("========================================");
				System.out.println("memIdDualChkId : " + memIdDualChkId);
				System.out.println("result : " + result);
				System.out.println("========================================");

				//중복된 회원아이디가 없을떼 엑셀에 회원항목들을 VO에 메핑한다.
				if("".equals(memIdDualChkId)){
					for (int i = 0; i < dataSet.size(); i++) {
						ExcelMemberStdVO memVO = new ExcelMemberStdVO();

						row = dataSet.getRow(i);
						memVO.setMemSeq(memberIdGnrService.getNextStringId()); //회원 고유번호
						memVO.setMemId(row.getColumn("회원아이디").trim()); //회원아이디

						String memType = memberStdVO.getMemType(); //회원유형
						memVO.setMemType(memType); //회원유형

						//권한그룹아이디
						if("STD".equals(memType)){ //학습자
							memVO.setAuthGroupId(Config.DEFAULT_AUTH_STD);
						} else if("PRT".equals(memType)){ //담당교수
							memVO.setAuthGroupId(Config.DEFAULT_AUTH_PRO_TUTOR);
						} else if("COT".equals(memType)){ //기업현장교사
							memVO.setAuthGroupId(Config.DEFAULT_AUTH_SUPERVISOR_TEACHAR);
						} else if("CCM".equals(memType)){ //기업전담자
							memVO.setAuthGroupId(Config.DEFAULT_AUTH_CRI_COMPANY);
						} else if("CCN".equals(memType)){ //센터전담자
							memVO.setAuthGroupId(Config.DEFAULT_AUTH_CRI_CENTER);
						} else { //관리자
							memVO.setAuthGroupId(Config.DEFAULT_AUTH_ADM);
						}

						memVO.setMemName(row.getColumn("회원성명").trim()); //회원성명

						String sex = row.getColumn("성별").trim(); //성별
						if("여자".equals(sex)){
							memVO.setSex("F"); //여자
						} else {
							memVO.setSex("M"); //남자
						}

						memVO.setMemBirth(row.getColumn("생년월일").trim().replaceAll("-", "")); //생년월일

						//엑셀일괄등록 비밀번호는 디폴트 1111 등록한다.
						String memPassword = "1111";
						memVO.setMemPassword(memPassword);  //비밀번호

						//핸드폰번호
						String phonNumber = "";
						phonNumber = row.getColumn("핸드폰번호");
						if (phonNumber.indexOf("-") < 0) {
							throw new Exception("핸드폰번호에 '-'이없습니다 ");
						}
						String[] phonNm = phonNumber.split("-");
						memVO.setHpNo1(phonNm[0].trim());
						memVO.setHpNo2(phonNm[1].trim());
						memVO.setHpNo3(phonNm[2].trim());

						memVO.setEmail(row.getColumn("이메일").trim()); //이메일
						memVO.setBndlRegYn("Y"); //일괄등록여부
						memVO.setForeignYn("N"); //외국인여부
						memVO.setReceiveMailYn("Y"); //이메일수신여부
						memVO.setReceiveSmsYn("Y"); //SMS수신여부
						memVO.setCertiYn("Y");

						memVO.setSessionMemSeq(memberStdVO.getSessionMemSeq()); //일괄업로드 등록자 세션정보
						memVO.setLoginPWMaxUsedDays(EgovProperties.getProperty("Globals.loginPWMaxUsedDays")); //사용만료일자
						memVO.setInsertResult("1");

						//기업아이디 정보셋팅
						if ("".equals(memberStdVO.getCompanyId())) {
							throw new Exception("("+memberStdVO.getCompanyName()+")에 선택한 기업아이디 정보가 없습니다 ");
						}
						memVO.setCompanyId(memberStdVO.getCompanyId());
						//기업명 정보셋팅
/*						if ("".equals(memberStdVO.getCompanyName())) {
							throw new Exception("("+memberStdVO.getCompanyName()+")에 선택한 기업아이디 정보가 없습니다 ");
						}*/
						memVO.setCompNm(memberStdVO.getCompanyId());

						//회원정보 저장
						try{
							sqlResultInt1 += memberStdMapper.insertMemberExcel(memVO);
						} catch(Exception e){
							if("COT".equals(memberStdVO.getMemType())){
								throw new Exception(memVO.getMemId()+"-회원ID에 액셀쉬트 정보를 읽어서 기업현장교사 메핑 테이블에 저장하는도중 애러가 발생 하였습니다.");
							}else{
								throw new Exception(memVO.getMemId()+"-회원ID에 액셀쉬트 정보를 읽어서 HRD전담자 메핑 테이블에 저장하는도중 애러가 발생 하였습니다.");
							}
						}
					}
				}
			}
		}

		//회원ID가 중복건이 없을시 엑셀업로드 저정후 결과값 셋팅
		if("".equals(memIdDualChkId)){
			if(sqlResultInt1 == 0){
				result = "FAIL";
			}else{
				result = "SUCCESS";
			}
		}

		return result;
	}

	@Override
	public int insertMemberTutMapping(ExcelMemberStdVO memberStdVO) throws Exception {
		String strPk = "";
		int insertCnt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		strPk = subjTutMappingIdGnrService.getNextStringId();
		memberStdVO.setSubjTutMappingId(strPk);
		insertCnt = memberStdMapper.insertMemberTutMapping(memberStdVO);

		return insertCnt;
	}


	@Override
	public String getMemberTutMappingGgoupName(ExcelMemberStdVO memberStdVO) throws Exception {
		String tutorGroupName = memberStdMapper.getMemberTutMappingGgoupName(memberStdVO);
		return tutorGroupName;
	}

	@Override
	public String getMemberName(String memSeq) throws Exception {
		return memberStdMapper.getMemberName(memSeq);
	}

	@Override
	public String insertMemberChangeApplication(ExcelMemberStdVO memberStdVO) throws Exception {
		String strMemSeq = "";
		String strPk1 = "";
		String strPk2 = "";
		int insertCnt1 = 0;
		int insertCnt2 = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		strMemSeq = memberIdGnrService.getNextStringId();
		memberStdVO.setMemSeq(strMemSeq);
		memberStdVO.setLoginPWMaxUsedDays(EgovProperties.getProperty("Globals.loginPWMaxUsedDays")); //사용만료일자

		System.out.println("========================================");
		System.out.println("memberStdVO : " + memberStdVO.toString());
		System.out.println("workingPlace : " + memberStdVO.getWorkingPlace());
		System.out.println("========================================");

		insertCnt1 = memberStdMapper.insertMemberChangeApplication(memberStdVO);

		if(insertCnt1 > 0){
			//회원유형에 따라 메핑정보 저장 테이블 분기처리
			if("COT".equals(memberStdVO.getMemType())){ //교사별 교과정보 메핑 데이타 저장
				strPk1 = subjTutMappingIdGnrService.getNextStringId();
				memberStdVO.setSubjTutMappingId(strPk1);
				insertCnt2 = memberStdMapper.insertMemberTutMapping(memberStdVO);
			} else { //HRD전담자 훈련정보 데이타 저장
				strPk2 = subjCcmMappingIdGnrService.getNextStringId();
				memberStdVO.setSubjCcmMappingId(strPk2);
				insertCnt2 = memberStdMapper.insertSubjCcmMapping(memberStdVO);
			}
		}

		if(insertCnt1 == 0 ||  insertCnt2 == 0){
			strMemSeq = "FAIL";
		}


		return strMemSeq;
	}

	@Override
	public int updateMemberChangeApplication(ExcelMemberStdVO memberStdVO) throws Exception {
		int updtCnt1 = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		updtCnt1 = memberStdMapper.updateMemberChangeApplication(memberStdVO);

		return updtCnt1;
	}

	@Override
	public int updateMemberChangeApplicationNew(ExcelMemberStdVO memberStdVO) throws Exception {
		int sqlResultInt = 0;
		int updtCnt1 = 0;
		int updtCnt2 = 0;
		int readCnt = 0;
		String strPk = "";
		String yyyy = "";
		yyyy = memberStdVO.getYyyy();

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		if("COT".equals(memberStdVO.getMemType())){
			readCnt = memberStdMapper.getChangeSubjectMemberCnt(memberStdVO);
		} else {
			readCnt = memberStdMapper.getChangeTrangeMemberCnt(memberStdVO);
		}

		if(readCnt == 0){
			updtCnt1 = memberStdMapper.updateMemberChangeApplication(memberStdVO);

			//담당자 변경신청건의 회원유형 : COT->기업현장교사 일떼만 수행
			if("COT".equals(memberStdVO.getMemType())){
				if(updtCnt1 > 0 && !"".equals(yyyy)){
					strPk = subjTutMappingIdGnrService.getNextStringId();
					memberStdVO.setSubjTutMappingId(strPk);
					updtCnt2 = memberStdMapper.insertMemberTutMapping(memberStdVO);

					if(updtCnt1 > 0 && updtCnt2 > 0){
						sqlResultInt = sqlResultInt+1;
					}
				}
			} else {
				if(updtCnt1 > 0){
					strPk = subjCcmMappingIdGnrService.getNextStringId();
					memberStdVO.setSubjCcmMappingId(strPk);
					updtCnt2 = memberStdMapper.insertSubjCcmMapping(memberStdVO);

					if(updtCnt1 > 0 && updtCnt2 > 0){
						sqlResultInt = sqlResultInt+1;
					}
				}
			}
		}

		return sqlResultInt;
	}

	@Override
	public int updateSubjCcmMapping(ExcelMemberStdVO memberStdVO) throws Exception {

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);
		int sqlResultInt = 0;

		if( memberStdVO.getMemIdCount() > 0 ){
			sqlResultInt = memberStdMapper.updateSubjCcmMapping(memberStdVO);
		}else{
			String pkStr = subjCcmMappingIdGnrService.getNextStringId();
			System.out.println("##### pkStr="+pkStr);
			memberStdVO.setSubjCcmMappingId(pkStr);

			sqlResultInt = memberStdMapper.insertSubjCcmMapping(memberStdVO);
		}

		return sqlResultInt;
	}

	@Override
	public int updateMemberChangeApplicationApproval(ExcelMemberStdVO memberStdVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);


		sqlResultInt = memberStdMapper.updateMemberChangeApplicationApproval(memberStdVO);

		return sqlResultInt;
	}

	@Override
	public int getCcmMappingCnt(ExcelMemberStdVO memberStdVO) throws Exception {
		int sqlResultInt = memberStdMapper.getCcmMappingCnt(memberStdVO);
		return sqlResultInt;
	}

	@Override
	public int getChangeSubjectMemberCnt(ExcelMemberStdVO memberStdVO) throws Exception {
		int sqlResultInt = memberStdMapper.getChangeSubjectMemberCnt(memberStdVO);
		return sqlResultInt;
	}

	@Override
	public int getChangeTrangeMemberCnt(ExcelMemberStdVO memberStdVO) throws Exception {
		int sqlResultInt = memberStdMapper.getChangeTrangeMemberCnt(memberStdVO);
		return sqlResultInt;
	}

	@Override
	public ExcelMemberStdVO getMemberChangeApplication(ExcelMemberStdVO memberStdVO) throws Exception {
		ExcelMemberStdVO data = null;

		String memSeq = "";
		String memType = "";
		String companyId = "";

		for( int idx = 0 ; idx < memberStdVO.getMemSeqs().length ; idx++ ) {
			System.out.println("idx ==> "+idx);
			System.out.println("arrMemSeq ==> "+memberStdVO.getMemSeqs()[idx]);

			if(idx == 0){
				memSeq = memberStdVO.getMemSeqs()[idx];
				memberStdVO.setMemSeq(memSeq);
			} else if(idx == 1){
				memType = memberStdVO.getMemSeqs()[idx];
				memberStdVO.setMemType(memType);
			} else {
				companyId = memberStdVO.getMemSeqs()[idx];
				memberStdVO.setCompanyId(companyId);
			}
		}

		System.out.println("memSeq ==> "+memberStdVO.getMemSeq());
		System.out.println("memType ==> "+memberStdVO.getMemType());
		System.out.println("companyId ==> "+memberStdVO.getCompanyId());

		if("COT".equals(memberStdVO.getMemType())){
			data = memberStdMapper.getMemberChangeApplicationCcn(memberStdVO);
		} else {
			data = memberStdMapper.getMemberChangeApplicationCcm(memberStdVO);
		}

		return data;
	}

	@Override
	public int deleteMemberTutMapping(ExcelMemberStdVO memberStdVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		sqlResultInt = memberStdMapper.deleteMemberTutMapping(memberStdVO);

		return sqlResultInt;
	}

	@Override
	public int deleteMemberList(ExcelMemberStdVO memberStdVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		sqlResultInt = memberStdMapper.deleteMemberList(memberStdVO);

		return sqlResultInt;
	}

	@Override
	public int deleteChangeMemberCot(ExcelMemberStdVO memberStdVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		sqlResultInt = memberStdMapper.deleteChangeMemberCot(memberStdVO);

		return sqlResultInt;
	}

	@Override
	public int deleteChangeMember(ExcelMemberStdVO memberStdVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		sqlResultInt = memberStdMapper.deleteChangeMember(memberStdVO);

		return sqlResultInt;
	}

	@Override
	public int deleteMemberCcmList(ExcelMemberStdVO memberStdVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		sqlResultInt = memberStdMapper.deleteMemberCcmList(memberStdVO);

		return sqlResultInt;
	}


}
