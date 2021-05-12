package com.innomind.vehiclesvc.mgmt.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


@Configuration
public class SpringQrtzScheduler {

	@Value("${reminder.schedule}")
	private String reminderJobSchedule;

	@PostConstruct
	public void init() {
		System.out.println("SpringQrtzScheduler.init()...................................");
	}

	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob().ofType(ServiceReminderJob.class).storeDurably().withIdentity("Qrtz_Job_Detail")
				.withDescription("Invoke Reminder...").build();
	}
		

	/*@Bean
	public Trigger trigger(JobDetail job) {
		return TriggerBuilder.newTrigger().forJob(job).withIdentity("Qrtz_Trigger").withDescription("Sample trigger")
				.withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(60)).build();
	}*/
	
	
	@Bean
	public Trigger trigger(JobDetail job) {
		return TriggerBuilder.newTrigger().forJob(job).withIdentity("Qrtz_Trigger").withDescription("Sample trigger")
				.withSchedule(cronSchedule(reminderJobSchedule)).build();
	}

	@Bean
	public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory) throws SchedulerException {
		Scheduler scheduler = factory.getScheduler();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		return scheduler;
	}	


	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		//factory.setJobFactory(springBeanJobFactory());
		Properties p = new Properties();
		p.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
		factory.setQuartzProperties(p);
		return factory;
	}	

}
