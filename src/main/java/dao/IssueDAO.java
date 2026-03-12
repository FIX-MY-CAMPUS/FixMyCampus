package dao;

import util.DBConnection;
import model.Issue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueDAO {

    public boolean reportIssue(Issue issue){

        boolean status=false;

        try{

            Connection con=DBConnection.getConnection();

            String sql="INSERT INTO issues(title,description,location,department,status,student_id) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,issue.getTitle());
            ps.setString(2,issue.getDescription());
            ps.setString(3,issue.getLocation());
            ps.setString(4,issue.getDepartment());
            ps.setString(5,"Pending");
            ps.setInt(6,issue.getStudentId());

            int i=ps.executeUpdate();

            if(i>0)
                status=true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }

    public List<Issue> getStudentIssues(int studentId){

        List<Issue> list=new ArrayList<>();

        try{

            Connection con=DBConnection.getConnection();

            String sql="SELECT * FROM issues WHERE student_id=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setInt(1,studentId);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Issue issue=new Issue();

                issue.setId(rs.getInt("issue_id"));
                issue.setTitle(rs.getString("title"));
                issue.setDescription(rs.getString("description"));
                issue.setLocation(rs.getString("location"));
                issue.setDepartment(rs.getString("department"));
                issue.setStatus(rs.getString("status"));

                list.add(issue);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<Issue> getAllIssues(){

        List<Issue> list=new ArrayList<>();

        try{

            Connection con=DBConnection.getConnection();

            String sql="SELECT * FROM issues";

            PreparedStatement ps=con.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Issue issue=new Issue();

                issue.setId(rs.getInt("issue_id"));
                issue.setTitle(rs.getString("title"));
                issue.setDescription(rs.getString("description"));
                issue.setLocation(rs.getString("location"));
                issue.setDepartment(rs.getString("department"));
                issue.setStatus(rs.getString("status"));

                list.add(issue);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public boolean updateStatus(int id,String status){

        boolean flag=false;

        try{

            Connection con=DBConnection.getConnection();

            String sql="UPDATE issues SET status=? WHERE issue_id=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,status);
            ps.setInt(2,id);

            int i=ps.executeUpdate();

            if(i>0)
                flag=true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}