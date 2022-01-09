package com.javaspring.team2.project.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "TV_SHOWS")
@SequenceGenerator(name = "idGenerator", sequenceName = "TV_SHOW_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class TvShow extends Title{

        @Column
        private Integer numberOfSeasons;

        @Column
        private Integer numberOfEpisodesPerSeason;

        @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
        @JoinTable(name = "CREATORS",
                joinColumns = @JoinColumn(name = "TITLE_ID"),
                inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
        private Set<Person> creators = new HashSet<>();

        @Column
        private Integer endYear;
}

