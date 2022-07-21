/*package kr.co.sitglobal.oklms.comm.job;

import java.text.SimpleDateFormat;

import kr.co.sitglobal.aunuri.service.AunuriLinkService;
import kr.co.sitglobal.oklms.la.link.service.LinkService;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
//import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class AunuriLinkJob extends QuartzJobBean{
	
	public Logger log = Logger.getLogger(getClass());
	
	
	private LinkService  linkService;
	
	public void setLinkService(LinkService linkService) { this.linkService = linkService; }
	 
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		//System.out.println("Start - QuartzJobBean :: " + getClass().getName() + ", " +DateTimeUtil.getDateTimeByPattern("yyyy-MM-dd HH:mm:ss"));
		
		
		long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        System.out.println("Cron trigger 2 (1 minute): current time = " + sdf.format(time));
		
		
		try {
	  
			//examScoreService.scoreEvaluation();
			linkService.executeBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}

}
*/