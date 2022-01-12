package com.javaspring.team2.project.smdb.controller;

import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.service.BaseService;
import com.javaspring.team2.project.smdb.service.MovieService;
import com.javaspring.team2.project.smdb.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController extends AbstractController<Movie>{
    final private MovieService movieService;

    @Override
    protected BaseService<Movie, Long> getBaseService() {
        return movieService;
    }

    @GetMapping(path = "person/movie", params = {"primaryTitle"})
    public ResponseEntity<ApiResponse<List<Person>>> getAllContentByContributorByFullName(@RequestParam("primaryTitle") String primaryTitle )
    {
        return ResponseEntity.ok(ApiResponse.<List<Person>>builder()
                .data(movieService.getPeopleParticipatingInTitle(primaryTitle))
                .build());
    }

    @GetMapping(path = "age", params = {"age"})
    public ResponseEntity<ApiResponse<List<Movie>>> getAllMoviesWithAgeRatingGreaterThan(@RequestParam("age") Integer age )
    {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder()
                .data(movieService.getMovieByAgeRatingGreaterThan(age))
                .build());
    }

    @GetMapping(path= "releaseYear", params = {"year"})
    public ResponseEntity<ApiResponse<List<Movie>>> getAllMoviesReleasedInYear(@RequestParam("year") Integer year )
    {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder()
                .data(movieService.getMovieByReleaseYearEquals(year))
                .build());
    }

    @GetMapping(path = "years", params = {"startYear", "endYear"})
    public ResponseEntity<ApiResponse<List<Movie>>> getAllMoviesBetweenTwoYears(@RequestParam("startYear") Integer startYear,
                                                                                @RequestParam("endYear") Integer endYear){
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder()
                .data(movieService.getMovieByReleaseYearGreaterThanAndReleaseYearLessThan(startYear, endYear))
                .build());
    }
}
