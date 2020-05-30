package com.todo.app.config;

import com.todo.app.task.TodoExpirationJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(TodoExpirationJob.class)
                .storeDurably()
                .withIdentity("TodoExpirationJob")
                .build();
    }

}
