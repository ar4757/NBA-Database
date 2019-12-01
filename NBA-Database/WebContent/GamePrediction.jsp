<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>GamePrediction</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="resources/css/font-awesome.min.css" >
</head>
<body style="background-color:grey" onload="hideLoadingImage();">

<div id="app" class="container-fluid" style="padding:0 2px 0 2px;">
	<nav>
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item">Prediction</li>
	    <li class="breadcrumb-item active" >Game Prediction</li>
	  </ol>
	</nav>
	

<form action = "GamePrediction" method = "post">

  <div class="form-group">
    <label for="FirstTeamSelect">Away Team</label>
    <select class="form-control" id="FirstTeamSelect" name="team_away">
	    <c:forEach items="${teams}" var="team">
			<option value="${team.abbreviation}">${team.name}</option>	
	    </c:forEach>
    </select>
  </div>

  
  <div class="form-group">
    <label for="SecondTeamSelect">Home Team</label>
    <select class="form-control" id="SecondTeamSelect" name="team_home">
      <c:forEach items="${teams}" var="team">
			<option value="${team.abbreviation}">${team.name}</option>	
	    </c:forEach>
    </select>
  </div>
  
  <button type="submit" class="btn btn-primary">
  Search
  </button>
</form>

		
		
</div>
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