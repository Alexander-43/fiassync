package ru.test.webscripts;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import ru.test.jobs.SchedulerServiceImpl;

public class TestwsGet extends AbstractWebScript {

	public static Logger logger = Logger.getLogger(TestwsGet.class);
	
	private SchedulerServiceImpl service;
	
	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		// TODO Auto-generated method stub
		logger.debug("Try to reschedule ... ");
		service.reschedule("messageTrigger", "0 0/3 * * * ? *");
		res.getWriter().write("Text from java webscript");
	}

	public void setService(SchedulerServiceImpl service) {
		this.service = service;
	}

}
