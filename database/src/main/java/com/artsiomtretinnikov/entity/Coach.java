package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private Set<DanceClass> danceClasses = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "id.coach")
    private Set<ClubCoach> clubCoaches;

    @ManyToMany(mappedBy = "coaches")
    private Set<Club> clubs = new HashSet<>();

    public Coach(String name, String secondName, boolean active, String info, Set<DanceClass> danceClasses,
                 Account account, Set<ClubCoach> clubCoaches, Set<Club> clubs) {
        super(name, secondName, active);
        this.info = info;
        this.danceClasses = danceClasses;
        this.account = account;
        this.clubCoaches = clubCoaches;
        this.clubs = clubs;
    }

}
