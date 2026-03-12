package controller;

import dao.IssueDAO;
import model.Issue;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ReportIssueServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{

        HttpSession session=request.getSession();

        User user=(User)session.getAttribute("user");

        if(user==null){
            response.sendRedirect("login.jsp");
            return;
        }

        int studentId=user.getId();

        String title=request.getParameter("title");
        String description=request.getParameter("description");
        String location=request.getParameter("location");
        String department=request.getParameter("department");

        Issue issue=new Issue(title,description,location,department,studentId);

        IssueDAO dao=new IssueDAO();

        if(dao.reportIssue(issue))
            response.sendRedirect("studentDashboard.jsp");
        else
            response.sendRedirect("reportIssue.jsp");
    }
}