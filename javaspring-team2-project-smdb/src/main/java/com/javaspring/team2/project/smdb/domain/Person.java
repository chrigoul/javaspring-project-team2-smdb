package com.javaspring.team2.project.smdb.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="PEOPLE")
@SequenceGenerator(name = "idGenerator", sequenceName = "PEOPLE_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends BaseModel {
    @Column(length = 20, nullable = false)
    private String firstName;

    @Column(length = 30, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String birthDay;

    @Column(nullable = true)
    private String deathDay;

    @Column(length = 50, nullable = false)
    private String birthPlace;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany( mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Actor> actors = new HashSet<>();

    @Column(name = "profession", nullable = false)
    @ElementCollection(targetClass = ContributionRole.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="person_worked_as", joinColumns= {@JoinColumn(name="PERSON_ID")})
    private Set<ContributionRole> professions;

}