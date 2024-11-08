package com.example.accessmanagement.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestIdStr = request.getParameter("requestId");
        String action = request.getParameter("action");

        try (Connection connection = DriverManager.getConnection(
                getServletContext().getInitParameter("db.url"),
                getServletContext().getInitParameter("db.username"),
                getServletContext().getInitParameter("db.password"))) {

            String status = action.equalsIgnoreCase("approve") ? "Approved" : "Rejected";

            String query = "UPDATE requests SET status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, Integer.parseInt(requestIdStr));
            statement.executeUpdate();

            response.sendRedirect("pendingRequests.jsp?success=Request+status+updated+to+" + status);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("pendingRequests.jsp?error=Failed+to+update+request+status");
        }
    }
}
