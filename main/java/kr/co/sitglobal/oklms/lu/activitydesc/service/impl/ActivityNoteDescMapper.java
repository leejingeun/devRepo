package kr.co.sitglobal.oklms.lu.activitydesc.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.activitydesc.vo.ActivityNoteVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("activityNoteDescMapper")
public interface ActivityNoteDescMapper {

	List<ActivityNoteVO> listActivityDesc(ActivityNoteVO activityVO)throws Exception;

	int insertActivityDesc(ActivityNoteVO activityVO)throws Exception;

	ActivityNoteVO goInsertActivityDesc(ActivityNoteVO activityVO)throws Exception;

	int selectActivityDescCnt(ActivityNoteVO activityVO)throws Exception;

	int updateActivityDesc(ActivityNoteVO activityVO)throws Exception;

	int selectActivityDescDetailCnt(ActivityNoteVO activityVO)throws Exception;

	int updateActivityDetailDesc(ActivityNoteVO activityVO)throws Exception;

	int insertActivityDescDetail(ActivityNoteVO activityVO)throws Exception;
	  
	List<ActivityNoteVO> listActivityDescDetail(ActivityNoteVO activityVO)throws Exception; 

	int updateActivityDescPrint(ActivityNoteVO activityVO)throws Exception;
	/**
	 * 활동 내역 삭제
	 * @param activityVO
	 * @return
	 * @throws Exception
	 */
	int deleteActivityDesc(ActivityNoteVO activityVO)throws Exception;	
}
