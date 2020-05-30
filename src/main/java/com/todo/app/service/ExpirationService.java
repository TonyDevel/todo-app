package com.todo.app.service;

import com.todo.app.entity.Todo;
import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
public class ExpirationService {

    private final Scheduler taskScheduler;
    private final JobDetail jobDetail;

    public ExpirationService(Scheduler taskScheduler, JobDetail jobDetail) {
        this.taskScheduler = taskScheduler;
        this.jobDetail = jobDetail;
    }

    public void createExpirationTrigger(Todo savedTodo) throws SchedulerException {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("id", savedTodo.getId());

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startAt(savedTodo.getDueDate())
                .usingJobData(dataMap)
                .build();

        taskScheduler.scheduleJob(trigger);
    }
}
