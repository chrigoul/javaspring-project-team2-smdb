package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieByPrimaryTitle(String primaryTitle);
}
