package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rating", schema = "dance_league")
public class Rating extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "dancer_id")
    private Dancer dancer;

    @Column(name = "value")
    private int value;

    @Column(name = "style")
    @Enumerated(EnumType.STRING)
    private Style style;

    public Rating(boolean active, Dancer dancer, int value, Style style) {
        super(active);
        this.dancer = dancer;
        this.value = value;
        this.style = style;
    }
}
