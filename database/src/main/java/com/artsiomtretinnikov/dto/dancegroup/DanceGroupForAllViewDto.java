package com.artsiomtretinnikov.dto.dancegroup;

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
public class DanceGroupForAllViewDto {

    private Long id;
    private String name;
    private boolean active;
}
