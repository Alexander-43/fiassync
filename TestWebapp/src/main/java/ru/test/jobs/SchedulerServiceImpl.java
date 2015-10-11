package ru.test.jobs;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.CronTriggerBean;

public class SchedulerServiceImpl implements SchedulerService {

	public static Logger logger = Logger.getLogger(SchedulerServiceImpl.class);
	
	private Scheduler scheduler;
	
	private List<CronTriggerBean> triggers;
	
    void init() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch ( Exception e ) {
            // handle exception
        }
        for (CronTriggerBean trigger : triggers){
        	this.register(trigger);
        }
    }

    void destroy() {
        try {
            scheduler.shutdown();
        } catch ( Exception e ) {
            // handle exception
        }
    }

    @Override
    public void register( CronTriggerBean cronTrigger ) {
        try {
            cronTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        	Date data = scheduler.scheduleJob(cronTrigger.getJobDetail(), cronTrigger );
            logger.debug("Registered " + cronTrigger.getName() + " trigger. Next date - " + data.toString());
        } catch ( Exception e ) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void reschedule( String triggerName, String cronExpression ) {
        try {
            
        	CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerName, Scheduler.DEFAULT_GROUP);
            if (trigger == null){
            	logger.error("Trigger " + triggerName + " doesen`t found");
            	return;
            }
            CronTrigger newTrigger = (CronTrigger) trigger.clone();
        	newTrigger.setCronExpression(cronExpression);
        	newTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
            Date data = scheduler.rescheduleJob( triggerName, Scheduler.DEFAULT_GROUP, newTrigger );
            logger.debug("Rescheduled " + triggerName + " to " + cronExpression + ". Next event in " + data.toString());
        } catch ( Exception e ) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

	@Override
	public void register(Class<? extends Job> jobClass, String cronExpression) throws NoSuchMethodException {
		throw new NoSuchMethodException();
		
	}

	@Override
	public void reschedule(Class<? extends Job> jobClass, String cronExpression) throws NoSuchMethodException {
		throw new NoSuchMethodException();
		
	}

	public void setTriggers(List<CronTriggerBean> triggers) {
		this.triggers = triggers;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}


}
