package by.artsiomtretinnikov.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RatingService {

    private static final RatingService INSTANCE = new RatingService();

//    public List<RatingDto> findByStyleAgeLeague(String style, String ageCategory, String league, int pageNumber, int limit) {
//        List<Rating> ratings = RatingDaoImpl.getInstance().findByStyleAgeLeague(Style.valueOf(style.toUpperCase()),
//                AgeCategory.valueOf(ageCategory.toUpperCase()), League.valueOf(league.toUpperCase()), pageNumber, limit);
//
//        return modelToDto(ratings);
//    }
//
//    public List<RatingDto> findAll() {
//        List<Rating> ratings = RatingDaoImpl.getInstance().findAll();
//        ratings.sort(Comparator.comparing(Rating::getValue));
//        Collections.reverse(ratings);
//
//        return modelToDto(ratings);
//    }
//
//    private List<RatingDto> modelToDto(List<Rating> ratings) {
//        return ratings.stream().map(rating -> RatingDto.builder()
//                .id(rating.getId())
//                .dancer(rating.getDancer())
//                .style(rating.getStyle().getName())
//                .value(rating.getValue())
//                .build())
//                .collect(Collectors.toList());
//    }

    public static RatingService getInstance() {
        return INSTANCE;
    }
}
