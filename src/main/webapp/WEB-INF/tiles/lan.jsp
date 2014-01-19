<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="joinUrl" value="/lan/join" />
<c:url var="addEventUrl" value="/admin/setup/lan/tournament/add" />
<c:url var="addServerUrl" value="/lan/server/add" />
<c:url var="leaveUrl" value="/lan/leave" />

<c:url var="steamPng" value="/static/images/steam.png" />

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">${lan.name}</tiles:putAttribute>
    <tiles:putAttribute name="head">
    	<style>
    		.tooltip-inner {
			    width: 300px;
			}
    	</style>
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
    <div class="col-md-12" style="text-align: center;">
    	<h1>Welcome to ${lan.name}</h1>
   	    <br />
    	<br />
    </div>
	<div class="col-md-3">
		<div class="panel panel-primary">
   			<div class="panel-heading" style="text-align: center;">
   				<h1 class="panel-title">Attendees</h1>
   			</div>
			<div class="panel-body" style="text-align: left;">
				<ul class="list-group">
					<c:forEach var="user" items="${lan.users}">
						<c:if test="${not empty user.steamGame}" >
							<c:set var="tooltipText" value="In Game - ${user.steamGame}" />
						</c:if>
						<c:if test="${empty user.steamGame}" >
							<c:set var="tooltipText" value="Not In Game" />
						</c:if>
						<li class="steamTooltip list-group-item" data-toggle="tooltip"  data-placement="right" title="${tooltipText}">
							<c:if test="${user.steamOnline}">
								<c:set var="border" value="border: 4px solid #8dccff" />
							</c:if>
							<c:if test="${user.steamOnline and not empty user.steamGame}">
								<c:set var="border" value="border: 4px solid #b3fc55" />
							</c:if>
							<c:if test="${!user.steamOnline}">
								<c:set var="border" value="border: 4px solid #cccccc" />
							</c:if>
							<img src="${user.imageUrl}" width="30" style="${border}" />
							&nbsp;&nbsp;
							${user.userName}
						</li>
					</c:forEach>
				</ul>
				<div style="text-align: center">
					<c:if test="${!attending}">
						<a href="${joinUrl}/${lan.id}" class="btn btn-default">Join</a>
					</c:if>
					<c:if test="${attending}">
						<a href="${leaveUrl}/${lan.id}" class="btn btn-default">Leave</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-md-5">
		<div class="panel panel-primary">
   			<div class="panel-heading" style="text-align: center;">
   				<h1 class="panel-title">Schedule</h1>
   			</div>
			<div class="panel-body" style="text-align: center;">
				<div class="list-group">
					<c:forEach var="tournament" items="${lan.tournaments}">
						<a href="#" class="list-group-item">${tournament.name}</a>
					</c:forEach>
				</div>
				<security:authorize url="/admin/setup/lan/tournament/add/">
					<a href="${addEventUrl}/${lan.id}" class="btn btn-default">Add Event</a>
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
				<hr />
				<h4>Time Left</h4>
				<div class="progress progress-striped active" style="height: 25px">
					<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${percentLeft}" aria-valuemin="0" aria-valuemax="100" style="width: ${percentLeft}%">
    					<span class="sr-only">${percentLeft}% Time Left</span>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-primary">
   			<div class="panel-heading" style="text-align: center">
   				<h1 class="panel-title">Servers</h1>
   			</div>
			<div class="panel-body">
				<table class="table">
					<tr>
						<th>Server</th>
						<th>Ping</th>
						<th>Players</th>
					</tr>
					<c:forEach var="server" items="${lan.servers}">
						<tr>
							<td>
								<c:if test="${server.steam}">
									<img src="${steamPng}" alt="Steam Server" />&nbsp;&nbsp;${server.game}
								</c:if>
								<c:if test="${!server.steam}">
									${server.game}&nbsp;-&nbsp;${server.hostname}:${server.port}
								</c:if>
							</td>
							<td>
								<c:if test="${server.steam}">
									${server.ping}
								</c:if>
							</td>
							<td>
								<c:if test="${server.steam}">
									${server.currentPlayers}/${server.maxPlayers}
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div style="text-align: center">
					<a href="${addServerUrl}/${lan.id}" class="btn btn-default" onclick="$('#pmModal').modal('show'); return false;">Add Server</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="clearfix"></div>

	<!-- Modal - Add Serve -->
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
						<form:button id="submit" name="submit" value="submit" class="btn btn-primary">Send</form:button>
					</div>
				</form:form>
				</div>
			</div>
		</div>
		
		<script>
		$('.steamTooltip').tooltip();
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>