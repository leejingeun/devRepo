package kr.co.sitglobal.oklms.lu.workcert.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.workcert.vo.WorkCertVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("workCertMapper")
public interface WorkCertMapper {
	/**
	 * 제직증빙서류 제출기간 카운트 조회
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int selectWorkCertPeriodCnt(WorkCertVO workCertVO)throws Exception;
	/**
     * 재직증빙서류제출 목록을 조회 한다.
     *
     * @param WorkCertVO
     */
	List<WorkCertVO> selectWorkCert(WorkCertVO workCertVo) throws Exception;
 
	/**
     * 재직증빙서류제출 첨부파일 아이디 조회한다.
     *
     * @param WorkCertVO
     */
	WorkCertVO selectAtchFileId(WorkCertVO workCertVo) throws Exception;
	/**
     * 재직증빙서류를 다운로드 하면 DB를 수정한다.
     *
     * @param WorkCertVO
     */
	int downloadWorkCert(WorkCertVO workCertVo) throws Exception;

	/**
     * 재직증빙서류를 다운로드 완료후 삭제에 대한 DB를 수정한다.
     *
     * @param WorkCertVO
     */
	int removeWorkCert(WorkCertVO workCertVo) throws Exception; 
	/**
	 * 제직증빙서류 제출기간 수정
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int updateWorkCertPeriod(WorkCertVO workCertVO)throws Exception; 
	/**
	 * 제직증빙서류 제출기간 리스트
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	List<WorkCertVO> listWorkCertPeriod(WorkCertVO workCertVO)throws Exception;
	/**
	 * 제직증빙서류 제출기간 상세
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	WorkCertVO getWorkCertPeriod(WorkCertVO workCertVO)throws Exception;
	/**
	 * 제직증빙서류 제출기간 삭제
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int deleteWorkCertPeriod(WorkCertVO workCertVO)throws Exception;

	int goInsertWorkCertPeriod(WorkCertVO workCertVO)throws Exception; 
	/**
	 * 학습 근로자 제직징빙서류 수정
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int updateWorkCert(WorkCertVO workCertVO)throws Exception;
	/**
	 * 학습 근로자 제직징빙서류 입력
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 *
	 */
	int goInsertWorkCert(WorkCertVO workCertVO)throws Exception; 
	/**
	 * 학습 글로자 제직 징빙서류 제출 반려 승인
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int updateWorkCertMember(WorkCertVO workCertVO)throws Exception; 
	/**
	 * 학습근로자 재직증빙서류 목록
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	List<WorkCertVO> listWorkCertStatePop(WorkCertVO workCertVO)throws Exception; 
	
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
	/**
	 * 학과전담자 파일다운로드 일자 변경처리
	 * @param workCertVO
	 * @return
	 * @throws Exception
	 */
	int updateWorkCertMemberFiledown(WorkCertVO workCertVO)throws Exception;
	
	int updateOffWorkCertMember(WorkCertVO workCertVO)throws Exception;
}