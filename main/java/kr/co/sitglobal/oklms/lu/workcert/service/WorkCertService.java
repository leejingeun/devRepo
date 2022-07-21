package kr.co.sitglobal.oklms.lu.workcert.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.sitglobal.oklms.lu.workcert.vo.WorkCertVO;


/**
 * 재직증빙서류 제출을 위한 서비스 인터페이스 클래스
 * @author 이창현
 * @since 2016.12.29
 * @version 1.0
 *
 *
 */
@Transactional(rollbackFor=Exception.class)
public interface WorkCertService {
	
	/**
	 * 재직증빙서류 제출현황
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	List<WorkCertVO> listWorkCertStatePop(WorkCertVO workCertVO)throws Exception;
	/**
	 * 재직증빙서류 제출기간
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	WorkCertVO getWorkCertPeriod(WorkCertVO workCertVO)throws Exception;
    /**
     * 재직증빙서류제출 목록을 조회 한다.
     *
     * @param WorkCertVO
     */
	List<WorkCertVO> selectWorkCert(WorkCertVO workCertVo) throws Exception;
 
	 /**
     * 재직증빙서류제출 첨부파일아이디를 조회한다.
     *
     * @param WorkCertVO
     */
	WorkCertVO selectAtchFileId(WorkCertVO workCertVo) throws Exception;

 

	/**
     * 재직증빙서류를 다운로드 하면 DB를 수정한다.
     *
     * @param WorkCertVO
     */
	String downloadWorkCert(WorkCertVO workCertVo) throws Exception;

	/**
     *  재직증빙서류를 다운로드 완료후 삭제에 대한 DB를 수정한다.
     *
     * @param WorkCertVO
     */
	String removeWorkCert(WorkCertVO workCertVo) throws Exception;
	/***
	 * 재직증빙서류 제출기간 등록 및 수정
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int goInsertWorkCertPeriod(WorkCertVO workCertVO)throws Exception;
	/**
	 * 재직증빙서류 제출기간 리스트
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	List<WorkCertVO> listWorkCertPeriod(WorkCertVO workCertVO)throws Exception;
 
	/**
	 * 재직증빙서류 제출기간 삭제
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int deleteWorkCertPeriod(WorkCertVO workCertVO) throws Exception;
 
	/**
	 * 학습근로자 재직증빙서류 등록
	 * @param workCertVO
	 * @return
	 */
	int goInsertWorkCert(WorkCertVO workCertVO,final MultipartHttpServletRequest multiRequest)throws Exception;
 
	/**
	 * 학과전담자 재직증빙서류 학습근로자 반려 승인
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int updateWorkCertMember(WorkCertVO workCertVO)throws Exception;
	/**
	 * 학과전담자 파일다운로드 일자 변경처리
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int updateWorkCertMemberFiledown(WorkCertVO workCertVO)throws Exception;
	/**
	 *  학과전담자 재직증빙서류 제출현황
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	List<WorkCertVO> listWorkCertStatePopup(WorkCertVO workCertVO)throws Exception;

	/**
	 *  학과전담자 재직증빙서류 제출현황 근로자목록
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	List<WorkCertVO> listWorkCertDetail(WorkCertVO workCertVO)throws Exception;
	
	List<WorkCertVO> selectAtchFileIdList(WorkCertVO workCertVO)throws Exception;
	
	 
	int updateOffWorkCertMember(WorkCertVO workCertVO)throws Exception;
}
