<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Page Not Found</tiles:putAttribute>
    <tiles:putAttribute name="body">
   		<div class="panel panel-warning">
   			<div class="panel-heading" style="text-align: center;">
   				<h3 class="panel-title">Page Not Found</h3>
   			</div>
			<div class="panel-body" style="text-align: center;">
  				<span style="font-size: 10em">404</span>
  				<br />
  				<span style="font-size: 3em">Page Not Found</span>
			</div>
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>