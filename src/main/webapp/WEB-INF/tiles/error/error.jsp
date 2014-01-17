<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Page Error</tiles:putAttribute>
    <tiles:putAttribute name="body">
   		<div class="panel panel-danger">
   			<div class="panel-heading" style="text-align: center;">
   				<h3 class="panel-title">Page Error</h3>
   			</div>
			<div class="panel-body" style="text-align: center;">
  					<h1>There was an error with the page, please contact support.</h1>
 					</div>
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>