package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.converter.ModelToDtoConverter;
import com.artsiomtretinnikov.dto.coach.CoachCreateDto;
import com.artsiomtretinnikov.dto.coach.CoachForAllViewDto;
import com.artsiomtretinnikov.dto.coach.CoachForSingleViewDto;
import com.artsiomtretinnikov.entity.Account;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.entity.Role;
import com.artsiomtretinnikov.repository.AccountRepository;
import com.artsiomtretinnikov.repository.CoachRepository;
import com.artsiomtretinnikov.repository.RoleRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.artsiomtretinnikov.converter.ModelToDtoConverter.accountCreateDtoToModel;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.coachCreateDtoToCoachModel;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.coachModelListToSimpleDtoList;

@Service
@Transactional
public class CoachService {

    private final CoachRepository coachRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository, RoleRepository roleRepository, AccountRepository accountRepository) {
        this.coachRepository = coachRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
    }

    public CoachForSingleViewDto getById(Long coachId) {
        Optional<Coach> coach = coachRepository.findById(coachId);
        return coach.map(ModelToDtoConverter::coachModelToFullDto).orElse(null);
    }

    public List<CoachForAllViewDto> getAll() {
        return coachModelListToSimpleDtoList(Lists.newArrayList(coachRepository.findAll()));
    }

    public List<CoachForAllViewDto> getAllByActive(boolean active) {
        return coachModelListToSimpleDtoList(Lists.newArrayList(coachRepository.findAllByActive(Coach.class, active)));
    }

    public void save(CoachCreateDto coachCreateDto) {
        Coach savedCoach = coachRepository.save(coachCreateDtoToCoachModel(coachCreateDto));

        Role role = roleRepository.findById(2L).orElse(null);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pass = bCryptPasswordEncoder.encode(coachCreateDto.getPassword());
        coachCreateDto.setPassword(pass);

        Account account = accountCreateDtoToModel(coachCreateDto);
        account.setCoach(savedCoach);
        account.setRole(role);
        accountRepository.save(account);
    }
}
