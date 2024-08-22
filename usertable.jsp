<%@page import="com.user_dto.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
List<User> u=(List<User>)request.getAttribute("userlist");
%>
<table cellpadding="20px" border="1">
<th>Id</th>
<th>Name</th>
<th>Email</th>
<th>Password</th>

<%
for(User user:u){
%>
<tr>
<td><%= user.getId() %></td>
<td><%= user.getName() %></td>
<td><%= user.getEmail() %>
<td><%= user.getPassword() %>

<%} %>

</table>


</body>
</html>