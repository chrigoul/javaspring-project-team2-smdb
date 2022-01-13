package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.TvShow;
import com.javaspring.team2.project.smdb.repository.TvShowRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

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

    @Override
    public List<Person> getPeopleParticipatingInTitle(String primaryTitle) {
        return tvShowRepository.getPeopleParticipatingInTitle(primaryTitle);
    }

    @Override
    public List<TvShow> getTvShowByNumberOfEpisodesGreaterThan(Integer num) {
        return tvShowRepository.getTvShowByNumberOfEpisodesGreaterThan(num);
    }

    @Override
    public List<TvShow> getTvShowByNumberOfSeasonsGreaterThan(Integer num) {
        return tvShowRepository.getTvShowByNumberOfSeasonsGreaterThan(num);
    }

    @Override
    public List<TvShow> getTvShowByReleaseYearEquals(Integer year) {
        return tvShowRepository.getTvShowByReleaseYearEquals(year);
    }

    @Override
    public List<TvShow> getTvShowBySmdbRatingGreaterThanEqual(Double smdbRating){
        return tvShowRepository.getTvShowBySmdbRatingGreaterThanEqual(smdbRating);
    }

    @Override
    public void csvTvShowsExport(Writer writer) throws IOException {
        List<TvShow> tvShows = tvShowRepository.findAll();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord("movie_id", "PrimaryTitle", "Duration", "SmdbRating", "ReleaseYear", "EndYear", "NumberOfSeasons", "NumberOfEpisodes", "StoryLine");
        for (TvShow tv : tvShows) {
            csvPrinter.printRecord(tv.getId(), tv.getPrimaryTitle(), tv.getDurationInMinutes(), tv.getSmdbRating(), tv.getReleaseYear(),
                    tv.getEndYear(), tv.getNumberOfSeasons(), tv.getNumberOfEpisodes(), tv.getStoryLine());
        }
        logger.info("[TvShows] table exported successfully. Number of rows {}", tvShows.size());
    }

}
