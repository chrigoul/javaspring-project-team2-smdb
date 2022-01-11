//package com.javaspring.team2.project.smdb.service;
//
//import com.javaspring.team2.project.smdb.domain.Genre;
//import com.javaspring.team2.project.smdb.domain.Title;
//import com.javaspring.team2.project.smdb.repository.ReportRepository;
//import com.javaspring.team2.project.smdb.repository.TitleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ReportServiceImpl extends BaseServiceImpl<Title> implements ReportService {
//    private final ReportRepository reportRepository;
//
//    @Override
//    JpaRepository<Title, Long> getRepository() {
//        return reportRepository;
//    }
//
//    @Override
//    public List<Title> findTop3ByOrderBySmdbRatingDesc(){
//        return reportRepository.findTop3ByOrderBySmdbRatingDesc();
//    }
//
//
//    @Override
//    public List<Title> findAllByGenresContains(Genre genre){
//        return reportRepository.findAllByGenresContains(genre);
//    }
//
//
//    @Override
//    public List<Title> findPersonParticipationInTitleByFullName(String firstName, String lastName){
//        return reportRepository.findPersonParticipationInTitleByFullName(firstName, lastName);
//    }
//
//    @Override
//    public Long countTvShowsByGenres(Genre genre){
//        return reportRepository.countTvShowsByGenres(genre);
//    }
//
//    @Override
//    public Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear){
//        return reportRepository.countTvShowByGenresAndReleaseYear(genre, releaseYear);
//    }
//
//}
