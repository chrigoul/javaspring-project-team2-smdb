package com.javaspring.team2.project.smdb.transfer;

import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Title;

public interface TitlesForAPersonOrganizedByGenresDto {
    Genre getGenre();

    String getName();


}
