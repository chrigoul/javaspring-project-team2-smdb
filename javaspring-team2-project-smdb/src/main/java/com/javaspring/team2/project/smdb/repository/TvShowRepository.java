package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {
    TvShow findTvShowByPrimaryTitle(String primaryTitle);

    //    5th Report: Number of TvShows per a given Genre
    Long countTvShowsByGenres(Genre genre);

    //    6th: Report: Number of TvShows per a Genre per Release Year
    Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear);

    @Query(value = "select distinct p from Person p join p.professions f join f.title t where t.id = (select distinct t.id from TvShow where t.primaryTitle = :primaryTitle)")
    List<Person> getPeopleParticipatingInTitle(String primaryTitle);

    List<TvShow> getTvShowByNumberOfEpisodesGreaterThan(Integer num);

    List<TvShow> getTvShowByNumberOfSeasonsGreaterThan(Integer num);

    List<TvShow> getTvShowByReleaseYearEquals(Integer year);

    List<TvShow> getTvShowBySmdbRatingGreaterThanEqual(Double smdbRating);
}
