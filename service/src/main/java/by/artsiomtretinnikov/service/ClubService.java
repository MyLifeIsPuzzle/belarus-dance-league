package by.artsiomtretinnikov.service;

import com.artsiomtretinnikov.daoImpl.ClubDaoImpl;
import com.artsiomtretinnikov.dto.ClubForViewDto;
import com.artsiomtretinnikov.entity.Club;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClubService {

    private static final ClubService INSTANCE = new ClubService();

    public ClubForViewDto getClub(Long clubId) {
        Club club = ClubDaoImpl.getInstance().find(clubId);
        return new ClubForViewDto(club.getId(), club.getName(), club.getInfo());
    }

    public static ClubService getInstance() {
        return INSTANCE;
    }
}
