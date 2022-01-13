package com.javaspring.team2.project.smdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import javax.validation.constraints.*;
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
    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="title_genres", joinColumns= {@JoinColumn(name="TITLE_ID")})
    private Set<Genre> genres;

    @NotNull
    @Column(nullable = false)
    private Integer durationInMinutes;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "title")
    private Set<Profession> professions = new HashSet<>();

    @Column(length = 4096, nullable = false)
    private String storyLine;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> countriesOfOrigin;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> languages;

    @Column
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "10.0", inclusive = true)
    @Digits(integer = 2, fraction = 1)
    private Double smdbRating;


    @Column
    private Integer releaseYear;
}
