package service;

import dao.MatchDao;
import dao.PlayerDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.entity.Match;
import model.entity.Player;

import java.util.Optional;

import static service.FinishedMatchService.PAGE_SIZE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchService {
    public static final MatchService MATCH_SERVICE = new MatchService();

    private final PlayerDao playerDao = PlayerDao.PLAYER_DAO;
    private final MatchDao matchDao = MatchDao.MATCH_DAO;

    public Match createNewMatch(String playerOneName, String playerTwoName) {
        playerOneName = playerOneName.trim();
        playerTwoName = playerTwoName.trim();

        Match newMatch = new Match();
        Optional<Player> playerOne = playerDao.findByName(playerOneName);
        Optional<Player> playerTwo = playerDao.findByName(playerTwoName);

        if (playerOne.isEmpty()) {
            newMatch.setPlayer1(addNewPlayer(playerOneName));
        } else {
            newMatch.setPlayer1(playerOne.get());
        }

        if (playerTwo.isEmpty()) {
            newMatch.setPlayer2(addNewPlayer(playerTwoName));
        } else {
            newMatch.setPlayer2(playerTwo.get());
        }

        return newMatch;
    }

    private Player addNewPlayer(String playerName) {
        Player newPlayer = new Player(playerName);
        playerDao.save(newPlayer);
        return newPlayer;
    }

    public void addFinishedMatch(Match match) {
        matchDao.save(match);
    }
}
