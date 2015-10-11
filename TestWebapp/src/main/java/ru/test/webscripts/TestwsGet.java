package ru.test.webscripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import ru.test.jobs.SchedulerServiceImpl;

public class TestwsGet extends DeclarativeWebScript {

	public static Logger logger = Logger.getLogger(TestwsGet.class);
	
	private SchedulerServiceImpl service;
	
	public Map<String, Object> executeImpl(WebScriptRequest req, WebScriptResponse res, Cache cache) throws IOException {
		// TODO Auto-generated method stub
		logger.debug("Try to reschedule ... ");
		//service.reschedule("messageTrigger", "0 0/3 * * * ? *");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res", "Just a test");
		return map;
	}

	public void setService(SchedulerServiceImpl service) {
		this.service = service;
	}

}
