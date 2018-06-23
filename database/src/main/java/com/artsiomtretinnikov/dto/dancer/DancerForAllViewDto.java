package com.artsiomtretinnikov.dto.dancer;

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
public class DancerForAllViewDto {

    private Long id;
    private String name;
    private String secondName;
    private String ageCategory;
    private String league;
    private boolean active;
}
