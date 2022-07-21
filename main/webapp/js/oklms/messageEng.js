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
Message.INVALID_ID  			="ID must consist of more than 4 alphabets";  
Message.INVALID_PW				="Password must consist of more than 4 letters. ";
Message.CONFIRM_LOGOUT			="Do you want to log out?";
Message.RECOMMENDED				="This article has been already recommended.";
Message.SESSION_OUT				="The session has been disconnected, please log in again.";
Message.NOTINSERT_CONTENT		="Put in the contents.";
Message.NOTALLINSERT_CONTENT	="put in the entire contents.";
Message.NO_SEARCH_WORD			="put in search word.";
Message.NO_URL					="No URL information for the page concerned.";
Message.DO_SAVE					="Do you want to save it?";
Message.NO_LIST					="There is no list to save.";
Message.NO_DOWN_LIST			="There is no list to download.";
Message.EXIST_EMPTY				="There is an empty value.";
Message.OVERTHEHEAD				="Put in the number down to the 1st place of decimal.";
Message.NO_NUMERIC				="This is not numeric.";
Message.CONFIRM_MEMDELETE		="Do you want to withdraw from the community?";
Message.INVALID_PASS_VALUE		="Invalid password, please check it again.";
Message.CHECK_ID				="Check the ID validity.";
Message.INSERT_ID_LENGTH		="ID can take 4 letters to 10 letters only.";
Message.INSERT_ID_CHAR			="ID can take in English and numeric letters only.";
Message.INSERT_ID				="Put in your ID please.";
Message.INSERT_JUMIN			="Put in your residents number, please.";
Message.NOT_EXIST_ID			="There is no such ID.\n join the membership and log in please ";
Message.NOT_AGREE				="Read the user agreement for the membership and click the agreement.";
Message.MEM_JOIN_SUCCESS		="Your application for the membership has been successfully completed.";
Message.MEM_UPDATE_SUCCESS		="The member information has been successfully amended ";
Message.ALERT_MEM_DELETE		="Please briefly write why you want to quit.";
Message.MEM_DELETE_SUCCESSE		="Your application to quit has been successfully completed.";
Message.NOT_EXIST_PW			="The password is not the same.";
Message.NOT_EXIST_LEC			="First, choose the course.";
Message.NOT_EXIST_LECSCH		="First, make the lecture plan.";
Message.NOT_EXIST_PREVIEW		="There is no preview for the lecture.";
Message.NOT_FOREIGN_FIND        ="This is allowed for the natives only. Foreigner must choose the cancel and ask the manager. .";
Message.REALNAME_CONFIRM        ="Your real name has been successfully authenticated. You are moving to the page of membership.";
Message.REALNAME_NOT_CONFIRM	="The authentication has failed.";
Message.MEM_DELETE_NOT_ADM		="The manager is not allowed to quit.";
Message.MEM_DELETE_NOT_INS		="The professor is not allowed to quit.\n please ask site operator.";

Message.SAME_CLASS_ID			="The class to move in is the same as the target class.";
Message.NOT_EXIST_CLASS			="Choose the class to move in.";
Message.NOT_EXIST_MEM           ="Choose the students to be moved.\n when many students are chosen, use the shift or ctrl key.";
Message.INSERT_SUBMIT			="Your opinion has been registered.";
Message.NOT_EXIST_FEE			="Put in the lesson fee.";
Message.INVALID_CLASS_NAME		="Enter the name of class divided.";
Message.INVALID_PERIOD_NAME		="Enter the number of class session.";
Message.FAILURE_SCORE			="In the case of rebate course, the reference score for the course completion is compulsory item.";
Message.TOTAL_STD				="In the case of rebate course, the total number of the class is compulsory item.";
Message.LESSON_CONDITION		="In the case of rebate course, the conditions for the class are compulsory item .";
Message.REBATE_LEVEL	        ="In the case of rebate course, the rebate level is compulsory item.";
Message.REBATE_LARGE_FEE		="In the case of rebate course, the rebate ratio for the major companies is compulsory item.";
Message.REBATE_MIDDLE_FEE		="In the case of rebate course, the rebate ratio for SMEs is compulsory.";

Message.INVALID_PERIOD_START_DATE		="The start day for the class session is not valid.\n the start day for the class concerned is [<@ 1 >].";
Message.INVALID_PERIOD_END_DATE		="The end day for the class session is not valid.\n the end day for the class concerned is [<@ 1 >].";
Message.DUPE_PERIOD_DATE		="The number of the class session for the same period is duplicate.";
Message.INITIALIZATIOIN			="The scores of the item chosen will be initialized. \n Do you want to initialize them?";
Message.TOTALIZATION_YN			="Some items of those chosen are not summed up.";

