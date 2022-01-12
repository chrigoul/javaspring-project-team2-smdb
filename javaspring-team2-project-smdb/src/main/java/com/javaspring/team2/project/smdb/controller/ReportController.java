package com.javaspring.team2.project.smdb.controller;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.service.BaseService;
import com.javaspring.team2.project.smdb.service.PersonService;
import com.javaspring.team2.project.smdb.service.ReportService;
import com.javaspring.team2.project.smdb.transfer.ApiResponse;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerGenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    final private ReportService reportService;

    @GetMapping(path = "titles/top3")
    public ResponseEntity<ApiResponse<List<Title>>> getTop3ByOrderBySmdbRatingDesc()
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getTop3ByOrderBySmdbRatingDesc())
                .build());
    }

    @GetMapping(path = "titles/person", params = {"firstname", "lastname"})
    public ResponseEntity<ApiResponse<List<Title>>> getAllContentByContributorByFullName(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName)
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getPersonParticipationInTitleByFullName(firstName, lastName))
                .build());
    }



    @GetMapping(path = "titles", params = {"genre"})
    public ResponseEntity<ApiResponse<List<Title>>> getAllByGenresContaining(@RequestParam("genre") Genre genre)
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getAllByGenresContaining(genre))
                .build());
    }

   @GetMapping(path = "titles/person", params = {"firstname", "lastname", "profession"})
    public ResponseEntity<ApiResponse<List<Title>>> getAllContentByContributorByFullNameAndContributionRole(@RequestParam("firstname") String firstName,
                                                                                                            @RequestParam("lastname") String lastName,
                                                                                                            @RequestParam("profession") String contributionRole)
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getPersonParticipationInTitleByFullNameAndProfessions(firstName, lastName, ContributionRole.valueOf(contributionRole)))
                .build());
    }

    @GetMapping(path = "tvShows/noPerGenre")
    public ResponseEntity<ApiResponse<List<NumberOfShowsPerGenreDto>>> getNumberOfShowsPerGenre()
    {
        return ResponseEntity.ok(ApiResponse.<List<NumberOfShowsPerGenreDto>>builder()
                .data(reportService.getNumberOfShowsPerGenre())
                .build());
    }

    @GetMapping(path = "titles/person/byGenres", params = {"firstname", "lastname"})
    public ResponseEntity<ApiResponse<List<Title>>> getAllTitlesForAPersonOrganizedByGenres(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName)
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getAllTitlesForAPersonOrganizedByGenres(firstName, lastName))
                .build());
    }



}
