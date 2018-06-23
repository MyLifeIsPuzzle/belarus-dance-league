package com.artsiomtretinnikov.dto.danceclass;

import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForAllViewDto;
import com.artsiomtretinnikov.dto.dancehall.DanceHallForAllViewDto;
import com.artsiomtretinnikov.dto.coach.CoachForAllViewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class DanceClassDto {

    private Long id;
    private DanceGroupForAllViewDto group;
    private CoachForAllViewDto coach;
    private DanceHallForAllViewDto danceHall;
    private String style;
    private String dayOfWeek;
    private String time;
    private boolean active;
}
