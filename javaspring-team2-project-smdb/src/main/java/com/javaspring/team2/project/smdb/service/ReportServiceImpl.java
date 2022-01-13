package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.repository.ReportRepository;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerGenreDto;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerReleaseYearGenreDto;
import com.javaspring.team2.project.smdb.transfer.TitlesForAPersonOrganizedByGenresDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    //    1st Report: Top 3 Titles containing both Movies and TvShows
    @Override
    public List<Title> getTop3ByOrderBySmdbRatingDesc(){
        return reportRepository.getTop3ByOrderBySmdbRatingDesc();
    }

    //    2nd Report: All Titles a Person has participated in regardless of his/her profession
    @Override
    public List<Title> getPersonParticipationInTitleByFullName(String firstName, String lastName){
        return reportRepository.getPersonParticipationInTitleByFullName(firstName, lastName);
    }

    //    3rd Report: All Titles a Person has participated per his/her profession
    @Override
    public List<Title> getPersonParticipationInTitleByFullNameAndProfessions(String firstName, String lastName, ContributionRole contributionRole) {
        return reportRepository.getPersonParticipationInTitleByFullNameAndProfessions(firstName, lastName, contributionRole);
    }

    //    4th Report: All TvShows per a given Genre
    @Override
    public List<Title> getAllByGenresContaining(Genre genre){
        return reportRepository.getAllByGenresContaining(genre);
    }

    //    5th Report: Number of TvShows per Genre
    @Override
    public List<NumberOfShowsPerGenreDto> getNumberOfShowsPerGenre(){
        return reportRepository.getNumberOfShowsPerGenre();
    }

    //    6th Report: Number of TvShows per Genre per Release Year
    @Override
    public List<NumberOfShowsPerReleaseYearGenreDto> getNumberOfShowsPerReleaseYearPerGenre(Integer year) {
        return reportRepository.getNumberOfShowsPerReleaseYearPerGenre(year);
    }

    //    7th Report: All Titles associated with a given individual organized per genre.
    @Override
    public List<TitlesForAPersonOrganizedByGenresDto> getAllTitlesForAPersonOrganizedByGenres(String firstName, String lastName){
        return reportRepository.getAllTitlesForAPersonOrganizedByGenres(firstName, lastName);
    }

}
