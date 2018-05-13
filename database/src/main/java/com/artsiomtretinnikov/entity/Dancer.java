package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "dancer_id")
@Table(name = "dancer", schema = "dance_league")
public class Dancer extends BaseHumanInfoEntity {

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "age_category")
    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    @Column(name = "league")
    @Enumerated(EnumType.STRING)
    private League league;

    @ManyToMany(mappedBy = "dancers")
    private Set<DanceGroup> danceGroups = new HashSet<>();

    @OneToMany(mappedBy = "id.dancer")
    private Set<DancerDanceGroup> dancerDanceGroups;

    @OneToMany(mappedBy = "dancer")
    private List<Rating> ratings = new ArrayList<>();

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public Dancer(String name, String secondName, boolean active, LocalDate dateOfBirth, AgeCategory ageCategory,
                  League league, Set<DanceGroup> danceGroups, Set<DancerDanceGroup> dancerDanceGroups,
                  List<Rating> ratings, String phoneNumber) {
        super(name, secondName, active);
        this.dateOfBirth = dateOfBirth;
        this.ageCategory = ageCategory;
        this.league = league;
        this.danceGroups = danceGroups;
        this.dancerDanceGroups = dancerDanceGroups;
        this.ratings = ratings;
        this.phoneNumber = phoneNumber;
    }
}
