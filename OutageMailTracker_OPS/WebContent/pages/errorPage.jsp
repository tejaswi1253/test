<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="javax.faces.context.FacesContext"%>
<%@page import="javax.faces.context.ExternalContext"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<link href="<%=request.getContextPath()%>/page-resources/styles/style.css" media="screen"
	rel="stylesheet">
<script type = "text/javascript" >
history.pushState(null, null, 'errorPage.jsp');
window.addEventListener('popstate', function(event) {
history.pushState(null, null, 'errorPage.jsp');
});
</script>

</head>
<body>

	<f:view>
	
			<h:form prependId="false">


				<div id="container">
					<!-- Left navigation pane links -->
					<div id="menu1"></div>


					<!-- Right navigation pane links -->
					<div id="menu2"></div>


					<!-- Main body page for displaying details -->
					<div id="errorPage">
						
						<h2>
							<h:outputText value="Error"></h:outputText><br>
							<h:outputText value="Take a deep breath"></h:outputText>
							
						</h2>
						<img src="<%=request.getContextPath()%>/page-resources/images/meditation.jpg" style="width:300px;height:300px;">
					<h2><h:outputText value="Try login after some time"></h:outputText></h2>
						
					</div>


				</div>
			</h:form>
		
		<jsp:include page="footer.jsp"></jsp:include>
	</f:view>
</body>
</html>