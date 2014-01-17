<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="homeUrl" value="/" />
<c:url var="logoutUrl" value="/logout" />
<c:url var="loginUrl" value="/login" />
<c:url var="signupUrl" value="/user/signup" />

<c:url var="profileUrl" value="/profile" />
<c:url var="usersUrl" value="/admin/users" />
<c:url var="statusUrl" value="/status" />
<c:url var="sessionsUrl" value="/status/sessions" />

<security:authentication var="user" property="principal" />

<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${homeUrl}">QLAN</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
            <security:authorize access="permitAll() && !isAuthenticated()">
                <li><a href="${loginUrl}"><i class="fa fa-sign-in fa-fw"></i>&nbsp;&nbsp;Login</a></li>
                <li><a href="${signupUrl}"><i class="fa fa-user fa-fw"></i>&nbsp;&nbsp;Sign Up</a></li>
            </security:authorize>
            <%-- Show the admin menu if we have the correct role --%>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-wrench fa-fw"></i>&nbsp;&nbsp;Admin <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                    	<security:authorize url="/admin/users">
                            <li><a href="${usersUrl}"><i class="fa fa-user fa-fw"></i>&nbsp;&nbsp;Users</a></li>
                        </security:authorize>
                        <security:authorize url="/status/sessions">
                            <li><a href="${sessionsUrl}"><i class="fa fa-signal fa-fw"></i>&nbsp;&nbsp;Sessions</a></li>
                        </security:authorize>
                        <security:authorize url="/status">
                            <li><a href="${statusUrl}"><i class="fa fa-dashboard fa-fw"></i>&nbsp;&nbsp;Status</a></li>
                        </security:authorize>
                    </ul>
                </li>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
		        <li class="dropdown">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                <img src="https://www.gravatar.com/avatar/${user.gravatarHash}?r=pg&d=identicon&s=20" />
		                &nbsp;&nbsp;${user.firstName}&nbsp;${user.lastName}
		                <b class="caret"></b>
		            </a>
		            <ul class="dropdown-menu">
		            	<li>
		                    <a id="profileLink" href="${profileUrl}"><i class="fa fa-user fa-fw"></i>&nbsp;&nbsp;Profile</a>
		                </li>
		                <li>
		                    <a id="logoutLink" href="#"><i class="fa fa-sign-out fa-fw"></i>&nbsp;&nbsp;Logout</a>
		                </li>
		            </ul>
		        </li>
	        </security:authorize>
        </ul>
    </div>
</nav>

<form:form id="logoutForm" action="${logoutUrl}" method="post">
</form:form>