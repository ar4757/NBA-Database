<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teams</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="topnav">
  <a href="index">Home</a>
  <a href="players">Players</a>
  <a href="teams">Teams</a>
  <a class="active" href="games">Games</a>
  <a href="about">About</a>
</div>
<ul>
	<c:if test="${games ne null}">
  		<h3>${fn:length(games)} tuple(s)</h3>
	</c:if>
	<c:forEach items="${games}" var="game">
		<li>${game}</li>
    </c:forEach>
    
</ul>
</body>
</html>