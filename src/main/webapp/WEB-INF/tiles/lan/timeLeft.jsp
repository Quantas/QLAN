<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="timeLeftContainer">
	<hr />
	<h4>Time Left</h4>
	<div class="progress progress-striped active" style="height: 25px">
		<c:choose>
			<c:when test="${percentLeft >= 50.0}">
				<c:set var="progressClass" value="progress-bar-success" />
			</c:when>
			<c:when test="${percentLeft < 50.0 and percentLeft > 25.0}">
				<c:set var="progressClass" value="progress-bar-warning" />
			</c:when>
			<c:otherwise>
				<c:set var="progressClass" value="progress-bar-danger" />
			</c:otherwise>
		</c:choose>
		<div class="progress-bar ${progressClass}" role="progressbar" aria-valuenow="${percentLeft}" aria-valuemin="0" aria-valuemax="100" style="width: ${percentLeft}%">
						<span class="sr-only">${percentLeft}% Time Left</span>
		</div>
	</div>
</div>