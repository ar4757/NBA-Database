<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Height Performance</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="resources/css/font-awesome.min.css" >
<script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts/modules/series-label.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts/modules/oldie.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
</head>
<body style="background-color:grey" onload="hideLoadingImage();">

<div id="container" style="max-width:800px;height:400px;padding:40px;">
        <script>
        var performance = [
        	<c:forEach items="${performance}" var="item" varStatus="loopStatus">
        	${item}
        	<c:if test="${!loopStatus.last}">
        	, 
        	</c:if>
        </c:forEach>];
        var chart = Highcharts.chart('container', {
        	title: {
        		text: 'Age Influence On Performance'
        	},
        	yAxis: {
        		title: {
        			text: 'Performance Score'
        		}
        	},
        	xAxis: {
        		title: {
        			text: 'Player Age'
        		}
        	},
        	legend: {
        		layout: 'vertical',
        		align: 'right',
        		verticalAlign: 'middle'
        	},
        	plotOptions: {
        		series: {
        			label: {
        				connectorAllowed: false
        			},
        			pointStart: 19
        		}
        	},
        	series: [{
        		name: 'Performance Score',
        		data: performance
        	},],
        	responsive: {
        		rules: [{
        			condition: {
        				maxWidth: 500
        			},
        			chartOptions: {
        				legend: {
        					layout: 'horizontal',
        					align: 'center',
        					verticalAlign: 'bottom'
        				}
        			}
        		}]
        	}
        });
        </script>
</div>

<ul>
  	<h3>Height Influence</h3>
	<c:forEach items="${heightPerforms}" var="heightPerform">
		<table class="table table-dark table-striped">
			<thead>
		 		<tr>
		 			<th>Height</th>
		 			<th>Performance Score</th>
		 		</tr>
		 	
		 	</thead>
		 		<tbody>
		 	
		 		<tr>
		 			<td>${heightPerform.heightFormatted}</td>
		 			<td>${heightPerform.performance}</td>
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