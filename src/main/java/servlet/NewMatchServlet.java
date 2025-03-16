package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Match;
import service.MatchService;
import service.OngoingMatchesService;
import util.Validation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@WebServlet("/new-match")
public class NewMatchServlet extends BaseServlet {

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.ONGOING_MATCHES_SERVICE;
    private final MatchService matchService = MatchService.MATCH_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerOne = req.getParameter("playerOne").trim();
        String playerTwo = req.getParameter("playerTwo").trim();
        Map<String, String> exceptions = new HashMap<>(); // Изменяем тип ключа на String

        postNewMatch(req, resp, playerOne, exceptions, playerTwo);
    }

    private void postNewMatch(HttpServletRequest req, HttpServletResponse resp, String playerOne, Map<String, String> exceptions, String playerTwo) throws IOException, ServletException {
        if (!Validation.validPlayerName(playerOne)) {
            exceptions.put("playerOne", "First player name is not valid.");
        }

        if (!Validation.validPlayerName(playerTwo)) {
            exceptions.put("playerTwo", "Second player name is not valid.");
        }

        if (playerOne.equals(playerTwo)) {
            exceptions.put("sameNames", "Names are the same.");
        }

        if (exceptions.isEmpty()) {
            Match match = matchService.createNewMatch(playerOne, playerTwo);
            UUID uuidOfMatch = ongoingMatchesService.add(match);
            resp.sendRedirect("/match-score?uuid=" + uuidOfMatch.toString());
        } else {
            req.setAttribute("exceptions", exceptions);
            System.out.println("Exceptions map: " + exceptions);
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }
    }
}
