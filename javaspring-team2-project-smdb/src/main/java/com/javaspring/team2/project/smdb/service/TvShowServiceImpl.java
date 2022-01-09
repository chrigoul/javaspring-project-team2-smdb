package com.javaspring.team2.project.smdb.service;

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

}
