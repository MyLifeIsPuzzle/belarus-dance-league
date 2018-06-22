package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "coach_id")
@Table(name = "coach", schema = "dance_league")
public class Coach extends BaseHumanInfoEntity {

    @Column(name = "info")
    private String info;

    @OneToMany(mappedBy = "coach", fetch = FetchType.EAGER)
    private Set<DanceClass> danceClasses = new HashSet<>();

    @OneToOne(mappedBy = "coach")
    private Account account;

    @OneToMany(mappedBy = "id.coach")
    private Set<ClubCoach> clubCoaches;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "club_coach", schema = "dance_league",
            joinColumns = {@JoinColumn(name = "coach_id")},
            inverseJoinColumns = {@JoinColumn(name = "club_id")})
    private Set<Club> clubs = new HashSet<>();

    public Coach(String name, String secondName, String info, Set<DanceClass> danceClasses,
                 Account account, Set<ClubCoach> clubCoaches, Set<Club> clubs) {
        super(name, secondName);
        this.info = info;
        this.danceClasses = danceClasses;
        this.account = account;
        this.clubCoaches = clubCoaches;
        this.clubs = clubs;
    }

    public Coach(String name, String secondName, String info, Set<Club> clubs) {
        super(name, secondName);
        this.info = info;
        this.clubs = clubs;
    }

    public Coach(String name, String secondName, String info) {
        super(name, secondName);
        this.info = info;
    }
}
