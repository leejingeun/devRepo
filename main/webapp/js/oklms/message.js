/**
 * Client User Message 가져오기
 * - 모든 스크립트는 이 함수를 통해 메세지를 가져온다.
 * - 메세지 내의  "<@ 인자인덱스 >" 란은 함수의 arguments와 순서대로 맵핑된다.
 * [example] "선택할 항목이 없습니다." --> getMessage("NO_ITEM");
 *           "[<@ 1 >]은(는) 반드시 입력해야 합니다." --> getMessage("ESSENTIAL", "캠페인명");
 *           "[<@ 1 >]은(는) 반드시 <@ 2 >자 이내로 입력해야 합니다." --> getMessage("MAXLENGTH", "아이디", "8");
 *
 * @param	key			메시지 코드
 * @param	optional	메시지에 set할 message value
 */
function getMessage (key)
{
	var oMessage = new Message(key);
	var sReturn = oMessage.content;


	if (sReturn != "" && arguments[1] != null)
	{
		for (var i=1; i<arguments.length; i++)
		{
			aMsg = sReturn.split("<@ " + i + " >");
			sReturn = aMsg[0] + arguments[i] + aMsg[1];
		}
	}
	return sReturn;
}


//=================================================================================
//---------------------------------------------------------------------------------
// Message Object Definition
//---------------------------------------------------------------------------------

/**
 * Message Object
 */
function Message(sMessageCD)
{
	this.content = Message_getMessage(sMessageCD);
}

function Message_getMessage (sMessageCD)
{
	return eval("Message." + sMessageCD);
}


//---------------------------------------------------------------------------------
// - Message
//---------------------------------------------------------------------------------

Message.INVALID_ID						="ID는 영문 4자리 이상 입력하셔야 합니다.";
Message.INVALID_PW						="패스워드는 4자리 이상 입력하셔야 합니다.";
Message.CONFIRM_LOGOUT					="로그아웃 하시겠습니까?";
Message.RECOMMENDED						="이미 추천한 글입니다.";
Message.SESSION_OUT						="세션이 끊어졌습니다. 다시 로그인 하십시요.";
Message.NOTINSERT_CONTENT				="내용을 입력하세요.";
Message.NOTALLINSERT_CONTENT			="내용을 전부 입력하세요.";
Message.NO_SEARCH_WORD					="검색어를 입력하세요.";
Message.NO_URL							="해당 페이지 URL 정보가 없습니다.";
Message.DO_SAVE							="저장하시겠습니까?";
Message.NO_LIST							="저장 할 목록이 없습니다.";
Message.NO_DOWN_LIST					="다운받을 목록이 없습니다.";
Message.EXIST_EMPTY						="빈 값이 있습니다.";
Message.OVERTHEHEAD						="소수점 첫 째 자리 까지 입력하세요.";
Message.NO_NUMERIC						="숫자가 아닙니다.";
Message.CONFIRM_MEMDELETE				="탈퇴 하시겠습니까?";
Message.INVALID_PASS_VALUE				="비밀번호가 다릅니다. 확인해 주세요.";
Message.CHECK_ID						="아이디 유효성을 확인해 주세요.";
Message.INSERT_ID_LENGTH				="아이디는 4 ~ 10자 이내만 가능합니다.";
Message.INSERT_ID_CHAR					="아이디는 영문,숫자만 가능합니다.";
Message.INSERT_ID						="아이디를 입력하세요.";
Message.INSERT_JUMIN					="주민등록 번호를 입력하세요.";
Message.NOT_EXIST_ID					="해당하는 ID가 존재하지 않습니다.\n회원가입 후 로그인 해 주십시요.";
Message.NOT_AGREE						="회원가입 이용약관을 읽고 동의하시기 바랍니다.";
Message.MEM_JOIN_SUCCESS				="회원가입이 성공했습니다.";
Message.MEM_UPDATE_SUCCESS				="회원정보 수정이 성공했습니다.";
Message.ALERT_MEM_DELETE				="회원 탈퇴 사유를 간단히 적어주세요.";
Message.MEM_DELETE_SUCCESSE				="회원 탈퇴가 되었습니다.";
Message.NOT_EXIST_PW					="패스워드가 일치하지 않습니다.";
Message.NOT_EXIST_LEC					="강의를 먼저 선택하십시요.";
Message.NOT_EXIST_LECSCH				="강의계획서를 먼저 작성해 주세요.";
Message.NOT_EXIST_PREVIEW				="미리보기 강의가 존재하지 않습니다.";
Message.NOT_FOREIGN_FIND				="내국인만 가능합니다. 외국인은 취소를 선택하시고 관리자에게 문의해주세요.";
Message.REALNAME_CONFIRM				="실명인증이 성공하였습니다. 회원가입페이지로 이동합니다.";
Message.REALNAME_NOT_CONFIRM			="인증에 실패 했습니다.";
Message.EXIST_JUMIN			="이미 가입된 주민번호 입니다.";
Message.MEM_DELETE_NOT_ADM				="관리자는 탈퇴 할수 없습니다.";
Message.MEM_DELETE_NOT_INS				="강사는 탈퇴 할수 없습니다.\n사이트 관리자에게 문의하세요.";

Message.SAME_CLASS_ID					="이동할 반이 대상의 반과 동일합니다.";
Message.NOT_EXIST_CLASS					="이동할 반을 선택하십시요.";
Message.NOT_EXIST_MEM					="이동할 수강생을 선택하십시요.\n여러 명을 선택할 경우 shift 또는 ctrl 키를 이용하십시요.";
Message.INSERT_SUBMIT					="의견이 등록되었습니다.";
Message.NOT_EXIST_FEE					="수강료를 입력하십시요.";
Message.INVALID_CLASS_NAME				="분반 명을 입력하십시요.";
Message.INVALID_PERIOD_NAME				="기수 명을 입력하십시요.";
Message.FAILURE_SCORE					="환급 과정일경우 수료기준점수는 필수항목입니다.";
Message.TOTAL_STD						="환급 과정일경우 학급정원은 필수항목입니다.";
Message.LESSON_CONDITION				="환급 과정일경우 학습대상 조건은 필수항목입니다.";
Message.REBATE_LEVEL					="환급 과정일경우 환급등급은 필수항목입니다.";
Message.REBATE_LARGE_FEE				="환급 과정일경우 대기업 환급률은 필수항목입니다.";
Message.REBATE_MIDDLE_FEE				="환급 과정일경우 중소기업 환급률은 필수항목입니다.";

Message.INVALID_PERIOD_START_DATE		="기수의 시작일이 유효하지 않습니다.\n해당 강의의 시작일이 [<@ 1 >] 입니다.";
Message.INVALID_PERIOD_END_DATE			="기수의 종료일이 유효하지 않습니다.\n해당 강의의 종료일이 [<@ 1 >] 입니다.";
Message.DUPE_PERIOD_DATE				="동일한 기간의 기수가 이미 존재합니다.";
Message.INITIALIZATIOIN					="선택된 항목 점수가 초기화됩니다. \n초기화 하시겠습니까?";
Message.TOTALIZATION_YN					="선택된 항목중 집계되지 않은 항목이 있습니다.";
Message.REQUIRED_FIELD					="필수항목을 입력해주세요.";

//---------------------------------------------------------------------------------
// system message
// - common function 에서 사용합니다. 삭제하지 마세요.
//---------------------------------------------------------------------------------
Message.NO_EVENT						="작업하신 내용이 없습니다.";
Message.NO_ITEM							="선택할 항목이 없습니다.";
Message.INVALID_LOGINID					="입력하신 아이디가 존재하지 않습니다. \n확인후 다시 시도해 주십시오!.";
Message.LOG_FAIL						="로그인에 실패하였습니다. 다시 시도해 주세요.";
Message.LOG_OUT							="일정 시간 사용하지 않았습니다.\n정보보호를 위하여 시스템을 종료합니다.\n\n다시 접속하십시오.";

