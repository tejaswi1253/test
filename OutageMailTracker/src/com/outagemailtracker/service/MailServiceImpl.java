package com.outagemailtracker.service;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.outagemailtracker.beans.Incident;
import com.outagemailtracker.beans.Resolution;
import com.outagemailtracker.resources.OmtLogger;

public class MailServiceImpl implements MailService {
	   public String incidentMailSend(Incident inc) throws Exception {
		   String output="";
	      String to = inc.getTo();
	      String from = inc.getFrom();
	      final String username = "";//change accordingly
	      final String password = "";//change accordingly
	      String host = "smtp.dsc.bp.com";
	      
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
		});

	      try {
	            // Create a default MimeMessage object.
	            Message message = new MimeMessage(session);
	            

	   	   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));

		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		   message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(inc.getBcc()));

		   // Set Subject: header field
		   message.setSubject(inc.getSubject());
		   String region=String.join("<br>", inc.getRegion());
//		   for(String reg:inc.getRegion()) {
//			   region=region+reg+"\n";
//		   }
		   SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		   String stopTime=sdf.format(inc.getStopTime());

		// This mail has 2 part, the BODY and the embedded image
	          MimeMultipart multipart = new MimeMultipart("related");
	         
	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String cid=String.format("%s-%s", "img-", UUID.randomUUID());
	         String htmlText = "<table max-width=\"650px\" style=\"border:1px solid green; border-collapse:collapse;font-family:Verdana;table-layout:fixed;\">\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td colspan=2 style=\"border:1px solid green;\"><img src=\"cid:"+cid+"\" /></td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td colspan=2 style=\"border:1px solid green;font-size:85%;background-color:#ffc000;font-weight:bold;padding:5px 5px 5px 5px;\">Incident Summary</td>\r\n" + 
			   		"  </tr>\r\n" +
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Region</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+region +"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Incident Details</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+ inc.getIncident()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Streaming Stopped Time</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+stopTime+" GMT</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Current Business Impact</td> \r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\"> "+inc.getBusinessImpact()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Data Loss</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+inc.getDataLoss()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Contact Team</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+inc.getContactTeam()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Escalation Contact</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+inc.getEscalationContact()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"</table>";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         MimeBodyPart imagePart = new MimeBodyPart();
	         ClassLoader classLoader = Test.class.getClassLoader();
	         File f=new File(classLoader.getResource("IncidentNotificationHeader.JPG").getFile());
	         imagePart.attachFile(f);
	         imagePart.setContentID("<" + cid + ">");
	         imagePart.setDisposition(MimeBodyPart.INLINE);
	         multipart.addBodyPart(imagePart);

	         // put everything together
	         message.setContent(multipart);
		   // Send the actual HTML message, as big as you like
//		   String htmlContent="";
//		   message.setContent(htmlContent,"text/html");

		   // Send message
		   try {
		   Transport.send(message);
		   System.out.println("Sent message successfully....");
		   output="success";
		   }catch(Exception e) {
			   throw new Exception("MailServiceImpl.SEND_MAIL_FAILED");
		   }
		   

	      } catch (MessagingException e) {
	    	  output="failure";
				if(e.getMessage().contains("Service"))
				{
					OmtLogger.logError(this.getClass().getName(), "incidentMailSend",e.getMessage());
				}
				throw e;
	      }
	      return output;
	   }
	   public String resolvedMailSend(Resolution res) throws Exception {
		   String output="";
	      String to = res.getTo();
	      String from = res.getFrom();
	      final String username = "";//change accordingly
	      final String password = "";//change accordingly
	      String host = "smtp.dsc.bp.com";;//change accordingly
	      
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
		});

	      try {
	            // Create a default MimeMessage object.
	            Message message = new MimeMessage(session);
	            

	   	   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));

		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		   message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(res.getBcc()));

		   // Set Subject: header field
		   message.setSubject(res.getSubject());
		   String region=String.join("<br>", res.getRegion());
		   SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		   String stopTime=sdf.format(res.getStopTime());
		   String resTime=sdf.format(res.getResolvedTime());
		   String outageWindow=stopTime+" GMT TO <br>"+resTime+" GMT";


		// This mail has 2 part, the BODY and the embedded image
	          MimeMultipart multipart = new MimeMultipart("related");
	         
	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String cid=String.format("%s-%s", "img-", UUID.randomUUID());
	         String htmlText = "<table max-width=\"650px\" style=\"border:1px solid green; border-collapse:collapse;font-family:Verdana;table-layout:fixed;\">\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td colspan=2 style=\"border:1px solid green;\"><img src=\"cid:"+cid+"\" /></td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td colspan=2 style=\"border:1px solid green;font-size:85%;color:#abcf92;background-color:#395524;font-weight:bold;padding:5px 5px 5px 5px;\">Incident Resolution Summary</td>\r\n" + 
			   		"  </tr>\r\n" +
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Region</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+region +"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Resolution Details</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+ res.getResolution()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Streaming Outage Window</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+outageWindow+" </td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Current Business Impact</td> \r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\"> "+res.getBusinessImpact()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Data Loss</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+res.getDataLoss()+"</td> \r\n" + 
			   		"  </tr>\r\n"; 

			   if((!res.isOutageDataNotAvailable()) && res.getDataLoss().equalsIgnoreCase("yes") && (res.getStreamType().equalsIgnoreCase("PI") || res.getStreamType().equalsIgnoreCase("OPC"))) {
				   String bfStart=sdf.format(res.getBackfillFrom());
				   String bfStop=sdf.format(res.getBackfillTo());
				   String extractionWindow=bfStart+" GMT TO <br>"+bfStop+" GMT";
				   htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Historical Extraction Window</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+extractionWindow+"</td> \r\n" + 
					   		"  </tr>\r\n";
			   }
			   if(res.isOutageDataNotAvailable() && (res.getStreamType().equalsIgnoreCase("PI") || res.getStreamType().equalsIgnoreCase("OPC"))) {
				   String dataUnavailable="Complete Outage. Data unavailable in source for backfill.";
				   htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Historical Extraction Window</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+dataUnavailable+"</td> \r\n" + 
					   		"  </tr>\r\n";
			   }
			   	htmlText+=	"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Contact Team</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+res.getContactTeam()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Escalation Contact</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+res.getEscalationContact()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"</table>";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         MimeBodyPart imagePart = new MimeBodyPart();
	         ClassLoader classLoader = Test.class.getClassLoader();
	         File f=new File(classLoader.getResource("ResolvedNotificationHeader.JPG").getFile());
	         imagePart.attachFile(f);
	         imagePart.setContentID("<" + cid + ">");
	         imagePart.setDisposition(MimeBodyPart.INLINE);
	         multipart.addBodyPart(imagePart);

	         // put everything together
	         message.setContent(multipart);
		   // Send the actual HTML message, as big as you like
