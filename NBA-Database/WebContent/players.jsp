<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Players</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="resources/css/font-awesome.min.css" >
</head>
<body style="background-color:grey" onload="hideLoadingImage();">
<ul>
	<c:if test="${players ne null}">
  		<h3>${fn:length(players)} tuple(s)</h3>
	</c:if>
	<c:forEach items="${players}" var="player">
		<table class="table table-dark table-striped">
			<thead>
		 		<tr>
		 			<th>Player Name</th>
		 			<th>Player Height</th>
		 			<th>Player Weight</th>
		 			<th>Player Birthday</th>
		 			<th>Player Teams</th>
		 		</tr>
		 	
		 	</thead>
		 		<tbody>
		 	
		 		<tr>
		 			<td>${player.name}</td>
		 			<td>${player.heightFormatted}</td>
		 			<td>${player.weightFormatted}</td>
		 			<td>${player.birthday}</td>
		 			<td></td>
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
<script type="text/javascript">
function showLoadingImage(){
	parent.document.getElementById("loading-image").style.display = 'block';
}

function hideLoadingImage(){
	parent.document.getElementById("loading-image").style.display = 'none';
}
</script>
</body>
</html>