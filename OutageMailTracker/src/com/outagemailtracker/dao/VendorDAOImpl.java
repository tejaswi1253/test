package com.outagemailtracker.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.outagemailtracker.entity.VendorEntity;
import com.outagemailtracker.resources.HibernateUtility;
import com.outagemailtracker.resources.OmtLogger;

public class VendorDAOImpl implements VendorDAO{
	@SuppressWarnings("unchecked")
	public List<String> fetchAllMailAddress(String vendor) throws Exception{
		SessionFactory sessionFactory;
		Session session=null;
		try {
			List<String> mailList=new ArrayList<String>();
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			session.beginTransaction();
			String query="select v from VendorEntity v where v.vendor='"+vendor+"'";
			Query q=session.createQuery(query);
			List<VendorEntity> vList=q.list();
			if(q.list().size()>0) {
				String[] mailArr=vList.get(0).getMail().split(",");
				for(String m:mailArr) {
					mailList.add(m);
				}
			}
			session.getTransaction().commit();
			return mailList;
		}catch (Exception e){
			e.printStackTrace();
			OmtLogger.logError(this.getClass().getName(),"fetchAllMailAddress", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
				session.close();
		}
	}
}
