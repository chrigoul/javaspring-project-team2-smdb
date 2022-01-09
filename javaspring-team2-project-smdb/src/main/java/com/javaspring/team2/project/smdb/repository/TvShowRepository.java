package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {
    TvShow findTvShowByPrimaryTitle(String primaryTitle);
}
