package ru.test.webscripts;

import java.io.IOException;

import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

public class TestwsGet extends AbstractWebScript {

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		// TODO Auto-generated method stub
		res.getWriter().write("Text from java webscript");
	}

}
