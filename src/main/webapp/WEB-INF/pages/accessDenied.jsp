<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Access denied</title>
</head>
<body>
<div>
  You are not authorized to access this page.
</div>
<a href="<c:url value="/" />">Go to home</a> OR <a href="<c:url value="/logout" />">Logout</a>
</body>
</html>
