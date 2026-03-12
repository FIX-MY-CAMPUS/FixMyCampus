<%@ page import="java.util.List,model.Issue" %>

<html>
<head>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

<h2>My Issues</h2>

<table border="1">

<tr>
<th>Title</th>
<th>Location</th>
<th>Department</th>
<th>Status</th>
</tr>

<%

List<Issue> issues = (List<Issue>) request.getAttribute("issues");

if(issues!=null){

for(Issue i : issues){

%>

<tr>

<td><%= i.getTitle() %></td>
<td><%= i.getLocation() %></td>
<td><%= i.getDepartment() %></td>
<td><%= i.getStatus() %></td>

</tr>

<%
}
}
%>

</table>

</body>
</html>