package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {
    private final PersonRepository personRepository;

    @Override
    JpaRepository<Person, Long> getRepository() {
        return personRepository;
    }

}
