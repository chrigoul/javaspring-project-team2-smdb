package com.javaspring.team2.project.smdb.bootstrap;

import com.javaspring.team2.project.smdb.base.AbstractLogComponent;
import com.javaspring.team2.project.smdb.domain.*;
import com.javaspring.team2.project.smdb.service.PersonService;
import com.javaspring.team2.project.smdb.service.TvShowService;
import lombok.RequiredArgsConstructor;
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
                .releaseYear(2015).smdbRating(8.1).storyLine("Devil runs LA club and solves murders").build();

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
        Set<Profession> professionSet = tvShow.getProfessions();
        for (Person p : people) {
            Profession profession = Profession.builder().key(ProfessionKey.builder().build())
                    .title(tvShow).person(p).build();
            if (professionSet == null)
                professionSet = new HashSet<>();
            professionSet.add(profession);
        }
        tvShow.setProfessions(professionSet);



        tvShowService.update(tvShow);
    }
}

