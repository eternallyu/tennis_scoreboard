<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <style><%@ include file="/css/style.css"%></style>
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
        <h1>Matches</h1>
        <div class="input-container">
            <form method="get" action="<c:url value="/matches"/>">
                <input class="input-filter" placeholder="filter_by_player_name" type="text" name="filter_by_player_name" value="${filterByPlayerName}" />
            </form>
            <div>
                <a href="<c:url value="/matches"/>">
                    <button class="btn-filter" type="button">Reset Filter</button>
                </a>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach var="match" items="${finishedMatches}">
                <tr>
                    <td>${match.playerOne}</td>
                    <td>${match.playerTwo}</td>
                    <td><span class="winner-name-td">${match.winner}</span></td>
                </tr>
            </c:forEach>
        </table>

        <div class="pagination">
            <c:if test="${currentPageNumber > 1}">
                <a class="prev" href="
            <c:url value="/matches">
                <c:param name="page" value="${currentPageNumber - 1}"/>
                <c:if test="${not empty filterByPlayerName}">
                    <c:param name="filter_by_player_name" value="${filterByPlayerName}"/>
                </c:if>
            </c:url>
        "> < </a>
            </c:if>
            <c:if test="${currentPageNumber < totalPages}">
                <a class="next" href="
            <c:url value="/matches">
                <c:param name="page" value="${currentPageNumber + 1}"/>
                <c:if test="${not empty filterByPlayerName}">
                    <c:param name="filter_by_player_name" value="${filterByPlayerName}"/>
                </c:if>
            </c:url>
        "> > </a>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from
            <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.
        </p>
    </div>
</footer>
</body>
</html>
