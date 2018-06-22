package com.artsiomtretinnikov.dto.dancer;

import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForAllViewDto;
import com.artsiomtretinnikov.dto.rating.RatingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class DancerForSingleViewDto {

    private Long id;
    private String name;
    private String secondName;
    private String dateOfBirth;
    private Set<DanceGroupForAllViewDto> danceGroups;
    private Set<RatingDto> ratings;
    private String phoneNumber;
    private String ageCategory;
    private String league;
    private boolean active;
}
