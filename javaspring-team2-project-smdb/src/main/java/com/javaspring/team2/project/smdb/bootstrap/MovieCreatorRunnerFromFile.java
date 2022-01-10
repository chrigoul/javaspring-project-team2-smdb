package com.javaspring.team2.project.smdb.bootstrap;

import com.javaspring.team2.project.smdb.base.AbstractLogComponent;
import com.javaspring.team2.project.smdb.domain.*;
import com.javaspring.team2.project.smdb.extraMethods.InsertMethods;
import com.javaspring.team2.project.smdb.service.MovieService;
import com.javaspring.team2.project.smdb.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
@Profile("movie-content-loader")
@RequiredArgsConstructor
public class MovieCreatorRunnerFromFile extends AbstractLogComponent implements CommandLineRunner {

    private final MovieService movieService;
    private final PersonService personService;
    private final InsertMethods im;

    @Override
    public void run(String... args) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject file = (JSONObject) parser.parse(new FileReader("javaspring-team2-project-smdb/src/main/resources/movies.json"));
        JSONArray movies = (JSONArray) file.get("movie");
        Iterator<JSONObject> movieIterator = movies.iterator();
        JSONObject dummyIterator;

        while (movieIterator.hasNext()) {
            dummyIterator = movieIterator.next();

            //Movie
            //!!! To do --> Before creating object check if movie exists in database
            Movie movie = movieService.create(im.addMovie((dummyIterator)));

            //Cast and Crew
            JSONArray castArray = (JSONArray) dummyIterator.get("cast");
            Iterator<JSONObject> castIterator = castArray.iterator();
            JSONObject dummy;

            //Prepare for adding actors
            Set<Profession> professionSet = movie.getProfessions();
            if (professionSet == null)
                professionSet = new HashSet<>();

            while (castIterator.hasNext()) {
                dummy = castIterator.next();
                Person person = im.addPerson(dummy);
                logger.info("Person {} is going to be added", person);

                //!!! To do --> Before creating object check if person exists in database
                Boolean check = personService.existsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                if (check == Boolean.TRUE) {
                    logger.info("Person {} {}, already exists", person.getFirstName(), person.getLastName());
                    person = personService.findPersonByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                } else {
                    personService.create(person);
                    logger.info("Created Person {}", person);
                }

                Profession profession = Profession.builder().key(ProfessionKey.builder().build())
                        .title(movie).person(person).build();
                JSONArray contributionsArray = (JSONArray) dummy.get("contributionRole");
                profession.setRole((String) dummy.get("role"));
                Set<ContributionRole> titleContributionRole= im.addContributionRole(contributionsArray);
                profession.setTitleContributionRole(titleContributionRole);
                professionSet.add(profession);
            }
            movie.setProfessions(professionSet);
            movieService.update(movie);
            logger.info("Added {} cast from movie: {}", professionSet.size(), movie.getPrimaryTitle());

        }
    }
}

