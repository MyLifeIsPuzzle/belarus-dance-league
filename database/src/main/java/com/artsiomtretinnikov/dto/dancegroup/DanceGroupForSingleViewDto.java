package com.artsiomtretinnikov.dto.dancegroup;

import com.artsiomtretinnikov.dto.dancer.DancerForAllViewDto;
import com.artsiomtretinnikov.dto.request.RequestDto;
import com.artsiomtretinnikov.dto.club.ClubForAllViewDto;
import com.artsiomtretinnikov.dto.danceclass.DanceClassDto;
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
public class DanceGroupForSingleViewDto {

    private Long id;
    private String name;
    private ClubForAllViewDto club;
    private Set<DanceClassDto> danceClasses;
    private Set<RequestDto> requests;
    private Set<DancerForAllViewDto> dancers;
    private boolean active;
}
