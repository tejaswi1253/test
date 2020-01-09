package com.outagemailtracker.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.outagemailtracker.beans.Resolution;
import com.outagemailtracker.entity.ResolutionEntity;
import com.outagemailtracker.resources.HibernateUtility;
import com.outagemailtracker.resources.OmtLogger;

public class ResolutionDAOImpl implements ResolutionDAO{

	public int insertResolution(Resolution resolution) throws Exception{
		SessionFactory sessionFactory;
		Session session = null;
		try 
		{
			sessionFactory = HibernateUtility.createSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			ResolutionEntity res=new ResolutionEntity();
			res.setBcc(resolution.getBcc());
			//res.setBusinessImpact(resolution.getBusinessImpact());
			res.setContactTeam(resolution.getContactTeam());
			///res.setDataLoss(resolution.getDataLoss());
			res.setEscalationContact(resolution.getEscalationContact());
			res.setFrom(resolution.getFrom());
			res.setIncId(resolution.getIncId());
			res.setIssueType(resolution.getIssueType());
			String region=String.join(",", resolution.getRegion().toArray(new String[resolution.getRegion().size()]));
//			for(String reg:resolution.getRegion()) {
//				region+=","+reg;
//			}
			res.setRegion(region);
			res.setResolution(resolution.getResolution());
			//res.setResolvedTime(resolution.getResolvedTime());
			//res.setStopTime(resolution.getStopTime());
			
			//res.setStreamType(resolution.getStreamType());
			res.setSubject(resolution.getSubject());
			res.setTo(resolution.getTo());
			//res.setBackfillFrom(resolution.getBackfillFrom());
			//res.setBackfillTo(resolution.getBackfillTo());
			//String ven=String.join(",", resolution.getVendor().toArray(new String[resolution.getVendor().size()]));
//			for(String str:resolution.getVendor()) {
//				ven+=","+str;
//			}
			//res.setVendor(ven);
			//res.setBackfillStatus(resolution.getBackfillStatus());
			res.setMaintenanceFrom(resolution.getMaintenanceFrom());
			res.setMaintenanceTo(resolution.getMaintenanceTo());
			//res.setOutageDataNotAvailable(resolution.isOutageDataNotAvailable());
			res.setRca(resolution.getRca());
			res.setCreated(resolution.getCreated());
			int resId=(int)session.save(res);
			session.getTransaction().commit();
			return resId;
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			OmtLogger.logError(this.getClass().getName(),"insertResolution", e.getMessage());
			throw new Exception("DAO RESOLUTION.TECHNICAL_ERROR");
		} 
		finally 
		{
			
			session.close();
			
		}
	}
	
	public Resolution getResolutionById(int resId) throws Exception{
		SessionFactory sessionFactory;
		Session session = null;
		try 
		{
			sessionFactory = HibernateUtility.createSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			ResolutionEntity resE=(ResolutionEntity)session.get(ResolutionEntity.class, resId);
			Resolution res=new Resolution();
			//res.setBackfillFrom(resE.getBackfillFrom());
			//res.setBackfillTo(resE.getBackfillTo());
			res.setBcc(resE.getBcc());
			//res.setBusinessImpact(resE.getBusinessImpact());
			res.setContactTeam(resE.getContactTeam());
			//res.setDataLoss(resE.getDataLoss());
			res.setEscalationContact(resE.getEscalationContact());
			res.setFrom(resE.getFrom());
			res.setIncId(resE.getIncId());
			res.setIssueType(resE.getIssueType());
			String[] regArr=resE.getRegion().split(",");
			List<String> regionList=Arrays.asList(regArr);
//			for(String reg:regArr) {
//				regionList.add(reg);
//			}
			res.setRegion(regionList);
			res.setResId(resId);
			res.setResolution(resE.getResolution());
			//res.setResolvedTime(resE.getResolvedTime());
			//res.setStopTime(resE.getStopTime());
			
			
			//res.setBackfillFrom(resE.getBackfillFrom());
			//res.setBackfillTo(resE.getBackfillTo());
			//res.setStreamType(resE.getStreamType());
			res.setSubject(resE.getSubject());
			res.setTo(resE.getTo());
			//String[] venArr=resE.getVendor().split(",");
			//List<String> venList=Arrays.asList(venArr);
//			for(String str:venArr) {
//				venList.add(str); not by me
//			}
			//res.setVendor(venList);
			/*res.setBackfillStatus(resE.getBackfillStatus());
			if(resE.getBackfillStatus().equalsIgnoreCase("COMPLETED")) {
				res.setBackfillStatusBool(true);
			}else {
				res.setBackfillStatusBool(false);
			}*/
			res.setMaintenanceFrom(resE.getMaintenanceFrom());
			res.setMaintenanceTo(resE.getMaintenanceTo());
			//res.setOutageDataNotAvailable(resE.isOutageDataNotAvailable());
			res.setRca(resE.getRca());
			res.setCreated(resE.getCreated());
			session.getTransaction().commit();
			return res;
		} catch (Exception e) 
		{
			OmtLogger.logError(this.getClass().getName(),"getResolutionById", e.getMessage());
			throw new Exception("RESOLUTION DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
			session.close();
			
		}
	}
	public int updateBackfillStatus(int resId,String backfillStatus) throws Exception{
		SessionFactory sessionFactory;
		Session session = null;
		int resid=0;
		try 
		{
			sessionFactory = HibernateUtility.createSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			ResolutionEntity resE=(ResolutionEntity)session.get(ResolutionEntity.class, resId);
			//resE.setBackfillStatus(backfillStatus);
			session.getTransaction().commit();
			resid= resE.getResId();
		} catch (Exception e) 
		{
			OmtLogger.logError(this.getClass().getName(),"updateBackfillStatus", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
			session.close();
			
		}
		return resid;
	}

}
