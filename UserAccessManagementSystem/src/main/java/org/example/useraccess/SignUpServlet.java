package org.example.useraccess;

import org.example.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet{
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try(Connection conn = DBUtils.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, 'Employee')");
            stmt.setString(1,username);
            stmt.setString(2,password);
            stmt.executeUpdate();
            response.sendRedirect("login.jsp");
    }catch (SQLException e){
            e.printStackTrace();
            response.sendRedirect("signup.jsp");
        }
    }

}
