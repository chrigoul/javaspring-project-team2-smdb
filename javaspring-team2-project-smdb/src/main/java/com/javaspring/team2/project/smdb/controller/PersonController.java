package com.javaspring.team2.project.smdb.controller;

import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.domain.TvShow;
import com.javaspring.team2.project.smdb.service.BaseService;
import com.javaspring.team2.project.smdb.service.PersonService;
import com.javaspring.team2.project.smdb.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController extends AbstractController<Person>{
    final private PersonService personService;

    @Override
    protected BaseService<Person, Long> getBaseService() {
        return personService;
    }


    @GetMapping(path = "titles/person", params = {"firstname", "lastname"})
    public ResponseEntity<ApiResponse<List<Title>>> getAllContentByContributorByFullName(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName)
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(personService.getTitlesThatPersonParticipatedIn(firstName, lastName))
                .build());
    }

    @GetMapping(params = {"lastname"})
    public ResponseEntity<ApiResponse<List<Person>>> getAllContentByContributorByLastName(@RequestParam("lastname") String lastName)
    {
        return ResponseEntity.ok(ApiResponse.<List<Person>>builder()
                .data(personService.findPersonByLastName(lastName))
                .build());
    }


}
