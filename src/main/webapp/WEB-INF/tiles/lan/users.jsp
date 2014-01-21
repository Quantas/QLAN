<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="joinUrl" value="/lan/join" />
<c:url var="leaveUrl" value="/lan/leave" />

<div id="usersContainer">
	<ul class="list-group">
		<c:forEach var="user" items="${users}">
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
			<a href="${joinUrl}/${lanId}" class="btn btn-default">Join</a>
		</c:if>
		<c:if test="${attending}">
			<a href="${leaveUrl}/${lanId}" class="btn btn-default">Leave</a>
		</c:if>
	</div>
</div>