//---------------------------------------------------------------------------------
// business message
// - 업무별로 필요한 메세지를 이곳에 추가하세요.
//---------------------------------------------------------------------------------
Message.ESSENTIAL					="[<@ 1 >]은(는) 반드시 입력해야 합니다.";
Message.DATALENGTH					="[<@ 1 >]은(는) <@ 2 >자 이내로 입력해야 합니다.";
//Message.VALID_DATE						="[<@ 1 >]은(는) 날짜형식(YYYY.MM.DD)으로 입력해야 합니다.";
Message.VALID_DATE					="[<@ 1 >]은(는) 날짜형식(YYYY-MM-DD)으로 입력해야 합니다.";
Message.DUP_CHECK					="[<@ 1 >]은(는) 중복된 항목입니다.";
Message.ESSENTIAL_COMBO				="[<@ 1 >]은(는) 반드시 선택해야 합니다.";
Message.FILE_MAX_SIZE           	="등록하신 파일은 최대 <@ 1 > M 를 초과했습니다.";

//회원가입.
Message.MSG_NODUALCHK               ="중복체크를 하지 않았습니다.";


//onblur에서 확인하는 메시지
Message.INVALID_CORPNO        		="유효하지 않은 사업자번호 입니다.";
Message.INVALID_BUNO        		="유효하지 않은 법인번호 입니다.";
Message.INVALID_DATE				="날짜형식(YYYY.MM.DD)으로 입력해야 합니다.";
Message.INVALID_NUMBER				="올바른 숫자값이 아닙니다.";
Message.INVALID_PHONENUMBER  		="문자는 허용할 수 없습니다. (단, 하이폰은 제외) 숫자만 입력하세요";
Message.INVALID_JUMIN        		="유효하지 않은 주민등록번호 입니다.";
Message.INVALID_JUMIN_BEFORE       	="앞 주민번호를 6자리로 정확히 입력하십시요.";
Message.INVALID_JUMIN_AFTER        	="뒤 주민번호를 7자리로 정확히 입력하십시요.";
Message.INVALID_JUMIN_NULL        	="주민번호를 입력하십시오.";
Message.INVALID_JUMIN_NUMBER       	="숫자만 입력 가능합니다.";
Message.INVALID_STRING			  	="특수문자는 입력할수 없습니다.";

Message.MSG_INP_DATEES				="종료일은 시작일보다 이후이어야 합니다.";
Message.MSG_INP_DATESE             	="시작일은 종료일보다 이전이어야 합니다.";
Message.MSG_SELECT_START_DATE		="시작일을 선택하세요.";
Message.MSG_SELECT_END_DATE			="종료일을 선택하세요.";
Message.MSG_SELECT_APPLY_DATE		="신청기간을 설정 하세요.";

Message.MSG_ALERT_DECIDE          		="선택하신 정보를 확정하려고 합니다. 정말로 확정하시겠습니까?";
Message.MSG_ALERT_PERMIT          		="선택하신 정보를 승인하려고 합니다. 정말로 승인하시겠습니까?";
Message.MSG_ALERT_CANCEL          		="선택하신 정보를 미확정하려고 합니다. 정말로 미확정하시겠습니까?";
Message.MSG_ALERT_INSERT           		="선택하신 정보를 등록하려고 합니다. 정말로 등록하시겠습니까?";
Message.MSG_ALERT_UPDATE          		="선택하신 정보를 수정하려고 합니다. 정말로 수정하시겠습니까?";
Message.MSG_ALERT_DELETE           		="선택하신 정보를 삭제하려고 합니다. 정말로 삭제하시겠습니까?";

Message.MSG_INSERT_SUCCESS      	="정보가 저장되었습니다.";
Message.MSG_UPDATE_SUCCESS        	="정보가 수정되었습니다.";
Message.MSG_DELETE_SUCCESS         	="정보가 삭제되었습니다.";

Message.MSG_INSERT_FAIL             ="정보 저장시 실패하였습니다.";
Message.MSG_DELETE_FAIL             ="정보 삭제시 실패하였습니다.";
Message.MSG_UPDATE_FAIL            	="정보 수정시 실패하였습니다.";


Message.MSG_UPLOAD_SUCCESS        	="파일업로드에 성공하였습니다.";
Message.MSG_ADDED                  	="선택된 내용을 추가하였습니다.";
Message.MSG_CONTADD                	="선택된 내용을 추가하였습니다.";
Message.MSG_CFM_ONLYONE            	="다수 건의 데이터는 처리 할 수 없습니다.\n\n1건의 데이터만 선택하십시오.";
Message.MSG_DELETE_CNTSUCCESS   	="건의 정보가  삭제되었습니다.";
Message.MSG_SELECT_QUES_DIV   	="질문 분류를 선택해 주십시오.";



Message.MSG_INSERT_DATA   	="등록하시겠습니까?";
Message.MSG_UPDATE_QUESTION   	="수정하시겠습니까?";
Message.MSG_DELETE_QUESTION   	="삭제하시겠습니까?";
Message.MSG_PERMIT_QUESTION   	="승인하시겠습니까?";
Message.MSG_END_QUESTION   		="종료하시겠습니까?";
Message.MSG_RECOMMEND_QUESTION   		="추천 하시겠습니까?";
Message.MSG_DELETE_RECOMMEND_QUESTION   		="비추천 하시겠습니까??";
Message.MSG_COMM_CLOSE_QUESTION   		="폐쇄한 커뮤니티는 다시 운영할수 없습니다.\n강제폐쇄 하시겠습니까?";

//수강신청에서 사용
Message.MSG_APPLY_RE = "이미 수강신청 하셨습니다.";
Message.MSG_PAYMENT_NOT_COMPLETE = "결제가 완료되지 않았습니다.\n결제 페이지로 이동합니다.";
Message.MSG_APPLY_PERIOD = "한기수에 여러반 수강신청할수 없습니다.";
Message.MSG_SELECT_PERIOD = "기수를 선택하세요";
Message.MSG_SELECT_CLASS = "반을 선택하세요";
Message.MSG_APPLY_QUESTION = "수강신청 하시겠습니까?";
Message.MSG_APPLY_CANCEL = "수강신청 취소하시겠습니까?";
Message.MSG_APPLY_CANCEL_APPROVE = "수강취소 신청을 승인하시겠습니까?";
Message.MSG_APPLY_CANCEL = "수강취소 하시겠습니까?";
Message.MSG_VIRTUAL_BANK_APPLY_CANCEL = "무통장(가상계좌) 입금 완료된 경우\n환불은 PG사 홈페이지에서 직접하셔야 합니다.\n수강신청 취소하시겠습니까?";
Message.MSG_APPLY_SUCCESS = "결제페이지로 이동하시겠습니까?";
Message.MSG_STD_CNT = "수강 정원이 초과되어 신청할 수 없습니다. \n다시 신청해 주세요.";
Message.MSG_APPLY_DATE = "이미 신청된 일자와 겹칩니다.\n다시 신청해 주세요.";
Message.MSG_APPROVAL_PAY  = "수강 승인하시겠습니까?";
Message.MSG_ALERT_APPROVAL  = "수강승인할 목록이 없습니다.";
Message.MSG_APPROVAL_CANCEL  = "수강신청 취소 승인 하시겠습니까?";
Message.MSG_PAYMETHOD_SELECT  = "결제방법을 선택하세요.";
Message.MSG_APPLY_LOGIN  = "로그인후 수강신청 할수 있습니다.";
Message.MSG_APP_ALERT_CANCEL  = "수강 취소 가능한 목록이 없습니다.";
Message.MSG_DELETELIST_NULL  = "삭제할 목록이 없습니다.";
Message.MSG_LIST_NULL  = "목록이 없습니다.";
Message.MSG_CHECK_LEC_DATE = "수강시작일은 강의 오픈기간 사이에 있어야 합니다.\n수강시작일을 다시 선택해 주세요.";
Message.MSG_CHECK_LEC_ENDDATE = "강의 종료일은 현재 날짜 이후로 선택해야 합니다.";
Message.MSG_CHECK_LEC_STARTDATE = "수강시작일은 현재 날짜 이후로 선택해야 합니다."
Message.MSG_APPLY_FAIL_EXEL = "실패한 결과를 엑셀다운로드 합니다.";
Message.MSG_SAME_NOT_TRAINING_NO = "이미 등록한 연수지명번호로는 강의 신청을 할 수 없습니다.";
Message.MSG_CHECK_TRAINING_NO = "해당과정은 교원 직무 연수 과정입니다. 회원정보를 확인하신 후 연수신청 바랍니다.\n※ 교원이 아니신 회원께서는 \"\e-저작권 아카데미\"\,\"\e-저작권 스쿨\"\ 과정을 신청해 주시기 바랍니다.";
Message.MSG_NOT_TRAINING_NO = "연수지명번호를 입력하셔야 연수신청이 가능합니다.";

