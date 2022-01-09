package com.javaspring.team2.project.smdb.bootstrap;

import com.javaspring.team2.project.smdb.base.AbstractLogComponent;
import com.javaspring.team2.project.smdb.domain.*;
import com.javaspring.team2.project.smdb.service.PersonService;
import com.javaspring.team2.project.smdb.service.TvShowService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile("anna")
@RequiredArgsConstructor
public class TvShowCreatorRunner extends AbstractLogComponent implements CommandLineRunner {

    private final PersonService personService;
    private final TvShowService tvShowService;

    public void run(String... ars) throws Exception {

        //Insert a title
        TvShow tvShow = TvShow.builder().primaryTitle("Lucifer").durationInMinutes(90).genres(Set.of(Genre.CRIME,Genre.MYSTERY))
                .releaseYear(2015).smdbRating(8.1f).storyLine("Devil runs LA club and solves murders").build();

        tvShowService.create(tvShow);

        //Inserting all people related to this title
        List<Person> people = List.of(
                Person.builder().firstName("Tom").lastName("Ellis").
                        birthDay("January").birthPlace("London").build(),
                Person.builder().firstName("Lauren").lastName("German").
                        birthDay("November").birthPlace("California").build(),
                Person.builder().firstName("Kevin").lastName("Alejandro").
                        birthDay("April").birthPlace("Texas").build(),
                Person.builder().firstName("D.B").lastName("Woodside").
                        birthDay("July").birthPlace("New York").build(),
                Person.builder().firstName("Lesley-Ann").lastName("Brandt").
                        birthDay("December").birthPlace("Cape Town").build(),
                Person.builder().firstName("Rachael").lastName("Harris").
                        birthDay("January").birthPlace("Ohio").build(),
                Person.builder().firstName("Aimee").lastName("Garcia").
                        birthDay("November").birthPlace("Chicago").build(),
                Person.builder().firstName("Scarlet").lastName("Estevez").
                        birthDay("November").birthPlace("Los Angeles").build()
        );
        String roles[] = {"Lucifer", "Chloe", "Daniel", "Amenadiel", "Mazekeen", "Linda", "Ella", "Trixie"};
        logger.info("Created {} people.", personService.createAll(people).size());
        Set<Actor> actorSet = tvShow.getActors();
        int i = 0;
        for (Person p : people) {
            Actor actor = Actor.builder().key(ActorKey.builder().build())
                    .title(tvShow).person(p).role(roles[i]).build();
            if (actorSet == null)
                actorSet = new HashSet<>();
            actorSet.add(actor);
            i++;
        }
        tvShow.setActors(actorSet);



        tvShowService.update(tvShow);
    }
}

