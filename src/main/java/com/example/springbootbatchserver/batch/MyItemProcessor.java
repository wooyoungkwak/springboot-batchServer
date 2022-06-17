package com.example.springbootbatchserver.batch;

import com.example.springbootbatchserver.model.entity.person.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * Date : 2022-05-23
 * Author : zilet
 * Project : springbootSample
 * Description :
 */
@Slf4j
public class MyItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person item) throws Exception {
        log.info(" ******* processor ::  personSeq = {} | name = {}", item.getPersonSeq(), item.getName());

        return item;
    }
}
