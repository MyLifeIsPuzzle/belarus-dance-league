package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AgeCategory {

    CHILDREN("Children"),
    JUVENAL("Juvenal"),
    JUNIOR("Junior"),
    ADULT("Adult");

    private String name;

    public String getName() {
        return name;
    }
}
