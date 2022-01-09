package com.javaspring.team2.project.smdb.bootstrap;

import com.javaspring.team2.project.smdb.base.AbstractLogComponent;
import com.javaspring.team2.project.smdb.domain.*;
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
public class TvShowCreatorRunnerFile extends AbstractLogComponent implements CommandLineRunner {

    private final PersonService personService;
    private final TvShowService tvShowService;

    @Override
    public void run(String... ars) throws Exception {

        JSONParser parser = new JSONParser();
        JSONObject file = (JSONObject) parser.parse(new FileReader("javaspring-team2-project-smdb/src/main/resources/TvShows.json"));
        JSONArray tvShows = (JSONArray) file.get("tvShow");
        Iterator<JSONObject> showIterator = tvShows.iterator();
        JSONObject dummyIterrator;
        while (showIterator.hasNext()) {
            dummyIterrator = showIterator.next();


            //TvShow
            //!!! To do --> Before creating object check if tvShow exists in database
            TvShow tvShow = new TvShow();
            tvShow.setPrimaryTitle((String) dummyIterrator.get("primaryTitle"));

            //Adding Genres from file
/*            Set<Genre> genres = new HashSet<>();
            JSONArray genresArray = (JSONArray) dummyIterrator.get("genre");
            for (int i = 0; i < genresArray.size(); i++) {
                genres.add((Genre)genresArray.get(i));
            }

            tvShow.setGenres(genres);*/
            tvShow.setDurationInMinutes(Integer.parseInt((String) dummyIterrator.get("durationInMinutes")));
            tvShow.setReleaseYear(Integer.parseInt((String) dummyIterrator.get("releaseYear")));
            tvShow.setEndYear(Integer.parseInt((String) dummyIterrator.get("endYear")));
            tvShow.setNumberOfSeasons(Integer.parseInt((String) dummyIterrator.get("numberOfSeasons")));
            tvShow.setNumberOfEpisodes(Integer.parseInt((String) dummyIterrator.get("numberOfEpisodes")));
            tvShow.setSmdbRating(Double.parseDouble((String) dummyIterrator.get("smdbRating")));
            tvShow.setStoryLine((String) dummyIterrator.get("storyLine"));

            Set<String> languages = new HashSet<>();
            JSONArray langsArray = (JSONArray) dummyIterrator.get("languages");
            for (int i = 0; i < langsArray.size(); i++) {
                languages.add((String) langsArray.get(i));
            }
            tvShow.setLanguages(languages);

            Set<String> countries = new HashSet<>();
            JSONArray countriesArray = (JSONArray) dummyIterrator.get("countriesOfOrigin");
            for (int i = 0; i < countriesArray.size(); i++) {
                countries.add((String) countriesArray.get(i));
            }
            tvShow.setCountriesOfOrigin(countries);

            tvShowService.create(tvShow);
            //Cast and Crew
            JSONArray castArray = (JSONArray) dummyIterrator.get("cast");
            Iterator<JSONObject> castIterator = castArray.iterator();
            JSONObject dummy;
            //Prepare for adding actors
            Set<Actor> actorSet = tvShow.getActors();
            if (actorSet == null)
                actorSet = new HashSet<>();
            while (castIterator.hasNext()) {
                dummy = castIterator.next();

                //!!! To do --> Before creating object check if person exists in database
                Person person = new Person();
                person.setFirstName((String) dummy.get("firstName"));
                person.setLastName((String) dummy.get("lastName"));
                person.setBirthDay((String) dummy.get("birthday"));
                person.setBirthPlace((String) dummy.get("birthPlace"));
                personService.create(person);

                String role = (String) dummy.get("role");
                Actor actor = Actor.builder().key(ActorKey.builder().build())
                        .title(tvShow).person(person).role(role).build();
                actorSet.add(actor);
            }
            tvShow.setActors(actorSet);
            tvShowService.update(tvShow);
            logger.info("Added {} actors from Show: {}", actorSet.size(), tvShow.getPrimaryTitle());

            JSONArray productionArray = (JSONArray) dummyIterrator.get("producers");
            Iterator<JSONObject> productionIterator = productionArray.iterator();
            JSONObject dummyProd;

            Set<Person> newProducers = tvShow.getProducers();
            if(newProducers==null)
                newProducers=new HashSet<>();
            while (productionIterator.hasNext()) {
                dummyProd=productionIterator.next();
                Person person = new Person();
                person.setFirstName((String) dummyProd.get("firstName"));
                person.setLastName((String) dummyProd.get("lastName"));
                person.setBirthDay((String) dummyProd.get("birthday"));
                person.setBirthPlace((String) dummyProd.get("birthPlace"));
                personService.create(person);

                newProducers.add(person);


            }
            tvShow.setProducers(newProducers);
            tvShowService.update(tvShow);
        }
    }
}
