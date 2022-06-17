package com.example.springbootbatchserver.model.entity.person.repository;

import com.example.springbootbatchserver.model.entity.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Date : 2022-06-10
 * Author : zilet
 * Project : springboot-batchServer
 * Description :
 */
public interface PersonRepsitory extends JpaRepository<Person, Integer> {

}
