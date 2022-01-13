package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerGenreDto;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerReleaseYearGenreDto;
import com.javaspring.team2.project.smdb.transfer.TitlesForAPersonOrganizedByGenresDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    //    5th Report: Number of TvShows per  Genre
    @Query(value = "select genre, count(title_id) as number from title_genres group by genre", nativeQuery = true)
    List<NumberOfShowsPerGenreDto> getNumberOfShowsPerGenre();

    //    6th Report: Number of TvShows per Genre per Release Year
    @Query(value = "SELECT genre, count(TVS.id) as number FROM TITLE_GENRES AS TG INNER JOIN TV_SHOWS AS TVS on TVS.ID = TG.TITLE_ID INNER JOIN TITLES T ON T.ID = TG.TITLE_ID WHERE T.RELEASEYEAR=?1 group by genre", nativeQuery = true)
    List<NumberOfShowsPerReleaseYearGenreDto> getNumberOfShowsPerReleaseYearPerGenre(Integer year);//

    //    7th Report: All Titles associated with a given individual organized per genre.
    @Query(value = "SELECT TG.genre, T.primaryTitle as name FROM TITLE_GENRES AS TG INNER JOIN TITLES as T ON T.ID = TG.TITLE_ID INNER JOIN PROFESSIONS AS P ON P.TITLE_ID = TG.TITLE_ID INNER JOIN PEOPLE AS P2 ON P2.ID = P.PERSON_ID WHERE P2.FIRSTNAME=?1 AND P2.LASTNAME=?2 group by TG.genre", nativeQuery = true)
    List<TitlesForAPersonOrganizedByGenresDto> getAllTitlesForAPersonOrganizedByGenres(String firstName, String lastName);

}