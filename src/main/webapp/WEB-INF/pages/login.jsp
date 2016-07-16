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
<div class="container">
  <form role="form">
    <div class="form-group">
      <label for="login">Login:</label>
      <input type=text class="form-control" id="login" placeholder="Enter login">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password">
    </div>
    <button type="submit" class="btn btn-default">Sign in</button>
    <button type="submit" class="btn btn-default">Sign up</button>
  </form>
</div>

</body>
</html>