//		   String htmlContent="";
//		   message.setContent(htmlContent,"text/html");

		   // Send message
		   try {
		   Transport.send(message);
		   System.out.println("Sent message successfully....");
		   output="success";
		   }catch(Exception e) {
			   throw new Exception("MailServiceImpl.SEND_MAIL_FAILED");
		   }
		   

	      } catch (MessagingException e) {
	    	  output="failure";
				if(e.getMessage().contains("Service"))
				{
					OmtLogger.logError(this.getClass().getName(), "resolvedMailSend",e.getMessage());
				}
				throw e;
	      }
	      return output;
	   }
	   public String maintenanceMailSend(Incident inc) throws Exception {
		   String output="";
	      String to = inc.getTo();
	      String from = inc.getFrom();
	      final String username = "";//change accordingly
	      final String password = "";//change accordingly
	      String host = "smtp.dsc.bp.com";
	      
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
		});

	      try {
	            // Create a default MimeMessage object.
	            Message message = new MimeMessage(session);
	            

	   	   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));

		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		   message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(inc.getBcc()));

		   // Set Subject: header field
		   message.setSubject(inc.getSubject());
		   String region=String.join("<br>", inc.getRegion());
		   SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		  

		// This mail has 2 part, the BODY and the embedded image
	          MimeMultipart multipart = new MimeMultipart("related");
	         
	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String cid=String.format("%s-%s", "img-", UUID.randomUUID());
	         String htmlText = "<table max-width=\"650px\" style=\"border:1px solid green; border-collapse:collapse;font-family:Verdana;table-layout:fixed;\">\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td colspan=2 style=\"border:1px solid green;\"><img src=\"cid:"+cid+"\" /></td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td colspan=2 style=\"border:1px solid green;font-size:85%;background-color:#ffc000;font-weight:bold;padding:5px 5px 5px 5px;\">Maintenance Summary</td>\r\n" + 
			   		"  </tr>\r\n" +
			   		
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Region</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+region +"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Change Details</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+ inc.getIncident()+"</td> \r\n" + 
			   		"  </tr>\r\n";
	         String mnStart=sdf.format(inc.getMaintenanceFrom());
			   String mnStop=sdf.format(inc.getMaintenanceTo());
			   String maintenanceWindow=mnStart+" GMT TO <br>"+mnStop+" GMT";
			   htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Maintenance Window</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+maintenanceWindow+"</td> \r\n" + 
				   		"  </tr>\r\n";
			   		htmlText+= "  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Current Business Impact</td> \r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\"> "+inc.getBusinessImpact()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Data Loss</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+inc.getDataLoss()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Contact Team</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+inc.getContactTeam()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Escalation Contact</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+inc.getEscalationContact()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"</table>";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         MimeBodyPart imagePart = new MimeBodyPart();
	         ClassLoader classLoader = Test.class.getClassLoader();
	         File f=new File(classLoader.getResource("MaintenanceNotificationHeader.JPG").getFile());
	         imagePart.attachFile(f);
	         imagePart.setContentID("<" + cid + ">");
	         imagePart.setDisposition(MimeBodyPart.INLINE);
	         multipart.addBodyPart(imagePart);

	         // put everything together
	         message.setContent(multipart);
		   // Send the actual HTML message, as big as you like
