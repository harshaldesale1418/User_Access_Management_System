package com.example.accessmanagement.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String softwareName = request.getParameter("softwareName");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        try (Connection connection = DriverManager.getConnection(
                getServletContext().getInitParameter("db.url"),
                getServletContext().getInitParameter("db.username"),
                getServletContext().getInitParameter("db.password"))) {

            String userIdQuery = "SELECT id FROM users WHERE username = ?";
            PreparedStatement userIdStmt = connection.prepareStatement(userIdQuery);
            userIdStmt.setString(1, username);
            var resultSet = userIdStmt.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");

                String softwareIdQuery = "SELECT id FROM software WHERE name = ?";
                PreparedStatement softwareIdStmt = connection.prepareStatement(softwareIdQuery);
                softwareIdStmt.setString(1, softwareName);
                var softwareResultSet = softwareIdStmt.executeQuery();

                if (softwareResultSet.next()) {
                    int softwareId = softwareResultSet.getInt("id");

                    String query = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, 'Pending')";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, userId);
                    statement.setInt(2, softwareId);
                    statement.setString(3, accessType);
                    statement.setString(4, reason);
                    statement.executeUpdate();

                    response.sendRedirect("requestAccess.jsp?success=Request+submitted+successfully");
                } else {
                    response.sendRedirect("requestAccess.jsp?error=Software+not+found");
                }
            } else {
                response.sendRedirect("requestAccess.jsp?error=User+not+found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("requestAccess.jsp?error=Failed+to+submit+request");
        }
    }
}
