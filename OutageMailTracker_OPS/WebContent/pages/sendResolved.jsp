<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>Send Resolved Notification</title>
<link href="<%=request.getContextPath()%>/page-resources/styles/style.css" media="screen"
	rel="stylesheet">
</head>
<body>
	<f:view>
			<!-- Left navigation pane links -->
			<div id="menu1"><jsp:include page="navBar.jsp"></jsp:include></div>
		<center>
			<jsp:include page="header.jsp"></jsp:include><br>
			
			</center>		
			<h:panelGroup id="container" >
				<h:form id="form">
					<h:panelGrid id="input" columns="2" styleClass="table">
						<h:outputText styleClass="label" value="From"></h:outputText>
						<h:inputText styleClass="textbox" value="#{resolutionBean.from}" id="from" 
								required="true" requiredMessage="from is Required"></h:inputText>
						
						<h:outputText styleClass="label" value="To"></h:outputText>
						<h:inputText styleClass="textbox" value="#{resolutionBean.to}" id="to" 
								required="true" requiredMessage="to is Required"></h:inputText>
						
						<h:outputText styleClass="label" value="Environment"></h:outputText>
						<h:panelGroup>
							<h:selectManyCheckbox value="#{resolutionBean.region}" 
									rendered="#{resolutionBean.inc eq null}" layout="pageDirection" >
									
								<f:selectItems value="#{resolutionBean.regionSelectItem}" />
							</h:selectManyCheckbox>
							<h:inputText styleClass="textbox" value="#{resolutionBean.regionOut}" 
									rendered="#{resolutionBean.inc ne null}" required="true" 
									requiredMessage="region is Required"></h:inputText>
						</h:panelGroup>
						
						
						
						
						<h:outputText styleClass="label" value="BCC"></h:outputText>
						<h:panelGroup>
							<h:selectManyCheckbox value="#{resolutionBean.bcc}" 
									rendered="#{resolutionBean.inc eq null}" layout="pageDirection">
								<f:selectItems value="#{resolutionBean.mailSelectItem}"  />
							</h:selectManyCheckbox>
							<h:inputText styleClass="textbox" value="#{resolutionBean.bccOut}" 
									rendered="#{resolutionBean.inc ne null}" required="true" 
									requiredMessage="bcc is Required"></h:inputText>
						</h:panelGroup>
						<h:outputText styleClass="label" value="Subject"></h:outputText>
						<h:inputTextarea styleClass="textbox" value="#{resolutionBean.subject}" 
								id="subject" required="true" requiredMessage="Subject is Required"></h:inputTextarea>
						
						<h:outputText styleClass="label" value="Issue Type"></h:outputText>
						<h:selectOneMenu value="#{resolutionBean.issueType}" onchange="submit()" 
								valueChangeListener="#{resolutionBean.assignIssueType}">
							<f:selectItem itemLabel="-----Select-----" itemValue="0" />
							<f:selectItem itemLabel="COMPLETE OUTAGE" itemValue="COMPLETE OUTAGE" />
							<f:selectItem itemLabel="PARTIAL OUTAGE" itemValue="PARTIAL OUTAGE" />
							<f:selectItem itemLabel="MAINTENANCE" itemValue="MAINTENANCE" />
						
						</h:selectOneMenu>
						
						<h:outputText styleClass="label" value="Root Cause" 
								rendered="#{resolutionBean.issueType != 'MAINTENANCE' }"></h:outputText>
						<h:selectOneMenu value="#{resolutionBean.rca}" 
								rendered="#{resolutionBean.issueType != 'MAINTENANCE' }">
							<f:selectItem itemLabel="-----Select-----" itemValue="0" />
							<f:selectItem itemLabel="SOURCE ISSUE" itemValue="SOURCE ISSUE" />
							<f:selectItem itemLabel="VENDOR ISSUE" itemValue="VENDOR ISSUE" />
							<f:selectItem itemLabel="UDL ISSUE" itemValue="UDL ISSUE" />
							<f:selectItem itemLabel="RCA INPROGRESS" itemValue="RCA INPROGRESS" />
						</h:selectOneMenu>
						
						<h:outputText styleClass="label" value="Resolution Details"></h:outputText>
						<h:inputTextarea styleClass="textbox" value="#{resolutionBean.resolution}" 
								id="resolution" required="true" requiredMessage="Resolution is Required">
								</h:inputTextarea>
						
						<h:outputText styleClass="label" value="Maintenance From (GMT) (Eg. 21-Dec-2018 23:45:00)" 
								rendered="#{resolutionBean.issueType == 'MAINTENANCE'}"></h:outputText>
						<h:inputText styleClass="textbox" value="#{resolutionBean.maintenanceFrom}" 
								id="maintenanceFrom" rendered="#{resolutionBean.issueType == 'MAINTENANCE'}" 
								converterMessage="maintenanceFrom must be of dd-MMM-yyyy HH:mm:ss format">
							<f:convertDateTime pattern="dd-MMM-yyyy HH:mm:ss"/>
						</h:inputText>
						
						<h:outputText styleClass="label" value="Maintenance To (GMT) (Eg. 21-Dec-2018 23:45:00)" 
								rendered="#{resolutionBean.issueType == 'MAINTENANCE' }" ></h:outputText>
						<h:inputText styleClass="textbox" value="#{resolutionBean.maintenanceTo}" 
								id="maintenanceTo" rendered="#{resolutionBean.issueType == 'MAINTENANCE' }" 
								converterMessage="maintenanceTo must be of dd-MMM-yyyy HH:mm:ss format">
							<f:convertDateTime pattern="dd-MMM-yyyy HH:mm:ss"/>
						</h:inputText>
						<h:outputText styleClass="label" value="Contact Team"></h:outputText>
						<h:inputText styleClass="textbox"  value="#{resolutionBean.contactTeam}" id="contact" 
								required="true" requiredMessage="ContactTeam is Required"></h:inputText>
						
						<h:outputText styleClass="label" value="Escalation Contact"></h:outputText>
						<h:inputText styleClass="textbox"  value="#{resolutionBean.escalationContact}" id="esContact" 
								required="true" requiredMessage="EscalationContact is Required"></h:inputText>
						
					</h:panelGrid>
					<br>
					<div id="buttonGroup">
						<h:commandButton styleClass="button" value="Proceed" action="#{resolutionBean.validate}" 
								rendered="#{!resolutionBean.proceed}"></h:commandButton>
						<h:panelGroup rendered="#{resolutionBean.proceed}">
							<h:outputText value="Confirm to Send Resolved Notification"></h:outputText><br>
							<h:commandButton styleClass="button" value="SendMail" action="#{resolutionBean.sendMail}"></h:commandButton>
							<h:commandButton styleClass="button" value="Cancel" action="#{resolutionBean.revalidate}"></h:commandButton>
						</h:panelGroup>
					</div>
				</h:form>
				<br>
				<div>
				<h:outputText value="#{resolutionBean.message}"></h:outputText>
				</div>
			</h:panelGroup>
			<br>
		<center>	
			<h:messages></h:messages>
			<!--<jsp:include page="footer.jsp"></jsp:include>-->
		</center>
		
	</f:view>

</body>
</html>