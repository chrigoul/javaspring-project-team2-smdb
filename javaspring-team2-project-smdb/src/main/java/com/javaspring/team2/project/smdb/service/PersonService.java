package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonService extends BaseService<Person, Long> {

//    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    Person findPersonByFirstNameAndLastName(String firstName, String lastName);

//    List<Person> getPersonForaContributionRole(@Param("contributionRole") ContributionRole contributionRole);

    Person findLazy(Long id);

    List<Person> findPersonByLastName(String lastName);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);

    List<Title> getTitlesThatPersonParticipatedIn(String firstName, String lastName);

}
