package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum League {

    BEGINNER("Beginner"),
    FIRST_LEAGUE("First_league"),
    SUPREME_LEAGUE("Supreme_league");

    private String name;

    public String getName() {
        return name;
    }
}
