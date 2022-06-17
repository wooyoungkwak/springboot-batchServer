package com.example.springbootbatchserver.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Date : 2022-05-23
 * Author : zilet
 * Project : springbootSample
 * Description :
 */
@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    @Override
    public void afterJob(JobExecution jobExecution) {
        if ( jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info(" *********************** job 작업 완료 ");
            // database 등록
        }
    }
}
