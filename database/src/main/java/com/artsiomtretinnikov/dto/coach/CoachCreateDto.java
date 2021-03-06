package com.artsiomtretinnikov.dto.coach;

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
public class CoachCreateDto {

    private String name;
    private String secondName;
    private String info;
    private String email;
    private String password;
}
