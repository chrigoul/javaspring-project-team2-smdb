package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Person;

public interface PersonService extends BaseService<Person, Long> {
    Person findPersonByFirstNameAndLastName(String firstName, String lastName);
    Person findLazy(Long id);

}
