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
}
