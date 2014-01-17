<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="saveUrl" value="/admin/setup/newlan/save" />
<c:url var="momentJsUrl" value="/static/js/moment.min.js" />
<c:url var="bootstrapDateTimeJsUrl" value="/static/js/bootstrap-datetimepicker.min.js" />
<c:url var="bootstrapDateTimeCssUrl" value="/static/css/bootstrap-datetimepicker.min.css" />

<tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title">New LAN</tiles:putAttribute>
    <tiles:putAttribute name="head">
    	<script type="text/javascript" src="${momentJsUrl}"></script>
    	<script type="text/javascript" src="${bootstrapDateTimeJsUrl}"></script>
        <link rel="stylesheet" href="${bootstrapDateTimeCssUrl}" />
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container" style="width: 500px">
            <form:form modelAttribute="lan" method="post" action="${saveUrl}" cssClass="form-signin" role="form">
                <h2 class="form-signin-heading">New LAN</h2>
                <div style="color: red">
                	<form:errors path="*" />
                </div>
                <form:input id="name" path="name" cssClass="form-control input-lg" placeholder="LAN Name" />
                <form:input id="location" path="location" cssClass="form-control input-lg" placeholder="Location" />
			  	<div class="form-group">
	               <div class='input-group date' id='startPicker'>
	                   <form:input path="start" data-format="YYYY-MM-DD HH:mm:ss" cssClass="form-control input-lg" placeholder="Start Date/Time"></form:input>
	                   <span class="input-group-addon">
	                   		<span class="glyphicon glyphicon-time"></span>
	                   </span>
	               </div>
	           	</div>
	           	<div class="form-group">
	               <div class='input-group date' id='endPicker'>
	                   <form:input path="end" data-format="YYYY-MM-DD HH:mm:ss" cssClass="form-control input-lg" placeholder="End Date/Time"></form:input>
	                   <span class="input-group-addon">
                   			<span class="glyphicon glyphicon-time"></span>
	                   </span>
	               </div>
	           	</div>
                <button id="submit" type="submit" name="submit" class="btn btn-lg btn-primary btn-block">Submit</button>
            </form:form>
        </div>
        <br />
        <script type="text/javascript">
		  $(function() {
			$('#start').val('');
			$('#end').val('');
			
		    $('#startPicker').datetimepicker();
		    $('#endPicker').datetimepicker();
		  
		  });
		</script>
    </tiles:putAttribute>
</tiles:insertDefinition>