// 카테고리 관리에서 사용
Message.MSG_CHKON_LEVEL = "하위레벨이 존재합니다! 최종레벨로 바꾸면 하위레벨이 삭제됩니다. 최종레벨로 수정하시겠습니까?";
Message.MSG_CHEOFF_LEVEL = "하위레벨이 존재합니다. 최종레벨 해제하시면 하위레벨이 복구됩니다. 수정하시겠습니까?";
Message.MSG_DELL_LEVEL = "하위레벨이 존재합니다! 삭제시 하위레벨이 같이 삭제됩니다. 삭제 하시겠습니까?";
Message.MSG_LAST_LEVEL = "최종레벨로 선택된 코드입니다.\n최종레벨을 해제후 하위레벨을 등록할수 있습니다..";
Message.MSG_NULL_CODE = "선택된 코드가 없습니다.\n하위레벨을 등록할 코드를 선택하고 다시 등록하여 주십시오.";
Message.MSG_CHK_RANK = "우선순위를 입력하세요";
Message.MSG_CHK_CODE = "코드 명을  입력하세요";
Message.MSG_CHK_DESC = "코드 설명을  입력하세요";
Message.MSG_CHK_NULL = "첫 글자는 공백이 들어갈수 없습니다";
Message.MSG_ALLCOURSE_DOWN = "전체과정에는 하위레벨을 등록할수 없습니다.";
Message.MSG_CATEUSE_LECTURE = "해당 카테고리에 속하는 강의가 존재 합니다. \n삭제시 강의는 사용할수 없습니다. 삭제 하시겠습니까?";

// 메뉴관리에서 사용
Message.MSG_MENU_AREA				= "메뉴 영역을 선택하세요.";
Message.MSG_MENU_DIV 				= "메뉴 구분을 선택하세요.";
Message.MSG_MENU_NAME 				= "메뉴명을  입력하세요";
Message.MSG_MENU_DESC 				= "메뉴 설명을  입력하세요";
Message.MSG_MENU_RANK 				= "Rank를 수정할 경우, \n같은레벨의 메뉴 Rank를 모두 바꿔야합니다. \n바꾸시겠습니까?";
Message.MSG_MENU_STATUS				= "메뉴 활성화 여부를 선택하세요.";

// 쪽지관리에서 사용
Message.MSG_PAPER_TITLE 			= "제목을 입력하세요.";
Message.PAPER_SEND_SUCCESS			="쪽지발송이 성공하였습니다.";

// 권한관리에서 사용
Message.MSG_AUTH_DEPTH 				= "탑메뉴는 선택하실 수 없습니다.";
Message.MSG_AUTH_NAME 				= "권한 그룹명을 입력하세요.";
Message.MSG_AUTH_DESC 				= "권한 그룹 설명을 입력하세요.";
Message.MSG_AUTH_ONCHANGE			= "선택에 따라 맵핑이 변경됩니다.";

//팝업관리에서 사용
Message.MSG_POP_WIDTH				= "팝업창의 넓이를 입력해주세요.";
Message.MSG_POP_HEIGHT				= "팝업창의 높이를 입력해주세요.";
Message.MSG_EXT_ZIP					= "확장자가 ZIP인 파일만 가능합니다.";
Message.MSG_SELECT_TEMPLATE			= "템플릿을 선택해주세요.";
Message.MSG_INSERT_POPTEXT			= "팝업에 들어갈 내용을 입력해주세요.";
Message.MSG_INSERT_POPFILE			= "파일명을 입력해주세요.";

// 메일발송에서 사용
Message.EMAIL_SEND_SUCCESS			="메일전송이 성공하였습니다.";
Message.EMAIL_SEND_FAIL				="메일전송이 실패하였습니다";
Message.EMAIL_NOT_CORRECT			="이메일을 정확히 입력하세요.";
Message.INVALID_EMAIL_NULL 			="이메일을 입력해 주세요.";
Message.EMAIL_CHECK_MEMBER			="메일을 보낼사람을 선택하세요.";
Message.SMS_CHECK_MEMBER			="SMS를 보낼사람을 선택하세요.";
Message.SMS_SEND_SUCCESS			="SMS전송이 성공하였습니다.";
Message.NOT_USE_REFRESH			="새로고침 버튼은 사용할 수 없습니다.";

// SMS 발송에서 사용
Message.SMS_RECV_NUM_NULL 			= "수신번호가 없습니다.";
Message.SMS_SEND_NUM_NULL 			= "발신번호가 없습니다.";
Message.SMS_CONTENT_NULL 			= "내용을 입력하세요.";
Message.SMS_SEND_YN 				= "메세지를 전송하시겠습니까?";
Message.SMS_GROUP_SEND_YN 				= "그룹 메세지를 전송하시겠습니까?";
Message.SMS_SEND_DONE 				= "발송되었습니다.";
Message.SMS_CONTENT_SIZE 			= "메시지 내용은 90바이트까지만 가능합니다.";
Message.SMS_SEND_YEAR_NULL 			= "년도를 선택하세요!";
Message.SMS_SEND_MONTH_NULL 		= "월을 선택하세요!";
Message.SMS_SEND_GROUP_NULL = "SMS를 발송할 그룹을 선택해 주세요.";

// 강사 신청 승인/거부 안내메일에서 사용
Message.EMAIL_APPROVAL_Y            ="승인";
Message.EMAIL_APPROVAL_N            ="거부";
Message.EMAIL_APPROVAL_TITLE_Y      ="강사신청이 승인되었습니다.";
Message.EMAIL_APPROVAL_TITLE_N      ="강사신청이 거부되었습니다.";
Message.EMAIL_APPROVAL_CONTENTS_Y   ="요청하신 강사신청이 승인되었습니다. \n\n회원님은 본 사이트에서 \n강사로서 활동할 수 있으며, \n사이트에서 제공하는 강사모드를 \n활용하여 강의를 수행할 수 있게 되었습니다.\n\n궁굼한 사항은 메일 또는 전화로 \n문의하시기 바랍니다.\n";
Message.EMAIL_APPROVAL_CONTENTS_N   ="요청하신 강사신청이 거부 되었습니다. \n\n회원님은 본 사이트에서 \n심사결과 강사로서 활동할 수 없습니다. \n\n궁굼한 사항은 메일 또는 전화로 \n문의하시기 바랍니다.\n";
Message.MSG_INS_APPLY_CONFIRM		="강사 신청 하시겠습니까?";
/*
LCMS 강의관리에서 사용
*/
Message.MSG_CHECK_LECSTEP			="컨텐츠 순서가 중복됩니다. 확인해주세요.";

// LCMS 콘텐츠관리에서 사용
Message.MSG_PACKAGING_YN			="콘텐츠를 패키징 하시겠습니까?";
Message.MSG_NOT_PACKAGING_NOT_PREVIEW	="패키징되지 않은 콘텐츠는 미리보기를 할 수 없습니다.";

/*
LCMS 롬데이터 관리
*/
Message.MSG_LOM_EXPORT = "최초에 저장하지 않은 롬데이터는 EXPORT할 수 없습니다. \n저장 후 EXPORT 해 주십시오.";
/*
영업활동에서 사용하는 메시지
*/
Message.MSG_UNABLE_DELETE_CODE = "이미 사용된 코드는 삭제 할 수 없습니다." ;
Message.MSG_OPP_STEP1 = "추가되는 단계의 이름 혹은 설명을 입력해 주십시오." ;

/**
 * VOC 메시지
 */
Message.MSG_VOC_CUSTOMER_SELECTED = "고객구분을 선택하여 주십시오." ;
Message.MSG_VOC_INSERT_CUSTOMER_INFO = "고객정보를 입력하여 주십시오." ;
Message.MSG_VOC_GROUP_SELECTED = "VOC분류를 선택하여 주십시오." ;
Message.MSG_VOC_INSERT_PROC_DESC = "처리내용을 입력하여 주십시오.";
Message.MSG_VOC_INSERT_STOP_REASON = "취소/중지 사유를 입력하여 주십시오.";
Message.MSG_VOC_SEARCH_TARGET_SELECTED = "대상을 선택하여 주십시오." ;
Message.MSG_VOC_NOT_FINAL_DIRECT = "처리하지 않은 지시사항이 있습니다." ;
Message.MSG_VOC_RECEIPT = "접수 후 처리하여 주시기바랍니다." ;

