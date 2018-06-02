package com.artsiomtretinnikov.dto;

import com.artsiomtretinnikov.entity.Dancer;
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
public class RatingDto {

    private Long id;

    private Dancer dancer;

    private int value;

    private String style;
}
