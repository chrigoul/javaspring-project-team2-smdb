package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.TvShow;

public interface TvShowService extends BaseService<TvShow, Long> {
    TvShow findTvShowByPrimaryTitle(String primaryTitle);
    Integer countTvShowsByGenresContains(Genre genre);
//    Integer countTvShowsByGenresContainsAndReleaseYearContains(Genre genre, Integer releaseYear);

//    Integer countTvShowsByGenresAndReleaseYearContains(Genre genre, Integer releaseYear);

}
