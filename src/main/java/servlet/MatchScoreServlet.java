package servlet;

import exception.BadRequestException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Match;
import service.MatchScoreCalculationService;
import service.MatchService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

import static util.Validation.checkUuidOfMatch;

@WebServlet("/match-score")
public class MatchScoreServlet extends BaseServlet {

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.ONGOING_MATCHES_SERVICE;
    private final MatchService matchService = MatchService.MATCH_SERVICE;
    private final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.MATCH_SCORE_CALCULATION_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuidOfMatch = checkUuidOfMatch(req.getParameter("uuid"));

        Match match = getMatch(uuidOfMatch);

        req.setAttribute("match", match);
        req.setAttribute("uuid", uuidOfMatch);

        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuidOfMatch = checkUuidOfMatch(req.getParameter("uuid"));
        Match match = getMatch(uuidOfMatch);

        String selectedPlayer = req.getParameter("player");

        matchScoreCalculationService.calculateScore(match, selectedPlayer);

        if (match.getWinner() != null) {
            endOfMatch(req, resp, uuidOfMatch, match);
            return;
        }

        resp.sendRedirect("/match-score?uuid=" + uuidOfMatch);
    }

    private void endOfMatch(HttpServletRequest req, HttpServletResponse resp, UUID uuidOfMatch, Match match) throws ServletException, IOException {
        ongoingMatchesService.remove(uuidOfMatch);
        matchService.addFinishedMatch(match);
        req.setAttribute("winner", match.getWinner());
        req.setAttribute("match", match);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    private Match getMatch(UUID uuidOfMatch) {
        Match match = ongoingMatchesService.get(uuidOfMatch);

        if (match == null) {
            throw new BadRequestException("Match not found.");
        }

        return match;
    }
}
