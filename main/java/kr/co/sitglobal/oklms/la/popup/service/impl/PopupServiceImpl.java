
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.popup.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.la.popup.service.PopupService;
import kr.co.sitglobal.oklms.la.popup.vo.PopupVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
//import org.apache.commons.beanutils.BeanUtils;

 /**
 * Service Implements 클레스 : 비지니스 로직을 구현하는 클레스.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("popupService")
public class PopupServiceImpl extends EgovAbstractServiceImpl implements PopupService{

	private static final Logger LOG = LoggerFactory.getLogger(PopupServiceImpl.class);
	
	/** ID Generation */
    @Resource(name="popupManageIdGnrService")
    private EgovIdGnrService popupManageIdGnrService;
   
    
    @Resource(name = "popupMapper")
    private PopupMapper popupMapper;
    
    @Resource(name = "atchFileService")
	private  AtchFileService atchFileService;
    

    
//	@Autowired
//	private FileService fileService;
//	
//	@Autowired
//	private FileMapper fileMapper;
//	
	
	
	/**
	 * 팝업사용가능한전체목록
	 * @param command
	 * @return
	 */
	public List<PopupVO> getPopupAllList(PopupVO popupVO) {
		LOG.debug("getPopupAllList");
		List<PopupVO> data = popupMapper.getPopupAllList(popupVO);
		return data;
	}
	
