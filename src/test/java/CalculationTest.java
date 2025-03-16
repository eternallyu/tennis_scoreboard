
import model.entity.Match;
import model.score.MatchScore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.MatchScoreCalculationService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculationTest {

    private static Match match;

    private final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.MATCH_SCORE_CALCULATION_SERVICE;

    @BeforeEach
    public void init() {
        MatchScore matchScore = new MatchScore();
        match = new Match(matchScore);
    }

    @Test
    public void testDeuceSituation() {
        match.getMatchScore().setPoints(new int[]{3, 3});
        matchScoreCalculationService.calculateScore(match, "player1");
        Assertions.assertThat(match.getMatchScore().getGames()[0] == 0).isTrue();
    }

    @Test
    public void testRegularSituation() {
        match.getMatchScore().setPoints(new int[]{3, 0});
        matchScoreCalculationService.calculateScore(match, "player1");
        Assertions.assertThat(match.getMatchScore().getGames()[0] == 1).isTrue();
    }

    @Test
    public void testTieBreakSituation() {
        match.getMatchScore().setGames(new int[]{6, 6});
        matchScoreCalculationService.calculateScore(match, "player1");
        Assertions.assertThat(match.getMatchScore().isTieBreak()).isTrue();
    }


}