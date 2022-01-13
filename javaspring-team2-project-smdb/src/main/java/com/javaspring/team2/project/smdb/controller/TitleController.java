package com.javaspring.team2.project.smdb.controller;

import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.domain.TvShow;
import com.javaspring.team2.project.smdb.service.BaseService;
import com.javaspring.team2.project.smdb.service.PersonService;
import com.javaspring.team2.project.smdb.service.TitleService;
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
@RequestMapping("/titles")
public class TitleController extends AbstractController<Title> {

    final private TitleService titleService;

    @Override
    protected BaseService<Title, Long> getBaseService() {
        return titleService;
    }

    @GetMapping(path = "person/title", params = {"primaryTitle"})
    public ResponseEntity<ApiResponse<List<Person>>> getAllContentByContributorByFullName(@RequestParam("primaryTitle") String primaryTitle )
    {
        return ResponseEntity.ok(ApiResponse.<List<Person>>builder()
                .data(titleService.getPeopleParticipatingInTitle(primaryTitle))
                .build());
    }

    @RequestMapping(headers = "action=export")
    public void export(HttpServletResponse servletResponse) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"titles" + "_" + currentDateTime + ".csv\"");
        titleService.csvTitlesExport(servletResponse.getWriter());
    }
}

