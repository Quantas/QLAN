<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="steamPng" value="/static/images/steam.png" />
<c:url var="removeServerUrl" value="/admin/setup/lan/server/remove/" />

<div id="serversContainer">
	<table class="table table-responsive">
		<tr>
			<th>Server</th>
			<th>Ping</th>
			<th>Players</th>
			<security:authorize url="/admin/setup/lan/server/remove/">
				<th>
					&nbsp;
				</th>
			</security:authorize>
		</tr>
		<c:forEach var="server" items="${servers}">
			<tr>
				<td>
					<c:if test="${server.steam}">
						<a href="steam://connect/${server.hostname}:${server.port}"><img src="${steamPng}" alt="Steam Server" /></a>&nbsp;&nbsp;${server.game}
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
				<security:authorize url="/admin/setup/lan/server/remove/">
					<td><a href="${removeServerUrl}${lanId}/${server.id}"><i class="fa fa-minus"></i></a></td>
				</security:authorize>
			</tr>
		</c:forEach>
	</table>
	<div style="text-align: center">
		<a href="#" class="btn btn-default" onclick="$('#pmModal').modal('show'); return false;">Add Server</a>
	</div>
</div>