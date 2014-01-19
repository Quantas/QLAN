<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="openIdUrl" value="/login/openid" />

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">Login</tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container" style="width: 300px">
            <c:url value="/login" var="loginUrl"/>
            <form:form name="f" action="${loginUrl}" method="post" cssClass="form-signin" role="form">
                <h2 class="form-signin-heading">Login</h2>
                <fieldset>
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
                    <input type="text" id="username" name="username" class="form-control input-lg" placeholder="User Name" />
                    <input type="password" id="password" name="password" class="form-control input-lg" placeholder="Password"/>
                    <label class="checkbox" for="_spring_security_remember_me"><input type='checkbox' id="_spring_security_remember_me" name='_spring_security_remember_me'/>Remember Me</label>
                    <button id="submit" type="submit" name="submit" class="btn btn-lg btn-primary btn-block">Submit</button>
                </fieldset>
            </form:form>
            <br />
            <form action="${openIdUrl}" method="post" target="_top">
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            	<input id="openid_identifier" name="openid_identifier" maxlength="100" type="hidden" value="http://steamcommunity.com/openid" />
            	<input type="image" src="http://cdn.steamcommunity.com/public/images/signinthroughsteam/sits_large_noborder.png">
            </form>
        </div>
        <br />
    </tiles:putAttribute>
</tiles:insertDefinition>