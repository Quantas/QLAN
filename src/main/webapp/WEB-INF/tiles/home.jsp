<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Home</tiles:putAttribute>
    <tiles:putAttribute name="body">
    	<h1>QLAN</h1>
    </tiles:putAttribute>
</tiles:insertDefinition>