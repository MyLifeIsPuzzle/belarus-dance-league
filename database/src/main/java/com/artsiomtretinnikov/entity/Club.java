package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "club", schema = "dance_league")
public class Club extends BaseEntity<Long> {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "id.club")
    private Set<ClubCoach> clubCoaches;

    @OneToMany(mappedBy = "club")
    private Set<DanceGroup> danceGroups;

    @ManyToMany
    @JoinTable(name = "club_coach", schema = "dance_league",
            joinColumns = {@JoinColumn(name = "club_id")},
            inverseJoinColumns = {@JoinColumn(name = "coach_id")})
    private Set<Coach> coaches = new HashSet<>();
}
