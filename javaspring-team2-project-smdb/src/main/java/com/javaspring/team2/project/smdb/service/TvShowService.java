package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.TvShow;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface TvShowService extends BaseService<TvShow, Long> {
    TvShow findTvShowByPrimaryTitle(String primaryTitle);
    Long countTvShowsByGenres(Genre genre);
    Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear);

    List<Person> getPeopleParticipatingInTitle(String primaryTitle);

    List<TvShow> getTvShowByNumberOfEpisodesGreaterThan(Integer num);

    List<TvShow> getTvShowByNumberOfSeasonsGreaterThan(Integer num);

    List<TvShow> getTvShowByReleaseYearEquals(Integer year);

    List<TvShow> getTvShowBySmdbRatingGreaterThanEqual(Double smdbRating);

    void csvTvShowsExport(Writer writer) throws IOException;

}
