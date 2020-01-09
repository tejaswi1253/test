package com.outagemailtracker.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.outagemailtracker.beans.Match;
import com.outagemailtracker.entity.MatchEntity;
import com.outagemailtracker.resources.HibernateUtility;
import com.outagemailtracker.resources.OmtLogger;

public class MatchDAOImpl implements MatchDAO{
	public int insertIncidentToMatch(int incId) throws Exception{
		SessionFactory sessionFactory;
		Session session=null;
		int matchId;
		try {
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			session.beginTransaction();
			MatchEntity m=new MatchEntity();
			m.setIncId(incId);
			m.setUpdated(new Date());
			matchId=(int)session.save(m);
			session.getTransaction().commit();
			return matchId;
		}catch (Exception e) 
		{
			e.printStackTrace();
			OmtLogger.logError(this.getClass().getName(),"insertIncidentToMatch", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
				session.close();

		}
	}
	@SuppressWarnings("unchecked")
	public int insertResolutionToMatch(int resId,int incId) throws Exception{
//		System.out.println("insertResolutionToMatch- resid: "+resId+" incid: "+incId);
		SessionFactory sessionFactory;
		Session session=null;
		int matchId=0;
		try {
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			session.beginTransaction();
			if(incId!=0) {
				String query="select m from MatchEntity m where m.incId="+incId;
				Query q=session.createQuery(query);
				List<MatchEntity> qOut=q.list();
				if(qOut.size()>1) {
					OmtLogger.logError(this.getClass().getName(),"insertResolutionToMatch", "Multiple MatchEntity found for incId : "+incId);
				}
				MatchEntity m=qOut.get(0);
				m.setResId(resId);
				m.setUpdated(new Date());
				matchId=m.getMatchId();
			}else {
				MatchEntity m=new MatchEntity();
				m.setResId(resId);
				m.setUpdated(new Date());
				matchId=(int)session.save(m);
			}
			
			session.getTransaction().commit();
			return matchId;
		}catch (Exception e) 
		{
			//e.printStackTrace();
			OmtLogger.logError(this.getClass().getName(),"insertResolutionToMatch", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
				session.close();

		}
	}
	@SuppressWarnings("unchecked")
	public List<Match> getAllFromMatch() throws Exception{
		SessionFactory sessionFactory;
		Session session=null;
		try {
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			List<Match> matchList=new ArrayList<Match>();
			session.beginTransaction();
			Query q=session.createQuery("select m from MatchEntity m order by m.updated desc");
			List<MatchEntity> list=q.list();
			for(MatchEntity mEntity:list) {
				Match m=new Match();
				m.setIncId(mEntity.getIncId());
				m.setMatchId(mEntity.getMatchId());
				m.setResId(mEntity.getResId());
				m.setUpdated(mEntity.getUpdated());
				matchList.add(m);
			}
			session.getTransaction().commit();
//			System.out.println("Extraction completed");
			return matchList;
		}catch (Exception e) 
		{
			e.printStackTrace();
			OmtLogger.logError(this.getClass().getName(),"getAllFromMatch", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
				session.close();

		}
	}
	public Match getMatchById(int matchId) throws Exception{
		SessionFactory sessionFactory;
		Session session=null;
		try {
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			session.beginTransaction();
			MatchEntity mEntity=(MatchEntity)session.get(MatchEntity.class, matchId);
			Match m=new Match();
			m.setIncId(mEntity.getIncId());
			m.setMatchId(mEntity.getMatchId());
			m.setResId(mEntity.getResId());
			m.setUpdated(mEntity.getUpdated());
			session.getTransaction().commit();
			return m;
		}catch (Exception e) 
		{
			e.printStackTrace();
			OmtLogger.logError(this.getClass().getName(),"fetchMatchDetails", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
				session.close();

		}
	}
}
