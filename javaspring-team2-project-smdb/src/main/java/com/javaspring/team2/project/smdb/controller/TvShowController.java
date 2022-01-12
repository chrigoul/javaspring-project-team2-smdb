package com.javaspring.team2.project.smdb.controller;

import com.javaspring.team2.project.smdb.domain.TvShow;
import com.javaspring.team2.project.smdb.service.BaseService;
import com.javaspring.team2.project.smdb.service.TvShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tvShows")
public class TvShowController extends AbstractController<TvShow>{
    final private TvShowService tvShowService;

    @Override
    protected BaseService<TvShow, Long> getBaseService() {
        return tvShowService;
    }

}
