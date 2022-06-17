package com.example.springbootbatchserver.batch;

import com.example.springbootbatchserver.model.entity.person.domain.Person;
import com.example.springbootbatchserver.model.entity.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

/**
 * Date : 2022-05-23
 * Author : zilet
 * Project : springbootSample
 * Description :
 */
@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration extends DefaultBatchConfigurer {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PersonService personService;

    private final DataSource dataSource;


    /* reader 데이터 건수 ( row 수 )*/
    private int chunkSize = 2;

    private static final String JOB_NAME = "sampleJob";
    private static final String BEAN_PREFIX = JOB_NAME + "_";

    // batch 메타 데이터를 데이터 베이스에 저장 하지 않기 위해 사용
    @Override
    public void setDataSource(DataSource dataSource) {
    }

    @Bean(JOB_NAME)
    public Job myJob(JobCompletionNotificationListener listener, Step step) {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step)
                .build();
    }

    @Bean(BEAN_PREFIX + "step")
    public Step myStep() {
        int choice = 1;
        Step step;

        switch (choice) {
            case 1:

                /* reader / processor / writer 방식 */
                step = stepBuilderFactory.get(BEAN_PREFIX + "step")
                        .<Person, Person>chunk(chunkSize)
                        .reader(jdbcCursorItemReaderBuilder())
                        .processor(processor())
                        .writer(writer())
                        .build();
                break;
            case 2:
            default:
                /* tasklet 방식 */
                step = stepBuilderFactory.get(BEAN_PREFIX + "step")
                        .tasklet(new MyTasklet())
                        .build();
                break;
        }

        return step;
    }

    @Bean
    @StepScope
    public JdbcCursorItemReader<Person> jdbcCursorItemReaderBuilder() {
        return new JdbcCursorItemReaderBuilder<Person>()
                .name("jdbcCursorItemReaderBuilder")
                .fetchSize(chunkSize)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Person.class))
                .sql("SELECT personSeq, name, age, email, address, sex FROM Person")
                .build();
    }

    // 테스트 용도로 만들었음
    // 주의 : 이것을 실행시 멈추지 않고 계속해서 Read 되어 무한 으로 동작하게 됨.
    //@Bean
    public MyItemReader myItemReader() {
        return new MyItemReader(personService);
    }

    // 테스트 용도로 만들었음.
    public ItemProcessor<Person, Person> processor() {
        return new MyItemProcessor();
    }

    // 테스트 용도로 만들었음.
    public ItemWriter<Person> writer() {
        return new MyItemWriter();
    }

}
