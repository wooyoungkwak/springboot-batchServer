package com.example.springbootbatchserver.batch;

import com.example.springbootbatchserver.model.entity.person.domain.Person;
import com.example.springbootbatchserver.model.entity.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

/**
 * Date : 2022-05-23
 * Author : zilet
 * Project : springbootSample
 * Description :
 */

@Slf4j
@RequiredArgsConstructor
public class MyItemReader implements ItemReader<Person> {

    private final PersonService personService;

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        List<Person> persons = personService.getsAll();

        // 0 ~ 2 까지 난수 ( 0, 1, 2)
        int start = 0;
        int end = 1;
        int index = (int) (Math.random() * end) + start;
        log.info(" ******* read ::  personSeq = {} | name = {} | state = {}", persons.get(index).getPersonSeq(), persons.get(index).getName());

        return persons.get(index);
    }
}
