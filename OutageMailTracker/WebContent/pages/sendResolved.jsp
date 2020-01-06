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
						
						<h:outputText styleClass="label" value="Region"></h:outputText>
						<h:panelGroup>
							<h:selectManyCheckbox value="#{resolutionBean.region}" 
									rendered="#{resolutionBean.inc eq null}" layout="pageDirection" 
									onclick="submit()" valueChangeListener="#{resolutionBean.setStreamTypeList}">
								<f:selectItems value="#{resolutionBean.regionSelectItem}" />
							</h:selectManyCheckbox>
							<h:inputText styleClass="textbox" value="#{resolutionBean.regionOut}" 
									rendered="#{resolutionBean.inc ne null}" required="true" 
									requiredMessage="region is Required"></h:inputText>
						</h:panelGroup>
						
						<h:outputText styleClass="label" value="Stream Type"></h:outputText>
						<h:panelGroup>
							<h:selectOneMenu value="#{resolutionBean.streamType}" onchange="submit()" 
								rendered="#{resolutionBean.inc eq null}"
								valueChangeListener="#{resolutionBean.setVendorList}">
								<f:selectItem itemLabel="-----Select-----" itemValue="0" />
								<f:selectItems value="#{resolutionBean.streamSelectItem}" />
							</h:selectOneMenu>
							<h:inputText styleClass="textbox" value="#{resolutionBean.streamTypeOut}" 
									rendered="#{resolutionBean.inc ne null}" required="true" 
									requiredMessage="streamType is Required"></h:inputText>
						</h:panelGroup>
						
						<h:outputText styleClass="label" value="Vendor"></h:outputText>
						<h:panelGroup>
							<h:selectManyCheckbox value="#{resolutionBean.vendor}" layout="pageDirection" 
								onclick="submit()" valueChangeListener="#{resolutionBean.setMailAddress}" >
								<f:selectItems value="#{resolutionBean.vendorSelectItem}" />
							</h:selectManyCheckbox>
							<h:inputText styleClass="textbox" value="#{resolutionBean.vendorOut}" 
									rendered="#{resolutionBean.inc ne null}" required="true" 
									requiredMessage="vendor is Required"></h:inputText>
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
							<f:selectItem itemLabel="STREAMING ISSUE" itemValue="STREAMING ISSUE" />
							<f:selectItem itemLabel="ENDPOINT ISSUE" itemValue="ENDPOINT ISSUE" />
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
						
						<h:outputText styleClass="label" value="Streaming Stopped Time (GMT) (Eg. 21-Dec-2018 23:45:00)" 
								rendered="#{resolutionBean.issueType != 'MAINTENANCE' }"></h:outputText>
						<h:inputText styleClass="textbox" value="#{resolutionBean.stopTime}" id="stopTime" 
								required="true" rendered="#{resolutionBean.issueType != 'MAINTENANCE' }" 
								requiredMessage="StopTime is Required" 
								converterMessage="stopTime must be of dd-MMM-yyyy HH:mm:ss format">
							<f:convertDateTime pattern="dd-MMM-yyyy HH:mm:ss"/>
						</h:inputText>
						
						<h:outputText styleClass="label" value="Streaming Resolved Time (GMT) (Eg. 21-Dec-2018 23:45:00)" 
								rendered="#{resolutionBean.issueType != 'MAINTENANCE' }"></h:outputText>
						<h:inputText styleClass="textbox" value="#{resolutionBean.resolvedTime}" id="resolvedTime" 
								required="true" rendered="#{resolutionBean.issueType != 'MAINTENANCE' }" 
								requiredMessage="ResolvedTime is Required" 
								converterMessage="resolvedTime must be of dd-MMM-yyyy HH:mm:ss format">
							<f:convertDateTime pattern="dd-MMM-yyyy HH:mm:ss"/>
						</h:inputText>						
						
						<h:outputText styleClass="label" value="Data Loss"></h:outputText>
						<h:selectOneMenu value="#{resolutionBean.dataLoss}" id="loss" onchange="submit()" 
								valueChangeListener="#{resolutionBean.assignDataLoss}">
							<f:selectItem itemLabel="-----Select-----" itemValue="0" />
							<f:selectItem itemLabel="YES" itemValue="YES" />
							<f:selectItem itemLabel="NO" itemValue="NO" />
							<f:selectItem itemLabel="WILL BE UPDATED" itemValue="WILL BE UPDATED" />
						</h:selectOneMenu>
						
						<h:outputText styleClass="label" value="Data Availability (select if no data available)" 
								rendered="#{resolutionBean.dataLoss=='YES' and resolutionBean.streamType != 'DAS'}"></h:outputText>
						<h:panelGroup rendered="#{resolutionBean.dataLoss=='YES' and resolutionBean.streamType != 'DAS'}" >
							<h:selectBooleanCheckbox value="#{resolutionBean.outageDataNotAvailable}" 
									valueChangeListener="#{resolutionBean.outageDataAvailability}" onclick="submit()">							
							</h:selectBooleanCheckbox>							
					   		<h:outputText value="Complete Data Loss - No data available on source" 
					   				rendered="#{resolutionBean.outageDataNotAvailable}"></h:outputText>
					   		<h:outputText value="Data Available on source for backfill" 
					   				rendered="#{not resolutionBean.outageDataNotAvailable}"></h:outputText>
						</h:panelGroup>
						
						<h:outputText styleClass="label" value="Historical Extraction From (GMT) (Eg. 21-Dec-2018 23:45:00)" 
								rendered="#{(not resolutionBean.outageDataNotAvailable) and resolutionBean.dataLoss=='YES' 
								and (resolutionBean.streamType == 'PI' or resolutionBean.streamType == 'OPC' ) }">
								</h:outputText>
						<h:inputText styleClass="textbox" value="#{resolutionBean.backfillFrom}" id="backfillFrom" 
								rendered="#{(not resolutionBean.outageDataNotAvailable) and resolutionBean.dataLoss=='YES' 
								and (resolutionBean.streamType == 'PI' or resolutionBean.streamType == 'OPC' ) }" 
								converterMessage="backfillFrom must be of dd-MMM-yyyy HH:mm:ss format">
							<f:convertDateTime pattern="dd-MMM-yyyy HH:mm:ss"/>
						</h:inputText>
						
						<h:outputText styleClass="label" value="Historical Extraction To (GMT) (Eg. 21-Dec-2018 23:45:00)" 
								rendered="#{(not resolutionBean.outageDataNotAvailable) and resolutionBean.dataLoss=='YES' 
								and (resolutionBean.streamType == 'PI' or resolutionBean.streamType == 'OPC' ) }" >
								</h:outputText>
						<h:inputText styleClass="textbox" value="#{resolutionBean.backfillTo}" id="backfillTo" 
								rendered="#{(not resolutionBean.outageDataNotAvailable) and resolutionBean.dataLoss=='YES' 
								and (resolutionBean.streamType == 'PI' or resolutionBean.streamType == 'OPC' ) }" 
								converterMessage="backfillTo must be of dd-MMM-yyyy HH:mm:ss format">
							<f:convertDateTime pattern="dd-MMM-yyyy HH:mm:ss"/>
						</h:inputText>
						
						<h:outputText styleClass="label" value="Business Impact"></h:outputText>
						<h:panelGroup>
							<h:outputText value="#{resolutionBean.businessImpact}" id="bImpactOut" 
									rendered="#{not resolutionBean.bImpactEdit}"></h:outputText>				
							<h:inputText styleClass="textbox" value="#{resolutionBean.businessImpact}" id="bImpact" 
									rendered="#{  resolutionBean.bImpactEdit}"></h:inputText>
							<h:commandButton value="Edit" action="#{resolutionBean.editBImpact}" 
									rendered="#{not resolutionBean.bImpactEdit and resolutionBean.vendor ne '' and resolutionBean.vendor ne null}"></h:commandButton>
							<h:commandButton value="Save" action="#{resolutionBean.saveBImpact}" 
									rendered="#{ resolutionBean.bImpactEdit and resolutionBean.vendor ne '' and resolutionBean.vendor ne null}"></h:commandButton>
						</h:panelGroup>
						
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