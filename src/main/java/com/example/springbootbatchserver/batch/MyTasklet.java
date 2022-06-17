package com.example.springbootbatchserver.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Date : 2022-05-25
 * Author : zilet
 * Project : springbootSample
 * Description :
 */
public class MyTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        /* 필요한 로직 */


        return RepeatStatus.FINISHED;
    }
}
