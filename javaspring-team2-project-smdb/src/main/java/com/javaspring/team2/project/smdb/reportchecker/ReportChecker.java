package com.javaspring.team2.project.smdb.reportchecker;

import com.javaspring.team2.project.smdb.base.AbstractLogComponent;
import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.repository.MovieRepository;
import com.javaspring.team2.project.smdb.repository.TvShowRepository;
import com.javaspring.team2.project.smdb.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Profile("report-checker")
@RequiredArgsConstructor
public class ReportChecker extends AbstractLogComponent implements CommandLineRunner {
    private final PersonService personService;
    private final MovieService movieService;
    private final TvShowService tvShowService;
    private final TitleService titleService;

    public void run(String... args) throws Exception {


        List<Title> top3 = titleService.findTop3ByOrderBySmdbRatingDesc();
        logger.info("The top three rating titles are:");
        logger.info("1. {}      SMDB Rating: {}", top3.get(0).getPrimaryTitle(), top3.get(0).getSmdbRating());
        logger.info("2. {}      SMDB Rating: {}", top3.get(1).getPrimaryTitle(), top3.get(1).getSmdbRating());
        logger.info("3. {}      SMDB Rating: {}", top3.get(2).getPrimaryTitle(), top3.get(2).getSmdbRating());

        List<Title> allByGenre = new ArrayList<>();
        allByGenre = titleService.findAllByGenresContains(Genre.ACTION);

        allByGenre.forEach(i-> logger.info("Action titles are: {}", i.getPrimaryTitle()));

        Long numberOfAdventureTvShows = tvShowService.countTvShowsByGenres(Genre.ADVENTURE);
        logger.info("The number of adventure tv shows is {}", numberOfAdventureTvShows);

        Long numberOfAdventureTvShowsFor2017 = tvShowService.countTvShowByGenresAndReleaseYear(Genre.ADVENTURE, 2017);
        logger.info("The number of adventure tv shows for 2017 is {}", numberOfAdventureTvShowsFor2017);

        logger.info("Person is {}", personService.findPersonByFirstNameAndLastName("Cillian", "Murphy"));

        logger.info("Title is {}", titleService.findTitleByPrimaryTitle("Money Heist").getClass());

        logger.info("Titles that {} has participated are: {}", personService.findPersonByFirstNameAndLastName("Cillian", "Murphy").getLastName(), titleService.findPersonParticipationInTitleByFullName("Cillian", "Murphy"));

//        logger.info("Titles that {} has participated as an Actor are: {}", personService.findPersonByFirstNameAndLastName("Cillian", "Murphy").getLastName(), titleService.findPersonParticipationInTitleByFullNameByAndProfessions("Cillian", "Murphy", ContributionRole.ACTOR));

    }
}
