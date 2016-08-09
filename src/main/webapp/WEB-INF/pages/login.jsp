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
<body onload=$(window).resize()>
<div class="container text-center center-block">
  <a class="link-unstyled" href="/">
    <div class="jumbotron">
      <h1>Merrit Money</h1>
    </div>
  </a>
  <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION and not empty error }">
    <div class="alert alert-info" role="alert">
      <strong>${SPRING_SECURITY_LAST_EXCEPTION.message}</strong>
    </div>
  </c:if>
  <c:if test="${not empty error}">
    <div class="alert alert-danger">
      <strong>${error}</strong>
    </div>
  </c:if>
  <c:if test="${not empty msg}">
    <div class="alert alert-info">
      <strong>${msg}</strong>
    </div>
  </c:if>
  <form:form action="/login" role="form" method="post" modelAttribute="userForm">
    <div class="form-actions">
      <div>
        <form:hidden path="id"/>
        <spring:bind path="login">
          <div class="form-group ${not empty error ? 'has-error' : ''}">
            <label class="control-label">Login</label>

            <div class="form-actions">
              <form:input path="login" type="text" class="form-control" id="login" placeholder="Enter login"/>
              <form:errors path="login" class="control-label"/>
            </div>
          </div>
        </spring:bind>
        <spring:bind path="password">
          <div class="form-group ${not empty error ? 'has-error' : ''}">
            <label class="control-label">Password</label>

            <div class="form-actions">
              <form:input path="password" type="password" class="form-control" id="password" placeholder="Enter password"/>
              <form:errors path="password" class="control-label"/>
            </div>
          </div>
        </spring:bind>
      </div>
      <div class="btn-group">
        <button type="submit" class="btn btn-default" name="signIn">Sign in</button>
      </div>
    </div>
  </form:form>
  <%--<button type="submit" class="btn btn-default" name="signUp">Sign up</button>--%>
</div>
</body>
</html>