/**
 * TASK 메시지
 */
Message.MSG_OFFLINE_TASK_ACTIVITY_NOT_FINISH = "처리중인 Task의 Activity를 완료하지 않으셨습니다.\n완료 후 종료처리를 하여 주십시오." ;
Message.MSG_OFFLINE_TASK_DIRECT_NOT_FINISH = "처리중인 Task의 지시사항을 완료하지 않으셨습니다.\n완료 후 종료처리를 하여 주십시오." ;
Message.MSG_OFFLINE_TASK_TERM_EXT_CONDITION = "기한연장은 Task처리자만 신청하실 수 있습니다.";
Message.MSG_OFFLINE_TASK_TRANS_OWNER = "이관은 Task처리자만 신청하실 수 있습니다.";
Message.MSG_OFFLINE_TASK_END_NOT_DELETE = "완료된 Task는 삭제하실 수 없습니다.";
Message.MSG_OFFLINE_TASK_ING_NOT_DELETE = "처리중 Task는 삭제하실 수 없습니다.";
Message.MSG_OFFLINE_TASK_TRANS_NOT_DELETE = "이관중 Task는 삭제하실 수 없습니다.";
Message.MSG_OFFLINE_TASK_ACTIVITY_RATE_NOT_SELECTED = "진행율을 선택 후 종료처리를 하여 주십시오.";


Message.MSG_NOTITLE						="타이틀이 설정되지 않았습니다.";
Message.MSG_SEL_BIZPART            	="사업부를 선택하여 주십시오.";
Message.MSG_SEL_PART               	="부서를 선택하십시오";
Message.MSG_SEL_USER               	="부서의 사용자를 선택하셔야 합니다.";
Message.MSG_INTEGRATE_SUCCESS="고객통합에 성공하였습니다.";
Message.MSG_INTEGRATE_FAIL         	="고객통합에 실패했습니다.";
Message.MSG_INTEGRATE_CANNOT  	="입력상태의 Import Job만 고객통합을 할 수 있습니다."
Message.MSG_NOT_SUPPORT           ="선택된 파일은 지원하지 않습니다.\n다음 형식의 파일을 선택하십시오.                               \n\n파일형식 : [###]";
Message.MSG_SVY_SELECT             	="질문복사는 한 건의 질문에 대해서만 가능합니다.";
Message.MSG_CUST_SELECT            	="고객 통합실행은 한 건의 Import작업에 대해서만 가능합니다.";
Message.MSG_NOT_EXISTDUP           ="중복된 고객이 없습니다.";

Message.VALID_DATE						="[<@ 1 >]은(는) 날짜형식(YYYYMMDD)으로 입력해야 합니다.";
Message.VALID_NUMBER				="[<@ 1 >]은(는) 숫자 형식으로 입력해야 합니다.";
Message.INVALID_DATE					="올바른 날짜값이 아닙니다.";
//Message.INVALID_NUMBER				="올바른 숫자값이 아닙니다.";
Message.INVALID_PHONENUMBER  ="문자는 허용할 수 없습니다. (단, 하이폰은 제외) 숫자만 입력하세요";
Message.INVALID_JUMIN        			="유효하지 않은 주민등록번호 입니다.";
Message.INVALID_CORPNO        		="유효하지 않은 사업자번호 입니다.";
Message.INVALID_EMAIL         			="유효하지 않은 E-Mail 주소 입니다.";
Message.NULL_SELECTED				= "선택된 목록이 없습니다.";

Message.INVALID_EMAIL3CHR     	="\n'@' 앞의 메일계정이 3자미만은 \n허용되지 않습니다.\n다시 입력해 주십시요.";
Message.INVALID_EMAIL6CHR     	="\n'@' 뒤 부분의 '.'을 포함한 글자 수가 6자미만은 \n허용되지 않습니다.\n다시 입력해 주십시요.";
Message.INVALID_HANMAIL			="한메일(hanmail.net)은 메일서비스의 유료화로정책상 메일제한이 있습니다. \n다른메일계정을 선택해 주십시오";







//---------------------------------------------------------------------------------
// - common function 에서 사용합니다. 삭제하지 마세요.
//---------------------------------------------------------------------------------
Message.MSG_NO_DATA                			="선택된 내용이 없습니다.";
Message.MSG_DEL_DATA_SELECTED               ="삭제하실 항목을 선택하여 주십시오.";
Message.MSG_DEL_NO_DATA               		="삭제하실 항목이 없습니다.";
Message.MSG_EXCEL_FILE_ONLY_UPLOAD          ="엑셀파일만 업로드 가능합니다.";
Message.MSG_NOT_DEL_ROW          ="더 이상 줄일 수 없습니다.";
/*
Message.MSG_CFM_DELBUSINESS        	="담당업무를 삭제하시겠습니까?";
Message.MSG_AUTH_IRRGR_CLOSED    ="이전 접속에서 비정상적인 종료가 되어, 이전 접속을 종료하고 다시 접속을 합니다.";
Message.MSG_AUTH_NOUSER            	="사용자 정보가 정확하지 않습니다.";
Message.MSG_AUTH_USED              		="다른 컴퓨터에서 동일한 사용자로 이미 사용중입니다.";
Message.MSG_BTNSCRT_INS            		="버튼 스크립트를 삽입해 주십시오.";
Message.MSG_DISCONN                			="서버로부터 연결이 끊어져 알림메세지 서비스가\n중단되었습니다. 재로그인을 하시면 알림메세지\n서비스를 다시 받으실 수 있습니다.";
Message.MSG_DUP_ID                 			="MSG_DUP_ID";
Message.MSG_DUPLICATED             		="[<@ 1 >]은(는) 중복된 항목입니다.";
Message.MSG_FAIL_DATA              			="<@ 1 > 건를 실패하였습니다. 다시 <@ 2 > 버튼을 클릭하시기 바랍니다.";
Message.MSG_INFO_COPY              		="복사했습니다.";
Message.MSG_INP_ESSENTIALFIELD     	="[<@ 1 >](을)를 입력해 주십시오.";
Message.MSG_INP_WAITPROCESS        	="현재 페이지는 요청한 작업을 처리중입니다.\n작업이 완료된 후 사용하여 주시기 바랍니다.";
Message.MSG_INS_EXIST              			= "이미 저장된 정보입니다.";
Message.MSG_INS_SUCCESS            		="정보가 저장되었습니다.";
Message.MSG_LOGOUT_IP              		="다른 장소[<@ 1 >]에서 동일한 사용자로 로그인하여 접속을 종료합니다.";
Message.MSG_NOADDED                		="추가된 내용이 없습니다.";
Message.MSG_NODUALCHK              		="중복체크를 하지 않았습니다.";
Message.MSG_NOFILE                 			="해당 파일이 서버에 없습니다.";
Message.MSG_NOFOUND                		="[<@ 1 >]을(를) 찾을 수 없습니다.";
Message.MSG_NONAVIGATION           		="네이게이션이 설정되지 않았습니다.";
Message.MSG_NONE_DATA              		="<@ 1 > 건이 존재하지 않습니다.";
Message.MSG_NORESULT               		="결과가 없습니다.";
Message.MSG_NOSEARCH               		="검색된 내용이 없습니다.";
Message.MSG_NOSEARCH_SEL           	="관련정보 내용을 조회할 수 없습니다.<@ 1 > 정보가 선택되어야 합니다.";
Message.MSG_NOSELEDTED             		="선택된 내용이 없습니다.";
Message.MSG_NOSORTING              		="정렬기준을 설정 할 수 없는 열입니다.";
Message.MSG_UPD_CNTSUCCESS         ="<@ 1 >건의 정보가 수정되었습니다.";
Message.MSG_UPL_FAIL               			="세션이 종료되어 업로드를 할 수 없습니다.";
Message.MSG_COPY_SUCCESS           	="성공적으로 복사되었습니다.";
*/



