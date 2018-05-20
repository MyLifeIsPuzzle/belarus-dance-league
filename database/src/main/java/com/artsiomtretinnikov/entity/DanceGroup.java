package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToMany(mappedBy = "danceGroups")
    private Set<Dancer> dancers = new HashSet<>();

    @OneToMany(mappedBy = "id.danceGroup")
    private Set<DancerDanceGroup> dancerDanceGroups;

    @OneToMany(mappedBy = "danceGroup")
    private Set<DanceClass> danceClasses = new HashSet<>();

    @OneToMany(mappedBy = "danceGroup")
    private Set<Request> requests = new HashSet<>();

    public DanceGroup(String name, Club club) {
        this.name = name;
        this.club = club;
    }
}
