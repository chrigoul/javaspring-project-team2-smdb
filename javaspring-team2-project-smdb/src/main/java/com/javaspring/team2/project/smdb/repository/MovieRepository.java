package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieByPrimaryTitle(String primaryTitle);

    List<Movie> getMovieBySmdbRating(Double smdbRating);

    @Query(value = "select distinct p from Person p join p.professions f join f.title t where t.id = (select distinct t.id from Title where t.primaryTitle = :primaryTitle)")
    List<Person> getPeopleParticipatingInTitle(String primaryTitle);

    List<Movie> getMovieByAgeRatingGreaterThan(Integer age);

    List<Movie> getMovieByReleaseYearEquals(Integer year);

    List<Movie> getMovieByReleaseYearGreaterThanAndReleaseYearLessThan(Integer startYear, Integer endYear);
}
