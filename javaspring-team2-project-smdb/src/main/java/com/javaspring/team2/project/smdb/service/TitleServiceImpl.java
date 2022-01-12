package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl extends BaseServiceImpl<Title> implements TitleService {
    private final TitleRepository titleRepository;

    @Override
    JpaRepository<Title, Long> getRepository() {
        return titleRepository;
    }

    @Override
   public  Title findTitleByPrimaryTitle(String primaryTitle){
        return titleRepository.findTitleByPrimaryTitle(primaryTitle);
    }

    @Override
    public List<Title> findTop3ByOrderBySmdbRatingDesc(){
        return titleRepository.findTop3ByOrderBySmdbRatingDesc();
    }


    @Override
    public List<Title> findAllByGenresContains(Genre genre){
        return titleRepository.findAllByGenresContains(genre);
    }

    @Override
    public Boolean existsByPrimaryTitle(String primaryTitle){
        return titleRepository.existsByPrimaryTitle(primaryTitle);
    }

//
    @Override
    public List<Title> findPersonParticipationInTitleByFullName(String firstName, String lastName){
        return titleRepository.findPersonParticipationInTitleByFullName(firstName, lastName);
    }

   @Override
    public List<Title> getPersonParticipationInTitleWithFullNameProfessions(String firstName, String lastName, ContributionRole profession){
        return titleRepository.getPersonParticipationInTitleWithFullNameProfessions(firstName, lastName, profession);
    }

    @Override
    public List<Person> getPeopleParticipatingInTitle(String primaryTitle) {
        return titleRepository.getPeopleParticipatingInTitle(primaryTitle);
    }


//
//    @Override
//    public void addActorsToTitle(Title title, Person person, String role){
//        title.getActors().add(newActor(title, person, role));
//    }
//
//    private Actor newActor(Title title, Person person, String role) {
//        return Actor.builder().title(title).person(person).role(role).build();
//    }
//    @Override
//    public Title findLazy(Long id){
//        return titleRepository.findLazy(id);
//    }





}
