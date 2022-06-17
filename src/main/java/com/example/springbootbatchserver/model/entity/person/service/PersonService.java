package com.example.springbootbatchserver.model.entity.person.service;

import com.example.springbootbatchserver.model.entity.person.domain.Person;

import java.util.List;

/**
 * Date : 2022-06-10
 * Author : zilet
 * Project : springboot-batchServer
 * Description :
 */
public interface PersonService {

    List<Person> getsAll();

    List<Person> gets(long offset, long limit);

}
