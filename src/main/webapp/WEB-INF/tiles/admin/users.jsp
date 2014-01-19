<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:url var="deleteUrl" value="/admin/users/delete/" />
<c:url var="activateUrl" value="/admin/users/activate/" />
<c:url var="deactivateUrl" value="/admin/users/deactivate/" />

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Users</tiles:putAttribute>
    <tiles:putAttribute name="head">
    	<style>
    		.center {
    			text-align: center;
    		}
    		
    		.left {
    			text-align: left;
    		}
    		
    	</style>
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <h1>Users</h1>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                    	<th class="center">ID</th>
                        <th class="left">Username</th>
                        <th class="left">First Name</th>
                        <th class="left">Last Name</th>
                        <th class="left">E-Mail</th>
                        <th class="center">Active</th>
                        <th class="center">&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td class="center">${user.id}</td>
                            <td class="left">${user.userName}</td>
                            <td class="left">${user.firstName}</td>
                            <td class="left">${user.lastName}</td>
                            <td class="left">${user.email}</td>
                            <td class="center">
                            	<c:choose>
							    	<c:when test="${user.active}">
							    		<i class="fa fa-check fa-fw"></i>
							    	</c:when>
							    	<c:otherwise>
							    		<i class="fa fa-ban fa-fw"></i>
							    	</c:otherwise>
							    </c:choose>
                           	</td>
                            <td>
                            	<div class="btn-group">
								  <a class="btn btn-primary btn-sm" href="#"><i class="fa fa-user fa-fw"></i>&nbsp;Edit</a>
								  <a class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" href="#">
								    <span class="fa fa-caret-down"></span></a>
								  <ul class="dropdown-menu">
								    <c:choose>
								    	<c:when test="${user.active}">
								    		<li><a href="${deactivateUrl}${user.id}"><i class="fa fa-ban fa-fw"></i> Deactivate</a></li>
								    	</c:when>
								    	<c:otherwise>
								    		<li><a href="${activateUrl}${user.id}"><i class="fa fa-check fa-fw"></i> Activate</a></li>
								    	</c:otherwise>
								    </c:choose>
								    <li><a href="${deleteUrl}${user.id}"><i class="fa fa-trash-o fa-fw"></i> Delete</a></li>
								  </ul>
								</div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>