package ru.test.jobs;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class Sheduler extends QuartzJobBean {

	public static final Logger logger = Logger.getLogger(Sheduler.class);
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.error("Triggered in " + (new Date()).toString());
		
	}

}
