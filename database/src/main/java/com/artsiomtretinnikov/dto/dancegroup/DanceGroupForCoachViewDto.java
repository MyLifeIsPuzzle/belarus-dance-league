package com.artsiomtretinnikov.dto.dancegroup;

import com.artsiomtretinnikov.dto.club.ClubForAllViewDto;
import com.artsiomtretinnikov.dto.danceclass.DanceClassDto;
import com.artsiomtretinnikov.dto.dancer.DancerForSingleViewDto;
import com.artsiomtretinnikov.dto.request.RequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class DanceGroupForCoachViewDto {

    private Long id;
    private String name;
    private ClubForAllViewDto club;
    private Set<DanceClassDto> danceClasses;
    private Set<RequestDto> requests;
    private Set<DancerForSingleViewDto> dancers;
    private boolean active;
}
