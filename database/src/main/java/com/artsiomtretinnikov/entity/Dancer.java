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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dancer_dance_group", schema = "dance_league",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "dance_group_id")})
    private Set<DanceGroup> danceGroups = new HashSet<>();

    @OneToMany(mappedBy = "id.dancer")
    private Set<DancerDanceGroup> dancerDanceGroups;

    @OneToMany(mappedBy = "dancer", fetch = FetchType.EAGER)
    private Set<Rating> ratings = new HashSet<>();

    @Column(name = "phone_number")
    private String phoneNumber;

    public Dancer(String name, String secondName, LocalDate dateOfBirth, AgeCategory ageCategory,
                  League league, Set<DanceGroup> danceGroups, String phoneNumber) {
        super(name, secondName);
        this.dateOfBirth = dateOfBirth;
        this.ageCategory = ageCategory;
        this.league = league;
        this.danceGroups = danceGroups;
        this.phoneNumber = phoneNumber;
    }
}
