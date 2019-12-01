<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Age Performance</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="resources/css/font-awesome.min.css" >
</head>
<body>
<ul>
  	<h3>Age Influence</h3>
	
		<table class="table table-dark table-striped">
			<thead>
		 		<tr>
		 			<th>Age</th>
		 			<th>Performance Score</th>
		 		</tr>
		 	
		 	</thead>
		 		<tbody>
		 	
		 		<tr>
		 			<td>${player.name}</td>
		 			<td>${player.heightFormatted}</td>
		 		</tr>
		 		
		 	</tbody>
				
		</table>
    
</ul>
<script src="resources/js/jquery.min.js" ></script>
<script src="resources/js/bootstrap.bundle.min.js"></script>
<script src="resources/layer/layer.js" ></script>
<script src="resources/js/sweetalert.min.js" ></script>
<script type="text/javascript" src="resources/js/vue.min.js"></script>
<script type="text/javascript" src="resources/js/my.js"></script>
</body>
</html>