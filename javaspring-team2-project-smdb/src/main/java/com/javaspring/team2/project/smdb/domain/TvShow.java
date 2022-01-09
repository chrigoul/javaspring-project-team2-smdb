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
//
//        @ToString.Exclude
//        @EqualsAndHashCode.Exclude
//        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tvshow")
//        private Set<Episode> episodes = new HashSet<>();


        @Column(name = "episodes", nullable = false)
        @ElementCollection
        @CollectionTable(name="episodes", joinColumns= {@JoinColumn(name="TV_SHOW_ID")})
        private Set<String> episodes;
}

