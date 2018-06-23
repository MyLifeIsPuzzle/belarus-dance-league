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
public class RequestDto {

    private Long id;
    private String name;
    private String secondName;
    private String dateOfBirth;
    private Long groupId;
    private String phoneNumber;
    private boolean active;
    private Integer version;
}
