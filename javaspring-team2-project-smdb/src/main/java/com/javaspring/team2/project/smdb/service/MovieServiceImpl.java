package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
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

    @Override
    public Movie findMovieByPrimaryTitle(String primaryTitle){
        return movieRepository.findMovieByPrimaryTitle(primaryTitle);
    }


    @Override
    public Long countMovieByGenresContaining(Genre genre){
        return movieRepository.countMovieByGenresContaining(genre);
    }

//    @Override
//    public void addActorsToMovie(Movie movie, Person person, String role){
//        movie.getActors().add(newActor(movie, person, role));
//    }
//
//    private Actor newActor(Movie movie, Person person, String role) {
//        return Actor.builder().
//    }


}
