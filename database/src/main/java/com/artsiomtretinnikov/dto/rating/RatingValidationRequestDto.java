package com.artsiomtretinnikov.dto.rating;

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
public class RatingValidationRequestDto {

    private String ageCategory = "";
    private String style = "";
    private String league = "";
    private String surname = "";
}
