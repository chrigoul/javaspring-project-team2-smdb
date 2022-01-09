package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.TvShow;

public interface TvShowService extends BaseService<TvShow, Long> {
    TvShow findTvShowByPrimaryTitle(String primaryTitle);
}
