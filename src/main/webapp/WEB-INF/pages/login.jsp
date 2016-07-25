<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="utf-8">
  <title>Merrit Money</title>

  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
  <link href="<c:url value="/css/bootstrap-responsive.css" />" rel="stylesheet">
  <script src="<c:url value="/js/jquery-2.1.4.min.js" />"></script>
  <script src="<c:url value="/js/main.js" />"></script>
</head>
<body>
<div class="container text-center">
  <form:form role="form" method="post" modelAttribute="userForm">
    <div class="form-actions">
      <div>
        <label for="login">Login:</label>
        <form:input type="text" class="form-control" id="login" path="login" placeholder="Enter login" />
        <label for="pwd">Password:</label>
        <form:input type="password" class="form-control" id="pwd" path="password" placeholder="Enter password" />
      </div>
      <div class="btn-group">
        <button type="submit" class="btn btn-default" name="signIn">Sign in</button>
        <button type="submit" class="btn btn-default" name="signUp">Sign up</button>
      </div>
    </div>
  </form:form>
</div>
</body>
</html>