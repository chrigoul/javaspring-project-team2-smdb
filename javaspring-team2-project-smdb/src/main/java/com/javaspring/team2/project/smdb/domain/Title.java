package com.javaspring.team2.project.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.GetMapping;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "TITLES")
@SequenceGenerator(name = "idGenerator", sequenceName = "TITLES_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Title extends BaseModel {
    @Column(length = 200, nullable = false)
    private String primaryTitle;

    @Column(name = "genre", nullable = false)
    @ElementCollection(targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="title_genres", joinColumns= {@JoinColumn(name="TITLE_ID")})
    private Set<Genre> genres;

    @NotNull
    @Column(nullable = false)
    private Integer durationInMinutes;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "title")
    private Set<Actor> actors = new HashSet<>();

    @Column(length = 4096, nullable = false)
    private String storyLine;

    @Column
    @ElementCollection
    private Set<String> countriesOfOrigin;

    @Column
    @ElementCollection
    private Set<String> languages;

    @Column
    @Min(0)
    @Max(10)
    private Float smdbRating;


    @Column
    private Integer releaseYear;
}
