package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Title;
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
    List<Title> getPersonParticipationInTitleByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    //@Query(value = "select distinct t from Title t join t.professions f on t.id join f.titleContributionRole ft join f.person where p.id = (select distinct p.id from Person p where p.firstName = :firstName and p.lastName = :lastName and ft= :contributionRole)")
    @Query(value = "select distinct t from Title t join t.professions f join f.person p join f.titleContributionRole ft where p.id = (select distinct p.id from Person p where p.firstName = :firstName and p.lastName = :lastName and ft = :contributionRole)")
    List<Title> getPersonParticipationInTitleByFullNameAndProfessions(@Param("firstName") String firstName, @Param("lastName") String lastName, ContributionRole contributionRole);

  //  @Query("select distinct t from Title t join t.professions f join f.person p join f.titleContributionRole ft where p.id = (select distinct p.id from Person p where p.firstName = :firstName and p.lastName = :lastName) and (f.titleContributionRole in (:profession))")
    //    4th Report: All TvShows per a given Genre
    List<Title> findAllByGenresContains(Genre genre);

    List<Title> getAllByGenresContaining(Genre genre);


//    //    5th Report: Number of TvShows per a given Genre
//    Long countTvShowsByGenres(Genre genre);
//
//    //    6th: Report: Number of TvShows per a Genre per Release Year
//    Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear);

}
