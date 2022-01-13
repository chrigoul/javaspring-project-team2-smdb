package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p join fetch p.professions where p.id = ?1")
    Person findLazy(Long id);

    Person findPersonByFirstNameAndLastName(String firstName, String lastName);

    Person findPersonByLastName(String lastName);

    @Query("select distinct t from Title t join t.professions f join f.person p where p.id = (select distinct p.id from Person p where p.firstName = :firstName and p.lastName = :lastName)")
    List<Title> getTitlesThatPersonParticipatedIn(String firstName,String lastName);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);

}