//---------------------------------------------------------------------------------
// system message
// - common function 에서 사용합니다. 삭제하지 마세요.
//---------------------------------------------------------------------------------
Message.NO_EVENT				="No work has been made.";
Message.NO_ITEM					="There are no items to choose from.";
Message.INVALID_LOGINID			="In valid ID logged in. \n check the ID and try it again!.";
Message.LOG_FAIL				="The login is unsuccessful, please try it again.";
Message.LOG_OUT					="This program will be unavailable for a certain time .\n for the protection of information, the system is closed.\n\n please log in again.";

//---------------------------------------------------------------------------------
// business message
// - 업무별로 필요한 메세지를 이곳에 추가하세요.
//---------------------------------------------------------------------------------
Message.ESSENTIAL				="[<@ 1 >] is essential input item."; 
Message.DATALENGTH				="[<@ 1 >] must be entered, not exceeding <@ 2 > letters.";                																																																			
//Message.VALID_DATE			="[<@ 1 >]은(는) 날짜형식(YYYY.MM.DD)으로 입력해야 합니다."; 																																																			
Message.VALID_DATE  			="[<@ 1 >]must be entered in the date form (YYYY-MM-DD)";
Message.DUP_CHECK				="[<@ 1 >] is duplicate.";                           																												
Message.ESSENTIAL_COMBO			="[<@ 1 >] must be chosen.";                              																																																			
                                                                                          																																																			
//onblur에서 확인하는 메시지                                                                       																																																			
Message.INVALID_CORPNO        	="This is invalid corporation number.";
Message.INVALID_DATE			="The input must be made in the date form (YYYY.MM.DD).";
Message.INVALID_NUMBER			="This is invalid number.";
Message.INVALID_PHONENUMBER  	="Characters are not allowed. (however, hyphen is allowed) please enter numeric only";
Message.INVALID_JUMIN        	="This is invalid resident’s number.";
Message.INVALID_JUMIN_BEFORE    ="Enter the first 6 digits of resident’s number correctly.";
Message.INVALID_JUMIN_AFTER     ="Enter the second part of 7 digits of resident’s number exactly.";
Message.INVALID_JUMIN_NULL      ="Enter your resident’s number please.";
Message.INVALID_JUMIN_NUMBER    ="Numbers only are allowed to enter.";
Message.INVALID_STRING			="No special letters are allowed to enter.";

Message.MSG_INP_DATEES			="The end date must be after the start date.";
Message.MSG_INP_DATESE          ="The start date must be before the end date.";
Message.MSG_SELECT_START_DATE	="Choose the start date.";
Message.MSG_SELECT_END_DATE		="Select the end date.";

Message.MSG_ALERT_DECIDE     	="The selected information will be fixed. Do you really want to fix it now?";
Message.MSG_ALERT_PERMIT 		="The selected information will be approved. Do you really want to approve it now?";
Message.MSG_ALERT_CANCEL   		="The selected information will be cancelled. Do you really want to cancel it now?";
Message.MSG_ALERT_INSERT        ="Do you want to register it ?";
Message.MSG_ALERT_UPDATE  		="The selected information will be updated. Do you really want to update it now?";
Message.MSG_ALERT_DELETE 		="The selected information will be deleted. Do you really want to delete it now?";

Message.MSG_INSERT_SUCCESS      ="The information is saved.";
Message.MSG_UPDATE_SUCCESS      ="The information is updated.";
Message.MSG_DELETE_SUCCESS      ="The information is deleted.";

Message.MSG_INSERT_FAIL         ="The information has failed in being saved.";
Message.MSG_DELETE_FAIL			="The information has failed in being deleted.";
Message.MSG_UPDATE_FAIL         ="The information has failed in being updated.";

Message.MSG_UPLOAD_SUCCESS      ="The upload of file is successful.";
Message.MSG_ADDED				="The information selected has been added up.";
Message.MSG_CONTADD				="The contents selected has been added up.";
Message.MSG_CFM_ONLYONE 		="No data of more than a single item can be processed.\n\n please choose only data of a single item.";
Message.MSG_DELETE_CNTSUCCESS	="00 items of information has been deleted.";
Message.MSG_SELECT_QUES_DIV		="Choose the question classification.";

Message.MSG_INSERT_QUESTION   	="Do you want to insert it?";
Message.MSG_UPDATE_QUESTION   	="Do you want to update it?";
Message.MSG_DELETE_QUESTION   	="Do you want to delete it?";
Message.MSG_PERMIT_QUESTION   	="Do you want to approve it?";
Message.MSG_END_QUESTION   		="Do you want to end it?";
Message.MSG_RECOMMEND_QUESTION  ="Do you want to recommend it?";
Message.MSG_COMM_CLOSE_QUESTION ="A closed community cannot be opened again.\n Do you really want to close it?";

