package com.javaspring.team2.project.smdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "MOVIES")
@SequenceGenerator(name = "idGenerator", sequenceName = "MOVIE_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Movie extends Title{

        @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
        @JoinTable(name = "DIRECTORS",
                joinColumns = @JoinColumn(name = "MOVIE_ID"),
                inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
        private Set<Person> directors = new HashSet<>();

        @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
        @JoinTable(name = "WRITERS",
                joinColumns = @JoinColumn(name = "MOVIE_ID"),
                inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
        private Set<Person> writers = new HashSet<>();


}

