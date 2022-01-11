package com.javaspring.team2.project.smdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionKey implements Serializable {

    @Column(name = "TITLE_ID")
    private Long titleId;
    @Column(name = "PERSON_ID")
    private Long personId;
}
