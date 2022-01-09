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

//
//    @Query("select t from Title t join fetch t.actors where t.id = ?1")
//    Title findLazy(Long id);

    Title findTitleByPrimaryTitle(String primaryTitle);
//
//    @Query(value = "select top 3 * from TITLES t order by t.smdbRating desc", nativeQuery = true)
//    List<Title> findThreeTopRatedTitles();

    List<Title> findTop3ByOrderBySmdbRatingDesc();

//    @Query(value = "select * from TITLES t, people p where p.person_id = t.id", nativeQuery = true)
//    List<Title> findParticipationOfAPerson();

    List<Title> findAllByGenresContains(Genre genre);

}
