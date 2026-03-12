package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{

        String email=request.getParameter("email");
        String password=request.getParameter("password");

        UserDAO dao=new UserDAO();

        User user=dao.login(email,password);

        if(user!=null){

            HttpSession session=request.getSession();

            session.setAttribute("user",user);   // store complete user

            if(user.getRole().equals("admin"))
                response.sendRedirect("adminDashboard.jsp");
            else
                response.sendRedirect("studentDashboard.jsp");

        }else{

            response.sendRedirect("login.jsp");

        }
    }
}