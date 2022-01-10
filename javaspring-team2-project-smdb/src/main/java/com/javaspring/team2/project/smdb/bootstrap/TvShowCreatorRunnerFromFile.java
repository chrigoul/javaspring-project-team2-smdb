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
            Set<Actor> actorSet = tvShow.getActors();
            if (actorSet == null)
                actorSet = new HashSet<>();
            while (castIterator.hasNext()) {
                dummy = castIterator.next();
                Person person= im.addPerson(dummy);
                //!!! To do --> Before creating object check if person exists in database
                personService.create(person);
                logger.info("Created Person {} {}", person.getFirstName(), person.getLastName());

                String role = (String) dummy.get("role");
                Actor actor = Actor.builder().key(ActorKey.builder().build())
                        .title(tvShow).person(person).role(role).build();
                actorSet.add(actor);
            }
            tvShow.setActors(actorSet);
            tvShowService.update(tvShow);
            logger.info("Added {} actors from Show: {}", actorSet.size(), tvShow.getPrimaryTitle());

            JSONArray productionArray = (JSONArray) dummyIterator.get("producers");
            Iterator<JSONObject> productionIterator = productionArray.iterator();
            JSONObject dummyProd;
            Set<Person> newProducers = tvShow.getProducers();
            if(newProducers==null)
                newProducers=new HashSet<>();
            while (productionIterator.hasNext()) {
                dummyProd=productionIterator.next();

                Person person = im.addPerson(dummyProd);
                personService.create(person);
                newProducers.add(person);
            }
            tvShow.setProducers(newProducers);
            tvShowService.update(tvShow);
        }
    }
}
