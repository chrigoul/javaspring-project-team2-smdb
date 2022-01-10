package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p join fetch p.professions where p.id = ?1")
    Person findLazy(Long id);

    Person findPersonByFirstNameAndLastName(String firstName, String lastName);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);

}
