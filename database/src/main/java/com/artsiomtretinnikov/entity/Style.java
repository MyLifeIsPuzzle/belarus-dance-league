package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Style {

    HIP_HOP("Hip_Hop"),
    HOUSE("House"),
    DISCO("Disco"),
    DANCE_SHOW("Dance_show"),
    BELLY_DANCE("Belly_dance");

    private String name;

    public String getName() {
        return name;
    }
}
