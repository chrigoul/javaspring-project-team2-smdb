package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

    Title findTitleByPrimaryTitle(String primaryTitle);

    Boolean existsByPrimaryTitle(String primaryTitle);

    @Query(value = "select distinct p from Person p join p.professions f join f.title t where t.id = (select distinct t.id from Movie where t.primaryTitle = :primaryTitle)")
    List<Person> getPeopleParticipatingInTitle(String primaryTitle);
}
