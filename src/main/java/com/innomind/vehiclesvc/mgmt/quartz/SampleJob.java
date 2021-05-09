package com.innomind.vehiclesvc.mgmt.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {

    //@Autowired
    //private SampleJobService jobService;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        //jobService.executeSampleJob();
    	System.out.println("SampleJob.execute() Job is triggered "+new Date());
    }
}
