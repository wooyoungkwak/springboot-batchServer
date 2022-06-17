package com.example.springbootbatchserver.batch;

import com.example.springbootbatchserver.model.entity.person.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Date : 2022-05-23
 * Author : zilet
 * Project : springbootSample
 * Description :
 */
@Slf4j
public class MyItemWriter implements ItemWriter<Person> {
    @Override
    public void write(List<? extends Person> items) throws Exception {

        for ( Person person : items)
            log.info(" ********* writer :: personSeq = {} | name = {}", person.getPersonSeq(), person.getName());

    }
}
