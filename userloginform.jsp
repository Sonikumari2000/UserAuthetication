<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${msg }

<form action="validateuser">
Enter Email: <input type="email" name="email">
Enter Password: <input type="password" name="password">
<input type="submit">
<br>

<a href="create">Create Account</a>
</form>

</body>
</html>