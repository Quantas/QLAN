<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="saveUrl" value="/user/save" />
<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Signup</tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container" style="width: 300px">
            <form:form modelAttribute="user" method="post" action="${saveUrl}" cssClass="form-signin" role="form">
                <h2 class="form-signin-heading">Sign Up</h2>
                <div style="color: red">
                	<form:errors path="*" />
                </div>
                <form:input id="userName" path="userName" cssClass="form-control input-lg" placeholder="User Name" />
                <form:input id="firstName" path="firstName" cssClass="form-control input-lg" placeholder="First Name" />
                <form:input id="lastName" path="lastName" cssClass="form-control input-lg" placeholder="Last Name" />
                <form:input id="email" path="email" cssClass="form-control input-lg" placeholder="E-Mail" />
                <form:password id="password" path="password" cssClass="form-control input-lg" placeholder="Password" />
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control input-lg" placeholder="Confirm Password" /> 
                <button id="submit" type="submit" name="submit" class="btn btn-lg btn-primary btn-block">Submit</button>
            </form:form>
        </div>
        <br />
    </tiles:putAttribute>
</tiles:insertDefinition>