package com.javaspring.team2.project.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name="Professions")
//@SequenceGenerator(name = "idGenerator", sequenceName = "ACTORS_SEQ", initialValue = 1, allocationSize = 1)
public class Profession implements Serializable {

    @EmbeddedId
    private ProfessionKey key;

    @NotNull
    @ManyToOne
    @MapsId("titleId")
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @NotNull
    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Column(name = "titleContributionRole", nullable = false)
    @ElementCollection(targetClass = ContributionRole.class)
    @Enumerated(EnumType.STRING)
    private Set<ContributionRole> titleContributionRole;

    @Column(length = 200)
    private String role;

}