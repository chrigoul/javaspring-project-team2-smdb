package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

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
    public List<Person> getPeopleParticipatingInTitle(String primaryTitle) {
        return movieRepository.getPeopleParticipatingInTitle(primaryTitle);
    }

    @Override
    public List<Movie> getMovieByAgeRatingGreaterThan(Integer age) {
        return movieRepository.getMovieByAgeRatingGreaterThan(age);
    }

    @Override
    public List<Movie> getMovieByReleaseYearEquals(Integer year) {
        return movieRepository.getMovieByReleaseYearEquals(year);
    }

    @Override
    public List<Movie> getMovieBySmdbRatingGreaterThanEqual(Double smdbRating){
        return movieRepository.getMovieBySmdbRatingGreaterThanEqual(smdbRating);
    }

    @Override
    public void csvMoviesExport(Writer writer) throws IOException {
        List<Movie> movies = movieRepository.findAll();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord("movie_id", "PrimaryTitle", "Duration", "SmdbRating", "ReleaseYear", "AgeRating", "StoryLine");
        for (Movie m : movies) {
            csvPrinter.printRecord(m.getId(), m.getPrimaryTitle(), m.getDurationInMinutes(), m.getSmdbRating(),
                    m.getReleaseYear(), m.getAgeRating(), m.getStoryLine());
        }
        logger.info("[Movies] table exported successfully. Number of rows {}", movies.size());
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
