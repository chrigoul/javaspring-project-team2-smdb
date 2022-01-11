package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Movie;
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
    Long countTvShowsByGenres(Genre genre);
    Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear);




}
