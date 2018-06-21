package com.artsiomtretinnikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "request", schema = "dance_league")
public class Request extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private DanceGroup danceGroup;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Request(boolean active, String name, String secondName, LocalDate dateOfBirth, DanceGroup danceGroup, String phoneNumber) {
        super(active);
        this.name = name;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.danceGroup = danceGroup;
        this.phoneNumber = phoneNumber;
    }
}
