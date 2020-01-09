<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>Send Incident Notification</title>
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
						<h:inputText styleClass="textbox" value="#{incidentBean.from}" 
								id="from" required="true" requiredMessage="from is Required"></h:inputText>
						
						<h:outputText styleClass="label" value="To"></h:outputText>
						<h:inputText styleClass="textbox" value="#{incidentBean.to}" 
								id="to" required="true" requiredMessage="to is Required"></h:inputText>
						
						
						<h:outputText styleClass="label" value="Environment"></h:outputText>
						<h:selectManyCheckbox value="#{incidentBean.region}" layout="pageDirection">
						
							<f:selectItems value="#{incidentBean.regionSelectItem}" />
						</h:selectManyCheckbox>
					
						
						<h:outputText styleClass="label" value="BCC" ></h:outputText>
						<h:selectManyCheckbox value="#{incidentBean.bcc}" layout="pageDirection" >
							<f:selectItems value="#{incidentBean.mailSelectItem}"  />
						</h:selectManyCheckbox>
						
						<h:outputText styleClass="label" value="Subject"></h:outputText>
						<h:inputTextarea styleClass="textbox" value="#{incidentBean.subject}" 
								id="subject" required="true" requiredMessage="Subject is Required"></h:inputTextarea>
						
						<h:outputText styleClass="label" value="Issue Type"></h:outputText>
						<h:selectOneMenu value="#{incidentBean.issueType}" onchange="submit()" 
								valueChangeListener="#{incidentBean.assignIssueType}">
							<f:selectItem itemLabel="-----Select-----" itemValue="0" />
							<f:selectItem itemLabel="COMPLETE OUTAGE" itemValue="COMPLETE OUTAGE" />
							<f:selectItem itemLabel="PARTIAL OUTAGE" itemValue="PARTIAL OUTAGE" />
							<f:selectItem itemLabel="MAINTENANCE" itemValue="MAINTENANCE" />
							
							<f:selectItem itemLabel="ANNOUNCEMENT" itemValue="ANNOUNCEMENT" />
							
						</h:selectOneMenu>
						
						<h:outputText styleClass="label" value="Root Cause" 
								rendered="#{incidentBean.issueType != 'MAINTENANCE'  and incidentBean.issueType != 'ANNOUNCEMENT'}"></h:outputText>
						<h:selectOneMenu value="#{incidentBean.rca}" 
								rendered="#{incidentBean.issueType != 'MAINTENANCE' and incidentBean.issueType != 'ANNOUNCEMENT' }">
							<f:selectItem itemLabel="-----Select-----" itemValue="0" />
							<f:selectItem itemLabel="SOURCE ISSUE" itemValue="SOURCE ISSUE" />
							<f:selectItem itemLabel="VENDOR ISSUE" itemValue="VENDOR ISSUE" />
							<f:selectItem itemLabel="UDL ISSUE" itemValue="UDL ISSUE" />
							<!--<f:selectItem itemLabel="RCA INPROGRESS" itemValue="RCA INPROGRESS" /> -->
						</h:selectOneMenu>
				
						
						<h:outputText rendered="#{incidentBean.issueType != 'ANNOUNCEMENT' }" styleClass="label" value="Incident Details"></h:outputText>
						<h:inputTextarea rendered="#{incidentBean.issueType != 'ANNOUNCEMENT' }" styleClass="textbox" value="#{incidentBean.incident}" 
								id="incident" required="true" requiredMessage="Incident is Required"></h:inputTextarea>
								
					<h:outputText rendered="#{incidentBean.issueType != 'ANNOUNCEMENT' }" styleClass="label" value="Impact Window"></h:outputText>
					<h:inputTextarea rendered="#{incidentBean.issueType != 'ANNOUNCEMENT' }" styleClass="textbox" value="#{incidentBean.impact_window}"
						id="impact_window" required="true" requiredMessage="Impact Window is Required">
					</h:inputTextarea>
						
								
								
					
						
						<h:outputText styleClass="label" value="Maintenance From (GMT) (Eg. 21-Dec-2018 23:45:00)" 
								rendered="#{incidentBean.issueType == 'MAINTENANCE'}"></h:outputText>
						<h:inputText styleClass="textbox" value="#{incidentBean.maintenanceFrom}" 
								id="maintenanceFrom" rendered="#{incidentBean.issueType == 'MAINTENANCE'}" 
								converterMessage="maintenanceFrom must be of dd-MMM-yyyy HH:mm:ss format">
							<f:convertDateTime pattern="dd-MMM-yyyy HH:mm:ss"/>
						</h:inputText>
						
						<h:outputText styleClass="label" value="Maintenance To (GMT) (Eg. 21-Dec-2018 23:45:00)" 
								rendered="#{incidentBean.issueType == 'MAINTENANCE' }" ></h:outputText>
						<h:inputText styleClass="textbox" value="#{incidentBean.maintenanceTo}" id="maintenanceTo"
								rendered="#{incidentBean.issueType == 'MAINTENANCE' }" converterMessage="maintenanceTo must be of dd-MMM-yyyy HH:mm:ss format">
							<f:convertDateTime pattern="dd-MMM-yyyy HH:mm:ss"/>
						</h:inputText>
						
						
						
						
					
						
						
						
						<h:outputText styleClass="label" value="ANNOUNCEMENT"
						 rendered="#{incidentBean.issueType == 'ANNOUNCEMENT' }" ></h:outputText>
					<h:inputTextarea styleClass="textbox" value="#{incidentBean.announcement}"
						id="announcement" rendered="#{incidentBean.issueType == 'ANNOUNCEMENT' }" requiredMessage="announcement Window is Required">
					</h:inputTextarea>
					
					
					
			
						<h:outputText styleClass="label" value="Contact Team"></h:outputText>
						<h:inputText styleClass="textbox"  value="#{incidentBean.contactTeam}" id="contact" 
								required="true" requiredMessage="ContactTeam is Required"></h:inputText>
						
						<h:outputText styleClass="label" value="Escalation Contact"></h:outputText>
						<h:inputText styleClass="textbox"  value="#{incidentBean.escalationContact}" 
								id="esContact" required="true" requiredMessage="EscalationContact is Required"></h:inputText>
						
					</h:panelGrid>
					<br>
					<div id="buttonGroup">
						<h:commandButton value="Proceed" action="#{incidentBean.validate}" 
								rendered="#{!incidentBean.proceed}"></h:commandButton>
						<h:panelGroup rendered="#{incidentBean.proceed}">
							<h:outputText value="Confirm to Send Incident Notification"></h:outputText><br>
							<h:commandButton value="SendMail" action="#{incidentBean.sendMail}"></h:commandButton>
							<h:commandButton value="Cancel" action="#{incidentBean.revalidate}"></h:commandButton>
						</h:panelGroup>
					</div>
				</h:form>
				<br>
				<div>
				<h:outputText value="#{incidentBean.message}"></h:outputText>
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