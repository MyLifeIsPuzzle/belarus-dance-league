package com.artsiomtretinnikov.converter;

import com.artsiomtretinnikov.dto.account.AccountDto;
import com.artsiomtretinnikov.dto.club.ClubForAllViewDto;
import com.artsiomtretinnikov.dto.club.ClubForSingleViewDto;
import com.artsiomtretinnikov.dto.coach.CoachCreateDto;
import com.artsiomtretinnikov.dto.coach.CoachForAllViewDto;
import com.artsiomtretinnikov.dto.coach.CoachForSingleViewDto;
import com.artsiomtretinnikov.dto.danceclass.DanceClassDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForAllViewDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForCoachViewDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForSingleViewDto;
import com.artsiomtretinnikov.dto.dancehall.DanceHallForAllViewDto;
import com.artsiomtretinnikov.dto.dancehall.DanceHallForSingleViewDto;
import com.artsiomtretinnikov.dto.dancer.DancerForAllViewDto;
import com.artsiomtretinnikov.dto.dancer.DancerForSingleViewDto;
import com.artsiomtretinnikov.dto.rating.RatingDto;
import com.artsiomtretinnikov.dto.request.CreateRequestDto;
import com.artsiomtretinnikov.dto.request.RequestDto;
import com.artsiomtretinnikov.dto.role.RoleDto;
import com.artsiomtretinnikov.entity.Account;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.entity.DanceClass;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.Dancer;
import com.artsiomtretinnikov.entity.DancingHall;
import com.artsiomtretinnikov.entity.Rating;
import com.artsiomtretinnikov.entity.Request;
import com.artsiomtretinnikov.entity.Role;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class ModelToDtoConverter {

    public static Account accountCreateDtoToModel(CoachCreateDto coach) {
        return new Account(coach.getEmail(), coach.getPassword());
    }

    public static AccountDto accountModelToDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .password(account.getPassword())
                .coach(coachModelToSimpleDto(account.getCoach()))
                .role(roleModelToDto(account.getRole()))
                .build();
    }

    public static ClubForAllViewDto clubModelToSimpleDto(Club club) {
        return ClubForAllViewDto.builder()
                .id(club.getId())
                .name(club.getName())
                .info(club.getInfo())
                .build();
    }

    public static ClubForSingleViewDto clubModelToFullDto(Club club) {
        return ClubForSingleViewDto.builder()
                .id(club.getId())
                .name(club.getName())
                .info(club.getInfo())
                .coaches(coachModelSetToSimpleDtoSet(club.getCoaches()))
                .danceGroups(danceGroupModelSetToSimpleDtoSet(club.getDanceGroups()))
                .build();
    }

    public static Set<ClubForAllViewDto> clubModelSetToSimpleDtoSet(Set<Club> clubs) {
        return clubs.stream().map(ModelToDtoConverter::clubModelToSimpleDto).collect(Collectors.toSet());
    }

    public static List<ClubForAllViewDto> clubModelListToSimpleDtoList(List<Club> clubs) {
        return clubs.stream().map(ModelToDtoConverter::clubModelToSimpleDto).collect(Collectors.toList());
    }

    public static Coach coachCreateDtoToCoachModel(CoachCreateDto coachCreateDto) {
        return new Coach(coachCreateDto.getName(), coachCreateDto.getSecondName(), coachCreateDto.getInfo());
    }

    public static CoachForAllViewDto coachModelToSimpleDto(Coach coach) {
        return CoachForAllViewDto.builder()
                .id(coach.getId())
                .name(coach.getName())
                .secondName(coach.getSecondName())
                .build();
    }

    public static CoachForSingleViewDto coachModelToFullDto(Coach coach) {
        return CoachForSingleViewDto.builder()
                .id(coach.getId())
                .name(coach.getName())
                .secondName(coach.getSecondName())
                .info(coach.getInfo())
                .danceClasses(danceClassModelSetToDtoSet(coach.getDanceClasses()))
                .account(accountModelToDto(coach.getAccount()))
                .clubs(clubModelSetToSimpleDtoSet(coach.getClubs()))
                .active(coach.isActive())
                .build();
    }

    public static Set<CoachForAllViewDto> coachModelSetToSimpleDtoSet(Set<Coach> coaches) {
        return coaches.stream().map(ModelToDtoConverter::coachModelToSimpleDto).collect(Collectors.toSet());
    }

    public static List<CoachForAllViewDto> coachModelListToSimpleDtoList(List<Coach> coaches) {
        return coaches.stream().map(ModelToDtoConverter::coachModelToSimpleDto).collect(Collectors.toList());
    }

    public static DanceClassDto danceClassModelToDto(DanceClass danceClass) {
        return DanceClassDto.builder()
                .id(danceClass.getId())
                .group(danceGroupModelToSimpleDto(danceClass.getDanceGroup()))
                .coach(coachModelToSimpleDto(danceClass.getCoach()))
                .danceHall(danceHallModelToSimpleDto(danceClass.getDancingHall()))
                .style(danceClass.getStyle().getName())
                .dayOfWeek(danceClass.getDayOfWeek().toString())
                .time(danceClass.getTime().toString())
                .active(danceClass.isActive())
                .build();
    }

    public static Set<DanceClassDto> danceClassModelSetToDtoSet(Set<DanceClass> danceClasses) {
        return danceClasses.stream().map(ModelToDtoConverter::danceClassModelToDto).collect(Collectors.toSet());
    }

    public static List<DanceClassDto> danceClassModelListToDtoList(List<DanceClass> danceClasses) {
        return danceClasses.stream().map(ModelToDtoConverter::danceClassModelToDto).collect(Collectors.toList());
    }

    public static DanceGroupForAllViewDto danceGroupModelToSimpleDto(DanceGroup danceGroup) {
        return DanceGroupForAllViewDto.builder()
                .id(danceGroup.getId())
                .name(danceGroup.getName())
                .active(danceGroup.isActive())
                .build();
    }

    public static DanceGroupForSingleViewDto danceGroupModelToFullDto(DanceGroup danceGroup) {
        return DanceGroupForSingleViewDto.builder()
                .id(danceGroup.getId())
                .name(danceGroup.getName())
                .club(clubModelToSimpleDto(danceGroup.getClub()))
                .danceClasses(danceClassModelSetToDtoSet(danceGroup.getDanceClasses()))
                .requests(requestModelSetToDtoSet(danceGroup.getRequests()))
                .dancers(dancerModelSetToSimpleDtoSet(danceGroup.getDancers()))
                .active(danceGroup.isActive())
                .build();
    }

    public static DanceGroupForCoachViewDto danceGroupModelToFullForCoachDto(DanceGroup danceGroup) {
        return DanceGroupForCoachViewDto.builder()
                .id(danceGroup.getId())
                .name(danceGroup.getName())
                .club(clubModelToSimpleDto(danceGroup.getClub()))
                .danceClasses(danceClassModelSetToDtoSet(danceGroup.getDanceClasses()))
                .requests(requestModelSetToDtoSet(danceGroup.getRequests()))
                .dancers(dancerModelSetToFullForCoachDtoSet(danceGroup.getDancers()))
                .active(danceGroup.isActive())
                .build();
    }

    public static Set<DanceGroupForAllViewDto> danceGroupModelSetToSimpleDtoSet(Set<DanceGroup> danceGroups) {
        return danceGroups.stream().map(ModelToDtoConverter::danceGroupModelToSimpleDto).collect(Collectors.toSet());
    }

    public static List<DanceGroupForAllViewDto> danceGroupModelListToSimpleDtoList(List<DanceGroup> danceGroups) {
        return danceGroups.stream().map(ModelToDtoConverter::danceGroupModelToSimpleDto).collect(Collectors.toList());
    }

    public static DanceHallForAllViewDto danceHallModelToSimpleDto(DancingHall dancingHall) {
        return DanceHallForAllViewDto.builder()
                .id(dancingHall.getId())
                .name(dancingHall.getName())
                .active(dancingHall.isActive())
                .build();
    }

    public static DanceHallForSingleViewDto danceHallModelToFullDto(DancingHall dancingHall) {
        return DanceHallForSingleViewDto.builder()
                .id(dancingHall.getId())
                .name(dancingHall.getName())
                .city(dancingHall.getAddress().getCity())
                .street(dancingHall.getAddress().getStreet())
                .hall(dancingHall.getAddress().getHall())
                .danceClasses(danceClassModelSetToDtoSet(dancingHall.getDanceClasses()))
                .active(dancingHall.isActive())
                .build();
    }

    public static List<DanceHallForAllViewDto> danceHallModelListToSimpleDtoList(List<DancingHall> dancingHalls) {
        return dancingHalls.stream().map(ModelToDtoConverter::danceHallModelToSimpleDto).collect(Collectors.toList());
    }

    public static DancerForAllViewDto dancerModelToSimpleDto(Dancer dancer) {
        return DancerForAllViewDto.builder()
                .id(dancer.getId())
                .name(dancer.getName())
                .secondName(dancer.getSecondName())
                .ageCategory(dancer.getAgeCategory().getName())
                .league(dancer.getLeague().getName())
                .active(dancer.isActive())
                .build();
    }

    public static DancerForSingleViewDto dancerModelToFullDto(Dancer dancer) {
        return DancerForSingleViewDto.builder()
                .id(dancer.getId())
                .name(dancer.getName())
                .secondName(dancer.getSecondName())
                .dateOfBirth(dancer.getDateOfBirth().toString())
                .danceGroups(danceGroupModelSetToSimpleDtoSet(dancer.getDanceGroups()))
                .ratings(ratingModelListToDtoList(dancer.getRatings()))
                .phoneNumber(dancer.getPhoneNumber())
                .ageCategory(dancer.getAgeCategory().getName())
                .league(dancer.getLeague().getName())
                .active(dancer.isActive())
                .build();
    }

    public static Set<DancerForAllViewDto> dancerModelSetToSimpleDtoSet(Set<Dancer> dancers) {
        return dancers.stream().map(ModelToDtoConverter::dancerModelToSimpleDto).collect(Collectors.toSet());
    }

    public static Set<DancerForSingleViewDto> dancerModelSetToFullForCoachDtoSet(Set<Dancer> dancers) {
        return dancers.stream().map(ModelToDtoConverter::dancerModelToFullDto).collect(Collectors.toSet());
    }

    public static List<DancerForAllViewDto> dancerModelListToSimpleDtoList(List<Dancer> dancers) {
        return dancers.stream().map(ModelToDtoConverter::dancerModelToSimpleDto).collect(Collectors.toList());
    }

    public static RatingDto ratingModelToDto(Rating rating) {
        return RatingDto.builder()
                .id(rating.getId())
                .dancer(dancerModelToSimpleDto(rating.getDancer()))
                .value(rating.getValue())
                .style(rating.getStyle().getName())
                .active(rating.isActive())
                .build();
    }

    public static Set<RatingDto> ratingModelListToDtoList(Set<Rating> ratings) {
        return ratings.stream().map(ModelToDtoConverter::ratingModelToDto).collect(Collectors.toSet());
    }

    public static Request requestCreateDtoToModel(CreateRequestDto requestDto) {
        Request request = new Request();
        request.setName(requestDto.getName());
        request.setSecondName(requestDto.getSecondName());
        request.setPhoneNumber(requestDto.getPhoneNumber());
        request.setDateOfBirth(LocalDate.parse(requestDto.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return request;
    }

    public static RequestDto requestModelToDto(Request request) {
        return RequestDto.builder()
                .id(request.getId())
                .name(request.getName())
                .secondName(request.getSecondName())
                .dateOfBirth(request.getDateOfBirth().toString())
                .danceGroup(danceGroupModelToSimpleDto(request.getDanceGroup()))
                .phoneNumber(request.getPhoneNumber())
                .active(request.isActive())
                .build();
    }

    public static Set<RequestDto> requestModelSetToDtoSet(Set<Request> requests) {
        return requests.stream().map(ModelToDtoConverter::requestModelToDto).collect(Collectors.toSet());
    }

    public static List<RequestDto> requestModelListToDtoList(List<Request> requests) {
        return requests.stream().map(ModelToDtoConverter::requestModelToDto).collect(Collectors.toList());
    }

    public static RoleDto roleModelToDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .active(role.isActive())
                .build();
    }
}
