package com.javaspring.team2.project.smdb.extraMethods;

import com.javaspring.team2.project.smdb.domain.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Component
public class InsertMethods {

    public Set<ContributionRole> addContributionRole(JSONArray professionsArray) {
        Set<ContributionRole> hasWorkedAs = new HashSet<>();
        for (int i = 0; i < professionsArray.size(); i++) {
            hasWorkedAs.add(ContributionRole.valueOf((String) professionsArray.get(i)));
        }
        return hasWorkedAs;
    }

    public Set<Genre> addGenres(JSONArray genresArray){
        Set<Genre> genres = new HashSet<>();
        for (int i = 0; i < genresArray.size(); i++) {
            genres.add(Genre.valueOf((String) (genresArray.get(i))));
        }
        return genres;
    }
    public Set <String> addSet(JSONArray array){
        Set<String> newSet = new HashSet<>();
        for (int i = 0; i < array.size(); i++) {
            newSet.add((String) array.get(i));
        }
        return newSet;

    }

    public Person addPerson(JSONObject iterator){
        Person person = new Person();
        person.setFirstName((String) iterator.get("firstName"));
        person.setLastName((String) iterator.get("lastName"));
        person.setBirthDay((String) iterator.get("birthday"));
        person.setBirthPlace((String) iterator.get("birthPlace"));

        return person;
    }
    
    public TvShow addTvShow(JSONObject iterator){
        TvShow tvShow = new TvShow();
        
        //Basic tvShow attributes
        tvShow.setPrimaryTitle((String) iterator.get("primaryTitle"));
        tvShow.setDurationInMinutes(Integer.parseInt((String) iterator.get("durationInMinutes")));
        tvShow.setReleaseYear(Integer.parseInt((String) iterator.get("releaseYear")));
        tvShow.setEndYear(Integer.parseInt((String) iterator.get("endYear")));
        tvShow.setNumberOfSeasons(Integer.parseInt((String) iterator.get("numberOfSeasons")));
        tvShow.setNumberOfEpisodes(Integer.parseInt((String) iterator.get("numberOfEpisodes")));
        tvShow.setSmdbRating(Double.parseDouble((String) iterator.get("smdbRating")));
        tvShow.setStoryLine((String) iterator.get("storyLine"));

        //Adding the sets
        JSONArray genresArray = (JSONArray) iterator.get("genre");
        tvShow.setGenres(addGenres(genresArray));
        JSONArray langsArray = (JSONArray) iterator.get("languages");
        tvShow.setLanguages(addSet(langsArray));
        JSONArray countriesArray = (JSONArray) iterator.get("countriesOfOrigin");
        tvShow.setCountriesOfOrigin(addSet(countriesArray));
        
        return tvShow;
    }

    public Movie addMovie(JSONObject iterator){
        Movie movie=new Movie();

        movie.setPrimaryTitle((String) iterator.get("primaryTitle"));
        movie.setDurationInMinutes(Integer.parseInt((String) iterator.get("durationInMinutes")));
        movie.setReleaseYear(Integer.parseInt((String) iterator.get("releaseYear")));
        movie.setSmdbRating(Double.parseDouble((String) iterator.get("smdbRating")));
        movie.setStoryLine((String) iterator.get("storyLine"));

        JSONArray genresArray = (JSONArray) iterator.get("genre");
        movie.setGenres(addGenres(genresArray));
        JSONArray countriesArray = (JSONArray) iterator.get("countriesOfOrigin");
        movie.setCountriesOfOrigin(addSet(countriesArray));
        JSONArray langsArray = (JSONArray) iterator.get("languages");
        movie.setLanguages(addSet(langsArray));

        return movie;

    }
}
