package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role", schema = "dance_league")
public class Role extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
