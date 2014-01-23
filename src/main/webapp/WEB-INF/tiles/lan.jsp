<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="momentJsUrl" value="/static/js/moment.min.js" />
<c:url var="bootstrapDateTimeJsUrl" value="/static/js/bootstrap-datetimepicker.min.js" />
<c:url var="bootstrapDateTimeCssUrl" value="/static/css/bootstrap-datetimepicker.min.css" />

<c:url var="addEventUrl" value="/admin/setup/lan/tournament/add/" />
<c:url var="removeEventUrl" value="/admin/setup/lan/tournament/remove/" />
<c:url var="addServerUrl" value="/lan/server/add" />

<c:url var="lanUrl" value="/lan/${lan.id}" />

<c:url var="loginUrl" value="/login" />

<c:url var="lansharkPng" value="/static/images/lanshark-banner.png" />

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">${lan.name}</tiles:putAttribute>
    <tiles:putAttribute name="head">
		<script type="text/javascript" src="${momentJsUrl}"></script>
   		<script type="text/javascript" src="${bootstrapDateTimeJsUrl}"></script>
       	<link rel="stylesheet" href="${bootstrapDateTimeCssUrl}" />
       	
    	<style>
    		.tooltip-inner {
			    width: 300px;
			}
    	</style>
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
    <div class="col-md-12" style="text-align: center;">
    	<c:choose>
	    	<c:when test="${lan.name == 'LANShark'}">
    			<img src="${lansharkPng}" class="img-responsive" style="margin: 0 auto;" />
	    		<br />
	    		<br />
	    	</c:when>
	    	<c:otherwise>
		    	<h1>Welcome to ${lan.name}</h1>
		   	    <br />
		    	<br />
	    	</c:otherwise>
    	</c:choose>
    </div>
	<div class="col-md-3">
		<div class="panel panel-primary">
   			<div class="panel-heading" style="text-align: center;">
   				<h1 class="panel-title">Attendees</h1>
   			</div>
			<div id="userContainer" class="panel-body" style="text-align: left;">
				<!-- Users will load here -->
			</div>
		</div>
	</div>
	
	<div class="col-md-5">
		<div class="panel panel-primary">
   			<div class="panel-heading" style="text-align: center;">
   				<h1 class="panel-title">Schedule</h1>
   			</div>
			<div class="panel-body">
				<table class="table">
					<c:forEach var="tournament" items="${lan.tournaments}">
						<tr>
							<td><joda:format value="${tournament.start}" pattern="E HH:mm"/></td>
							<td>${tournament.name}</td>
							<security:authorize url="/admin/setup/lan/tournament/remove/">
								<th><a href="${removeEventUrl}${lan.id}/${tournament.id}"><i class="fa fa-minus"></i></a></th>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>
				<security:authorize url="/admin/setup/lan/tournament/add/">
					<div style="text-align: center;">
						<a href="#" class="btn btn-default" onclick="$('#tournamentModal').modal('show'); return false;">Add Event</a>
					</div>
				</security:authorize>
			</div>
		</div>
	</div>
	
	<div class="col-md-4">
		<div class="panel panel-primary">
   			<div class="panel-heading" style="text-align: center">
   				<h1 class="panel-title">LAN Info</h1>
   			</div>
			<div class="panel-body">
				<table class="table">
					<tr>
						<th>Location</th>
						<td>${lan.location}</td>
					</tr>
					<tr>
						<th>Start</th>
						<td><joda:format value="${lan.start}" pattern="MM/dd/yyyy HH:mm"/></td>
					</tr>
					<tr>
						<th>End</th>
						<td><joda:format value="${lan.end}" pattern="MM/dd/yyyy HH:mm"/></td>
					</tr>
				</table>
				<div id="timeLeftContainer">
					<!-- Time Left Will Load Here -->
				</div>
			</div>
		</div>
		<div class="panel panel-primary">
   			<div class="panel-heading" style="text-align: center">
   				<h1 class="panel-title">Servers</h1>
   			</div>
			<div class="panel-body" id="serverContainer">
				<!-- Servers will load here -->
			</div>
		</div>
	</div>
	
	<div class="clearfix"></div>

	<!-- Modal - Add Server -->
	<div class="modal fade" id="pmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						Create New Server
					</h4>
				</div>
				<form:form modelAttribute="newServer" action="${addServerUrl}/${lan.id}" method="POST">
					<form:hidden path="id" value="0" />
					<div class="modal-body">
						<table class="table">
							<tr>
								<th>Hostname/IP</th>
								<td><form:input path="hostname" cssClass="form-control input-md" /></td>
							</tr>
							<tr>
								<th>Port</th>
								<td><form:input path="port" cssClass="form-control input-md" /></td>
							</tr>
							<tr>
								<th>Steam Server</th>
								<td>Yes <form:radiobutton path="steam" value="true" /> No <form:radiobutton path="steam" value="false" /> </td>
							</tr>
							<tr>
								<th>Game&nbsp;*</th>
								<td><form:input path="game" cssClass="form-control input-md" /></td>
							</tr>
							<tr>
								<th>Max Players&nbsp;*</th>
								<td><form:input path="maxPlayers" cssClass="form-control input-md" /></td>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<form:button id="submit" name="submit" value="submit" class="btn btn-primary">Create</form:button>
					</div>
				</form:form>
				</div>
			</div>
		</div>
		
		<!-- Modal - Add Tournament -->
		<div class="modal fade" id="tournamentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">
							Create New Event
						</h4>
					</div>
					<form:form modelAttribute="newTournament" action="${addEventUrl}${lan.id}" method="POST">
						<form:hidden path="id" value="0" />
						<div class="modal-body">
							<table class="table">
								<tr>
									<th>Name</th>
									<td><form:input path="name" cssClass="form-control input-md" /></td>
								</tr>
								<tr>
									<th>Date/Time</th>
									<td>
										<div class='input-group date' id='startPicker'>
						                   <form:input path="start" data-format="YYYY-MM-DD HH:mm:ss" cssClass="form-control input-lg" placeholder="Start Date/Time"></form:input>
						                   <span class="input-group-addon">
						                   		<span class="glyphicon glyphicon-time"></span>
						                   </span>
						               </div>
					               </td>
								</tr>
							</table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<form:button id="submit" name="submit" value="submit" class="btn btn-primary">Create</form:button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		
		<script>
		var loadServers = function() {
			$('#serverContainer').load("${lanUrl}/servers #serversContainer");
		};
		
		var loadUsers = function() {
			$('#userContainer').load("${lanUrl}/users #usersContainer", function() {
				if ($('#usersContainer').length) {
					$('.steamTooltip').tooltip();
				} else {
					window.location = '${loginUrl}';
				}
			});
		};
		
		var loadTimeLeft = function() {
			$('#timeLeftContainer').load("${lanUrl}/timeLeft #timeLeftContainer");
		};
		
		$(document).ready(function(){
			$('#startPicker').datetimepicker();
			
			loadServers();
			loadUsers();
			loadTimeLeft();
			
			setInterval(loadServers, 5000);
			setInterval(loadUsers, 12000);
			setInterval(loadTimeLeft, 60000);
		});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>