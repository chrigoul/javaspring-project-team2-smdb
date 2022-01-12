package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.TvShow;

import java.util.List;

public interface TvShowService extends BaseService<TvShow, Long> {
    TvShow findTvShowByPrimaryTitle(String primaryTitle);
    Long countTvShowsByGenres(Genre genre);
    Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear);

    List<Person> getPeopleParticipatingInTitle(String primaryTitle);
}
