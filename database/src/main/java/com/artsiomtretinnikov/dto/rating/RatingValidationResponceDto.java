package com.artsiomtretinnikov.dto.rating;

import com.artsiomtretinnikov.dto.dancer.DancerForAllViewDto;
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
public class RatingValidationResponceDto {

    private Long id;
    private DancerForAllViewDto dancer;
    private int value;
    private String style;
    private int page;
    private int totalPages;
    private boolean active;
}
