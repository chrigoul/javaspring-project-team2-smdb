package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {
    private final PersonRepository personRepository;

    @Override
    JpaRepository<Person, Long> getRepository() {
        return personRepository;
    }

    public Person findPersonByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Person findLazy(Long id) {
        return null;
    }

    public Person findPersonByLastName(String lastName) {
        return personRepository.findPersonByLastName(lastName);
    }

/*    @Override
    public List<Title> findLazy(Long id){
        return personRepository.findLazy(id);
    }*/

    @Override
    public Boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

//    @Override
//    public List<Person> getPersonForaContributionRole(@Param("contributionRole") ContributionRole contributionRole){
//        return personRepository.getPersonForaContributionRole(contributionRole);
//    }
//

    @Override
    public List<Title> getTitlesThatPersonParticipatedIn(String firstName, String lastName) {
        return personRepository.getTitlesThatPersonParticipatedIn(firstName, lastName);
    }

    @Override
    public void csvPeopleExport(Writer writer) throws IOException {
        List<Person> people = personRepository.findAll();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord("person_id", "firstName", "lastName", "birthDay", "deathDay", "birthPlace");
        for (Person p : people) {
            csvPrinter.printRecord(p.getId(), p.getFirstName(), p.getLastName(), p.getBirthDay(), p.getDeathDay(), p.getBirthPlace());
        }
        logger.info("[People] table exported successfully. Number of rows {}", people.size());
    }
}
