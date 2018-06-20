package com.artsiomtretinnikov.dto.dancehall;

import com.artsiomtretinnikov.dto.danceclass.DanceClassDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DanceHallForSingleViewDto {

    private Long id;
    private String name;
    private String city;
    private String street;
    private String hall;
    private Set<DanceClassDto> danceClasses = new HashSet<>();
    private boolean active;
}
