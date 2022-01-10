package com.javaspring.team2.project.smdb.bootstrap;

import com.javaspring.team2.project.smdb.base.AbstractLogComponent;
import com.javaspring.team2.project.smdb.domain.Actor;
import com.javaspring.team2.project.smdb.domain.ActorKey;
import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;
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

        while(movieIterator.hasNext()){
            dummyIterator=movieIterator.next();

            //Movie
            //!!! To do --> Before creating object check if movie exists in database
            Movie movie=movieService.create(im.addMovie((dummyIterator)));

            //Cast and Crew
            JSONArray castArray = (JSONArray) dummyIterator.get("cast");
            Iterator<JSONObject> castIterator = castArray.iterator();
            JSONObject dummy;

            //Prepare for adding actors
            Set<Actor> actorSet = movie.getActors();
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
                        .title(movie).person(person).role(role).build();
                actorSet.add(actor);
            }
            movie.setActors(actorSet);
            movieService.update(movie);
            logger.info("Added {} actors from movie: {}", actorSet.size(), movie.getPrimaryTitle());

            JSONArray directorsArray = (JSONArray) dummyIterator.get("directors");
            Iterator<JSONObject> directorsIterator = directorsArray.iterator();
            JSONObject dummydirect;

            Set<Person> newDirectors = movie.getDirectors();

            if(newDirectors==null)
                newDirectors=new HashSet<>();

            while (directorsIterator.hasNext()) {
                dummydirect=directorsIterator.next();
                Person person = im.addPerson(dummydirect);
                personService.create(person);
                newDirectors.add(person);
            }
            movie.setDirectors(newDirectors);

            JSONArray writersArray = (JSONArray) dummyIterator.get("writers");
            Iterator<JSONObject> writersIterator = writersArray.iterator();
            JSONObject dummywriter;
            Set<Person> newWriters = movie.getWriters();
            if(newWriters==null)
                newWriters=new HashSet<>();
            while (writersIterator.hasNext()) {
                dummywriter=writersIterator.next();

                Person person = im.addPerson(dummywriter);
                personService.create(person);
                newWriters.add(person);
            }
            movie.setWriters(newWriters);

            movieService.update(movie);
        }
    }
}

