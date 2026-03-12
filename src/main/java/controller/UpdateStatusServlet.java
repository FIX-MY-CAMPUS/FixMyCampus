package controller;

import dao.IssueDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateStatusServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{

        int id=Integer.parseInt(request.getParameter("id"));
        String status=request.getParameter("status");

        IssueDAO dao=new IssueDAO();

        dao.updateStatus(id,status);

        response.sendRedirect("manageIssues.jsp");
    }
}