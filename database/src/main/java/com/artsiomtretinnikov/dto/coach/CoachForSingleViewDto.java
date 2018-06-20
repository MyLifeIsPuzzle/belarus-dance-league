package com.artsiomtretinnikov.dto.coach;

import com.artsiomtretinnikov.dto.account.AccountDto;
import com.artsiomtretinnikov.dto.club.ClubForAllViewDto;
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
public class CoachForSingleViewDto {

    private Long id;
    private String name;
    private String secondName;
    private String info;
    private Set<DanceClassDto> danceClasses = new HashSet<>();
    private AccountDto account;
    private Set<ClubForAllViewDto> clubs = new HashSet<>();
    private boolean active;
}
