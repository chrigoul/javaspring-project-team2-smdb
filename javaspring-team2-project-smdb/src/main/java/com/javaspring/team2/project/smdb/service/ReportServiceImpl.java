package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.repository.ReportRepository;
import com.javaspring.team2.project.smdb.transfer.NumberOfShowsPerGenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;


    @Override
    public List<Title> getTop3ByOrderBySmdbRatingDesc(){
        return reportRepository.getTop3ByOrderBySmdbRatingDesc();
    }

    @Override
    public List<Title> getPersonParticipationInTitleByFullName(String firstName, String lastName){
        return reportRepository.getPersonParticipationInTitleByFullName(firstName, lastName);
    }

    @Override
    public List<Title> getPersonParticipationInTitleByFullNameAndProfessions(String firstName, String lastName, ContributionRole contributionRole) {
        return reportRepository.getPersonParticipationInTitleByFullNameAndProfessions(firstName, lastName, contributionRole);
    }


    @Override
    public List<Title> getAllByGenresContaining(Genre genre){
        return reportRepository.getAllByGenresContaining(genre);
    }

    @Override
    public List<NumberOfShowsPerGenreDto> getNumberOfShowsPerGenre(){
        return reportRepository.getNumberOfShowsPerGenre();
    }


    @Override
    public List<Title> getAllTitlesForAPersonOrganizedByGenres(String firstName, String lastName){
        return reportRepository.getAllTitlesForAPersonOrganizedByGenres(firstName, lastName);
    }


}
