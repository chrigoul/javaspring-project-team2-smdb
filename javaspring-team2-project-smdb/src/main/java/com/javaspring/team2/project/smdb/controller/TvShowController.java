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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping(params = {"year"})
    public ResponseEntity<ApiResponse<List<TvShow>>> getAllTvShowsReleasedInYear(@RequestParam("year") Integer year )
    {
        return ResponseEntity.ok(ApiResponse.<List<TvShow>>builder()
                .data(tvShowService.getTvShowByReleaseYearEquals(year))
                .build());
    }

    @GetMapping(params = {"rating"})
    public ResponseEntity<ApiResponse<List<TvShow>>> getAllTvShowsBetweenTwoYears(@RequestParam("rating") Double smdbRating)
    {
        return ResponseEntity.ok(ApiResponse.<List<TvShow>>builder()
                .data(tvShowService.getTvShowBySmdbRatingGreaterThanEqual(smdbRating))
                .build());
    }

    @GetMapping(params = {"episodes"})
    public ResponseEntity<ApiResponse<List<TvShow>>> getAllTvShowsWithEpisodesMoreThan(@RequestParam("episodes") Integer episodes )
    {
        return ResponseEntity.ok(ApiResponse.<List<TvShow>>builder()
                .data(tvShowService.getTvShowByNumberOfEpisodesGreaterThan(episodes))
                .build());
    }
    @GetMapping(params = {"seasons"})
    public ResponseEntity<ApiResponse<List<TvShow>>> getAllTvShowsWithSeasonsMoreThan(@RequestParam("seasons") Integer season )
    {
        return ResponseEntity.ok(ApiResponse.<List<TvShow>>builder()
                .data(tvShowService.getTvShowByNumberOfSeasonsGreaterThan(season))
                .build());
    }

    @RequestMapping(headers = "action=export")
    public void export(HttpServletResponse servletResponse) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"tvShows" + "_" + currentDateTime + ".csv\"");
        tvShowService.csvTvShowsExport(servletResponse.getWriter());
    }
}
