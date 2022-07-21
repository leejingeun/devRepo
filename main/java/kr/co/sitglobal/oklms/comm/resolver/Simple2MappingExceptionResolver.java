package kr.co.sitglobal.oklms.comm.resolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.RedirectView;
//import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class Simple2MappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Simple2MappingExceptionResolver.class);
	
//	@Override
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
//			Object handler, Exception ex) {
//
//	    if(ex instanceof MaxUploadSizeExceededException) {
//	        MaxUploadSizeExceededException maxe = (MaxUploadSizeExceededException)ex;
//	        String errorMessage = "Max filesize exceeded, please ensure filesize is too large.";
//	        HashMap<String, Object> model = new HashMap<String, Object>(2);
//	        model.put("errorMessage", errorMessage);
////	        return new ModelAndView("cmm/error/maxUploadSizeExceededException", model);
//	        
////	        RedirectView rv = new RedirectView( "/cmm/error/maxUploadSizeExceededException.do" );
////	        rv.setExposeModelAttributes(true);
////	        return (ModelAndView)new ModelAndView(rv, model);
//	        
//
////	        return new ModelAndView("forward:/cmm/error/maxUploadSizeExceededException.do", model);
//	        return new ModelAndView("redirect:/cmm/error/maxUploadSizeExceededException.do", model);
//	            
//	        
//	    } else {
//	        return super.resolveException(request, response, handler, ex); // Do whatever default behaviour is (ie throw to error page).
//	    }
//	}
	
    @Override  
    protected ModelAndView doResolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {  
    	
        String viewName = determineViewName(ex, request);  
        if (viewName != null) {
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                    .getHeader("X-Requested-With")!= null && request  
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  

            	// JSP  
            	LOGGER.info("#### Simple2MappingExceptionResolver : JSP ( viewName : " + viewName + ") , Exception name : " + ex.getClass().getName() );
                Integer statusCode = determineStatusCode(request, viewName);  
                if (statusCode != null) {  
                    applyStatusCodeIfPossible(request, response, statusCode);  
                }  
                return getModelAndView(viewName, ex, request);  
            } else {

            	// JSON
            	LOGGER.info("#### Simple2MappingExceptionResolver : JSON ( viewName : " + viewName + ")");
            	ModelAndView modelAndView;
        		try {
//        			response.reset();
        			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        			response.setCharacterEncoding("UTF-8");
        			response.setContentType("text/json");
        			
        			Map<String, String> asd = new HashMap<String, String>();
        			asd.put("message", ex.getMessage());
        			asd.put("msg", ex.getMessage() + " ####MY MSG");

        			MappingJackson2JsonView view = new MappingJackson2JsonView();
        			view.setAttributesMap(asd);
        			modelAndView = new ModelAndView(view); 
        		} catch (Exception e) {
        			LOGGER.error( "send back error status and message : " + ex.getMessage(), e);
            		modelAndView = new ModelAndView("jsonView");
        		}
        		
        		return modelAndView;
            	
//                return null;  
  
            }  
        } else {  
            return null;  
        }  
    } 
}
