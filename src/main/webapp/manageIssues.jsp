<%@ page import="dao.IssueDAO,model.Issue,java.util.List" %>

<%
IssueDAO dao = new IssueDAO();
List<Issue> list = dao.getAllIssues();
%>

<html>

<head>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

<h2>All Issues</h2>

<table border="1">

<tr>
<th>ID</th>
<th>Title</th>
<th>Department</th>
<th>Status</th>
<th>Update</th>
</tr>

<%

for(Issue i : list){

%>

<tr>

<td><%= i.getId() %></td>
<td><%= i.getTitle() %></td>
<td><%= i.getDepartment() %></td>
<td><%= i.getStatus() %></td>

<td>

<form action="updateStatus" method="post">

<input type="hidden" name="id" value="<%= i.getId() %>">

<select name="status">

<option value="Pending">Pending</option>
<option value="In Progress">In Progress</option>
<option value="Resolved">Resolved</option>

</select>

<button type="submit">Update</button>

</form>

</td>

</tr>

<%
}
%>

</table>

</body>

</html>