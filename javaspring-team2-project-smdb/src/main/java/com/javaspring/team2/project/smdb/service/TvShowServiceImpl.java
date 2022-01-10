package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.TvShow;
import com.javaspring.team2.project.smdb.repository.TvShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TvShowServiceImpl extends BaseServiceImpl<TvShow> implements TvShowService {
    private final TvShowRepository tvShowRepository;

    @Override
    JpaRepository<TvShow, Long> getRepository() {
        return tvShowRepository;
    }

    @Override
    public TvShow findTvShowByPrimaryTitle(String primaryTitle){
        return tvShowRepository.findTvShowByPrimaryTitle(primaryTitle);
    }

    public Integer countTvShowsByGenresContains(Genre genre){
        return tvShowRepository.countTvShowsByGenresContains(genre);
    }
//
//    @Override
//    public Integer countTvShowsByGenresContainsAndReleaseYearContains(Genre genre, Integer releaseYear){
//        return tvShowRepository.countTvShowsByGenresContainsAndReleaseYearContains(genre, releaseYear);
//    }
//
//    @Override
//    public Integer countTvShowsByGenresAndReleaseYearContains(Genre genre, Integer releaseYear){
//        return tvShowRepository.countTvShowsByGenresAndReleaseYearContains(genre,releaseYear);
//    }



}
