package service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.entity.Match;
import model.score.MatchScore;

import static util.CalculationUtil.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreCalculationService {

    public static final MatchScoreCalculationService MATCH_SCORE_CALCULATION_SERVICE = new MatchScoreCalculationService();

    public void calculateScore(Match match, String selectedPlayer) {
        MatchScore matchScore = match.getMatchScore();
        int playerIndex = selectedPlayer.equals("player1") ? 0 : 1;

        if (matchScore.isTieBreak()) {
            updateTieBreak(matchScore, playerIndex);
        } else {
            updateRegularPoints(matchScore, playerIndex);
        }

        checkSetWin(matchScore);
        checkMatchWin(match);
    }

    private static void updateRegularPoints(MatchScore matchScore, int playerIndex) {
        int[] points = matchScore.getPoints();
        int opponentIndex = getOpponentIndex(playerIndex);

        if (points[playerIndex] < 3) {
            points[playerIndex]++;
        } else if (points[playerIndex] == 3) {

            if (points[opponentIndex] < 3) {
                regularAddGameToPlayer(matchScore, playerIndex);
            } else if (points[opponentIndex] == 3) {
                points[playerIndex] = 4;
            } else if (points[opponentIndex] == 4) {
                points[opponentIndex] = 3;
            }
        } else if (points[playerIndex] == 4) {
            regularAddGameToPlayer(matchScore, playerIndex);
        }

        checkDeuce(matchScore, points);
    }

    private static void updateTieBreak(MatchScore matchScore, int playerIndex) {
        int[] tieBreakPoints = matchScore.getTieBreakPoints();
        int opponentIndex = getOpponentIndex(playerIndex);

        tieBreakPoints[playerIndex]++;

        if (tieBreakPoints[playerIndex] >= 7 && tieBreakPoints[playerIndex] - tieBreakPoints[opponentIndex] >= 2) {
            addGameToPlayerAfterTieBreak(matchScore, playerIndex);
        }
    }
}
