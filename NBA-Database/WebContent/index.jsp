<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Home</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="resources/css/font-awesome.min.css" >

<style type = "text/css">

	.my-title{
		position:fixed;
		left:0px;
		top:0px;
		z-index:10;
		
		width:100%;
		height:50px;
		background-color:#112233;
		
		color:#cccccc;
		font-size:24px;
		font-weight:bold;
		font-family:times new roman;
		line-height:50px;
		padding-left:5px;
	}

	.my-nav{	
		position:fixed;
		left:0px;
		top:50px;
		bottom:30px;
	
		width:150px;
		
		background-color: #334455;	
	}
	
	.my-content{
		position:fixed;
		left:150px;
		right:0px;
		top:50px;
		bottom:30px;
		background-color:grey;
	}
	
	.my-content iframe{
		width:100%;
		height:100%;
		border:none;
	}
	
	.my-copyright{
		position:fixed;
		left:0px;
		bottom:0px;
		
		width:100%;
		height:30px;
		
		background-color:#112233;
		
		text-align:center;
		
		color:white;
		font-size:10px;
		font-weight:bold;
		font-family:times new roman;
		line-height:50px;
		padding-left:5px;
	}
	
	.my-main-item{
		height:45px;
		line-height:45px;
		/*text-align:center;*/
		
		padding-left:20px;
		
		border-bottom:1px solid #aaaaaa;
		cursor:pointer;
		}
		
	.my-sub-item{
	
		display:block;
		
		text-align:center;
		
		color:white;
		
		margin:0 15px 0 15px;
		border-radius:10px;
		padding:8px 0 8px 0;
	}
	
	.my-sub-item:link,.my-sub-item:visited{
		text-decoration:none;
	}
	
	.my-sub-item:hover{
		font-weight:bold;
		background-color:#00bbdd;
		color:white;
	}
</style>

</head>
<body onload="hideLoadingImage();">

<div class= "my-title">NBA-Status</div>
<div class= "my-nav">

	<div class="accordion" id="my-menu" style="color:white;">
		<!-- menu unit start-->
		<div>
			<div class="my-main-item" data-toggle="collapse" data-target="#player-info">
				Player
			</div>
			
			<div id="player-info" class="collapse" data-parent="#my-menu" style="font-size:12px;">
				
				<a href="players" target="contentframe" class="my-sub-item" onclick="showLoadingImage();">
					Player Basic Information
				</a>
				<a href="PlayerRank.html" target="contentframe" class="my-sub-item" onclick="showLoadingImage();">Player Rank</a>
				<a href="playerImprovement" target="contentframe" class="my-sub-item" onclick="showLoadingImage();">Player Improvement</a>
			</div>
		</div>
		<!-- menu unit end -->
		
		<!-- menu unit start-->
		<div>
			<div class="my-main-item" data-toggle="collapse" data-target="#Team-info">
				Team
			</div>
			
			<div id="Team-info" class="collapse" data-parent="#my-menu" style="font-size:12px;">
				
				<a href="teams" target="contentframe" class="my-sub-item" onclick="showLoadingImage();">Team Basic Information</a>
				<a href="TeamRank.html" target="contentframe" class="my-sub-item" onclick="showLoadingImage();">Team Rank</a>
			</div>
		</div>
		<!-- menu unit end -->
		
		<!-- menu unit start-->
		<div>
			<div class="my-main-item" data-toggle="collapse" data-target="#Game-info">
				Game
			</div>
			
			<div id="Game-info" class="collapse" data-parent="#my-menu" style="font-size:12px;">
				
				<a href="GameSearch.html" target="contentframe" class="my-sub-item">Game Search</a>

			</div>
		</div>
		<!-- menu unit end -->
		
		<!-- menu unit start-->
		<div>
			<div class="my-main-item" data-toggle="collapse" data-target="#Predict">
				Prediction
			</div>
			
			<div id="Predict" class="collapse" data-parent="#my-menu" style="font-size:12px;">
				
				<a href="GamePrediction" target="contentframe" class="my-sub-item" onclick="showLoadingImage();">
					Game Prediction
				</a>

			</div>
		</div>
		<!-- menu unit end -->
		
		<!-- menu unit start-->
		<div>
			<div class="my-main-item" data-toggle="collapse" data-target="#MVP">
				MVP
			</div>
			
			<div id="MVP" class="collapse" data-parent="#my-menu" style="font-size:12px;">
				
				<a href="MVPdisplay.html" target="contentframe" class="my-sub-item" onclick="showLoadingImage();">Most Valuable Players</a>

			</div>
		</div>
		<!-- menu unit end -->
		
		<!-- menu unit start-->
		<div>
			<div class="my-main-item" data-toggle="collapse" data-target="#Advanced-info">
				Advanced
			</div>
			
			<div id="Advanced-info" class="collapse" data-parent="#my-menu" style="font-size:12px;">
				
				<a href=AgePerformance target="contentframe" class="my-sub-item">Common Age Influence</a>

			</div>
		</div>
		<!-- menu unit end -->
		
		<!-- menu unit start-->
		<div>
			<div class="my-main-item" data-toggle="collapse" data-target="#About">
				About
			</div>
			
			<div id="About" class="collapse" data-parent="#my-menu" style="font-size:12px;">
				
				<a href="about" target="contentframe" class="my-sub-item">Group 27</a>
				
				<a href="tuples" target="contentframe" class="my-sub-item" onclick="showLoadingImage();">
					Tuple Count
				</a>
			</div>
		</div>
		<!-- menu unit end -->
		
	</div>
	
</div>
<div class = "my-content">	
	<div id = "loading-image"><img src=images/loading.gif style="width:30px;height:30px;">   Loading</img></div>
	<iframe name="contentframe" id = "contentframe"></iframe>
</div>
<div class = "my-copyright">Group 27: Lin Yang,   Yan Yang,  Shuyu Yang,  Andrew Ratz</div>

<script src="resources/js/jquery.min.js" ></script>
<script src="resources/js/bootstrap.bundle.min.js"></script>
<script src="resources/layer/layer.js" ></script>
<script src="resources/js/sweetalert.min.js" ></script>
<script type="text/javascript" src="resources/js/vue.min.js"></script>
<script type="text/javascript" src="resources/js/my.js"></script>
<script type="text/javascript">
function showLoadingImage(){
	document.getElementById("loading-image").style.display = 'block';
	document.getElementById("contentframe").style.display = 'none';
}

function hideLoadingImage(){
	document.getElementById("loading-image").style.display = 'none';
	document.getElementById("contentframe").style.display = 'block';
}
</script>

</body>
</html>