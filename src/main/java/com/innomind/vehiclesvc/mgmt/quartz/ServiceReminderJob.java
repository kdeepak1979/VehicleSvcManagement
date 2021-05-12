package com.innomind.vehiclesvc.mgmt.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.innomind.vehiclesvc.mgmt.reminder.IServiceReminderStrategy;
import com.innomind.vehiclesvc.mgmt.reminder.ServiceReminderStratgy;

@Component
public class ServiceReminderJob implements Job, ApplicationContextAware {

    
    private static IServiceReminderStrategy serviceReminderStratgy ;

    public void execute(JobExecutionContext context) throws JobExecutionException {
    	serviceReminderStratgy.execute();
    }

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {		
		serviceReminderStratgy = appContext.getBean(ServiceReminderStratgy.class);
	}
}
