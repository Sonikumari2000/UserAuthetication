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

<form action="password">
Enter email: <input type="email" name="email"><br>
Enter Old Password: <input type="password" name="password"><br>
Enter new Password: <input type="password" name="newpassword"><br>

<button type="submit">Reset Password</button>
</form>
</body>
</html>