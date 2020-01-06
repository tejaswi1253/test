package com.outagemailtracker.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.outagemailtracker.beans.Region;
import com.outagemailtracker.entity.RegionEntity;
import com.outagemailtracker.resources.HibernateUtility;
import com.outagemailtracker.resources.OmtLogger;

public class RegionDAOImpl implements RegionDAO {
	@SuppressWarnings("unchecked")
	public List<Region> getAllRegions() throws Exception{
		List<Region> regionList=new ArrayList<Region>();
		SessionFactory sessionFactory;
		Session session=null;
		try {
//			System.out.println("InDAO");
			sessionFactory=HibernateUtility.createSessionFactory();
			session=sessionFactory.openSession();
			session.beginTransaction();
			Query q=session.createQuery("select r from RegionEntity r");
			List<RegionEntity> regionEntityList=q.list();
			for(RegionEntity re:regionEntityList) {
				Region r=new Region();
				r.setRegion(re.getRegion());
				String[] venArr=re.getVendors().split(",");
				List<String> venList=Arrays.asList(venArr);
//				for(String ven:venArr) {
//					venList.add(ven.toUpperCase());
//				}
				r.setVendors(venList);
				String[] strArr=re.getStream().split(",");
				List<String> strList=Arrays.asList(strArr);
//				for(String str:strArr) {
//					strList.add(str.toUpperCase());
//				}
				r.setStream(strList);
				regionList.add(r);
			}
//			for(Region r:regionList) {
//				System.out.println(r.getRegion());
//			}
			session.getTransaction().commit();
			return regionList;
		}catch (Exception e){
			e.printStackTrace();
			OmtLogger.logError(this.getClass().getName(),"getAllRegions", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
				session.close();
		}
	}
}
