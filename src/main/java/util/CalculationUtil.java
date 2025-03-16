package util;

import exception.BadRequestException;
import lombok.experimental.UtilityClass;
import model.entity.Match;
import model.score.MatchScore;

@UtilityClass
public class CalculationUtil {

    public static void checkSetWin(MatchScore matchScore) {
        int[] games = matchScore.getGames();

        if (games[0] == 7 && games[1] == 6 || games[0] >= 6 && (games[0] - games[1]) >= 2) {
            addSetToPlayer(matchScore, 0);
        } else if (games[1] == 7 && games[0] == 6 || games[1] >= 6 && (games[1] - games[0]) >= 2) {
            addSetToPlayer(matchScore, 1);
        } else if (!matchScore.isTieBreak() && games[0] == 6 && games[1] == 6) {
            matchScore.setTieBreak(true);
        }
    }


    public static void checkMatchWin(Match match) {
        MatchScore matchScore = match.getMatchScore();
        int[] sets = matchScore.getSets();

        chooseWinner(match, sets);
    }

    private static void chooseWinner(Match match, int[] sets) {
        if (sets[0] == 2) {
            match.setWinner(match.getPlayer1());
        } else if (sets[1] == 2) {
            match.setWinner(match.getPlayer2());
        }
    }

    public static void checkDeuce(MatchScore matchScore, int[] points) {
        if (points[0] == 3 && points[1] == 3) {
            matchScore.setDeuce(true);
        }
    }

    private static void resetGames(MatchScore matchScore) {
        matchScore.setGames(new int[]{0, 0});
        resetRegularPoints(matchScore);
    }

    private static void resetRegularPoints(MatchScore matchScore) {
        matchScore.setPoints(new int[]{0, 0});
        matchScore.setDeuce(false);
    }

    private static void resetTieBreakPoints(MatchScore matchScore) {
        matchScore.setTieBreakPoints(new int[]{0, 0});
    }

    public static void addGameToPlayerAfterTieBreak(MatchScore matchScore, int playerIndex) {
        matchScore.addGame(playerIndex);
        resetTieBreakPoints(matchScore);
        matchScore.setTieBreak(false);
    }

    public static void regularAddGameToPlayer(MatchScore matchScore, int playerIndex) {
        matchScore.addGame(playerIndex);
        resetRegularPoints(matchScore);
    }

    private static void addSetToPlayer(MatchScore matchScore, int index) {
        matchScore.addSet(index);
        resetGames(matchScore);
    }

    public static int getOpponentIndex(int playerIndex) {
        return playerIndex == 0 ? 1 : 0;
    }

    public static int getCurrentPageNumber(String receivedPageNumber) {
        int resultPageNumber;
        if (receivedPageNumber != null) {
            try {
                resultPageNumber = Integer.parseInt(receivedPageNumber);
            } catch (NumberFormatException exception) {
                throw new BadRequestException("Invalid page number.");
            }
        } else {
            resultPageNumber = 1;
        }
        return resultPageNumber;
    }
}
