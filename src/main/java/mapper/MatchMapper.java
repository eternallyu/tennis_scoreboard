package mapper;

import dto.FinishedMatchDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.entity.Match;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchMapper {

    public static final MatchMapper MATCH_MAPPER = new MatchMapper();

    public FinishedMatchDto matchToFinishedMatchDto(Match match) {
        return buildFinishedMatchDto(match);
    }

    private static FinishedMatchDto buildFinishedMatchDto(Match match) {
        return FinishedMatchDto.builder()
                .id(match.getId())
                .playerOne(match.getPlayer1Name())
                .playerTwo(match.getPlayer2Name())
                .winner(match.getWinner().getName())
                .build();
    }
}