/*
Message.INP_VALIDIDHEAD				="[주민등록번호 앞부분]이 잘못되었습니다.";
Message.INP_VALIDIDNUM         		="유효하지 않은 [주민등록번호]입니다.";
Message.INP_VALIDIDTAIL        		="[주민등록번호 뒷부분]이 잘못되었습니다.";
Message.INP_VALIDNUM0          		="최초숫자는 '0'이 될수 없습니다.";
Message.INP_VALIDNUMCHR       	="문자는 허용할 수 없습니다.숫자만 입력하세요";
Message.INP_VALIDNUMCHR2     	="문자는 허용할 수 없습니다. (단, 하이폰은 제외) 숫자만 입력하세요";
Message.INP_VALIDTIME          		="다음의 형식으로 입력하셔야 합니다.\nex)2004-01-01 01";
Message.2BYTE                  				="[<@ 1 >] 은(는) 2자 이상 입력하셔야 합니다.";
Message.INP_OVER               			="[<@ 1 >](은)는 [<@ 2 >]자리만을 허용합니다.";
Message.INP_REINPUT            		="\n다시 입력해 주십시요.";
Message.INP_VALIDEMAIL         		="유효하지 않은 E-Mail 주소 입니다.";
Message.INP_VALIDEMAIL3CHR     	="\n'@' 앞의 메일계정이 3자미만은 \n허용이 안됩니다.";
Message.INP_VALIDEMAIL6CHR     	="\n'@' 뒤 부분의 '.'을 포함한 글자 수가 6자미만은 \n허용이 안됩니다.";
Message.INP_VALIDEMAILCHR      	="E-Mail 에 허용할 수 없는 문자가 입력되었습니다.";
Message.INP_VALIDEMAILDOT      	="\n'@' 뒤 부분의 '.' 구분자가 없습니다.";
*/


//=================================================================================

/*	IMK - VOC SOP에서 사용	*/
Message.MSG_ONE_INSERT      	="신규등록/수정은 하나씩 해야 합니다.";
/*	IMK - TEMPLATE에서 사용	*/
Message.MSG_NO_CONTENT      	="미리보기 내용이 없습니다.";
/*	IMK - 개인일정에서 사용	*/
Message.MSG_INP_TIMESE			="시간을 잘못 지정하였습니다.";
/*	IMK - 업무지시에서 사용	*/
Message.MSG_ALARM_NO_DATA      = "업무지시 알림방법은 하나이상 체크되어야 합니다.";
Message.MSG_BIZ_TARGET_NO_DATA = "지시대상자를 선택해 주세요.";

Message.NO_SELECTED = "항목을 선택하여 주십시오.";
Message.MAIL_TMPL_NO_SELECTED = "메일 템플릿을 선택하여 주십시오.";
Message.MAIL_TMPL_MULT_SELECTED = "메일 템플릿은 한건만 선택하여 주십시오.";
Message.SMS_TMPL_NO_SELECTED = "SMS 템플릿을 선택하여 주십시오.";
Message.SMS_TMPL_MULT_SELECTED = "SMS 템플릿은 한건만 선택하여 주십시오.";
Message.MSG_NOT_TRANS = "본인에게는 이관을 하실수가 없습니다.";
Message.MSG_TRANS_CNT_LIMIT = "이관을 3회 이상 하였습니다.";

Message.MSG_CHECK_PNT = "태그를 삽입할 위치를 선택하십시오.";

Message.MSG_NO_CUST = "고객사를 선택 하십시오.";

//=================================================================================
Message.MSG_NO_EMP_ANA = "사원을 선택 하십시오";
Message.MSG_ANALYSIS_05_MAX_ERROR ="2자리를 넘을수 없습니다.";
Message.MSG_ANALYSIS_05_DURATION_ERROR ="기간을 잘못 입력하셨습니다";

/*	첨부파일 에서 사용	*/
Message.MSG_NOT_SELECT_ATTACH ="첨부파일을 선택해 주세요.";
Message.MSG_FILENAME_LIMIT ="파일명은 100자 이하만 가능합니다.";
Message.MSG_FAIL_FILE_UP ="파일업로드가 실패하였습니다.";
Message.MSG_SPC_FILE	= "확장자가 asf와 wmv인 파일만 가능합니다.";
Message.MSG_INTRO_FILE	= "동영상 형식의 파일만 가능합니다.";

/*	SOP 에서 사용	*/
Message.MSG_NOT_SELECT_NODE ="등록할 노드를 선택하십시오.";
Message.MSG_MODE_END ="마지막 노드입니다.";
Message.MSG_NOT_SELECT_UPDATE_NODE ="수정할 노드를 선택하십시오.";
Message.MSG_NOT_SELECT_DELETE_NODE ="삭제할 노드를 선택하십시오.";

/*	휴일관리 에서 사용	*/
Message.MSG_NOT_SELECT_MM ="저장할 월을 선택하십시요.";

/* 강의 개설 */
Message.MSG_EVAL_TOTAL = "총 배점의 합이 '100' 이 아닙니다.";
Message.MSG_STD_LEC = "수강신청한 학생이 존재하므로 수정할수 없습니다.";
Message.MSG_LEC_SCH_CHECK = "강의 계획서를 작성하여야 사용할 수 있습니다.";

/* 시험, 시험문제 관리 */
Message.MSG_EXAM_QUESTION_SELECT = "선택된 문제가 없습니다";
Message.MSG_EXAM_QUESTION_LIMIT = "선택된 문제를 추가할 수 없습니다\n\n추가할 수 있는 문제는<@ 1 >문제 입니다.";
Message.MSG_EXAM_QUESTION_MAX = "문제를 더 이상 추가할 수 없습니다.";
Message.MSG_EXAM_RIGHT_CLICK = "시험중엔 오른쪽 버튼을 이용할 수 없습니다.";
Message.MSG_EXAM_KEY_PRESS = "시험중엔 키보드를 사용할 수 없습니다.";
Message.MSG_EXAM_RANGE = "시험 끝 범위가 시작 범위보다 작습니다."
Message.MSG_EXAM_STEP_RANGE = "시험 차시가 시험범위 이전일 수 없습니다."
Message.MSG_EXAM_DATE_RANGE = "시험 종료일시가 시험 시작일시 이전입니다."
Message.MSG_EXAM_TIME_REQUIRED = "시험 시간은 0보다 커야합니다."
Message.MSG_EXAM_SCORE_REQUIRED = "시험 점수는 0보다 커야합니다."
Message.MSG_EXAM_Q_CNT_REQUIRED = "시험 문항수는 1 이상이어야 합니다."
Message.MSG_EXAM_START_DATE = "시험 시작일시가 현재시간보다 이전 입니다."
Message.MSG_TASK_DATE_RANGE = "제출 종료일시가 제출 시작일시 이전입니다."
Message.MSG_OFF_NONSCORM = "오프라인강의는 비스콤강의만 지원합니다."
Message.MSG_OFF_ALWAYSN = "오프라인강의는 비상시강의만 지원합니다."
Message.MSG_INSERT_TASK_CONNECT = "첨삭지도를 입력하시거나 첨삭파일을 등록하세요 ."	
Message.MSG_INSERT_FILE = "첨부파일을 등록 하시겠습니까?"
Message.MSG_DELETE_FILE = "첨부파일을 삭제 하시겠습니까?"
Message.MSG_INVALID_FILE = "첨부할 파일이 존재하지 않습니다."
Message.MSG_EXSIT_FILE = "기존 첨부파일을 삭제한 후 등록해주세요."
Message.MSG_NOT_DELETE_QUESTION = "시험 중이거나 마감된 시험의 문제는 삭제할 수 없습니다."
Message.MSG_ALL_APPLY_QUESTION = "문제가 전부 출제되었습니다."
Message.MSG_NOT_SAME_EXAM_HOUR = "시험시간 설정을 다시 해 주십시오."
/* 시험 응시 */
Message.MSG_EXAM_APPLY_CONFIRM = "시험에 응시 하시겠습니까?";
Message.MSG_EXAM_APPLY_TIME_FINISHED = "시험 응시 시간이 종료되었습니다.";
Message.MSG_EXAM_APPLY_TIME_OVER = "시험 시간이 종료되었습니다.\n\n[확인]을 누르시면 시험이 종료됩니다.";
Message.MSG_EXAM_APPLY_CLOSE = "시험창을 닫으시겠습니까?\n\n시험창을 닫아도 남은 시험시간은 지나가고 있으며 \n\n남은 시험시간이 지나면 시험을 보실 수 없습니다.\n\n남은 시험종료시간 이내에 다시 응시할 수 있습니다.";
Message.MSG_EXAM_APPLY_SAVE = "작성된 답안이 저장되었습니다.";
Message.MSG_EXAM_APPLY_FINISH = "시험을 종료하시겠습니까?\n\n종료하시면 더 이상 시험에 응시할 수 없습니다.";
Message.MSG_EXAM_APPLIED = "이미 시험에 응시하셨습니다.";
Message.MSG_INIT_EXAM_APPLY = "시험을 초기화 하시겠습니까?";
Message.MSG_INIT_REPORT_APPLY = "과제점수를 초기화 하시겠습니까?";

