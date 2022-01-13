package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

public interface TitleService extends BaseService<Title, Long> {

    Title findTitleByPrimaryTitle(String primaryTitle);

    Boolean existsByPrimaryTitle(String primaryTitle);

    List<Person> getPeopleParticipatingInTitle(String primaryTitle);

    void csvTitlesExport(Writer writer) throws IOException;

}
