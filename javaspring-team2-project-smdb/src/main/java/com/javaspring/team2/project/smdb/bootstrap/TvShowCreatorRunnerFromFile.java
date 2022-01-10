package com.javaspring.team2.project.smdb.bootstrap;

import com.javaspring.team2.project.smdb.base.AbstractLogComponent;
import com.javaspring.team2.project.smdb.domain.*;
import com.javaspring.team2.project.smdb.extraMethods.InsertMethods;
import com.javaspring.team2.project.smdb.service.PersonService;
import com.javaspring.team2.project.smdb.service.TvShowService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.*;

@Component
@Profile("tvShow-content-loader")
@RequiredArgsConstructor
public class TvShowCreatorRunnerFromFile extends AbstractLogComponent implements CommandLineRunner {

    private final PersonService personService;
    private final TvShowService tvShowService;
    private final InsertMethods im;

    @Override
    public void run(String... ars) throws Exception {

        JSONParser parser = new JSONParser();
        JSONObject file = (JSONObject) parser.parse(new FileReader("javaspring-team2-project-smdb/src/main/resources/TvShows.json"));
        JSONArray tvShows = (JSONArray) file.get("tvShow");
        Iterator<JSONObject> showIterator = tvShows.iterator();
        JSONObject dummyIterator;

        while (showIterator.hasNext()) {
            dummyIterator = showIterator.next();

            //TvShow
            //!!! To do --> Before creating object check if tvShow exists in database
            TvShow tvShow = tvShowService.create(im.addTvShow(dummyIterator));
            //Cast and Crew
            JSONArray castArray = (JSONArray) dummyIterator.get("cast");
            Iterator<JSONObject> castIterator = castArray.iterator();
            JSONObject dummy;
            //Prepare for adding actors
            Set<Profession> professionSet = tvShow.getProfessions();
            if (professionSet == null)
                professionSet = new HashSet<>();
            while (castIterator.hasNext()) {
                dummy = castIterator.next();
                Person person = im.addPerson(dummy);
                logger.info("Person {} is going to be added", person);
                //Before creating object check if person exists in database
                Boolean check = personService.existsByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                if (check == Boolean.TRUE) {
                    logger.info("Person {} {}, already exists", person.getFirstName(), person.getLastName());
                    person = personService.findPersonByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                } else {
                    personService.create(person);
                    logger.info("Created Person {}", person);
                }

                Profession profession = Profession.builder().key(ProfessionKey.builder().build())
                        .title(tvShow).person(person).build();
                JSONArray contributionsArray = (JSONArray) dummy.get("contributionRole");
                profession.setRole((String) dummy.get("role"));
                Set<ContributionRole> titleContributionRole= im.addContributionRole(contributionsArray);
                profession.setTitleContributionRole(titleContributionRole);
                professionSet.add(profession);
            }
            tvShow.setProfessions(professionSet);
            tvShowService.update(tvShow);
            logger.info("Added {} people from Show: {}", professionSet.size(), tvShow.getPrimaryTitle());

        }
    }
}
