package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerGenreDto;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerReleaseYearGenreDto;
import com.javaspring.team2.project.smdb.transfer.TitlesForAPersonOrganizedByGenresDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Title, Long> {

    //    1st Report: Top 3 Titles containing both Movies and TvShows
    List<Title> getTop3ByOrderBySmdbRatingDesc();

    //    2nd Report: All Titles a Person has participated in regardless of his/her profession
    @Query(value = "select distinct t from Title t join t.professions f join f.person p where p.id = (select distinct p.id from Person p where p.firstName = :firstName and p.lastName = :lastName)")
    List<Title> getPersonParticipationInTitleByFullName(String firstName, String lastName);

    //    3rd Report: All Titles a Person has participated per his/her profession
    @Query(value = "select distinct t from Title t join t.professions f join f.person p join f.titleContributionRole ft where p.id = (select distinct p.id from Person p where p.firstName = :firstName and p.lastName = :lastName and ft =:contributionRole)")
    List<Title> getPersonParticipationInTitleByFullNameAndProfessions(String firstName, String lastName, ContributionRole contributionRole);

    //    4th Report: All TvShows per a given Genre
    List<Title> getAllByGenresContaining(Genre genre);

    //    5th Report: Number of TvShows per a given Genre
    @Query(value = "select genre, count(title_id) as number from title_genres group by genre", nativeQuery = true)
    List<NumberOfShowsPerGenreDto> getNumberOfShowsPerGenre();

    @Query(value = "SELECT TD.genre, count(T.id) as number FROM TITLE_GENRES AS TD INNER JOIN TITLES AS T on T.ID = TD.TITLE_ID WHERE RELEASEYEAR=2022 GROUP BY GENRE", nativeQuery = true)
    List<NumberOfShowsPerReleaseYearGenreDto> getNumberOfShowsPerReleaseYearPerGenre(Integer year);
//
    //  @Query(value = "select genre, select distinct t from Title t join t.genres gn join t.professions f join f.person p where p.id = (select distinct p.id from Person p where p.firstName = :firstName and p.lastName = :lastName) as content group by genre", nativeQuery = true)
/*    @Query(value = "SELECT title.*, p.person_id FROM (select t.id as titleId, t.primaryTitle as primarytitle, t.storyLine as storyline, t.releaseYear as releaseyear t.smdbRatind as smdbrating FROM TITLE_GENRES as TG inner join TITLES as T on T.ID = TG.TITLE_ID) AS titlecontents inner join professions as p on titlecontents.id = p.TITLE_ID WHERE P", nativeQuery = true)
    List<Title> getAllTitlesForAPersonOrganizedByGenres(Long id, String lastName);*/

}