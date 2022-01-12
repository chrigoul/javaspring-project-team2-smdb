package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Title;

import java.util.List;

public interface ReportService {

    List<Title> getTop3ByOrderBySmdbRatingDesc();

    List<Title> getPersonParticipationInTitleByFullName(String firstName, String lastName);

    List<Title> getPersonParticipationInTitleByFullNameAndProfessions(String firstName, String lastName, String contributionRole);

    List<Title> findAllByGenresContains(Genre genre);

    List<Title> getAllByGenresContaining(Genre genre);

//
//    Long countTvShowsByGenres(Genre genre);
//
//    Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear);
}
