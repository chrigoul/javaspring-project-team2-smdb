//package com.javaspring.team2.project.smdb.service;
//
//import com.javaspring.team2.project.smdb.domain.Genre;
//import com.javaspring.team2.project.smdb.domain.Title;
//
//import java.util.List;
//
//public interface ReportService extends BaseService<Title, Long> {
//
//    List<Title> findTop3ByOrderBySmdbRatingDesc();
//
//    List<Title> findPersonParticipationInTitleByFullName(String firstName, String lastName);
//
//    List<Title> findAllByGenresContains(Genre genre);
//
//    Long countTvShowsByGenres(Genre genre);
//
//    Long countTvShowByGenresAndReleaseYear(Genre genre, Integer releaseYear);
//}
