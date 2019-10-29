<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Players</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="topnav">
  <a href="index">Home</a>
  <a class="active" href="players">Players</a>
  <a href="teams">Teams</a>
  <a href="about">About</a>
</div>
<ul>
	<c:if test="${playerNames ne null}">
  		<h3>${fn:length(playerNames)} tuple(s)</h3>
	</c:if>
	<c:forEach items="${playerNames}" var="playerName">
		<li>${playerName}</li>
    </c:forEach>
    
</ul>
</body>
</html>