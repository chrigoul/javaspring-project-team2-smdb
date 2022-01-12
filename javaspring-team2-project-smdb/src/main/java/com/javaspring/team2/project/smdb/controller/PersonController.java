package com.javaspring.team2.project.smdb.controller;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.service.BaseService;
import com.javaspring.team2.project.smdb.service.PersonService;
import com.javaspring.team2.project.smdb.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping(path = "person", params = {"contributionrole"})
//    public ResponseEntity<ApiResponse<List<Person>>> getPersonForaContributionRole(@RequestParam("contributionRole") ContributionRole contributionRole )
//    {
//        return ResponseEntity.ok(ApiResponse.<List<Person>>builder()
//                .data(personService.getPersonForaContributionRole(contributionRole))
//                .build());
//    }



}
