package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Rating extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dancer_id", nullable = false)
    private Dancer dancer;

    @Column(name = "style", nullable = false)
    @Enumerated(EnumType.STRING)
    private Style style;

    @Column(name = "value", nullable = false)
    private int value;
}
