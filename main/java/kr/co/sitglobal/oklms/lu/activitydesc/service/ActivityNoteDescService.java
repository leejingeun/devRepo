package kr.co.sitglobal.oklms.lu.activitydesc.service;

import java.util.List;

import kr.co.sitglobal.oklms.lu.activitydesc.vo.ActivityNoteVO;



import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public interface ActivityNoteDescService {

	/**
	 * 활동 내역 리스트
	 * @param activityVO
	 * @return
	 * @throws Exception
	 */
	List<ActivityNoteVO> listActivityDesc(ActivityNoteVO activityVO)throws Exception;
	/**
	 * 활동 내역 등록 및 수정
	 * @param activityVO
	 * @return
	 * @throws Exception
	 */
	int insertActivityDesc(ActivityNoteVO activityVO)throws Exception;
	/**
	 * 활동 내역 삭제
	 * @param activityVO
	 * @return
	 * @throws Exception
	 */
	int deleteActivityDesc(ActivityNoteVO activityVO)throws Exception;	
	/**
	 * 활동 내역 상세 및 수정 뷰
	 * @param activityVO
	 * @return
	 * @throws Exception
	 */
	ActivityNoteVO goInsertActivityDesc(ActivityNoteVO activityVO)throws Exception;
	/**
	 * 훈련관리 > 활동내역서 > 활동내역서 상세조회 기업현장교사
	 * @param activityVO
	 * @return
	 * @throws Exception
	 */
	List<ActivityNoteVO> listActivityDescDetail(ActivityNoteVO activityVO)throws Exception; 
	/**
	  * 훈련관리 > 활동내역서 > 출력
	 * @param activityVO
	 * @return
	 * @throws Exception
	 *
	 */
	int updateActivityDescPrint(ActivityNoteVO activityVO)throws Exception;


}
