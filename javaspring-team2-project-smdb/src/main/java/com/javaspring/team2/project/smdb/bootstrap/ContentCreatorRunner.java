package com.javaspring.team2.project.smdb.bootstrap;

import com.javaspring.team2.project.smdb.base.AbstractLogComponent;
import com.javaspring.team2.project.smdb.domain.*;
import com.javaspring.team2.project.smdb.service.MovieService;
import com.javaspring.team2.project.smdb.service.PersonService;
import com.javaspring.team2.project.smdb.service.TvShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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
                        .birthDay("26/03/1987").birthPlace("Dallas").professions(Set.of(ContributionRole.ACTOR)).build(),
                Person.builder().firstName("Anna").lastName("Alexandrova")
                        .birthDay("30/02/1974").birthPlace("Moscwa").professions(Set.of(ContributionRole.DIRECTOR,ContributionRole.WRITER)).build(),
                Person.builder().firstName("Norman").lastName("Butter")
                        .birthDay("03/07/1994").birthPlace("Chicago").professions(Set.of(ContributionRole.DIRECTOR,ContributionRole.PRODUCER)).build(),
                Person.builder().firstName("Juliet").lastName("Kendwood")
                        .birthDay("09/05/1982").birthPlace("New York").professions(Set.of(ContributionRole.ACTOR)).build(),
                Person.builder().firstName("Jorje").lastName("Ramos")
                        .birthDay("15/03/1992").birthPlace("Sevilla").professions(Set.of(ContributionRole.ACTOR,ContributionRole.DIRECTOR)).build(),
                Person.builder().firstName("Norah").lastName("Jupiter")
                        .birthDay("18/07/1994").birthPlace("New Orleans").professions(Set.of(ContributionRole.ACTOR,ContributionRole.DIRECTOR,ContributionRole.WRITER)).build()
        );

        logger.info("Created {} people.", personService.createAll(people).size());

        List<Movie> movies = List.of(
                Movie.builder().primaryTitle("Better in New York").genres(Set.of(Genre.COMEDY,Genre.DRAMA)).durationInMinutes(105)
                        .smdbRating(6.8f).releaseYear(2008).storyLine("A wonderful journey to New York")
                        .countriesOfOrigin(Set.of("USA")).languages(Set.of("English")).directors(Set.of(people.get(2)))
                        .writers(Set.of(people.get(2)))
                        .build(),
                Movie.builder().primaryTitle("Knock Out").genres(Set.of(Genre.ACTION,Genre.DRAMA)).durationInMinutes(117)
                        .smdbRating(7.4f).releaseYear(2015).storyLine("A young man struggles in his early life")
                        .countriesOfOrigin(Set.of("UK")).languages(Set.of("English")).directors(Set.of(people.get(1)))
                        .writers(Set.of(people.get(3)))
                        .build(),
                Movie.builder().primaryTitle("In the heart of Berlin").genres(Set.of(Genre.ACTION,Genre.DRAMA,Genre.BIOGRAPHY)).durationInMinutes(145)
                        .smdbRating(8.8f).releaseYear(2014).storyLine("The worst day of the season")
                        .countriesOfOrigin(Set.of("USA")).languages(Set.of("English")).directors(Set.of(people.get(1)))
                        .writers(Set.of(people.get(2))).build()
        );

        logger.info("Movies created: {}.",movieService.createAll(movies).size());

        List<TvShow> tvShows = List.of(
        TvShow.builder().primaryTitle("Stupid thing").genres(Set.of(Genre.COMEDY)).durationInMinutes(20).smdbRating(5.2f).releaseYear(2014)
                .endYear(2018).storyLine("A stupid story").countriesOfOrigin(Set.of("USA")).languages(Set.of("English")).producers(Set.of(people.get(1),people.get(3)))
                .numberOfEpisodesPerSeason(22).numberOfSeasons(4).build()
        );

        logger.info("TvShows created: {}.",tvShowService.createAll(tvShows).size());


    }
}
