package ru.test.webscripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.springframework.extensions.webscripts.json.JSONUtils;

import ru.test.jobs.SchedulerServiceImpl;

public class TestwsPost extends AbstractWebScript {

	public static Logger logger = Logger.getLogger(TestwsPost.class);
	
	private SchedulerServiceImpl service;
	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		// TODO Auto-generated method stub
		logger.debug("Try to reschedule ... ");
		try {
			String content = req.getContent().getContent();
			if (content.endsWith("\"") && content.startsWith("\"")){
				content = content.substring(1, content.length() - 1);
			}
			JSONObject jsonObject = new JSONObject(StringEscapeUtils.unescapeJava(content));
			Iterator it = jsonObject.keys();
			String result = "{\"data\":[";
			while (it.hasNext()){
				String key = (String)it.next();				
				String value = (String) jsonObject.get(key);
				result +="{\"Key\":\"" + key + "\", \"Value\":\"" + value + "\"}" + (it.hasNext() ? "," : "");
			}
			result +="]}";
			res.getWriter().write(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//service.reschedule("messageTrigger", "0 0/3 * * * ? *");
	}

	public void setService(SchedulerServiceImpl service) {
		this.service = service;
	}

}
