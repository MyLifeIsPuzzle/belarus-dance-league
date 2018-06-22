package com.artsiomtretinnikov.dto.club;

import com.artsiomtretinnikov.dto.coach.CoachForAllViewDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForAllViewDto;
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
@ToString
@EqualsAndHashCode
@Builder
public class ClubForSingleViewDto {

    private Long id;
    private String name;
    private String info;
    private Set<CoachForAllViewDto> coaches;
    private Set<DanceGroupForAllViewDto> danceGroups;
    private boolean active;
}
