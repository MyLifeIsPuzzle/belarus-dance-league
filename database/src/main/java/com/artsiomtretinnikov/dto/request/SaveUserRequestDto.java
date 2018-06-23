package com.artsiomtretinnikov.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaveUserRequestDto {

    private String name;
    private String secondName;
    private String dateOfBirth;
    private Long requestId;
    private Long danceGroupId;
    private String phoneNumber;
    private String ageCategory;
    private String league;
}
