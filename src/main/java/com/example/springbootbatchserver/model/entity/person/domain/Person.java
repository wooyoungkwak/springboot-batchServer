package com.example.springbootbatchserver.model.entity.person.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Date : 2022-06-10
 * Author : zilet
 * Project : springboot-batchServer
 * Description :
 */

@Setter
@Getter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer personSeq;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer age;

    @Column
    String email;

    @Column
    String address;

    @Column(nullable = false)
    Integer sex;

}
