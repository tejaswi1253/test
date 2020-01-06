package com.outagemailtracker.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.outagemailtracker.beans.Incident;
import com.outagemailtracker.entity.IncidentEntity;
import com.outagemailtracker.resources.HibernateUtility;
import com.outagemailtracker.resources.OmtLogger;

public class IncidentDAOImpl implements IncidentDAO{

	public int insertIncident(Incident incident) throws Exception{
		SessionFactory sessionFactory;
		Session session = null;
		try 
		{
			sessionFactory = HibernateUtility.createSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			IncidentEntity inc=new IncidentEntity();
			inc.setBcc(incident.getBcc());
			inc.setBusinessImpact(incident.getBusinessImpact());
			inc.setContactTeam(incident.getContactTeam());
			inc.setDataLoss(incident.getDataLoss());
			inc.setEscalationContact(incident.getEscalationContact());
			inc.setFrom(incident.getFrom());
			inc.setIncident(incident.getIncident());
			inc.setIssueType(incident.getIssueType());
			String region=String.join(",", incident.getRegion().toArray(new String[incident.getRegion().size()]));
//			for(String reg:incident.getRegion()) {
//				region+=","+reg;
//			}
			inc.setRegion(region);
			inc.setStopTime(incident.getStopTime());
			String ven=String.join(",", incident.getVendor().toArray(new String[incident.getVendor().size()]));
//			for(String str:incident.getVendor()) {
//				ven+=","+str;
//			}
			inc.setVendor(ven);
			inc.setStreamType(incident.getStreamType());
			inc.setTo(incident.getTo());
			inc.setSubject(incident.getSubject());
			
			inc.setMaintenanceFrom(incident.getMaintenanceFrom());
			inc.setMaintenanceTo(incident.getMaintenanceTo());
			inc.setRca(incident.getRca());
			inc.setCreated(incident.getCreated());
			int incId=(int)session.save(inc);
			
			session.getTransaction().commit();
			return incId;
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			OmtLogger.logError(this.getClass().getName(),"insertIncident", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
				session.close();

		}
	}
	
	public Incident getIncidentById(int incId) throws Exception{
		SessionFactory sessionFactory;
		Session session = null;
		try 
		{
			sessionFactory = HibernateUtility.createSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			IncidentEntity incEntity=(IncidentEntity)session.get(IncidentEntity.class, incId);
			
			Incident inc=new Incident();
			inc.setBcc(incEntity.getBcc());
			inc.setBusinessImpact(incEntity.getBusinessImpact());
			inc.setContactTeam(incEntity.getContactTeam());
			inc.setIncId(incId);
			inc.setDataLoss(incEntity.getDataLoss());
			inc.setEscalationContact(incEntity.getEscalationContact());
			inc.setFrom(incEntity.getFrom());
			inc.setIncident(incEntity.getIncident());
			inc.setIssueType(incEntity.getIssueType());
			String[] regArr=incEntity.getRegion().split(",");
			List<String> regionList=Arrays.asList(regArr);
//			for(String reg:regArr) {
//				regionList.add(reg);
//			}
			inc.setRegion(regionList);
			inc.setStopTime(incEntity.getStopTime());
			
			String[] venArr=incEntity.getVendor().split(",");
			List<String> venList=Arrays.asList(venArr);
//			for(String str:venArr) {
//				venList.add(str);
//			}
			inc.setVendor(venList);
			inc.setStreamType(incEntity.getStreamType());
			inc.setTo(incEntity.getTo());
			inc.setSubject(incEntity.getSubject());
			inc.setMaintenanceFrom(incEntity.getMaintenanceFrom());
			inc.setMaintenanceTo(incEntity.getMaintenanceTo());
			inc.setRca(incEntity.getRca());
			inc.setCreated(incEntity.getCreated());
			session.getTransaction().commit();
			return inc;
		} catch (Exception e) 
		{
			OmtLogger.logError(this.getClass().getName(),"getIncidentById", e.getMessage());
			e.printStackTrace();
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
			session.close();
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Incident> getAllIncidents() throws Exception{
		SessionFactory sessionFactory;
		Session session = null;
		List<Incident> allInc=new ArrayList<Incident>();
		try 
		{
			sessionFactory = HibernateUtility.createSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query q=session.createQuery("select i from IncidentEntity i order by i.incId desc");
			List<IncidentEntity> qOut=q.list();
			for(int i=0;i<qOut.size();i++) {
				IncidentEntity incEntity=qOut.get(i);
				Incident inc=new Incident();
				inc.setBcc(incEntity.getBcc());
				inc.setBusinessImpact(incEntity.getBusinessImpact());
				inc.setContactTeam(incEntity.getContactTeam());
				inc.setIncId(incEntity.getIncId());
				inc.setDataLoss(incEntity.getDataLoss());
				inc.setEscalationContact(incEntity.getEscalationContact());
				inc.setFrom(incEntity.getFrom());
				inc.setIncident(incEntity.getIncident());
				inc.setIssueType(incEntity.getIssueType());
				String[] regArr=incEntity.getRegion().split(",");
				List<String> regionList=Arrays.asList(regArr);
//				for(String reg:regArr) {
//					regionList.add(reg);
//				}
				inc.setRegion(regionList);
				inc.setStopTime(incEntity.getStopTime());
				String[] venArr=incEntity.getVendor().split(",");
				List<String> venList=Arrays.asList(venArr);
//				for(String str:venArr) {
//					venList.add(str);
//				}
				inc.setVendor(venList);
				inc.setStreamType(incEntity.getStreamType());
				inc.setTo(incEntity.getTo());
				inc.setSubject(incEntity.getSubject());
				
				inc.setMaintenanceFrom(incEntity.getMaintenanceFrom());
				inc.setMaintenanceTo(incEntity.getMaintenanceTo());
				inc.setRca(incEntity.getRca());
				inc.setCreated(incEntity.getCreated());
				allInc.add(inc);
			}
			
			return allInc;
		} catch (Exception e) 
		{
			OmtLogger.logError(this.getClass().getName(),"getAllIncidents", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
			session.close();
			
		}
	}
}
