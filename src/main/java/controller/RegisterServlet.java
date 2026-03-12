package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{

        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String role=request.getParameter("role");

        User user=new User(name,email,password,role);

        UserDAO dao=new UserDAO();

        if(dao.registerUser(user))
            response.sendRedirect("login.jsp");
        else
            response.sendRedirect("register.jsp");

    }
}