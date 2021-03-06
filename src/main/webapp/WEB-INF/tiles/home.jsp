<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:url var="viewUrl" value="/lan" />

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Home</tiles:putAttribute>
    <tiles:putAttribute name="body">
   		<div class="panel panel-default">
   			<div class="panel-heading" style="text-align: center;">
   				<h3 class="panel-title">Current LANs</h3>
   			</div>
			<div class="panel-body" style="text-align: center;">
				<c:if test="${empty currentLans}">
					<h1>No LANs are happening right now...</h1>
				</c:if>
				<c:if test="${not empty currentLans}">
					<c:forEach var="currentLan" items="${currentLans}">
						<h1><a href="${viewUrl}/${currentLan.id}">${currentLan.name} - ${currentLan.location}</a></h1>
					</c:forEach>
				</c:if>
			</div>
		</div>
    	
   		<div class="panel panel-default">
   			<div class="panel-heading" style="text-align: center;">
   				<h3 class="panel-title">Future LANs</h3>
   			</div>
			<div class="panel-body" style="text-align: center;">
				<c:if test="${empty futureLans}">
					<h1>There are no LANs scheduled!</h1>
				</c:if>
				<c:if test="${not empty futureLans}">
					<c:forEach var="futureLan" items="${futureLans}">
						<h1><a href="${viewUrl}/${futureLan.id}">${futureLan.name} - ${futureLan.location}</a></h1>
					</c:forEach>
				</c:if>
			</div>
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>