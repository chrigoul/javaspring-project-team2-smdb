package com.javaspring.team2.project.smdb.bootstrap;

import com.javaspring.team2.project.smdb.base.AbstractLogComponent;
import com.javaspring.team2.project.smdb.domain.*;
import com.javaspring.team2.project.smdb.service.MovieService;
import com.javaspring.team2.project.smdb.service.PersonService;
import com.javaspring.team2.project.smdb.service.TitleService;
import com.javaspring.team2.project.smdb.service.TvShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Profile("content-creator")
@RequiredArgsConstructor
public class ContentCreatorRunner extends AbstractLogComponent implements CommandLineRunner {
    private final PersonService personService;
    private final MovieService movieService;
    private final TvShowService tvShowService;



    public void run(String... args) throws Exception {
        List<Person> people = List.of(
                Person.builder().firstName("George").lastName("Waleck")
                        .birthDay("26/03/1987").birthPlace("Dallas").build(),
                Person.builder().firstName("Anna").lastName("Alexandrova")
                        .birthDay("30/02/1974").birthPlace("Moscwa").build(),
                Person.builder().firstName("Norman").lastName("Butter")
                        .birthDay("03/07/1994").birthPlace("Chicago").build(),
                Person.builder().firstName("Juliet").lastName("Kendwood")
                        .birthDay("09/05/1982").birthPlace("New York").build(),
                Person.builder().firstName("Jorje").lastName("Ramos")
                        .birthDay("15/03/1992").birthPlace("Sevilla").build(),
                Person.builder().firstName("Norah").lastName("Jupiter")
                        .birthDay("18/07/1994").birthPlace("New Orleans").build()
        );

        logger.info("Created {} people.", personService.createAll(people).size());

        List<Movie> movies = List.of(
                Movie.builder().primaryTitle("Better in New York").genres(Set.of(Genre.COMEDY,Genre.DRAMA)).durationInMinutes(105)
                        .smdbRating(6.8).releaseYear(2008).storyLine("A wonderful journey to New York")
                        .countriesOfOrigin(Set.of("USA")).languages(Set.of("English")).build(),
                Movie.builder().primaryTitle("Knock Out").genres(Set.of(Genre.ACTION,Genre.DRAMA)).durationInMinutes(117)
                        .smdbRating(7.4).releaseYear(2015).storyLine("A young man struggles in his early life")
                        .countriesOfOrigin(Set.of("UK")).languages(Set.of("English"))
                        .build(),
                Movie.builder().primaryTitle("In the heart of Berlin").genres(Set.of(Genre.ACTION,Genre.DRAMA,Genre.BIOGRAPHY)).durationInMinutes(145)
                        .smdbRating(8.8).releaseYear(2014).storyLine("The worst day of the season")
                        .countriesOfOrigin(Set.of("USA")).languages(Set.of("English")).build()
        );

        logger.info("Movies created: {}.",movieService.createAll(movies).size());

        List<TvShow> tvShows = List.of(
        TvShow.builder().primaryTitle("Stupid thing").genres(Set.of(Genre.COMEDY)).durationInMinutes(20).smdbRating(5.2).releaseYear(2014)
                .endYear(2018).storyLine("A stupid story").countriesOfOrigin(Set.of("USA")).languages(Set.of("English"))
                .numberOfEpisodes(22).numberOfSeasons(4).build(),
        TvShow.builder().primaryTitle("The show").genres(Set.of(Genre.COMEDY)).durationInMinutes(25).smdbRating(7.5).releaseYear(2018)
                .endYear(2021).storyLine("A different world").countriesOfOrigin(Set.of("USA")).languages(Set.of("English"))
                .numberOfEpisodes(12).numberOfSeasons(3).build(),
        TvShow.builder().primaryTitle("Computer nerds").genres(Set.of(Genre.COMEDY, Genre.DRAMA)).durationInMinutes(20).smdbRating(5.9).releaseYear(2014)
                .endYear(2016).storyLine("PC....").countriesOfOrigin(Set.of("USA")).languages(Set.of("English"))
                .numberOfEpisodes(18).numberOfSeasons(2).build(),
        TvShow.builder().primaryTitle("La cafe").genres(Set.of(Genre.DRAMA)).durationInMinutes(45).smdbRating(7.8).releaseYear(2014)
                .endYear(2019).storyLine("Somewhere in France").countriesOfOrigin(Set.of("France")).languages(Set.of("French"))
                .numberOfEpisodes(8).numberOfSeasons(5).build()
        );

        logger.info("TvShows created: {}.",tvShowService.createAll(tvShows).size());


//        Person x = personService.findPersonByFirstNameAndLastName("Jorje", "Ramos");
//        logger.info("The person is {}", x.getLastName());
//
//        List<Title> top3 = titleService.findTop3ByOrderBySmdbRatingDesc();
//        logger.info("The top three rating titles are:");
//        logger.info("1. {}      SMDB Rating: {}", top3.get(0).getPrimaryTitle(), top3.get(0).getSmdbRating());
//        logger.info("2. {}      SMDB Rating: {}", top3.get(1).getPrimaryTitle(), top3.get(1).getSmdbRating());
//        logger.info("3. {}      SMDB Rating: {}", top3.get(2).getPrimaryTitle(), top3.get(2).getSmdbRating());
//
//        List<Title> allByGenre = new ArrayList<>();
//        allByGenre = titleService.findAllByGenresContains(Genre.ACTION);
//
//        allByGenre.forEach(i-> logger.info("Action titles are: {}", i.getPrimaryTitle()));


//        Title y = titleService.findTitleByPrimaryTitle("In the heart of Berlin");
//        logger.info("The title is {}", y.getPrimaryTitle());

//        Person p = personService.find(2L);
//        logger.info("The person is {}", p.getLastName());
//
//        Title w = titleService.find(1L);
//        logger.info("The title is {}", w.getPrimaryTitle());
//
//
//        Actor.builder().key(ActorKey.builder().build()).build();
//        titleService.addActorsToTitle(titleService.find(1L),personService.find(2L),"Jose");
////        logger.info("Actor [{}] played the role of [{}] in the title [{}]",  );
//


    }
}
