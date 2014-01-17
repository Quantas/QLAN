<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Home</tiles:putAttribute>
    <tiles:putAttribute name="body">
    	<c:if test="${not empty currentLan}">
    		TEST
    	</c:if>
    	<c:if test="${empty currentLan}">
    		<div class="panel panel-primary">
    			<div class="panel-heading" style="text-align: center;">
    				<h3 class="panel-title">Current LAN</h3>
    			</div>
				<div class="panel-body" style="text-align: center;">
   					<h1>No LANs are happening right now...</h1>
				</div>
			</div>
    	</c:if>
    	<c:if test="${empty futureLans}">
    		<div class="panel panel-primary">
    			<div class="panel-heading" style="text-align: center;">
    				<h3 class="panel-title">Future LANs</h3>
    			</div>
				<div class="panel-body" style="text-align: center;">
   					<h1>There are no LANs scheduled!</h1>
				</div>
			</div>
    	</c:if>
    </tiles:putAttribute>
</tiles:insertDefinition>