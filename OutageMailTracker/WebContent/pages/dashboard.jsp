<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Dashboard-Notification Tracker</title>
<link href="<%=request.getContextPath()%>/page-resources/styles/style.css" media="screen"
	rel="stylesheet">
</head>
<body>

	<f:view>
	<div id="menu1"><jsp:include page="navBar.jsp"></jsp:include></div>
	<center>
		<jsp:include page="header.jsp"></jsp:include>
			<div id="datatable">
				<h:dataTable styleClass="tabl" rowClasses="row1,row2" value="#{dashboardBean.mdList}" var="match"
						rendered="#{not empty dashboardBean.mdList }">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Notification Time"></h:outputText>
						</f:facet>
						<h:panelGroup>
							<h:form>
								<h:panelGroup rendered="#{not empty match.res.subject}" >
									<h:outputText value="Resolution: " />
									<h:outputText value="#{match.res.created}" >
										<f:convertDateTime pattern="dd-MM-yyyy HH:mm:ss"/>
									</h:outputText>
									<br>
								</h:panelGroup>
								<h:panelGroup rendered="#{not empty match.inc.subject}" >
									<h:outputText value="Incident: " />
									<h:outputText value="#{match.inc.created}">
										<f:convertDateTime pattern="dd-MM-yyyy HH:mm:ss"/>
									</h:outputText>	
									<br>
								</h:panelGroup>
							</h:form>
						</h:panelGroup>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Incident"></h:outputText>
						</f:facet>
						<h:panelGroup>
							<h:form>
								<h:panelGroup rendered="#{empty match.inc.subject}" >
									<h:outputText value="No Incident Notification Found" />
								</h:panelGroup>
								<h:panelGroup rendered="#{not empty match.inc.subject}" >
									<h:outputText value="#{match.inc.subject}" /><br>
									<h:outputText value="#{match.inc.stopTime}">
										<f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss"/>
									</h:outputText>	
									<h:outputText value="#{match.inc.maintenanceFrom}">
										<f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss"/>
									</h:outputText>	<br>
									<h:outputText value="#{match.inc.maintenanceTo}">
										<f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss"/>
									</h:outputText>	
									<br><h:commandButton value="View" action="#{dashboardBean.viewIncidentNotification(match.inc.incId)}" ></h:commandButton>
								</h:panelGroup>
							</h:form>
						</h:panelGroup>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Resolved"></h:outputText>
						</f:facet>
						<h:panelGroup>
							<h:form>
								<h:panelGroup rendered="#{empty match.res.subject}" >
									<h:outputText value="No Resolved Notification Found" /><br>
									<h:commandLink value="Send Resolved Notification" action="#{dashboardBean.sendResolution(match.inc.incId)}"></h:commandLink>
								</h:panelGroup>
								<h:panelGroup rendered="#{not empty match.res.subject}" >
									
									<h:outputText value="#{match.res.subject}" /><br>
									<h:outputText value="#{match.res.maintenanceFrom}" >
										<f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss"/>
									</h:outputText>	<br>
									<h:outputText value="#{match.res.maintenanceTo}">
										<f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss"/>
									</h:outputText>	
									<h:outputText value="#{match.res.stopTime}" >
										<f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss"/>
									</h:outputText>	<br>
									<h:outputText value="#{match.res.resolvedTime}" >
										<f:convertDateTime pattern="yyyy-MM-dd HH:mm:ss"/>
									</h:outputText>		
									<br><h:commandButton value="View" action="#{dashboardBean.viewResolvedNotification(match.res.resId)}" ></h:commandButton>								
								</h:panelGroup>
							</h:form>
						</h:panelGroup>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="Backfill Status"></h:outputText>
						</f:facet>
						<h:panelGroup>
							<h:form>
								<h:panelGroup rendered="#{not empty match.res.subject}" >
									<h:selectBooleanCheckbox value="#{match.res.backfillStatusBool}" valueChangeListener="#{dashboardBean.assignBackfillStatus}" onclick="submit()">
										<f:attribute name="selectedResId" value="#{match.res.resId}" />
									</h:selectBooleanCheckbox>
									
							   		<h:outputText value="#{match.res.backfillStatus}"></h:outputText>
								</h:panelGroup>
							</h:form>
						</h:panelGroup>
					</h:column>
				</h:dataTable>
				<h:outputText styleClass="noData" value="No data available" rendered="#{empty dashboardBean.mdList }"></h:outputText>
			</div>
			<div id="footer">
			<h:form id="footerForm">
			<table class="footerItems">
				<tr style="border: none;">
					<td style="border: none;" ><h:commandButton styleClass="navButton" value="←" action="#{dashboardBean.prevPage}" disabled="#{dashboardBean.disabledPrev }"></h:commandButton></td>
					<td style="border: none;" ><h:commandButton id="centerButton" value="Update" action="#{dashboardBean.updateBackfillStatus}" rendered="#{dashboardBean.backfillStatusUpdate eq 'true'}" onchange="submit"></h:commandButton></td>
					<td style="border: none;" ><h:commandButton styleClass="navButton" value="→" action="#{dashboardBean.nextPage}" disabled="#{dashboardBean.disabledNext }"></h:commandButton></td>
				</tr>
			</table>
			
			</h:form>
				
			</div>
	</center>
	</f:view>
</body>
</html>