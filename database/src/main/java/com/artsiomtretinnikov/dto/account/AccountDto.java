package com.artsiomtretinnikov.dto.account;

import com.artsiomtretinnikov.dto.coach.CoachForAllViewDto;
import com.artsiomtretinnikov.dto.role.RoleDto;
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
public class AccountDto {

    private Long id;
    private String email;
    private String password;
    private CoachForAllViewDto coach;
    private RoleDto role;
}
