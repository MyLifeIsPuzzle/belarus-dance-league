package by.artsiomtretinnikov.service;

import com.artsiomtretinnikov.dao.ClubDao;
import com.artsiomtretinnikov.dto.ClubForViewDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClubService {

    private static final ClubService INSTANCE = new ClubService();

    public ClubForViewDto getClub(Long clubId) {
        return ClubDao.getInstance().getClubById(clubId)
                .map(it -> ClubForViewDto.builder()
                        .id(it.getId())
                        .name(it.getName())
                        .info(it.getInfo())
                        .build())
                .orElse(null);
    }

    public static ClubService getInstance() {
        return INSTANCE;
    }
}