//		   String htmlContent="";
//		   message.setContent(htmlContent,"text/html");

		   // Send message
		   try {
		   Transport.send(message);
		   System.out.println("Sent message successfully....");
		   output="success";
		   }catch(Exception e) {
			   throw new Exception("MailServiceImpl.SEND_MAIL_FAILED");
		   }
		   

	      } catch (MessagingException e) {
	    	  output="failure";
				if(e.getMessage().contains("Service"))
				{
					OmtLogger.logError(this.getClass().getName(), "maintenanceMailSend",e.getMessage());
				}
				throw e;
	      }
	      return output;
	   }
	   public String maintenanceCompletionMailSend(Resolution res) throws Exception {
		   String output="";
	      String to = res.getTo();
	      String from = res.getFrom();
	      final String username = "";//change accordingly
	      final String password = "";//change accordingly
	      String host = "smtp.dsc.bp.com";;//change accordingly
	      
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
		});

	      try {
	            // Create a default MimeMessage object.
	            Message message = new MimeMessage(session);
	            

	   	   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));

		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		   message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(res.getBcc()));

		   // Set Subject: header field
		   message.setSubject(res.getSubject());
		   String region=String.join("<br>", res.getRegion());
		   SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		


		// This mail has 2 part, the BODY and the embedded image
	          MimeMultipart multipart = new MimeMultipart("related");
	         
	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String cid=String.format("%s-%s", "img-", UUID.randomUUID());
	         String htmlText = "<table max-width=\"650px\" style=\"border:1px solid green; border-collapse:collapse;font-family:Verdana;table-layout:fixed;\">\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td colspan=2 style=\"border:1px solid green;\"><img src=\"cid:"+cid+"\" /></td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td colspan=2 style=\"border:1px solid green;font-size:85%;color:#abcf92;background-color:#395524;font-weight:bold;padding:5px 5px 5px 5px;\">Maintenance Completion Summary</td>\r\n" + 
			   		"  </tr>\r\n" +
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Region</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+region +"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Resolution Details</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+ res.getResolution()+"</td> \r\n" + 
			   		"  </tr>\r\n";
	         String mnStart=sdf.format(res.getMaintenanceFrom());
			   String mnStop=sdf.format(res.getMaintenanceTo());
			   String maintenanceWindow=mnStart+" GMT TO <br>"+mnStop+" GMT";
			   htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Maintenance Window</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+maintenanceWindow+"</td> \r\n" + 
				   		"  </tr>\r\n";  		
	         
			   		htmlText+="  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Current Business Impact</td> \r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\"> "+res.getBusinessImpact()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Data Loss</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+res.getDataLoss()+"</td> \r\n" + 
			   		"  </tr>\r\n"; 

			  if((!res.isOutageDataNotAvailable()) && res.getDataLoss().equalsIgnoreCase("yes") && (res.getStreamType().equalsIgnoreCase("PI") || res.getStreamType().equalsIgnoreCase("OPC"))) {
				   String bfStart=sdf.format(res.getBackfillFrom());
				   String bfStop=sdf.format(res.getBackfillTo());
				   String extractionWindow=bfStart+" GMT TO <br>"+bfStop+" GMT";
				   htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Historical Extraction Window</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+extractionWindow+"</td> \r\n" + 
					   		"  </tr>\r\n";
			   }
			  if(res.isOutageDataNotAvailable() && (res.getStreamType().equalsIgnoreCase("PI") || res.getStreamType().equalsIgnoreCase("OPC"))) {
				   String dataUnavailable="Complete Outage. Data unavailable in source for backfill.";
				   htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Historical Extraction Window</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+dataUnavailable+"</td> \r\n" + 
					   		"  </tr>\r\n";
			   }
			   		
			   	htmlText+=	"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Contact Team</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+res.getContactTeam()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"  <tr>\r\n" + 
			   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Escalation Contact</td>\r\n" + 
			   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+res.getEscalationContact()+"</td> \r\n" + 
			   		"  </tr>\r\n" + 
			   		"</table>";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         MimeBodyPart imagePart = new MimeBodyPart();
	         ClassLoader classLoader = Test.class.getClassLoader();
	         File f=new File(classLoader.getResource("CompletedMaintenanceNotificationHeader.JPG").getFile());
	         imagePart.attachFile(f);
	         imagePart.setContentID("<" + cid + ">");
	         imagePart.setDisposition(MimeBodyPart.INLINE);
	         multipart.addBodyPart(imagePart);

	         // put everything together
	         message.setContent(multipart);
		   // Send the actual HTML message, as big as you like
//		   String htmlContent="";
//		   message.setContent(htmlContent,"text/html");

		   // Send message
		   try {
		   Transport.send(message);
		   System.out.println("Sent message successfully....");
		   output="success";
		   }catch(Exception e) {
			   throw new Exception("MailServiceImpl.SEND_MAIL_FAILED");
		   }
		   

	      } catch (MessagingException e) {
	    	  output="failure";
				if(e.getMessage().contains("Service"))
				{
					OmtLogger.logError(this.getClass().getName(), "maintenanceCompletionMailSend",e.getMessage());
				}
				throw e;
	      }
	      return output;
	   }
}
