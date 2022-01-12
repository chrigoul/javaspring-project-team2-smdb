package com.javaspring.team2.project.smdb.transfer;

import com.javaspring.team2.project.smdb.domain.Genre;

public interface NumberOfShowsPerReleaseYearGenreDto {
    Genre getGenre();

    Integer getReleaseYear();

    Integer getNumber();

}
