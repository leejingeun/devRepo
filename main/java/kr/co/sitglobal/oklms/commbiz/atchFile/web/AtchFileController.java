
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * AA    2016. 8. 9.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.commbiz.atchFile.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.web.EgovImageProcessController;

 /**
 * 클래스에 대한 내용을 작성한다.
 * @author AA
 * @since 2016. 8. 9.
 */
@Controller
public class AtchFileController extends BaseController {

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;

	private static final Logger LOG = Logger.getLogger(AtchFileController.class.getName());
	/**
	 * 공통 첨부파일에 대한 목록을 조회한다.
	 */
	@RequestMapping("/commbiz/atchfle/atchFileListImport.do")
	public String atchFileListImport(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		String atchFileId = (String) commandMap.get("param_atchFileId");
		String deleYn = StringUtils.defaultIfBlank((String) commandMap.get("param_deleYn"),"N"); // 삭제가능여부
		String returnUrl = StringUtils.defaultIfBlank((String) commandMap.get("param_returnUrl"), StringUtils.defaultIfBlank((String)commandMap.get("returnUrl") ,EgovProperties.getProperty("Globals.MainPage") ) );


		String tranBbsId = (String) commandMap.get("tranBbsId");
        String tranBbctId = (String) commandMap.get("tranBbctId");

		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setPageSize(20);
		atchFileVO.setAtchFileId(atchFileId);

		List<AtchFileVO> result = null;
		if( StringUtils.isNoneBlank( atchFileId ) ){

			result = atchFileService.listAtchFile(atchFileVO);
			model.addAttribute("fileListCnt", result.size());
		}else{

			model.addAttribute("fileListCnt", 0);
		}

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", "N");
		model.addAttribute("atchFileId", atchFileId);
        model.addAttribute("tranBbsId", tranBbsId);
        model.addAttribute("tranBbctId", tranBbctId);
		model.addAttribute("deleYn"   , deleYn);   // 삭제가능여부 세팅
		model.addAttribute("returnUrl", returnUrl );

		return "oklms_import/commbiz/atchFile/atchFileList";

	}




	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 * 화면에서 page import로 사용되는 페이지에서 <input name="atrchFleId"> 가 여러개 지정되는 경우 배열로 넘어오는 경우가 발생하여 이름을 변경하여 받도록함.
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = { "/commbiz/atchfle/atchFileDown.do" })
	public void atchFileDown(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String arg0 = (String) commandMap.get("arg0");
		String arg1 = (String) commandMap.get("arg1");

		String atchFileId = (String) commandMap.get("importAtchFileId");
		String fleSn = (String) commandMap.get("importFleSn");


		atchFileId = StringUtils.defaultIfBlank(atchFileId, arg0);
		fleSn = StringUtils.defaultIfBlank(fleSn, arg1);

		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		// if (isAuthenticated) {

		AtchFileVO fileVO = new AtchFileVO();
		fileVO.setAtchFileId( atchFileId );
		fileVO.setFileSn(Integer.parseInt( fleSn ) );
		AtchFileVO fvo = atchFileService.getAtchFile(fileVO );

		File uFile = new File(fvo.getFileSavePath(), fvo.getSaveFileName());
		int fSize = (int) uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			// response.setBufferSize(fSize); // OutOfMemeory 발생
			response.setContentType(mimetype);
			// response.setHeader("Content-Disposition",
			// "attachment; filename=\"" +
			// URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
			atchFileUtil.setDisposition(fvo.getOrgFileName(), request, response);
			response.setContentLength(fSize);

			/*
			 * FileCopyUtils.copy(in, response.getOutputStream()); in.close(); response.getOutputStream().flush(); response.getOutputStream().close();
			 */
			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (Exception ex) {
				// ex.printStackTrace();
				// 다음 Exception 무시 처리
				// Connection reset by peer: socket write error
				logger.debug("IGNORED: " + ex.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						// no-op
						logger.debug("IGNORED: " + ignore.getMessage());
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (Exception ignore) {
						// no-op
						logger.debug("IGNORED: " + ignore.getMessage());
					}
				}
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<script>");
			printwriter.println("alert('서버에서 파일을 찾을 수 없습니다 : \\n" + fvo.getOrgFileName() + "');");
			printwriter.println("window.open(\"about:blank\",\"_self\").close();");
			printwriter.println("</script>");
//			printwriter.println("<br><br><br><h2>서버에서 파일을 찾을 수 없습니다 :<br>" + fvo.getOrgFileName() + "</h2>");
//				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
//			printwriter.println("<br><br><br><center><h3><a href='/index.jsp'>Home</a></h3></center>");
//			printwriter.println("<br><br><br><center><h3><a href='#/' onclick='window.open(\"about:blank\",\"_self\").close();'>닫기</a></h3></center>");
//			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
		// }
	}

	/**
	 * 첨부파일에 대한 삭제를 처리한다.
	 * 화면에서 page import로 사용되는 페이지에서 <input name="atrchFleId"> 가 여러개 지정되는 경우 배열로 넘어오는 경우가 발생하여 이름을 변경하여 받도록함.
	 * @param fileVO
	 * @param returnUrl
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( "/commbiz/atchfle/atchFileDelete.do" )
	public String atchFileDelete(@RequestParam Map<String, Object> commandMap , HttpServletRequest request, ModelMap model ,RedirectAttributes redirectAttributes ) throws Exception {

//		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//
//		if (isAuthenticated) {

			String arg0 = (String) commandMap.get("arg0");
			String arg1 = (String) commandMap.get("arg1");
			String returnUrl = (String) commandMap.get("arg2"); // returnUrl

			System.out.println("substring1 ===>"+returnUrl.substring( returnUrl.indexOf( "nttId", 3 )));
			System.out.println("substring2 ===>"+returnUrl.indexOf( "BBSMSTR_000000000005"));


			String atchFileId = (String) commandMap.get("importAtchFileId");
			String fileSn = (String) commandMap.get("importFileSn");


			atchFileId = StringUtils.defaultIfBlank(atchFileId, arg0);
			fileSn = StringUtils.defaultIfBlank(fileSn, arg1);

			AtchFileVO fileVO = new AtchFileVO();
			fileVO.setAtchFileId( atchFileId );
			fileVO.setFileSn(Integer.parseInt( fileSn ) );


			List<AtchFileVO> fvoList = atchFileService.listAtchFile(fileVO);


			Integer fvo = atchFileService.deleteAtchFile(fvoList);
//		}

		if ("".equals( request.getContextPath() ) || "/".equals( request.getContextPath() )) {
			return "redirect:" + returnUrl;
		}

		if (returnUrl.startsWith( request.getContextPath() )) {
			return "redirect:" + returnUrl.substring( returnUrl.indexOf( "/", 1 ) );
		} else {
			return "redirect:" + returnUrl;
		}
		// //------------------------------------------
	}
	  /**
     * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
     *
     * @param atchFileId
     * @param fileSn
     * @param sessionVO
     * @param model
     * @param response
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/getImageAtch.do")
    public void getImageAtch(SessionVO sessionVO, ModelMap model,@RequestParam Map<String, Object> commandMap, HttpServletResponse response) throws Exception {

//    	@RequestParam("atchFileId") String atchFileId,
//		@RequestParam("fileSn") String fileSn,


		String atchFileId = (String)commandMap.get("atchFileId");
		String fileSn = (String)commandMap.get("fileSn");

		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setPageSize(20);
		atchFileVO.setAtchFileId(atchFileId);
		//------------------------------------------------------------
		// fileSn이 없는 경우 마지막 파일 참조
		//------------------------------------------------------------
		if (fileSn == null || fileSn.equals("")) {
			atchFileVO.setFileSn(1);
		}else{
			atchFileVO.setFileSn(Integer.parseInt(fileSn));
		}

		//------------------------------------------------------------

		AtchFileVO fvo = atchFileService.getAtchFile(atchFileVO) ;//.listAtchFile(fileVO);// fileService.selectFileInf(vo);

		//String fileLoaction = fvo.getFileStreCours() + fvo.getStreFileNm();

		// 2011.10.10 보안점검 후속조치
		File file = null;
		FileInputStream fis = null;

		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;

		try {
		    file = new File(fvo.getFileSavePath(), fvo.getSaveFileName());
		    fis = new FileInputStream(file);

		    in = new BufferedInputStream(fis);
		    bStream = new ByteArrayOutputStream();

		    int imgByte;
		    while ((imgByte = in.read()) != -1) {
		    	bStream.write(imgByte);
		    }

			String type = "";

			if (fvo.getFileExtn()!= null && !"".equals(fvo.getFileExtn())) {
			    if ("jpg".equals(fvo.getFileExtn().toLowerCase())) {
				type = "image/jpeg";
			    } else {
			    	type = "image/" + fvo.getFileExtn().toLowerCase();
			    }
			    type = "image/" + fvo.getFileExtn().toLowerCase();

			} else {
			    LOG.debug("Image fileType is null.");
			}

			response.setHeader("Content-Type", type);
			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();

			// 2011.10.10 보안점검 후속조치 끝
		} finally {
			if (bStream != null) {
				try {
					bStream.close();
				} catch (Exception ignore) {
					//System.out.println("IGNORE: " + ignore);
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception ignore) {
					//System.out.println("IGNORE: " + ignore);
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ignore) {
					//System.out.println("IGNORE: " + ignore);
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
		}
    }
}
