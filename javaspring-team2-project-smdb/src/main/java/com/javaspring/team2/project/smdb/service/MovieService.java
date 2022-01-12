package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;

import java.util.List;

public interface MovieService extends BaseService<Movie, Long> {
    Movie findMovieByPrimaryTitle(String primaryTitle);
//    void addActorsToMovie(Movie movie, Person person, String role);

    List<Person> getPeopleParticipatingInTitle(String primaryTitle);

    List<Movie> getMovieByAgeRatingGreaterThan(Integer age);

    List<Movie> getMovieByReleaseYearEquals(Integer year);

    List<Movie> getMovieByReleaseYearGreaterThanAndReleaseYearLessThan(Integer startYear, Integer endYear);

}
