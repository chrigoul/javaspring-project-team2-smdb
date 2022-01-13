package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerGenreDto;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerReleaseYearGenreDto;
import com.javaspring.team2.project.smdb.transfer.TitlesForAPersonOrganizedByGenresDTO;

import java.util.List;

public interface ReportService {

    List<Title> getTop3ByOrderBySmdbRatingDesc();

    List<Title> getPersonParticipationInTitleByFullName(String firstName, String lastName);

   List<Title> getPersonParticipationInTitleByFullNameAndProfessions(String firstName, String lastName, ContributionRole contributionRole);

    List<Title> getAllByGenresContaining(Genre genre);

    List<NumberOfShowsPerGenreDto> getNumberOfShowsPerGenre();

    List<NumberOfShowsPerReleaseYearGenreDto> getNumberOfShowsPerReleaseYearPerGenre(Integer year);

//    List<Title> getAllTitlesForAPersonOrganizedByGenres(String firstName, String lastName);



//
//    Long countTvShowsByGenres(Genre genre);
//
//    Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear);
}
