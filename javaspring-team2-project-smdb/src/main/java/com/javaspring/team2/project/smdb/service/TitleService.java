package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Title;

import java.util.List;

public interface TitleService extends BaseService<Title, Long> {

    Title findTitleByPrimaryTitle(String primaryTitle);
//
//    List<Title> findThreeTopRatedTitles();

    List<Title> findTop3ByOrderBySmdbRatingDesc();

    List<Title> findAllByGenresContains(Genre genre);

//    List<Title> findParticipationOfAPerson();

//    void addActorsToTitle(Title title, Person person, String role);
//
//    Title findLazy(Long id);



}
