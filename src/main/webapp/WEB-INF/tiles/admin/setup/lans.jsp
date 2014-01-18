<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:url var="newLanUrl" value="/admin/setup/newlan" />
<c:url var="editUrl" value="/admin/setup/lan" />

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">LANS</tiles:putAttribute>
    <tiles:putAttribute name="body">
        <h1>LANs</h1>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Tournaments</th>
                        <th>Attendees</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lans}" var="lan">
                        <tr>
                            <td>${lan.id}</td>
                            <td>${lan.name}</td>
                            <td>${lan.location}</td>
                            <td><joda:format value="${lan.start}" pattern="MM/dd/yyyy HH:mm"/></td>
                            <td><joda:format value="${lan.end}" pattern="MM/dd/yyyy HH:mm"/></td>
                            <td>${fn:length(lan.tournaments)}</td>
                            <td>${fn:length(lan.users)}</td>
                            <td><a href="${editUrl}/${lan.id}" class="input-sm btn-default">Edit</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br />
            <br />
            <a href="${newLanUrl}" class="btn btn-default">Create New LAN</a>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>