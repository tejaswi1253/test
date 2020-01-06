<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>View Notification</title>
<link href="<%=request.getContextPath()%>/page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<style>
	#viewNotification{
	left:25%;
	position:relative;
	display: block;
	width: 700px;
}
.textBold{
	font-weight: bold;
}
	</style>
</head>
<body>
	<f:view>
	
			<!-- Left navigation pane links -->
			<div id="menu1"><jsp:include page="navBar.jsp"></jsp:include></div>
		<center>
			<jsp:include page="header.jsp"></jsp:include><br>
			</center>		
			<div id="viewNotification" >
				<h:outputText styleClass="textBold" value="From : "></h:outputText>
				<h:outputText value="#{viewBean.from }"></h:outputText><br>
			
				<h:outputText styleClass="textBold" value="To : "></h:outputText>
				<h:outputText value="#{viewBean.to }"></h:outputText><br>
				
				<h:outputText styleClass="textBold" value="BCC : "></h:outputText>
				<h:outputText value="#{viewBean.bcc }"></h:outputText><br>
				
				<h:outputText styleClass="textBold" value="Incident Notification date : " 
						rendered="#{not empty viewBean.inc.subject }"></h:outputText>
				<h:outputText value="#{viewBean.inc.created }" 
						rendered="#{not empty viewBean.inc.subject }"></h:outputText><br>
				
				<h:outputText styleClass="textBold" value="Resolution Notification date : "
						rendered="#{not empty viewBean.res.subject }"></h:outputText>
				<h:outputText value="#{viewBean.res.created }"
						rendered="#{not empty viewBean.res.subject }"></h:outputText><br>
				<br>
				<h:outputText value="#{viewBean.htmlText }" escape="false"></h:outputText>
			</div>
			<br>

		
	</f:view>

</body>
</html>