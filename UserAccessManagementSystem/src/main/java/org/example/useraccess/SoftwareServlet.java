package org.example.useraccess;

import org.example.utils.DBUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SoftwareServlet extends HttpServlet {
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String accessLevels = request.getParameter("access_levels");
        try(Connection conn = DBUtils.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, accessLevels);
            stmt.executeUpdate();
            response.sendRedirect("createSoftware.jsp?success=Software+added+successfully");
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("createSoftware.jsp");
        }

    }
}
