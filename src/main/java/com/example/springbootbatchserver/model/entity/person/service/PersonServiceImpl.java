package com.example.springbootbatchserver.model.entity.person.service;

import com.example.springbootbatchserver.model.entity.person.domain.Person;
import com.example.springbootbatchserver.model.entity.person.domain.QPerson;
import com.example.springbootbatchserver.model.entity.person.repository.PersonRepsitory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Date : 2022-06-10
 * Author : zilet
 * Project : springboot-batchServer
 * Description :
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepsitory personRepsitory;

    private final EntityManager entityManager;

    @Override
    public List<Person> getsAll() {
        return personRepsitory.findAll();
    }

    @Override
    public List<Person> gets(long offset, long limit) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        return query.selectFrom(QPerson.person)
                .offset(offset)
                .limit(limit)
                .fetch();
    }

}
