package com.javaspring.team2.project.smdb.controller;

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
}
