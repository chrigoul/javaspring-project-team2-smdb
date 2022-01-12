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

    //    1st Report: Top 3 Titles containing both Movies and TvShows
    List<Title> findTop3ByOrderBySmdbRatingDesc();

    //    2nd Report: All Titles a Person has participated in regardless of his/her profession
    @Query("select distinct t from Title t join t.professions f join f.person p where p.id = (select distinct p.id from Person p where p.firstName = :firstName and p.lastName = :lastName)")
    List<Title> findPersonParticipationInTitleByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    //    3rd Report: All Titles a Person has participated per his/her profession
    @Query("select distinct t from Title t join t.professions f join f.person p where p.id = (select distinct p.id from Person p where p.firstName = :firstName and p.lastName = :lastName) and (f.titleContributionRole = :profession)")
    List<Title> findPersonParticipationInTitleByFullNameByAndProfessions(@Param("firstName") String name, @Param("lastName") String surname, @Param("profession") ContributionRole profession);

    //    4th Report: All TvShows per a given Genre
    List<Title> findAllByGenresContains(Genre genre);

    Boolean existsByPrimaryTitle(String primaryTitle);

    @Query(value = "select distinct p from Person p join p.professions f join f.title t where t.id = (select distinct t.id from Movie where t.primaryTitle = :primaryTitle)")
    List<Person> getPeopleParticipatingInTitle(String primaryTitle);
}
