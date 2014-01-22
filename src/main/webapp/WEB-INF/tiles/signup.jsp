<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="saveUrl" value="/user/save" />
<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Signup</tiles:putAttribute>
    <tiles:putAttribute name="body">
    	<form:form modelAttribute="user" method="post" action="${saveUrl}" cssClass="form-signin" role="form">
        	<div class="container" style="width: 600px">
            	<h2 class="form-signin-heading">Sign Up</h2>
            	<div style="color: red">
                	<form:errors path="*" />
                </div>
            	<table class="table">
            		<tr>
            			<th>User Name</th>
            			<td><form:input id="userName" path="userName" cssClass="form-control imput-md" placeholder="User Name" /></td>
            		</tr>
            		<tr>
            			<th>First Name</th>
            			<td><form:input id="firstName" path="firstName" cssClass="form-control imput-md" placeholder="First Name" /></td>
            		</tr>
            		<tr>
            			<th>Last Name</th>
            			<td><form:input id="lastName" path="lastName" cssClass="form-control imput-md" placeholder="Last Name" /></td>
            		</tr>
            		<tr>
            			<th>E-mail</th>
            			<td><form:input id="email" path="email" cssClass="form-control imput-md" placeholder="E-Mail" /></td>
            		</tr>
            		<tr>
            			<th>Password</th>
            			<td><form:password id="password" path="password" cssClass="form-control imput-md" placeholder="Password" /></td>
            		</tr>
            		<tr>
            			<th>Confirm Password</th>
            			<td><input type="password" id="confirmPassword" name="confirmPassword" class="form-control imput-md" placeholder="Confirm Password" /> </td>
            		</tr>
            	</table>
           	</div> 
           	<div class="container" style="width: 300px;">
                <button id="submit" type="submit" name="submit" class="btn btn-lg btn-primary btn-block">Submit</button>
            </div>
        </form:form>
        <br />
    </tiles:putAttribute>
</tiles:insertDefinition>