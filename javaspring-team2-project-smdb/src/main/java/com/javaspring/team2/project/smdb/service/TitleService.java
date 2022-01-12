package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TitleService extends BaseService<Title, Long> {

    Title findTitleByPrimaryTitle(String primaryTitle);

    List<Title> findTop3ByOrderBySmdbRatingDesc();

    List<Title> findAllByGenresContains(Genre genre);

    Boolean existsByPrimaryTitle(String primaryTitle);

    List<Title> findPersonParticipationInTitleByFullName(String firstName, String lastName);

   // List<Title> findPersonParticipationInTitleByFullNameByAndProfessions(String firstName, String lastName, ContributionRole profession);

    List<Person> getPeopleParticipatingInTitle(String primaryTitle);

}