//수강신청에서 사용
Message.MSG_APPLY_RE 			= "You have already applied for the class";
Message.MSG_APPLY_PERIOD 		= "You cannot apply for more than one class at a class session.";
Message.MSG_SELECT_PERIOD 		= "Select the number of class session";
Message.MSG_SELECT_CLASS 		= "Select the number of your class";
Message.MSG_APPLY_QUESTION 		= "Do you want to apply for the class?";
Message.MSG_APPLY_CANCEL 		= "Do you want to cancel the class?";
Message.MSG_APPLY_SUCCESS 		= "Your application for the course has been completed. \n Do you want to transfer to account page?";
Message.MSG_STD_CNT				= "The number limit for the class has been filled, and any more students cannot be allowed \n Please, try it again next time.";
Message.MSG_APPLY_DATE			= "The date selected is duplicate with the date applied.\n Please re-try.";
Message.MSG_APPROVAL_PAY		= "Do you want to approve the application for the class?";
Message.MSG_ALERT_APPROVAL		= "There is no list to approve the application for the class.";
Message.MSG_APPROVAL_CANCEL		= "Do you want to approve the cancellation for the class?";
Message.MSG_PAYMETHOD_SELECT	= "Select the payment method.";
Message.MSG_APPLY_LOGIN			= "You can apply for the class after you log in.";
Message.MSG_APP_ALERT_CANCEL	= "There is no list of the class possible to be cancelled.";
Message.MSG_DELETELIST_NULL		= "There is no list to be deleted.";
Message.MSG_LIST_NULL			= "There is no list.";
Message.MSG_CHECK_LEC_DATE		= "The start date for the class must be in the open period for the class.\n select the start date for the class again.";
Message.MSG_CHECK_LEC_ENDDATE	= "The end date for the class must be chosen after the current date.";
Message.MSG_APPLY_FAIL_EXEL		= "The result of failure is downloaded into the excel file.";

// 쪽지관리에서 사용
Message.MSG_PAPER_TITLE 		= "Enter the title.";

// 메일발송에서 사용
Message.EMAIL_SEND_SUCCESS		="Your mail has been successfully transferred.";
Message.EMAIL_SEND_FAIL			="Your mail has not been transferred.";
Message.EMAIL_NOT_CORRECT		="Enter the correct email address.";
Message.INVALID_EMAIL_NULL 		="Enter your email address.";

// 강사 신청 승인/거부 안내메일에서 사용
Message.EMAIL_APPROVAL_Y            	="Approval";
Message.EMAIL_APPROVAL_N            	="Refusal"; 
Message.EMAIL_APPROVAL_TITLE_Y      	="The approval for lecturer has been approved."; 
Message.EMAIL_APPROVAL_TITLE_N      	="The approval for lecturer has been rejected.";
Message.EMAIL_APPROVAL_CONTENTS_Y   	="The application for lecturer requested has been approved. \n\n as the member of the community, you can act as a \n lecturer in the site, and give the lecture using the mode for the lecturer provided by \n site.\n\n for any question, consult with the manager concerned through email or telephone.\n";
Message.EMAIL_APPROVAL_CONTENTS_N  		="The application for lecturer requested has been rejected. \n\n as the result of the examination, you cannot act as a lecturer in the site and for any question, please consult with the manager concerned through mail or telephone.\n";
Message.MSG_INS_APPLY_CONFIRM			="Do you want to apply for lecturer?";

Message.MSG_NOTITLE						="The title has not been established.";
Message.MSG_SEL_BIZPART          		="Select the business part.";
Message.MSG_SEL_PART               		="Select the department";
Message.MSG_SEL_USER               		="You must select the user of the department.";
Message.MSG_INTEGRATE_SUCCESS			="You have succeeded in integration of customers.";
Message.MSG_INTEGRATE_FAIL         		="You have failed in integration of customers.";
Message.MSG_INTEGRATE_CANNOT  			="The important jobs only can make the customer integration in input state."
Message.MSG_NOT_SUPPORT           		="The file selected can not be supported.\n select the file form following  \n\n file form : [###]";
Message.MSG_SVY_SELECT   				="The copy of question can be allowed only for one case of question.";
Message.MSG_CUST_SELECT  				="The execution of customer integration can be made only for one case of import jobs.";
Message.MSG_NOT_EXISTDUP				="No duplicate customer is found.";

Message.VALID_DATE				="[<@ 1 >] must be entered in the date form of (YYYYMMDD).";
Message.VALID_NUMBER			="[<@ 1 >] must be entered in the numeric form.";
Message.INVALID_DATE			="This is not valid date number.";
Message.INVALID_NUMBER			="This is not right number.";
Message.INVALID_PHONENUMBER 	="Characters are not allowed. (however, hyphen is allowed) only numbers are allowed";
Message.INVALID_JUMIN        	="This is not valid resident’s number.";
Message.INVALID_CORPNO        	="This is not valid businessman number.";
Message.INVALID_EMAIL         	="This is not valid E-Mail address.";
Message.NULL_SELECTED			= "There is no list selected.";

