
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.la.company.service.impl;

import java.util.List;

import javax.annotation.Resource;

//import kr.co.sitglobal.oklms.comm.util.FileUploadUtil;
//import kr.co.sitglobal.oklms.comm.util.UUID;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
//import kr.co.sitglobal.oklms.commbiz.util.IOUtills;
//import kr.co.sitglobal.oklms.la.mail.service.MailService;
//import kr.co.sitglobal.oklms.la.mail.vo.MailVO;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("companyService")
public class CompanyServiceImpl extends EgovAbstractServiceImpl implements CompanyService {

	/** ID Generation */
    @Resource(name="companyIdGnrService")
    private EgovIdGnrService companyIdGnrService;

    @Resource(name = "companyMapper")
    private CompanyMapper companyMapper;

  	@Override
	public List<CompanyVO> listCompanyTraningProcess(CompanyVO companyVO) throws Exception {
		List<CompanyVO> data = companyMapper.listCompanyTraningProcess(companyVO);
		return data;
	}

  	@Override
	public List<CompanyVO> listCompanyTraningProcessSearch(CompanyVO companyVO) throws Exception {
		List<CompanyVO> data = companyMapper.listCompanyTraningProcessSearch(companyVO);
		return data;
	}

  	@Override
	public List<CompanyVO> listCompany(CompanyVO companyVO) throws Exception {
		List<CompanyVO> data = companyMapper.listCompany(companyVO);
		return data;
	}

  	@Override
	public List<CompanyVO> listCompanySearch(CompanyVO companyVO) throws Exception {
		List<CompanyVO> data = companyMapper.listCompanySearch(companyVO);
		return data;
	}

	 @Override
	 public CompanyVO getCompany(CompanyVO companyVO) throws Exception {
		 CompanyVO data = companyMapper.getCompany(companyVO);
		 return data;
	 }

	@Override
	public String insertCompany(CompanyVO companyVO) throws Exception {
		String returnStr = "";
		String pkCompanyId = companyIdGnrService.getNextStringId();
		companyVO.setCompanyId(pkCompanyId);

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(companyVO);

		//공백제거
		companyVO.setCompanyId(StringUtils.delete(companyVO.getCompanyId(), " ").trim());
		int sqlResultInt = companyMapper.insertCompany(companyVO);
		if( 0 < sqlResultInt ){
			returnStr = pkCompanyId;
		}
		return returnStr;
	}

	@Override
	public int updateCompany(CompanyVO companyVO) throws Exception {

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(companyVO);

		int sqlResultInt = companyMapper.updateCompany(companyVO);
		return sqlResultInt;
	}

	@Override
	public int deleteCompany(CompanyVO companyVO) throws Exception {

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(companyVO);

		return companyMapper.deleteCompany(companyVO);
	}

	@Override
	public int getCompanyNoCnt(CompanyVO companyVO) throws Exception {

		return companyMapper.getCompanyNoCnt(companyVO);
	}
}