//	@Override
//	public List<MenuVO> listMenu(MenuVO menuVO) throws Exception {
//		LOG.debug("listMenu");
//		List<MenuVO> data = fileMapper.listMenu(menuVO);
//		return data;
//	}

	/**
	 * 팝업목록
	 * @param command
	 * @return
	 */
	public List<PopupVO> getPopupList(PopupVO popupVO) {
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int currentPage = Integer.parseInt(popupVO.getCurrentPage()==null || popupVO.getCurrentPage().equals("")?"0":popupVO.getCurrentPage());
		int countPerPage = Integer.parseInt(popupVO.getCountPerPage()==null || popupVO.getCountPerPage().equals("")?"0":popupVO.getCountPerPage());

		String startDate = popupVO.getStartDate();
		String endDate = popupVO.getFinishDate();
		
		
		String searchKey = popupVO.getSearchKeyword();
		String searchValue = popupVO.getSearchCondition();
				
		if (StringUtils.isNotEmpty(startDate)) {
			startDate = startDate.replaceAll("-", "");
			startDate = ( startDate + "000000" );
			popupVO.setStartDate(startDate);
		}
		
		if (StringUtils.isNotEmpty(endDate)) {
			endDate = endDate.replaceAll("-", "");
			endDate = ( endDate + "235900" );
			popupVO.setFinishDate(endDate);
		}
		
		if (StringUtils.isNotEmpty(searchKey)  && StringUtils.isNotEmpty(searchValue)) {
			if (searchKey.equals("popupId")) {
				try {
					popupVO.setSearchCondition(searchValue);
				} catch (NumberFormatException e) {
					popupVO.setSearchCondition("0");
				}
			}
		}
		
		List<PopupVO> data = popupMapper.getPopupList(popupVO);
	
		return data;
	}
	

	/**
	 * 이베트정보조회
	 * @param command
	 * @return
	 */
	public PopupVO getPopup(PopupVO popupVO) throws Exception {
		PopupVO data = popupMapper.getPopup(popupVO);

		String startDate = data.getStartDate();
		String startDateTime = startDate.substring(8, startDate.length());
		startDate = startDate.substring(0, 8);
		StringBuffer sb = new StringBuffer();
		sb.append(startDate.substring(0, 4)).append("-");
		sb.append(startDate.substring(4, 6)).append("-");
		sb.append(startDate.substring(6, 8));
		
		data.setStartDate(sb.toString());
		data.setStartDateTime(startDateTime);

		String finishDate = data.getFinishDate();
		String finishDateTime = finishDate.substring(8, finishDate.length());
		finishDate = finishDate.substring(0, 8);
		sb = new StringBuffer();
		
		sb.append(finishDate.substring(0, 4)).append("-");
		sb.append(finishDate.substring(4, 6)).append("-");
		sb.append(finishDate.substring(6, 8));
		
		data.setFinishDate(sb.toString());
		data.setFinishDateTime(finishDateTime);
		
		return data;
	}
	

	private int createOrModify(PopupVO popupVO) {
		
		
		int popupId = Integer.parseInt((popupVO.getPopupId().equals("")?"0":popupVO.getPopupId()));
		
		// 등록 || 수정 판단
		boolean isCreate = popupId == 0;
		int result=0;
		
		// 첨부파일 등록
		
//		String[] tempFileId = StringUtil.convertStringArray(command, "tempFileId");
//
//		String imageFileId = null;
//		
//		if (tempFileId != null) {
//			List<Map<String, Object>> alFileRelation = new ArrayList<Map<String, Object>>();
//			Map<String, Object> fileData = null;
//			for (int i = 0; i < tempFileId.length; i++) {
//				fileData = new HashMap<String, Object>();
//				fileData.put("tempFileId", tempFileId[i]);
//				alFileRelation.add(fileData);
//			}
//			
//			imageFileId = fileService.fileArchiving(alFileRelation);
//			
//		}
//		
//		if (imageFileId == null) {
//			if (command.containsKey("imageFileId")) {
//				imageFileId = (String) command.get("imageFileId");
//			}
//		}
//		
//		command.put("imageFileId", imageFileId);
//		
//		String[] isCloseViewSettingTemp = StringUtil.convertStringArray(command, "isCloseViewSettings");
//		
//		
//		String isCloseViewSettings = "N,N";
//		
//		if (isCloseViewSettingTemp != null && isCloseViewSettingTemp.length > 0) {
//			if (isCloseViewSettingTemp.length == 1) {
//				if (isCloseViewSettingTemp[0].equals("D")) {
//					isCloseViewSettings = isCloseViewSettingTemp[0] + ",N";
//				} else if (isCloseViewSettingTemp[0].equals("W")) {
//					isCloseViewSettings = "N," + isCloseViewSettingTemp[0];
//				}
//			} else {
//				StringBuffer sb = new StringBuffer();
//				for (String s : isCloseViewSettingTemp) {
//					sb.append(s).append(",");
//				}
//				
//				String temp = sb.toString();
//				
//				temp = temp.substring(0, temp.length() - 1);
//				
//				isCloseViewSettings = temp;
//			}
//		}
//		
//		command.put("isCloseViewSettings", isCloseViewSettings);
		String startDate = popupVO.getStartDate();
		String startDateTime = popupVO.getStartDateTime();
		String finishDate = popupVO.getFinishDate();
		String finishDateTime = popupVO.getFinishDateTime();
		
		
		if (StringUtils.isNotEmpty(startDate)) {
			startDate = startDate.replaceAll("-", "");
			startDate = ( startDate + startDateTime );
			popupVO.setStartDate(startDate);
		}
		
		if (StringUtils.isNotEmpty(finishDate)) {
			finishDate = finishDate.replaceAll("-", "");
			finishDate = ( finishDate + finishDateTime );
			popupVO.setFinishDate(finishDate);
		}
		
		if (isCreate) {
			result = popupMapper.insertPopup(popupVO);
//			popupId = (Integer) command.get("popupId");
		} else {
			
//			// 기존 파일 삭제
//			String[] fileId = StringUtil.convertStringArray(command, "fileId");
//			
//			String contentType = (String) command.get("contentType");
//			
//			// HTML 형식일경우
//			if (contentType.equals("H") && StringUtils.isNotEmpty(imageFileId)) {
//				
//				if (fileId != null) {
//					String[] temp = new String[fileId.length + 1];
//					int i = 0;
//					for (String s : fileId) {
//						temp[i] = s;
//						i++;
//					}
//					temp[temp.length - 1] = imageFileId;
//					fileId = temp;
//				} else {
//					fileId = new String[] { imageFileId };
//				}
//				command.put("imageFileId", null);
//			}
//			
//			if (fileId != null) {
//				
//				Map<String, Object> fileData = null;
//				for (int i = 0; i < fileId.length; i++) {
//					fileData = new HashMap<String, Object>();
//					
//					fileData.put("fileId", fileId[i]);
//					fileData.put("relationTable", "COM_POPUP");
////					fileData.put("contentId1", command.get("popupId"));
//					fileData.put("contentId1", popupVO.getPopupId());
//					fileData.put("relationField1", "POPUP_ID");
//					
//					fileService.deleteFileRelationInfo(fileData);
//				}
//			}
			
			
			result = popupMapper.updatePopup(popupVO);
			
		}
		
//		resultModel.setResultModel("S", 1, command);
		return result;		
	}
	
	/**
	 * 팝업등록
	 * @param command
	 * @return
	 * @throws Exception
	 */
	public int insertPopup(PopupVO popupVO)  throws Exception {

		String sMakeId = popupManageIdGnrService.getNextStringId();
		popupVO.setPopupId(sMakeId);
		
		int result=0;
		
		String startDate = popupVO.getStartDate();
		String startDateTime = popupVO.getStartDateTime();
		String finishDate = popupVO.getFinishDate();
		String finishDateTime = popupVO.getFinishDateTime();
		
		if (StringUtils.isNotEmpty(startDate)) {
			startDate = startDate.replaceAll("-", "");
			startDate = ( startDate + startDateTime );
			popupVO.setStartDate(startDate);
		}
		
		if (StringUtils.isNotEmpty(finishDate)) {
			finishDate = finishDate.replaceAll("-", "");
			finishDate = ( finishDate + finishDateTime );
			popupVO.setFinishDate(finishDate);
		}
		
		
		
		result = popupMapper.insertPopup(popupVO);
		
		return result;		
	}
	
	/**
	 * 팝업수정
	 * @param command
	 * @return
	 * @throws Exception
	 */
	public int updatePopup(PopupVO popupVO) {
		int result=0;
		String startDate = popupVO.getStartDate();
		String startDateTime = popupVO.getStartDateTime();
		String finishDate = popupVO.getFinishDate();
		String finishDateTime = popupVO.getFinishDateTime();
		
		if (StringUtils.isNotEmpty(startDate)) {
			startDate = startDate.replaceAll("-", "");
			startDate = ( startDate + startDateTime );
			popupVO.setStartDate(startDate);
		}
		
		if (StringUtils.isNotEmpty(finishDate)) {
			finishDate = finishDate.replaceAll("-", "");
			finishDate = ( finishDate + finishDateTime );
			popupVO.setFinishDate(finishDate);
		}

		result = popupMapper.updatePopup(popupVO);
		
		return result;		
	}
	
	/**
	 * 팝업 사용여부 수정
	 * @param command
	 * @return
	 */
	public int updatePopupIsUse(PopupVO popupVO) {
//		ResultModel resultModel =  new ResultModel();
		int result=0;
		String[] updateItems = ((String)popupVO.getCheckItem()).split(",");
//		String[] updateItems = StringUtil.convertStringArray(command, "checkItem");
		if (updateItems != null) {
			for (String id : updateItems) {
				popupVO.setPopupId(id);
//				command.put("popupId", id);
				result = popupMapper.updatePopupIsUse(popupVO);
			}
		}
//		resultModel.setResultModel("S", 1, command);
		return result;
	}
	
	/**
	 * 팝업삭제
	 * @param command
	 * @return
	 * @throws Exception
	 */
	public int deletePopup(PopupVO popupVO) {
		int result=0;
		result = popupMapper.deletePopup(popupVO);
		return result;
	}


}
