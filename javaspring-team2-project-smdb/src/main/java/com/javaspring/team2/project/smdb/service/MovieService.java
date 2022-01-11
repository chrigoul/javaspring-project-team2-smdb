package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;

public interface MovieService extends BaseService<Movie, Long> {
    Movie findMovieByPrimaryTitle(String primaryTitle);
//    void addActorsToMovie(Movie movie, Person person, String role);

    Long countMovieByGenresContaining(Genre genre);


}
