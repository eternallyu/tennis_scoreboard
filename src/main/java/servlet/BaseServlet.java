package servlet;

import exception.BadRequestException;
import exception.DatabaseException;
import exception.ResourcesException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BaseServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(BaseServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (DatabaseException | IOException exception) {
            logger.error("Database or IO error: {}", exception.getMessage(), exception);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage());
        } catch (BadRequestException exception) {
            logger.warn("Bad request: {}", exception.getMessage(), exception);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
        } catch (ResourcesException exception) {
            logger.error("Resource not found: {}", exception.getMessage(), exception);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
        }
    }
}