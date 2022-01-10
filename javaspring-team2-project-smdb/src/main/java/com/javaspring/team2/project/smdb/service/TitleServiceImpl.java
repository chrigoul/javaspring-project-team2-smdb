package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl extends BaseServiceImpl<Title> implements TitleService {
    private final TitleRepository titleRepository;

    @Override
    JpaRepository<Title, Long> getRepository() {
        return titleRepository;
    }

    @Override
   public  Title findTitleByPrimaryTitle(String primaryTitle){
        return titleRepository.findTitleByPrimaryTitle(primaryTitle);
    }

//    @Override
//    public List<Title> findThreeTopRatedTitles(){
//        return titleRepository.findThreeTopRatedTitles();
//    }

    @Override
    public List<Title> findTop3ByOrderBySmdbRatingDesc(){
        return titleRepository.findTop3ByOrderBySmdbRatingDesc();
    }

//    @Override
//    public List<Title> findParticipationOfAPerson(){
//        return titleRepository.findParticipationOfAPerson();
//    }
    @Override
    public List<Title> findAllByGenresContains(Genre genre){
        return titleRepository.findAllByGenresContains(genre);
    }

    @Override
    public Boolean existsByPrimaryTitle(String primaryTitle){ return titleRepository.existsByPrimaryTitle(primaryTitle); }
//    @Override
//    public Title findLazy(Long id){
//        return titleRepository.findLazy(id);
//    }





}
