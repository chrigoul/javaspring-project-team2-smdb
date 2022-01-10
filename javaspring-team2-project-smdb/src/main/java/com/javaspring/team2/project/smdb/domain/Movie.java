package com.javaspring.team2.project.smdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;



@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "MOVIES")
@SequenceGenerator(name = "idGenerator", sequenceName = "MOVIE_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Movie extends Title{

        Integer ageRating;
}

