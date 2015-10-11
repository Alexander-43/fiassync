package ru.test.jobs;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class Sheduler extends QuartzJobBean implements StatefulJob {

	public static final Logger logger = Logger.getLogger(Sheduler.class);
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.debug("Triggered in " + (new Date()).toString());
		try {
			logger.debug("Wait ...");
			Thread.sleep(10000);
			logger.debug("Wait again ...");
			Thread.sleep(20000);
			logger.debug("Last Wait ...");
			Thread.sleep(10000);
			logger.debug("Next >>>");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
