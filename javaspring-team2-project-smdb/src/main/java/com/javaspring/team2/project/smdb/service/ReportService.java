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

    //    1st Report: Top 3 Titles containing both Movies and TvShows
    List<Title> getTop3ByOrderBySmdbRatingDesc();

    //    2nd Report: All Titles a Person has participated in regardless of his/her profession
    List<Title> getPersonParticipationInTitleByFullName(String firstName, String lastName);

    //    3rd Report: All Titles a Person has participated per his/her profession
    List<Title> getPersonParticipationInTitleByFullNameAndProfessions(String firstName, String lastName, ContributionRole contributionRole);

    //    4th Report: All TvShows per Genre
    List<Title> getAllByGenresContaining(Genre genre);

    //    5th Report: Number of TvShows per a given Genre
    List<NumberOfShowsPerGenreDto> getNumberOfShowsPerGenre();

    //    6th Report: Number of TvShows per Genre per Release Year
    List<NumberOfShowsPerReleaseYearGenreDto> getNumberOfShowsPerReleaseYearPerGenre(Integer year);

//    List<Title> getAllTitlesForAPersonOrganizedByGenres(String firstName, String lastName);



//
//    Long countTvShowsByGenres(Genre genre);
//
//    Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear);
}
