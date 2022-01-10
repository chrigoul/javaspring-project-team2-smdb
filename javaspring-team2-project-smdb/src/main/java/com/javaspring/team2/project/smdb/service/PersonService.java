package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PersonService extends BaseService<Person, Long> {

//    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    Person findPersonByFirstNameAndLastName(String firstName, String lastName);

    Person findLazy(Long id);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);

}
