package com.javaspring.team2.project.smdb.repository;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Movie;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {
    TvShow findTvShowByPrimaryTitle(String primaryTitle);

    @Query(value = "select distinct p from Person p join p.professions f join f.title t inner join TvShow tv on tv.id=t.id  where tv.id = (select distinct tv.id from TvShow where tv.primaryTitle = :primaryTitle)")
    List<Person> getPeopleParticipatingInTitle(String primaryTitle);

    List<TvShow> getTvShowByNumberOfEpisodesGreaterThan(Integer num);

    List<TvShow> getTvShowByNumberOfSeasonsGreaterThan(Integer num);

    List<TvShow> getTvShowByReleaseYearEquals(Integer year);

    List<TvShow> getTvShowBySmdbRatingGreaterThanEqual(Double smdbRating);
}
