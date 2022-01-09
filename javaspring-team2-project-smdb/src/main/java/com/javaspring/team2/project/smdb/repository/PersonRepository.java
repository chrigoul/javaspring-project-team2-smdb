package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByLastNameAndFirstName(String lastName, String firstName);
}