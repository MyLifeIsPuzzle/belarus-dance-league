package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "dancer_dance_group", schema = "dance_league")
public class DancerDanceGroup {

    @EmbeddedId
    private ComplexForDancerDanceGroup id;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;
}
