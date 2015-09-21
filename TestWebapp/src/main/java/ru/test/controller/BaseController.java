package ru.test.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;

@Controller
public class BaseController implements ServletContextAware {

	@Autowired
	ServletContext context;
	
	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private final static Logger logger = Logger.getLogger(BaseController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		logger.debug(String.format("[welcome] counter : %s", counter));

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		logger.debug(String.format("[welcomeName] counter : %s", counter));
		Scheduler sh = (Scheduler)getWepAppContext().getBean("scheduleFactory");
		return VIEW_INDEX;

	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
		
	}
	
	public WebApplicationContext getWepAppContext(){
		return WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	}

}