<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Players Rank in 2014</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="resources/css/font-awesome.min.css" >
</head>
<body style="background-color:grey">
<ul>
  	<h3>Player Rank in 2014</h3>
	<c:forEach items="${playerRanks}" var="playerRank">
		<table class="table table-dark table-striped">
			<thead>
		 		<tr>
		 			<th>Player Rank</th>
		 			<th>Player Name</th>
		 			<th>Player's Position</th>
		 		</tr>
		 	
		 	</thead>
		 	<tbody>
		 	
		 		<tr>
		 			<td>${playerRank.rank}</td>
		 			<td>${playerRank.name}</td>
		 			<td>${playerRank.position}</td>
		 		</tr>
		 		
		 	</tbody>
				
		</table>
    </c:forEach>
    
</ul>

<script src="resources/js/jquery.min.js" ></script>
<script src="resources/js/bootstrap.bundle.min.js"></script>
<script src="resources/layer/layer.js" ></script>
<script src="resources/js/sweetalert.min.js" ></script>
<script type="text/javascript" src="resources/js/vue.min.js"></script>
<script type="text/javascript" src="resources/js/my.js"></script>
</body>
</html>