package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dancing_hall", schema = "dance_league")
public class DancingHall extends BaseEntity<Long> {

    @Column(name = "name", unique = true)
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "dancingHall", fetch = FetchType.EAGER)
    private Set<DanceClass> danceClasses = new HashSet<>();

    public DancingHall(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
