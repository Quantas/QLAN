<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="saveUrl" value="/profile/save" />

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Profile</tiles:putAttribute>
    <tiles:putAttribute name="body">
    	<form:form modelAttribute="profileUser" method="post" action="${saveUrl}" cssClass="form-signin" role="form">
        	<div class="container" style="width: 600px">
                <h2 class="form-signin-heading">QLAN Profile</h2>
                <div style="color: red">
                	<form:errors path="*" />
                </div>
                <table class="table">
                	<tr>
						<th>
							User Name
						</th>
						<td>
							${profileUser.userName}
						</td>         	
                	</tr>
                	<tr>
                		<th>
                			Profile Image
                		</th>
                		<td>
                			<img src="${profileUser.imageUrl}" width="50" />
                		</td>
                	</tr>
                	<tr>
                		<th>
                			Steam
                		</th>
                		<td>
                			<c:if test="${profileUser.steam}">
                				<i class="fa fa-check fa-fw"></i>
                			</c:if>
                			<c:if test="${!profileUser.steam}">
                				<i class="fa fa-ban fa-fw"></i>
                			</c:if>
                		</td>
                	</tr>
                	<c:if test="${profileUser.steam}">
                		<tr>
                			<th>
                				Steam ID
                			</th>
                			<td>
                				${profileUser.steamId}
                			</td>
                		</tr>
                		<tr>
                			<th>
                				Steam Online
                			</th>
                			<td>
                				<c:if test="${profileUser.steamOnline}">
	                				<i class="fa fa-check fa-fw"></i>
	                			</c:if>
	                			<c:if test="${!profileUser.steamOnline}">
	                				<i class="fa fa-ban fa-fw"></i>
	                			</c:if>
                			</td>
                		</tr>
                		<tr>
                			<th>
                				Current Game
                			</th>
                			<td>
                				${profileUser.steamGame}
                			</td>
                		</tr>
                	</c:if>
                	<tr>
	                	<th>
	                		First Name
	                	</th>
	                	<td>
	                		<form:input id="firstName" path="firstName" cssClass="form-control input-md" placeholder="First Name" />
	                	</td>
	                </tr>
	                <tr>
	                	<th>
	                		Last Name
	                	</th>
	                	<td>
	                		<form:input id="lastName" path="lastName" cssClass="form-control input-md" placeholder="Last Name" />
	                	</td>
	                </tr>
	                <tr>
	                	<th>
	                		E-Mail
	                	</th>
	                	<td>
	                		<form:input id="email" path="email" cssClass="form-control input-md" placeholder="E-Mail" />
	                	</td>
	                </tr>
                </table>
           	</div>
           	<div class="container" style="width: 200px">
                <button id="submit" type="submit" name="submit" class="btn btn-lg btn-primary btn-block">Update</button>
            
        	</div>
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>