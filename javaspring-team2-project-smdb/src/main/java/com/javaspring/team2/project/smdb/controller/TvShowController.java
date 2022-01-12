package com.javaspring.team2.project.smdb.controller;

import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.TvShow;
import com.javaspring.team2.project.smdb.service.BaseService;
import com.javaspring.team2.project.smdb.service.TvShowService;
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
@RequestMapping("/tvShows")
public class TvShowController extends AbstractController<TvShow>{
    final private TvShowService tvShowService;

    @Override
    protected BaseService<TvShow, Long> getBaseService() {
        return tvShowService;
    }

    @GetMapping(path = "person/tvShow", params = {"primaryTitle"})
    public ResponseEntity<ApiResponse<List<Person>>> getAllContentByContributorByFullName(@RequestParam("primaryTitle") String primaryTitle )
    {
        return ResponseEntity.ok(ApiResponse.<List<Person>>builder()
                .data(tvShowService.getPeopleParticipatingInTitle(primaryTitle))
                .build());
    }

    @GetMapping(path= "releaseYear", params = {"year"})
    public ResponseEntity<ApiResponse<List<TvShow>>> getAllTvShowsReleasedInYear(@RequestParam("year") Integer year )
    {
        return ResponseEntity.ok(ApiResponse.<List<TvShow>>builder()
                .data(tvShowService.getTvShowByReleaseYearEquals(year))
                .build());
    }

    @GetMapping(path = "years", params = {"startYear", "endYear"})
    public ResponseEntity<ApiResponse<List<TvShow>>> getAllTvShowsBetweenTwoYears(@RequestParam("startYear") Integer startYear,
                                                                                @RequestParam("endYear") Integer endYear){
        return ResponseEntity.ok(ApiResponse.<List<TvShow>>builder()
                .data(tvShowService.getTvShowByReleaseYearGreaterThanEqualAndReleaseYearLessThanEqual(startYear, endYear))
                .build());
    }

    @GetMapping(path= "episodes", params = {"episodes"})
    public ResponseEntity<ApiResponse<List<TvShow>>> getAllTvShowsWithEpisodesMOreThan(@RequestParam("episodes") Integer episodes )
    {
        return ResponseEntity.ok(ApiResponse.<List<TvShow>>builder()
                .data(tvShowService.getTvShowByNumberOfEpisodesGreaterThan(episodes))
                .build());
    }
    @GetMapping(path= "seasons", params = {"seasons"})
    public ResponseEntity<ApiResponse<List<TvShow>>> getAllTvShowsWithSeasonsMoreThan(@RequestParam("seasons") Integer season )
    {
        return ResponseEntity.ok(ApiResponse.<List<TvShow>>builder()
                .data(tvShowService.getTvShowByNumberOfSeasonsGreaterThan(season))
                .build());
    }
}