/* 자가진단 */
Message.MSG_TEST_APPLY_CONFIRM = "자가진단에 응시 하시겠습니까?";
Message.MSG_TEST_APPLY_TIME_FINISHED = "자가진단 응시 시간이 종료되었습니다.";
Message.MSG_TEST_APPLY_TIME_OVER = "자가진단 시간이 종료되었습니다.\n\n[확인]을 누르시면 자가진단이 종료됩니다.";
Message.MSG_TEST_APPLY_CLOSE = "자가진단창을 닫으시겠습니까?\n\n자가진단창을 닫아도 남은 시험시간은 지나가고 있으며 \n\n남은 시험시간이 지나면 시험을 보실 수 없습니다.\n\n남은 시험종료시간 이내에 다시 응시할 수 있습니다.";
Message.MSG_TEST_APPLY_SAVE = "작성된 답안이 저장되었습니다.";
Message.MSG_TEST_APPLY_FINISH = "자가진단을 종료하시겠습니까?\n\n종료하시면 더 이상 자가진단에 응시할 수 없습니다.";
Message.MSG_TEST_APPLIED = "이미 자가진단에 응시하셨습니다.";

/* 컨텐츠 업로드 */
Message.MSG_INDEX_FILE_NOTNULL = "인덱스 파일명을 입력해 주세요.";
Message.MSG_CONTENT_UPDATE_SUCCESS = "Index File 경로가 수정되었습니다.";
Message.MSG_CONTENT_UPDATE_FAIL = "존재하지 않는 파일입니다.\n\n파일 경로를 확인해 주십시요.";

/* 강의 유형및 강의오픈일자 설정 */
Message.MSG_NOT_EXIST_LEC = "강의 유형을 체크하여 주십시오.";
Message.MSG_NOT_EXIST_LEC_DATE = "강의 오픈기간을 설정하여 주십시오. ";

/* 구성요소관리 */
Message.MSG_SHARE_YN = "공유여부를 수정하시겠습니까?";
Message.MSG_RES_SELECT_CATE = "Asset을 등록할 카테고리를 선택하세요.";
Message.MSG_RES_NOT_FILE = "지원되지 않는 형식의 확장자 입니다.";
Message.MSG_PREVIEW_NOT_FILE = "미리보기 대상파일이 없습니다.";
Message.MSG_INSERT_ITEM = "선택한 구성요소를 삽입하였습니다.";
Message.MSG_NOT_ASSET_PREVIEW = "해당 Asset은 미리보기를 할 수 없습니다.";
Message.MSG_NOT_ASSET_FILE_CHANGE = "해당 Asset은 파일 변경을 할 수 없습니다.";

/* 에디터 */
Message.MSG_INSERT_CHECK_FLASH = "플래시 삽입은 swf 파일만 가능합니다. 다시 선택하여 주십시요."
Message.MSG_INSERT_CHECK_IMAGE = "그림 삽입은 GIF, JPG, PNG 파일만 가능합니다. 다시 선택하여 주십시요."
Message.MSG_INSERT_CHECK_MOVIE = "동영상 삽입은 ASF, WMV 파일만 가능합니다. 다시 선택하여 주십시요."
Message.MSG_INSERT_SUCCESS = "성공적으로 저장되었습니다!"
Message.MSG_CANNOT_DEL_ONEPAGE = "한페이지만 있을 경우 삭제하실 수 없습니다."
Message.MSG_ADD_CMI = "CMI Data 추가"
Message.MSG_CONFIRM_PAGE_DEL = "현재 선택된 페이지가 삭제됩니다. \n삭제하시겠습니까? "
Message.MSG_MODIFY_INFO = "현재 페이지를 저장한후 교시 정보를 수정하시려면 취소 선택후 페이지를 먼저 저장합니다."
Message.MSG_SAVE_CONTENTS = "현재 작성중인 콘텐츠를 모두 저장합니다."
Message.MSG_SELECT_FILE = "리소스 리스트에서 파일을 선택하세요."

/* 강의관리 */
Message.MSG_lEC_ALWAYS_CHECK ="강의유형을 변경하실경우 \n반드시 강의 기본정보의 평가방법과 평가기준을 \n변경하여 주시기 바랍니다."
Message.MSG_lEC_REBATEYN ="수강료가 0원인 강의는 환급받을수 없습니다."

/* 템플릿 적용 */
Message.MSG_TEMPLATE_NOCHANGE="선택하신 템플릿은 이전 템플릿과 같습니다."
Message.MSG_TEMPLATE_LEC_CHANGE=" 강의에 템플릿을 적용하였습니다. "
Message.MSG_TEMPLATE_CON_CHANGE=" 컨텐츠에 템플릿을 적용하였습니다. "

/* 커뮤니티 */
Message.MSG_COMM_BOARD_DELETE			= "게시판 삭제 시 작성된 모든 글이 삭제됩니다.\n\n삭제 하시겠습니까?"
Message.MSG_COMM_BOARD_ACCESS			= "해당 게시판 접근 권한이 없습니다."
Message.MSG_IMG_FILE_ONLY_UPLOAD        = "이미지 형식 파일만 업로드 가능합니다.";
Message.MSG_IMG_TYPE					= "jpg, gif, jpeg 형식의 파일만 업로드 할수 있습니다.";
Message.MSG_COMM_NICKNAME_LENGTH 		= "닉네임은 7자까지 입력 가능합니다.";
Message.MSG_MEM_DELETE 					= "선택한 회원을 강제 탈퇴 하시겠습니까?";
Message.MSG_COMM_LINK_DELETE 			= "링크 정보를 삭제하시겠습니까?";
Message.MSG_COMM_NICKNAME_LENGTH 		= "닉네임은 7자까지 입력 가능합니다.";
Message.MSG_MEM_APPROVE 				= "가입 승인 하시겠습니까?";
Message.MSG_MEM_NOT_APPROVE 			= "가입 거절 하시겠습니까?";
Message.MSG_MEM_MGR_REPLACE 			= "관리자 위임 하시겠습니까?";
Message.MSG_MEM_MGR_APPOINT 			= "운영진으로 임명하시겠습니까?";
Message.MSG_MEM_COMM_APPLY				= "커뮤니티에 가입하시겠습니까?";
Message.MSG_MEM_NOT_MGR_REPLACE			= "정회원으로 변환하시겠습니까?"
Message.MSG_MEM_MGR_REPLAC_ONE			= "한 명씩만 선택할 수 있습니다."
Message.MSG_COMM_BOARD_REQUIRED			= "필수 커뮤니티 메뉴이므로 삭제할 수 없습니다.";

