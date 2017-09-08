<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="openIdUrl" value="/login/openid" />
<c:url var="loginUrl" value="/login" />
<c:url var="signUpUrl" value="/user/signup" />

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Login</tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container" style="width: 500px">
        	<c:if test="${param.error != null}">
                 <div class="alert alert-dismissable alert-danger">
                     <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                     Invalid credentials.
                 </div>
             </c:if>
             <c:if test="${param.logout != null}">
                 <div class="alert alert-dismissable alert-success">
                     <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                     You have been logged out.
                 </div>
             </c:if>
        
        	 <div class="panel panel-default">
        	 	<div class="panel-heading">
   				<h1 class="panel-title">Sign In With Steam</h1>
   			</div>
  				<div class="panel-body" style="text-align: center">
		            <form action="${openIdUrl}" method="post" target="_top">
		            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		            	<input id="openid_identifier" name="openid_identifier" maxlength="100" type="hidden" value="https://steamcommunity.com/openid" />
		            	<input type="image" src="http://cdn.steamcommunity.com/public/images/signinthroughsteam/sits_large_border.png">
		            </form>
	            </div>
            </div>
            
            <div class="panel panel-default">
        	 	<div class="panel-heading">
   					<h1 class="panel-title">Sign In With Local Account</h1>
   				</div>
  				<div class="panel-body">
		            <form:form action="${loginUrl}" method="post" cssClass="form-signin" role="form">
		            	<table class="table">
		            		<tr>
		            			<th>User Name</th>
		            			<td><input type="text" id="username" name="username" class="form-control input-md" placeholder="User Name" /></td>
		            		</tr>
		                    <tr>
		                    	<th>Password</th>
		                    	<td><input type="password" id="password" name="password" class="form-control input-md" placeholder="Password"/></td>
		                    </tr>
		                    <tr>
		                    	<th><label for="_spring_security_remember_me">Remember Me</label></th>
		                    	<td><input type='checkbox' id="_spring_security_remember_me" name="_spring_security_remember_me" /></td>
		                    </tr>
		            	</table>
		            	<div class="container" style="width: 250px;">
		            		<button id="submit" type="submit" name="submit" class="btn btn-lg btn-primary btn-block">Login</button>
		            	</div>
		            </form:form>
           		</div>	
            </div>
            
            <div class="panel panel-default">
        	 	<div class="panel-heading">
   					<h1 class="panel-title">Create Local Account</h1>
   				</div>
  				<div class="panel-body">
  					<div class="container" style="width: 250px;">
  						<br /><a href="${signUpUrl}" class="btn btn-lg btn-primary btn-block">Sign Up</a><br />
					</div>
           		</div>	
            </div>
        </div>
        <br />
    </tiles:putAttribute>
</tiles:insertDefinition>