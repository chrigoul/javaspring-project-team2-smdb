package com.javaspring.team2.project.smdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
@Table(name="ACTORS")
//@SequenceGenerator(name = "idGenerator", sequenceName = "ACTORS_SEQ", initialValue = 1, allocationSize = 1)
public class Actor implements Serializable {

    @EmbeddedId
    private ActorKey key;

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

    @Column(length = 200, nullable = false)
    @NotNull
    private String role;

}