/* 설문관리 */
Message.MSG_SEARCH_DATE 			= "기간검색은 FROM-TO 모두 입력하셔야 합니다.";
Message.MSG_INSERT_SURVEY_TITLE		= "설문 제목을 입력하셔야 합니다.";
Message.MSG_INSERT_SURVEY_TYPE		= "설문 유형을 선택하셔야 합니다.";
Message.MSG_INSERT_SURVEY_LECTURE		= "강의를 선택하셔야 합니다.";
Message.MSG_INSERT_DATE			= "기간을 입력하셔야 합니다.";
Message.MSG_INSERT_QUESTION 			= "질문 내용을 입력하셔야 합니다.";
Message.MSG_INSERT_QUESTION_DTL 		= "예문을 1개 이상 등록하셔야 합니다.";
Message.MSG_INSERT_QUESTION_DTL_CONTENT 	= "예문 내용을 모두 입력하셔야 합니다.";
Message.MSG_MULTI_CHECK_CORRECT 		= "1개 이상 선택하셔야 합니다.";
Message.MSG_UNIQUE_CHECK_CORRECT 		= "선택하셔야 합니다.";
Message.MSG_SHORT_ANSWER_CORRECT 		= "응답을 입력하셔야 합니다.";
Message.MSG_CLOSED_YN_CLOSED 			= "설문을 종료 하시겠습니까?";
Message.MSG_CLOSED_YN_OPEN 			= "설문을 시작 하시겠습니까?";
Message.MSG_SURVEY_APPLY 			= "로그인 후 참여 할 수 있습니다.";
Message.MSG_DIFFERENT_START_END_DATE = "설문 시작일과 종료일은 달라야 합니다.";
/*********************************************************************************
 * 강의 관리 - 2009.04.27 안병진
 *********************************************************************************/
Message.LEC_INFO_UPDATE = "이 강의는 LMS에 매핑된 콘텐츠 정보를 포함하고 있습니다.\n\n강의 구성 정보를 업데이트 할 경우 LMS의 매핑 정보를 잃게 됩니다.\n\n강의 구성정보를 변경하시겠습니까?"
Message.CONTENT_TYPE_SELECT = "콘텐츠 유형을 선택해 주세요."

/*********************************************************************************
 * 학습 - 2009.05.20 안병진
 *********************************************************************************/
Message.LESSON_EJECTED = "다른 컴퓨터에서 학습이 시작되어 현재 학습창을 닫습니다."

/*********************************************************************************
 * MAIL SMS 수신 거부시  - 2009.11.30 김상범
 *********************************************************************************/
Message.MSG_EMAIL_RECEIVE_N ="E-MAIL 수신거부시 \n다문화전문인력 온라인교육에서 보내는 각종 정보를 받으실 수 없습니다.";
Message.MSG_SMS_RECEIVE_N ="SMS 수신거부시 \n다문화전문인력 온라인교육에서 보내는 각종 정보를 받으실 수 없습니다.";

/*********************************************************************************
 * Off-Line 콘텐츠 등록 접근 불가
 *********************************************************************************/
Message.MSG_OFF_LINE_CONTENT ="Off-Line 강의는 콘텐츠를 등록할 수 없습니다.";

/*********************************************************************************
 * 로그인 시 접근 불가
 *********************************************************************************/
Message.MSG_NOT_LOGIN_MENU ="로그인 시 접근 할 수 없는 메뉴입니다.";
Message.MSG_LOGIN_MENU ="로그인 후 이용해주세요.";

/* 시퀀싱 */
Message.MSG_SELECT_CONDITION			= "조건을 선택하세요.";
Message.MSG_ONLY_ONE_PRIMARY_OBJECTIVE	= "Primary Objective는 Objectirve ID를 하나만 추가 할 수 있습니다.";
Message.MSG_EQUAL_OR_OVER_ONE_PRIMARY_OBJECTIVE	= "Primary Objective는 1개 이상 존재해야 합니다.";
Message.MSG_OBJECTIVE_ID_NOT_DUPLICATE	= "Objective ID는 중복 추가 할 수 없습니다.";
Message.MSG_INSERT_OBJECTIVE_ID			= "Objective ID를 정하세요.";
Message.MSG_INSERT_TARGET_OBJECTIVE_ID	= "Target Objective ID를 정하세요.";
Message.MSG_CHECK_MAP_INFO_VARIABLE		= "Map Objective Info. to Gloval Variable을 체크 하세요.";
Message.MSG_LEARNING_ONE_CHECK			="학습유형은 한번만 선택할 수 있습니다. ";
Message.MSG_LEARNING_CHECK				="학습유형을 먼저 선택하여 주세요.";
Message.MSG_LEARNING_MODEL_EXIST_YN		="목록에 학습모델이 존재합니다.\n학습모델이 없어야 학습유형이 제거 됩니다.";
Message.MSG_COURSE_TEMPLATE_TITLE_VALUE	="템플릿명을 입력하세요.";
Message.MSG_MAPPING_COURSE_TEMPLATE		="매핑을 실행하면 코스템플릿을 선택한 콘텐츠의 시퀀싱, 메타정보 및 SCO 내용이 초기화됩니다.\n코스템플릿 매핑을 실행 하시겠습니까?";

/* SMS 관련 */
Message.MSG_SMS_LIMIT_BYTE = "최대 90 Bytes 까지만 보낼 수 있습니다.";
Message.SMS_CHECK_MEMBER = "SMS를 보낼사람을 선택하세요.";
/* 분반 강사권한 관련 */
Message.LEC_AUTH_TUTOR_ALREADY = "해당기수에 이미 권한이 있는 강사입니다.";
Message.SAME_CLASS = "동일한 분반입니다.";
Message.SELECTED_CLASS = "분반을 선택 해 주세요.";
Message.MAX_APPLY_COUNT = "반에 인원이 초과되었습니다.";
Message.PERIOD_EXIST_STUDENT = "현재 기수에 수강생이 존재해 수정할 수 없습니다.";
Message.NOT_DELETE_EXIST_STUDENT = "현재 기수에 수강생이 존재해 삭제할 수 없습니다.";
Message.NOT_DELETE_ALWAYS_Y = "상시 강의는 기수를 삭제 할 수 없습니다.";
Message.NOT_DELETE_FIRST_PERIOD = "첫번째 기수는 삭제 할 수 없습니다.";
Message.INS_DO_SAVE = "운영튜터가 등록되었습니다.";
Message.AST_DO_SAVE = "내용튜터가 등록되었습니다.";
/* EVENT 관련 */
Message.NO_EVENT_APPLY_SELECT = "선택된 이벤트 응시자가 없습니다.";
Message.EVENT_MAX_ENTRY = "당첨 인원을 초과하였습니다.";
Message.APPLY_EVENT_ENTRY = "이벤트 당첨 등록 하시겠습니까?";
/* 튜터 약관 동의 */
Message.NO_TUTOR_CLAUSE_AGREE = "약관 동의에 체크해 주시기 바랍니다.";
/* 도서관련  */
Message.PROD_SAVE = "도서를 등록하시겠습니까?";
Message.PROD_UPDATE = "도서정보를 수정하시겠습니까?";
Message.PROD_DELETE = "도서를 삭제하시겠습니까?";
Message.PRDO_SAVE_SUCCESS = "도서가 등록되었습니다.";
Message.PRDO_UPDATE_SUCCESS = "도서정보가 수정되었습니다.";
Message.PRDO_DELETE_SUCCESS = "도서가 삭제되었습니다.";
Message.PROD_ORDER_SAVE = "도서를 과정에 등록하시겠습니까?";
Message.PROD_ORDER_UPDATE = "도서과정정보를 수정하시겠습니까?";
Message.PROD_ORDER_DELETE = "도서과정정보를 삭제하시겠습니까?";
Message.PROD_ORDER_SAVE_SUCCESS = "도서과정정보가 등록되었습니다.";
Message.PROD_ORDER_UPDATE_SUCCESS = "도서과정정보가 수정되었습니다.";
Message.PROD_ORDER_DELETE_SUCCESS = "도서과정정보가 삭제되었습니다.";

Message.PROD_BASKET_SAVE_SUCCESS = "해당 도서가 장바구니에 등록되었습니다.\n장바구니로 이동하시겠습니까?";
Message.PROD_BASKET_DELETE_SUCCESS = "장바구니가 삭제되었습니다.";
/*메인 수강후기 등록 관련*/
Message.MAIN_VIEW_Y = "수강후기를 메인에 등록 하시겠습니까?";
Message.MAIN_VIEW_N = "메인에 등록된 수강후기를 등록 취소 하시겠습니까?";

Message.QFE_RECEPTION_CANCEL = "접수 취소하시겠습니까?";
Message.QFE_CERTI_RECEPT_COMPLETE = "접수 완료하시겠습니까?";

/*********************************************************************************
 * 구인구직
 *********************************************************************************/
