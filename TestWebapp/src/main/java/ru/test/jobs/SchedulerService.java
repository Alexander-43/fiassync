package ru.test.jobs;

import org.quartz.Job;
import org.springframework.scheduling.quartz.CronTriggerBean;

public interface SchedulerService {
	void register( Class<? extends Job> jobClass, String cronExpression ) throws NoSuchMethodException;
	
	void register (CronTriggerBean cronTrigger);
	
    void reschedule( Class<? extends Job> jobClass, String cronExpression ) throws NoSuchMethodException;
    
    void reschedule( String triggerName, String cronExpression );
}
