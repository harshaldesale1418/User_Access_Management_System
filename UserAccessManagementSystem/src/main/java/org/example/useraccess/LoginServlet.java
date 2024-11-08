package org.example.useraccess;

import org.example.utils.DBUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try(Connection conn = DBUtils.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            stmt.setString(1,username);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String role = rs.getString("role");
                HttpSession session = request.getSession();
                session.setAttribute("role",role);
                if("Employee".equals(role)){
                    response.sendRedirect("requestAccess.jsp");

                }else if("Manager".equals(role)){
                    response.sendRedirect("pendingRequests.jsp");

                }else {
                    response.sendRedirect("createSoftware.jsp");
                }
            }else {
                response.sendRedirect("login.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
