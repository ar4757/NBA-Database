<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Teams</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="resources/css/font-awesome.min.css" >
</head>
<body style="background-color:grey" onload="hideLoadingImage();">
<ul>
  	<h3>Teams</h3>
	<c:forEach items="${teams}" var="team">
		<table class="table table-dark table-striped">
			<thead>
		 		<tr>
		 			<th>Team Logo</th>
		 			<th>Team Abr</th>
		 			<th>Team Name</th>
		 			<th>Specialty</th>
		 		</tr>
		 	
		 	</thead>
		 		<tbody>
		 	
		 		<tr onclick="showLoadingImage();" data-href="<c:url value="/teamDetails">
                             <c:param name="team_abbreviation" value="${team.abbreviation}"/>
                         </c:url>" style="cursor:pointer">
		 			<td><img src=${team.logo} style="width:100px;height:100px;"></td>
		 			<td>${team.abbreviation}</td>
		 			<td>${team.name}</td>
		 			<td>${team.specialty}</td>
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
	parent.document.getElementById("contentframe").style.display = 'none';
}

function hideLoadingImage(){
	parent.document.getElementById("loading-image").style.display = 'none';
	parent.document.getElementById("contentframe").style.display = 'block';
}

$(document).ready(function(){
    $('table tr').click(function(){
        window.location = $(this).data('href');
        return false;
    });
});
</script>
</body>
</html>