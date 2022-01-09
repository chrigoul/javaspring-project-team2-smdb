package com.javaspring.team2.project.smdb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ActorKey implements Serializable {
    @Column(name = "TITLE_ID")
    private Long titleId;
    @Column(name = "PERSON_ID")
    private Long personId;
}
