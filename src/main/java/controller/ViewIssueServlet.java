package controller;

import dao.IssueDAO;
import model.Issue;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ViewIssueServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{

        HttpSession session=request.getSession();

        User user=(User)session.getAttribute("user");

        if(user==null){
            response.sendRedirect("login.jsp");
            return;
        }

        int studentId=user.getId();

        IssueDAO dao=new IssueDAO();

        List<Issue> issues=dao.getStudentIssues(studentId);

        request.setAttribute("issues",issues);

        RequestDispatcher rd=request.getRequestDispatcher("viewIssues.jsp");

        rd.forward(request,response);
    }
}