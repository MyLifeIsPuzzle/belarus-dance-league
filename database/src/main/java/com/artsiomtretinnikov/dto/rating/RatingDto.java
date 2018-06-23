package com.artsiomtretinnikov.dto.rating;

import com.artsiomtretinnikov.dto.dancer.DancerForAllViewDto;
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
public class RatingDto {

    private Long id;
    private DancerForAllViewDto dancer;
    private int value;
    private String style;
    private boolean active;
}
