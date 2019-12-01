<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Players Rank in 2013</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="resources/css/font-awesome.min.css" >
</head>
<body style="background-color:grey">
<ul>
  	<h3>Player Rank in 2013</h3>
	<c:forEach items="${players}" var="player">
		<table class="table table-dark table-striped">
			<thead>
		 		<tr>
		 			<th>Player Rank</th>
		 			<th>Player Name</th>
		 			<th>Player's position</th>
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
	parent.document.getElementById("contentframe").style.display = 'none';
}

function hideLoadingImage(){
	parent.document.getElementById("loading-image").style.display = 'none';
	parent.document.getElementById("contentframe").style.display = 'block';
}
</script>
</body>
</html>