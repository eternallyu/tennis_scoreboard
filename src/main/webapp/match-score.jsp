<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <style>
        <%@ include file="/css/style.css" %>
    </style>
    <script src="js/app.js"></script>
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="<c:url value="/"/>">Home</a>
                <a class="nav-link" href="<c:url value="/matches"/>">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">

            <div class="game-state-container">
                <c:if test="${requestScope.match.getMatchScore().isDeuce()}">
                    <span class="game-state">Deuce</span>
                </c:if>
                <c:if test="${requestScope.match.getMatchScore().isTieBreak()}">
                    <span class="game-state">TieBreak</span>
                </c:if>
            </div>
            <c:choose>
                <c:when test="${not empty requestScope.winner}">
                    <c:if test="${not empty requestScope.winner}">
                        <div class="game-state-container">
                            <span class="game-state">
                                Матч завершен<br>
                                Победитель: ${requestScope.winner.getName()}
                            </span>
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead class="result">
                        <tr>
                            <th class="table-text">Player</th>
                            <th class="table-text">Sets</th>
                            <th class="table-text">Games</th>
                            <th class="table-text">Points</th>
                            <th class="table-text">Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <tr class="player1">
                            <td class="table-text">${requestScope.match.getPlayer1Name()}</td>
                            <td class="table-text">${requestScope.match.getMatchScore().getSets()[0]}</td>
                            <td class="table-text">${requestScope.match.getMatchScore().getGames()[0]}</td>
                            <c:choose>
                                <c:when test="${requestScope.match.getMatchScore().isTieBreak() == false}">
                                    <td class="table-text">${requestScope.match.getMatchScore().getPointsForDisplay(0)}</td>
                                </c:when>
                                <c:otherwise>
                                    <td class="table-text">${requestScope.match.getMatchScore().getTieBreakPoints()[0]}</td>
                                </c:otherwise>
                            </c:choose>
                            <td class="table-text">
                                <form action="match-score" method="post">
                                    <input type="hidden" name="uuid" value="${requestScope.uuid}"/>
                                    <input type="hidden" name="player" value="player1"/>
                                    <button type="submit" class="score-btn">Score</button>
                                </form>
                            </td>
                        </tr>

                        <tr class="player2">
                            <td class="table-text">${requestScope.match.getPlayer2Name()}</td>
                            <td class="table-text">${requestScope.match.getMatchScore().getSets()[1]}</td>
                            <td class="table-text">${requestScope.match.getMatchScore().getGames()[1]}</td>
                            <c:choose>
                                <c:when test="${requestScope.match.getMatchScore().isTieBreak() == false}">
                                    <td class="table-text">${requestScope.match.getMatchScore().getPointsForDisplay(1)}</td>
                                </c:when>
                                <c:otherwise>
                                    <td class="table-text">${requestScope.match.getMatchScore().getTieBreakPoints()[1]}</td>
                                </c:otherwise>
                            </c:choose>
                            <td class="table-text">
                                <form action="match-score" method="post">
                                    <input type="hidden" name="uuid" value="${requestScope.uuid}"/>
                                    <input type="hidden" name="player" value="player2"/>
                                    <button type="submit" class="score-btn">Score</button>
                                </form>

                            </td>
                        </tr>
                        </tbody>
                    </table>

                </c:otherwise>
            </c:choose>
        </section>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
