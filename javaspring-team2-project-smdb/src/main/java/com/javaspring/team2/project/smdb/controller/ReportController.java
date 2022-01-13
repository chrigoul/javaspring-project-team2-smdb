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
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerReleaseYearGenreDto;
import com.javaspring.team2.project.smdb.transfer.TitlesForAPersonOrganizedByGenresDTO;
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


    //    1st Report: Top 3 Titles containing both Movies and TvShows
    @GetMapping(path = "report1", headers = "action=getTop3ByOrderBySmdbRatingDesc")
    public ResponseEntity<ApiResponse<List<Title>>> getTop3ByOrderBySmdbRatingDesc()
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getTop3ByOrderBySmdbRatingDesc())
                .build());
    }

    //    2nd Report: All Titles a Person has participated in regardless of his/her profession
    @GetMapping(path = "report2", params = {"firstname", "lastname"}, headers = "action=getAllContentByContributorByFullName")
    public ResponseEntity<ApiResponse<List<Title>>> getAllContentByContributorByFullName(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName)
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getPersonParticipationInTitleByFullName(firstName, lastName))
                .build());
    }

    //    3rd Report: All Titles a Person has participated per his/her profession
    @GetMapping(path = "report3", params = {"firstname", "lastname", "profession"}, headers = "action=getAllContentByContributorByFullNameAndContributionRole")
    public ResponseEntity<ApiResponse<List<Title>>> getAllContentByContributorByFullNameAndContributionRole(@RequestParam("firstname") String firstName,
                                                                                                            @RequestParam("lastname") String lastName,
                                                                                                            @RequestParam("profession") String contributionRole)
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getPersonParticipationInTitleByFullNameAndProfessions(firstName, lastName, ContributionRole.valueOf(contributionRole)))
                .build());
    }

    //    4th Report: All TvShows per a given Genre
    @GetMapping(path = "report4", params = {"genre"}, headers = "action=getAllByGenresContaining")
    public ResponseEntity<ApiResponse<List<Title>>> getAllByGenresContaining(@RequestParam("genre") Genre genre)
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getAllByGenresContaining(genre))
                .build());
    }

    //    5th Report: Number of TvShows per Genre
    @GetMapping(path = "report5", headers = "action=getNumberOfShowsPerGenre" )
    public ResponseEntity<ApiResponse<List<NumberOfShowsPerGenreDto>>> getNumberOfShowsPerGenre()
    {
        return ResponseEntity.ok(ApiResponse.<List<NumberOfShowsPerGenreDto>>builder()
                .data(reportService.getNumberOfShowsPerGenre())
                .build());
    }

    //    6th Report: Number of TvShows per Genre per Release Year
    @GetMapping(path= "report6", headers = "action=getNumberOfShowsPerReleaseYearPerGenre")
    public ResponseEntity<ApiResponse<List<NumberOfShowsPerReleaseYearGenreDto>>> getNumberOfShowsPerYearPerGenre()
    {
        return ResponseEntity.ok(ApiResponse.<List<NumberOfShowsPerReleaseYearGenreDto>>builder()
                .data(reportService.getNumberOfShowsPerReleaseYearPerGenre(2022))
                .build());
    }


/*    @GetMapping(path = "titles/person/byGenres", params = {"firstname", "lastname"})
    public ResponseEntity<ApiResponse<List<Title>>> getAllTitlesForAPersonOrganizedByGenres(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName)
    {
        return ResponseEntity.ok(ApiResponse.<List<Title>>builder()
                .data(reportService.getAllTitlesForAPersonOrganizedByGenres(firstName, lastName))
                .build());
    }*/



}