Message.JOB_PERMIT_QUESTION = "승인 처리 하시겠습니까?";
Message.JOB_PERMIT_QUESTION_CANCEL = "미승인 처리 하시겠습니까?";
Message.JOB_PERMIT_SUCCESS = "처리 되었습니다.";
Message.JOB_PERMIT_FAIL = "처리에 실패 했습니다.";

Message.MSG_JOB_COM_APP_INSERT_SUCCESS ="구인기업에 신청 되었습니다.\n관리자 승인 후 채용정보를 등록하실수 있습니다.";
Message.MSG_JOB_COM_APP_UPDATE_SUCCESS ="기업정보가 수정되었습니다.";

Message.MSG_JOB_COM_APP_INSERT_QUESTION ="구인기업에 신청 하시겠습니까?";
Message.MSG_JOB_COM_APP_UPDATE_QUESTION ="기업정보를 수정 하시겠습니까?";

Message.MSG_JOB_COM_APP_INSERT_FAIL = "구인기업 신청이 실패 되었습니다.";
Message.MSG_JOB_COM_APP_UPDATE_FAIL = "기업정보 수정이 실패 되었습니다.";

Message.MSG_JOB_RECRUIT_INSERT_SUCCESS ="채용정보가 등록 되었습니다.";
Message.MSG_JOB_RECRUIT_UPDATE_SUCCESS ="채용정보가 수정 되었습니다.";
Message.MSG_JOB_RECRUIT_DELETE_SUCCESS ="채용정보가 삭제 되었습니다.";

Message.MSG_JOB_RECRUIT_INSERT_QUESTION ="채용정보를 등록 하시겠습니까?";
Message.MSG_JOB_RECRUIT_UPDATE_QUESTION ="채용정보를 수정 하시겠습니까?";
Message.MSG_JOB_RECRUIT_DELETE_QUESTION="채용정보를 삭제 하시겠습니까?";

Message.MSG_JOB_RECRUIT_INSERT_FAIL = "채용정보 등록에 실패 했습니다.";
Message.MSG_JOB_RECRUIT_UPDATE_FAIL = "채용정보 수정에 실패 햇습니다.";
Message.MSG_JOB_RECRUIT_DELETE_FAIL="채용정보를 삭제에 실패 했습니다.";

Message.MSG_JOB_RECRUIT_CLOSE_QUESTION ="강제마감 하시겠습니까?";
Message.MSG_JOB_RECRUIT_CLOSE_SUCCESS ="강제마감 되었습니다.";
Message.MSG_JOB_RECRUIT_CLOSE_FAIL = "강제마감에 실패 했습니다.";

Message.MSG_JOB_PRESIDENT_UPDATE_QUESTION ="대표 이력서로 등록 하시겠습니까?";
Message.MSG_JOB_PRESIDENT_UPDATE_SUCCESS ="등록 되었습니다.";
Message.MSG_JOB_PRESIDENT_UPDATE_FAIL = "등록에 실패 했습니다.";

Message.MSG_JOB_RESUME_INSERT1_QUESTION ="기본인적사항을 등록 하시겠습니까?";
Message.MSG_JOB_RESUME_UPDATE1_QUESTION ="기본인적사항을 수정 하시겠습니까?";
Message.MSG_JOB_RESUME_DELETE_QUESTION ="이력서를 삭제 하시겠습니까?";

Message.MSG_JOB_RESUME_INSERT1_SUCCESS = "기본인적사항 등록에 성공 했습니다.";
Message.MSG_JOB_RESUME_UPDATE1_SUCCESS = "기본인적사항 수정에 성공 했습니다.";
Message.MSG_JOB_RESUME_DELETE_SUCCESS = "이력서 삭제에 성공 했습니다.";

Message.MSG_JOB_RESUME_INSERT1_FAIL = "기본인적사항 등록에 실패 했습니다.";
Message.MSG_JOB_RESUME_UPDATE1_FAIL = "기본인적사항 수정에 실패 햇습니다.";
Message.MSG_JOB_RESUME_DELETE_FAIL = "이력서 삭제에 실패 햇습니다.";

Message.MSG_JOB_RESUME_INSERT2_QUESTION ="지원 및 경력사항을 등록 하시겠습니까?";
Message.MSG_JOB_RESUME_UPDATE2_QUESTION ="지원 및 경력사항을 수정 하시겠습니까?";

Message.MSG_JOB_RESUME_INSERT2_SUCCESS = "지원 및 경력사항 등록에 성공 했습니다.";
Message.MSG_JOB_RESUME_UPDATE2_SUCCESS = "지원 및 경력사항 수정에 성공 했습니다.";

Message.MSG_JOB_RESUME_INSERT2_FAIL = "지원 및 경력사항 등록에 실패 했습니다.";
Message.MSG_JOB_RESUME_UPDATE2_FAIL = "지원 및 경력사항 수정에 실패 햇습니다.";

Message.MSG_JOB_RESUME_INSERT3_QUESTION ="자기소개서를 등록 하시겠습니까?";
Message.MSG_JOB_RESUME_INSERT3_SUCCESS = "이력서 등록이 완료 되었습니다.";
Message.MSG_JOB_RESUME_INSERT3_FAIL = "자기소개서 등록에 실패 했습니다.";


Message.MSG_JOB_RESUME_CONTENT_SIZE 	= "내용은 최대 1000바이트까지만 가능합니다.";

Message.MSG_JOB_APPLY_QUESTION="입사지원을 하시겠습니까?";
Message.MSG_JOB_APPLY_SUCCESS="입사지원 신청에 성공 했습니다.";
Message.MSG_JOB_APPLY_FAIL="입사지원 신청에 실패 했습니다.";

Message.MSG_JOB_APPLY_CANCEL_QUESTION="입사지원을 취소 하시겠습니까?";
Message.MSG_JOB_APPLY_CANCEL_SUCCESS="입사지원 취소에 성공 했습니다.";
Message.MSG_JOB_APPLY_CANCEL_FAIL="입사지원 취소에 실패 했습니다.";

Message.MSG_JOB_MEMO_INSERT_QUESTION="메모를 등록 하시겠습니까?";
Message.MSG_JOB_MEMO_INSERT_SUCCESS="메모 등록에 성공 했습니다.";
Message.MSG_JOB_MEMO_INSERT_FAIL="메모 등록에 실패 했습니다.";

Message.MSG_JOB_MEMO_INSERT_QUESTION="메모를 등록 하시겠습니까?";
Message.MSG_JOB_MEMO_INSERT_SUCCESS="메모 등록에 성공 했습니다.";
Message.MSG_JOB_MEMO_INSERT_FAIL="메모 등록에 실패 했습니다.";

Message.MSG_JOB_PROCESS_QUESTION="상태를 변경 하시겠습니까?";
Message.MSG_JOB_PROCESS_SUCCESS="처리 되었습니다.";
Message.MSG_JOB_PROCESS_FAIL="처리에 실패 했습니다.";

Message.MSG_JOB_OFFER_QUESTION="입사지원 요청을 하시겠습니까?";
Message.MSG_JOB_OFFER_SUCCESS="입사지원 요청에 성공 했습니다.";
Message.MSG_JOB_OFFERY_FAIL="입사지원 요청에 실패 했습니다.";


Message.MSG_JOB_OFFER_USER_QUESTION="입사지원 요청에 승낙 하시겠습니까?";
Message.MSG_JOB_OFFER_USER_NO_QUESTION="입사지원 요청에 거절 하시겠습니까?";
Message.MSG_JOB_OFFER_USER_SUCCESS="처리 되었습니다.";
Message.MSG_JOB_OFFERY_USER_FAIL="처리에 실패했습니다.";

Message.MSG_JOB_RESUME_SCRAP_QUESTION="이력서를 스크랩 하시겠습니까?";
Message.MSG_JOB_RESUME_SCRAP_SUCCESS="처리 되었습니다.";
Message.MSG_JOB_RESUME_SCRAP_FAIL="처리에 실패했습니다.";

Message.MSG_JOB_RECRUIT_SCRAP_QUESTION="채용공고를 스크랩 하시겠습니까?";
Message.MSG_JOB_RECRUIT_SCRAP_SUCCESS="처리 되었습니다.";
Message.MSG_JOB_RECRUIT_SCRAP_FAIL="처리에 실패했습니다.";





