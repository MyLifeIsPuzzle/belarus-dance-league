package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class ComplexForDancerDanceGroup implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dancer_id")
    private Dancer dancer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dance_group_id")
    private DanceGroup danceGroup;
}
