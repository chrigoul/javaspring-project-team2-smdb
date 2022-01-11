package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

    Title findTitleByPrimaryTitle(String primaryTitle);

    List<Title> findTop3ByOrderBySmdbRatingDesc();

//    @Query("select DISTINCT t from Title t JOIN t.professions f JOIN f.person p where p.id =(select " +
//            "DISTINCT p.id from Person p WHERE p.firstName = :firstname and p.lastName =:lastname)")
//    List<Title> findPersonParticipationInTitleByFullName(String firstName,String lastName);

    List<Title> findAllByGenresContains(Genre genre);

    Boolean existsByPrimaryTitle(String primaryTitle);

}
