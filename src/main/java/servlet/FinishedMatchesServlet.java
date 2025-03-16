package servlet;

import dto.FinishedMatchDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FinishedMatchService;
import service.MatchService;

import java.io.IOException;
import java.util.List;

import static service.FinishedMatchService.PAGE_SIZE;
import static util.CalculationUtil.getCurrentPageNumber;

@WebServlet("/matches")
public class FinishedMatchesServlet extends BaseServlet {

    private final FinishedMatchService finishedMatchService = FinishedMatchService.FINISHED_MATCH_SERVICE;
    private final MatchService matchService = MatchService.MATCH_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String receivedPageNumber = req.getParameter("page");
        String filterByPlayerName = req.getParameter("filter_by_player_name");

        int currentPageNumber = getCurrentPageNumber(receivedPageNumber);
        List<FinishedMatchDto> finishedMatches = finishedMatchService.getFinishedMatches(currentPageNumber, filterByPlayerName);
        int totalPages = finishedMatchService.getTotalPages(filterByPlayerName);

        setAttributesForFinishedMatches(req, finishedMatches, currentPageNumber, filterByPlayerName, totalPages);

        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }

    private static void setAttributesForFinishedMatches(HttpServletRequest req, List<FinishedMatchDto> finishedMatches, int currentPageNumber, String filterByPlayerName, int totalPages) {
        req.setAttribute("finishedMatches", finishedMatches);
        req.setAttribute("currentPageNumber", currentPageNumber);
        req.setAttribute("filterByPlayerName", filterByPlayerName);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("pageSize", PAGE_SIZE);
    }

}
