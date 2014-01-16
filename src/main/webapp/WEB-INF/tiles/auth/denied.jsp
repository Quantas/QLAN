<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Login</tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="alert alert-danger">
            Access Denied!
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>