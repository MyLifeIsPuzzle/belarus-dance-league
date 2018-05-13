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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dance_group", schema = "dance_league")
public class DanceGroup extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToMany
    @JoinTable(name = "dancer_dance_group", schema = "dance_league",
            joinColumns = {@JoinColumn(name = "dance_group_id")},
            inverseJoinColumns = {@JoinColumn(name = "dancer_id")})
    private Set<Dancer> dancers = new HashSet<>();

    @OneToMany(mappedBy = "id.danceGroup")
    private Set<DancerDanceGroup> dancerDanceGroups;

    @OneToMany(mappedBy = "danceGroup")
    private Set<DanceClass> danceClasses = new HashSet<>();

    @OneToMany(mappedBy = "danceGroup")
    private Set<Request> requests = new HashSet<>();

    @Column(name = "active")
    private Boolean isActive = true;
}
