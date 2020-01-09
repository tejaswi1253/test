<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/page-resources/styles/style.css" media="screen"
	rel="stylesheet">
<script>

</script>
</head>
<body>
	<f:view>
		
			<ul>
				<li><h:outputLink id="nav-dashboard" value="#{request.contextPath}/app/pages/dashboard.jsp?faces-redirect=true"><h:outputText value="Dashboard"></h:outputText></h:outputLink></li><br>
				<li><h:outputLink id="nav-sendIncident" value="#{request.contextPath}/app/pages/sendIncident.jsp?faces-redirect=true"><h:outputText value="Send Incident Notification"></h:outputText></h:outputLink></li><br>
				<li><h:outputLink id="nav-sendResolved" value="#{request.contextPath}/app/pages/sendResolved.jsp?faces-redirect=true"><h:outputText value="Send Resolved Notification"></h:outputText></h:outputLink></li><br>
			</ul>
		
	</f:view>


</body>
</html>