Message.INVALID_EMAIL3CHR     	="\n the mail account before '@' must include more than 3 letters.\n enter it again.";
Message.INVALID_EMAIL6CHR     	="\n the number of the letters must exceed more than 6 including '.', after ‘@’ .\n enter it again.";
Message.INVALID_HANMAIL			="hanmail has some limit to the use of mail due to the charging policy for the mail service \n select other email account";

//---------------------------------------------------------------------------------
// - common function 에서 사용합니다. 삭제하지 마세요.
//---------------------------------------------------------------------------------
Message.MSG_NO_DATA                	="There is no items to be selected.";
Message.MSG_DEL_DATA_SELECTED    	="Select the items to be deleted.";
Message.MSG_DEL_NO_DATA           	="There is no data to be deleted.";
Message.MSG_EXCEL_FILE_ONLY_UPLOAD  ="Excel file only can be loaded up.";

//=================================================================================

/*	IMK - VOC SOP에서 사용	*/
Message.MSG_ONE_INSERT      		="New entry/update must be made one by one.";
/*	IMK - TEMPLATE에서 사용	*/
Message.MSG_NO_CONTENT      		="There is no contents available for preview.";
/*	IMK - 개인일정에서 사용	*/
Message.MSG_INP_TIMESE				="The wrong time has been designated.";
/*	IMK - 업무지시에서 사용	*/
Message.MSG_ALARM_NO_DATA      		= "More than one item must be checked for notice method of business instruction"; 
Message.MSG_BIZ_TARGET_NO_DATA 		= "Select the target designee.";

Message.NO_SELECTED                 = "Select the items.";
Message.MAIL_TMPL_NO_SELECTED 		= "Select the mail template."; 
Message.MAIL_TMPL_MULT_SELECTED 	= "Select only one item of mail template."; 
Message.SMS_TMPL_NO_SELECTED 	    = "Select SMS template."; 
Message.SMS_TMPL_MULT_SELECTED 		= "Select only one item of SMS template."; 
Message.MSG_NOT_TRANS 				= "The transfer cannot be made to the concerned person himself.."; 
Message.MSG_TRANS_CNT_LIMIT 		= "The transfer has been made more than 3 times."; 
Message.MSG_CHECK_PNT 				= "Select the place for the tag to be put in.";
Message.MSG_NO_CUST 				= "Select the customer company.";

//=================================================================================
Message.MSG_NO_EMP_ANA 					= "Select the employees";
Message.MSG_ANALYSIS_05_MAX_ERROR 		="The maximum number cannot exceed 2 digits.";
Message.MSG_ANALYSIS_05_DURATION_ERROR 	="The wrong period is entered";

/*	첨부파일 에서 사용	*/
Message.MSG_NOT_SELECT_ATTACH 			="Select the files attached.";
Message.MSG_FILENAME_LIMIT 				="The file name cannot exceed more than 100 letters.";
Message.MSG_FAIL_FILE_UP 				="The upload of file is unsuccessful.";
Message.MSG_SPC_FILE					= "The only files with asf and wmv in extension are available.";
Message.MSG_INTRO_FILE					= "The only files in the form of motion picture are available.";

/* 커뮤니티 */
Message.MSG_COMM_BOARD_DELETE			= "All the sentences written are deleted at the time of deletion.\n\n Do you want to delete it?";
Message.MSG_COMM_BOARD_ACCESS			= "You are not allowed to get access to the bulletin board concerned.";
Message.MSG_IMG_FILE_ONLY_UPLOAD        = "The only files in the image form can be uploaded.";
Message.MSG_IMG_TYPE					= "The only files in the form of jpg, gif, jpeg can be uploaded.";
Message.MSG_COMM_NICKNAME_LENGTH 		= "Nick names cannot exceed more than 7 letters.";
Message.MSG_MEM_DELETE 					= "Do you want to delete the member selected without any consideration?";
Message.MSG_COMM_LINK_DELETE 			= "Do you want to delete the link information?";
Message.MSG_COMM_NICKNAME_LENGTH 		= "Nick names cannot exceed more than 7 letters.";
Message.MSG_MEM_APPROVE 				= "Do you want to approve the application for the admission?";
Message.MSG_MEM_NOT_APPROVE 			= "Do you want to decline the admission?";
Message.MSG_MEM_MGR_REPLACE 			= "Do you want leave it to the manager?";
Message.MSG_MEM_MGR_APPOINT 			= "Do you want to appoint them as the management?";

