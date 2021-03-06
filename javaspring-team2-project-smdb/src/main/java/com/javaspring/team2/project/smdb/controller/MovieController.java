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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Person>>> getAllContributorsForMovie(@RequestParam("title") String title )
    {
        return ResponseEntity.ok(ApiResponse.<List<Person>>builder()
                .data(movieService.getPeopleParticipatingInTitle(title))
                .build());
    }


    @GetMapping(params = {"age"})
    public ResponseEntity<ApiResponse<List<Movie>>> getAllMoviesWithAgeRatingGreaterThan(@RequestParam("age") Integer age )
    {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder()
                .data(movieService.getMovieByAgeRatingGreaterThan(age))
                .build());
    }

    @GetMapping(params = {"year"})
    public ResponseEntity<ApiResponse<List<Movie>>> getAllMoviesReleasedInYear(@RequestParam("year") Integer year )
    {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder()
                .data(movieService.getMovieByReleaseYearEquals(year))
                .build());
    }

    @GetMapping(params = {"rating"})
    public ResponseEntity<ApiResponse<List<Movie>>> getMovieBySmdbRatingGreaterThanEqual(@RequestParam("rating") Double smdbRating )
    {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder()
                .data(movieService.getMovieBySmdbRatingGreaterThanEqual(smdbRating))
                .build());
    }

    @RequestMapping(headers = "action=export")
    public void exportMovies(HttpServletResponse servletResponse) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"movies" + "_" + currentDateTime + ".csv\"");
        movieService.csvMoviesExport(servletResponse.getWriter());
    }
}
