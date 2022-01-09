package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    JpaRepository<Movie, Long> getRepository() {
        return movieRepository;
    }

}
