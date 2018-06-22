package com.artsiomtretinnikov.converter;

import com.artsiomtretinnikov.config.ServiceConfiguration;
import com.artsiomtretinnikov.dto.account.AccountDto;
import com.artsiomtretinnikov.dto.club.ClubForSingleViewDto;
import com.artsiomtretinnikov.dto.coach.CoachForSingleViewDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForCoachViewDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForSingleViewDto;
import com.artsiomtretinnikov.dto.dancehall.DanceHallForSingleViewDto;
import com.artsiomtretinnikov.entity.Account;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.DancingHall;
import com.artsiomtretinnikov.repository.AccountRepository;
import com.artsiomtretinnikov.repository.ClubRepository;
import com.artsiomtretinnikov.repository.CoachRepository;
import com.artsiomtretinnikov.repository.DanceGroupRepository;
import com.artsiomtretinnikov.repository.DancingHallRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.artsiomtretinnikov.converter.ModelToDtoConverter.accountModelToDto;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.clubModelSetToSimpleDtoSet;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.clubModelToSimpleDto;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.coachModelSetToSimpleDtoSet;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.coachModelToFullDto;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.coachModelToSimpleDto;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.danceClassModelSetToDtoSet;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.danceGroupModelSetToSimpleDtoSet;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.danceGroupModelToFullDto;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.dancerModelSetToFullForCoachDtoSet;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.dancerModelSetToSimpleDtoSet;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.requestModelSetToDtoSet;
import static com.artsiomtretinnikov.converter.ModelToDtoConverter.roleModelToDto;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ModelToDtoConverterTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private DanceGroupRepository danceGroupRepository;

    @Autowired
    private DancingHallRepository dancingHallRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Test
    public void accountModelToDtoTest() {
        Account account = accountRepository.findByEmail("someemail@gmail.com").orElse(new Account());
        AccountDto expect = AccountDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .password(account.getPassword())
                .coach(coachModelToSimpleDto(account.getCoach()))
                .role(roleModelToDto(account.getRole()))
                .build();

        assertEquals(expect, accountModelToDto(account));
    }

    @Test
    public void coachModelToFullDtoTest() {
        Coach coach = coachRepository.findById(1L).orElse(new Coach());
        CoachForSingleViewDto expect = CoachForSingleViewDto.builder()
                .id(coach.getId())
                .name(coach.getName())
                .secondName(coach.getSecondName())
                .info(coach.getInfo())
                .danceClasses(danceClassModelSetToDtoSet(coach.getDanceClasses()))
                .account(accountModelToDto(coach.getAccount()))
                .clubs(clubModelSetToSimpleDtoSet(coach.getClubs()))
                .active(coach.isActive())
                .build();

        assertEquals(expect, coachModelToFullDto(coach));
    }

    @Test
    public void danceGroupModelToFullDtoTest() {
        DanceGroup danceGroup = danceGroupRepository.findById(1L).orElse(new DanceGroup());
        DanceGroupForSingleViewDto expected = DanceGroupForSingleViewDto.builder()
                .id(danceGroup.getId())
                .name(danceGroup.getName())
                .club(clubModelToSimpleDto(danceGroup.getClub()))
                .danceClasses(danceClassModelSetToDtoSet(danceGroup.getDanceClasses()))
                .requests(requestModelSetToDtoSet(danceGroup.getRequests()))
                .dancers(dancerModelSetToSimpleDtoSet(danceGroup.getDancers()))
                .active(danceGroup.isActive())
                .build();

        assertEquals(expected, danceGroupModelToFullDto(danceGroup));
    }

    @Test
    public void danceHallModelToFullDto() {
        DancingHall dancingHall = dancingHallRepository.findById(1L).orElse(new DancingHall());
        DanceHallForSingleViewDto expected = DanceHallForSingleViewDto.builder()
                .id(dancingHall.getId())
                .name(dancingHall.getName())
                .city(dancingHall.getAddress().getCity())
                .street(dancingHall.getAddress().getStreet())
                .hall(dancingHall.getAddress().getHall())
                .danceClasses(danceClassModelSetToDtoSet(dancingHall.getDanceClasses()))
                .active(dancingHall.isActive())
                .build();

        assertEquals(expected, ModelToDtoConverter.danceHallModelToFullDto(dancingHall));
    }

    @Test
    public  void clubModelToFullDto() {
        Club club = clubRepository.findById(1L).orElse(new Club());
        ClubForSingleViewDto expected = ClubForSingleViewDto.builder()
                .id(club.getId())
                .name(club.getName())
                .info(club.getInfo())
                .coaches(coachModelSetToSimpleDtoSet((club.getCoaches())))
                .danceGroups(danceGroupModelSetToSimpleDtoSet((club.getDanceGroups())))
                .build();

        assertEquals(expected, ModelToDtoConverter.clubModelToFullDto(club));
    }

    @Test
    public void danceGroupModelToFullForCoachDto() {
        DanceGroup danceGroup = danceGroupRepository.findById(1L).orElse(new DanceGroup());
        DanceGroupForCoachViewDto expected = DanceGroupForCoachViewDto.builder()
                .id(danceGroup.getId())
                .name(danceGroup.getName())
                .club(clubModelToSimpleDto(danceGroup.getClub()))
                .danceClasses(danceClassModelSetToDtoSet(danceGroup.getDanceClasses()))
                .requests(requestModelSetToDtoSet(danceGroup.getRequests()))
                .dancers(dancerModelSetToFullForCoachDtoSet((danceGroup.getDancers())))
                .active(danceGroup.isActive())
                .build();

        assertEquals(expected, ModelToDtoConverter.danceGroupModelToFullForCoachDto(danceGroup));
    }
}
