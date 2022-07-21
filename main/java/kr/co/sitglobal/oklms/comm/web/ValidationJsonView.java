/*******************************************************************************
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 7. 14.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.comm.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sitglobal.oklms.comm.resolver.Simple2MappingExceptionResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.view.AbstractView;

//import org.codehaus.jackson.JsonEncoding; // spring 3.x , jackson 1.x
//import org.codehaus.jackson.JsonGenerator; // spring 3.x , jackson 1.x
//import org.codehaus.jackson.map.ObjectMapper; // spring 3.x , jackson 1.x

//import com.fasterxml.jackson.core.JsonEncoding; // spring 4.x , jackson 2.x
//import com.fasterxml.jackson.core.JsonGenerator; // spring 4.x , jackson 2.x
//import com.fasterxml.jackson.databind.ObjectMapper; // spring 4.x , jackson 2.x

 /**
 * Valid Annotation 에서 에러가 있을 경우, 에러정보를 포함한 Json 으 리턴한다..
 * @author <a href="mailto:test@reo_me@sitglobal.co.kr">AA</a>
 * @since 2016. 7. 14.
 */
//public class ValidationJsonView extends AbstractView {
public class ValidationJsonView  {

	//private static final Logger LOGGER = LoggerFactory.getLogger(Simple2MappingExceptionResolver.class);

	/*private BindException ex;
	private HttpServletResponse response;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public ValidationJsonView() {
		setContentType("application/json");
	};
	
	private ModelMap getErrorInfo() {
		ModelMap model = new ModelMap();
		model.addAttribute("errorType", "validError");
		model.addAttribute("errorCount", ex.getErrorCount());
		model.addAttribute("errors", ex.getFieldErrors());
		return model;
	}
	
	private void setScBadRequest() {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.ex = (BindException) model.get("exception");
		this.response = response;
		
		LOGGER.info( ex.getFieldErrors().get(0).toString());
		LOGGER.info("Error Fields : {}", ex.getFieldErrors());
		
		setScBadRequest();
		JsonGenerator generator =  objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
		
		objectMapper.writeValue(generator, getErrorInfo());
		
	}*/

}
