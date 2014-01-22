<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="jqueryUrl" value="/static/js/jquery.min.1.10.2.js" />
<c:url var="bootstrapJsUrl" value="/static/js/bootstrap.min.3.0.2.js" />
<c:url var="bootstrapCssUrl" value="/static/css/bootstrap.min.3.0.2-slate.css" />
<c:url var="fontAwesomeUrl" value="/static/css/font-awesome/css/font-awesome.min.css" />

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QLAN | <tiles:insertAttribute name="title" ignore="true" /></title>

        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${bootstrapJsUrl}"></script>

        <link rel="stylesheet" href="${bootstrapCssUrl}" />
        <link rel="stylesheet" href="${fontAwesomeUrl}" />

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="//oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="//oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <script type="text/javascript">
            $(document).ready(function() {
                $('#logoutLink').on("click", function(e) {
                    e.preventDefault();
                    $('#logoutForm').submit();
                });
            });
        </script>

        <tiles:insertAttribute name="head" />
    </head>
    <body>
        <br />
        <div class="container">
            <div id="top">
                <tiles:insertAttribute name="header" />
            </div>
            <div id="middle" style="margin-bottom: 30px">
            	<c:if test="${not empty successStatus}">
		    		<div class="alert alert-dismissable alert-success">
		    			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		    			${successStatus}
		    		</div>
		    	</c:if>
		    	<c:if test="${not empty failureStatus}">
		    		<div class="alert alert-dismissable alert-danger">
		    			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		    			${failureStatus}
		    		</div>
		    	</c:if>
               	<tiles:insertAttribute name="body" />
            </div>
            <div id="bottom">
                <tiles:insertAttribute name="footer" />
            </div>
        </div>
    </body>
</html>