package com.javaspring.team2.project.smdb.controller;

import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.service.BaseService;
import com.javaspring.team2.project.smdb.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController extends AbstractController<Person>{
    final private PersonService personService;

    @Override
    protected BaseService<Person, Long> getBaseService() {
        return personService;
    }

}
