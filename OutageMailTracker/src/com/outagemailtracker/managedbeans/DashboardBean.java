package com.outagemailtracker.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import com.outagemailtracker.beans.Incident;
import com.outagemailtracker.beans.Match;
import com.outagemailtracker.beans.MatchDetails;
import com.outagemailtracker.beans.Resolution;
import com.outagemailtracker.resources.Factory;
import com.outagemailtracker.resources.OmtLogger;

@ManagedBean
@ViewScoped
public class DashboardBean {
	private String message;
	private int start;
	private int end;
	private int total;
	private List<Match> matchList;
	private List<Match> filterList;
	private List<MatchDetails> mdList;
	private String backfillStatus;
	private String backfillStatusUpdate;
	private Boolean backfillStatusBool;
	private int selectedResId;
	private boolean disabledNext;
	private boolean disabledPrev;
	
	public boolean isDisabledNext() {
		return disabledNext;
	}
	public void setDisabledNext(boolean disabledNext) {
		this.disabledNext = disabledNext;
	}
	public boolean isDisabledPrev() {
		return disabledPrev;
	}
	public void setDisabledPrev(boolean disabledPrev) {
		this.disabledPrev = disabledPrev;
	}
	public int getSelectedResId() {
		return selectedResId;
	}
	public void setSelectedResId(int selectedResId) {
		this.selectedResId = selectedResId;
	}
	public Boolean getBackfillStatusBool() {
		return backfillStatusBool;
	}
	public void setBackfillStatusBool(Boolean backfillStatusBool) {
		this.backfillStatusBool = backfillStatusBool;
	}
	public String getBackfillStatusUpdate() {
		return backfillStatusUpdate;
	}
	public void setBackfillStatusUpdate(String backfillStatusUpdate) {
		this.backfillStatusUpdate = backfillStatusUpdate;
	}
	public String getBackfillStatus() {
		return backfillStatus;
	}
	public void setBackfillStatus(String backfillStatus) {
		this.backfillStatus = backfillStatus;
	}
	public List<MatchDetails> getMdList() {
		return mdList;
	}
	public void setMdList(List<MatchDetails> mdList) {
		this.mdList = mdList;
	}
	public List<Match> getFilterList() {
		return filterList;
	}
	public void setFilterList(List<Match> filterList) {
		this.filterList = filterList;
	}
	public List<Match> getMatchList() {
		return matchList;
	}
	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public DashboardBean() {
		try {
//			System.out.println("Display initiated");
			HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String clearSession=req.getParameter("faces-redirect");
			if(clearSession!=null) {
				if(clearSession.equalsIgnoreCase("true")) {
					System.out.println("Clearing session");
					FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				}
			}
			this.message=null;
			this.matchList=Factory.createMatchService().getAllMatch();
//			System.out.println("size : "+this.matchList.size());
			this.start=0;
			if(this.matchList.size()>10) {
				this.disabledNext=false;
			}else {
				this.disabledNext=true;
			}
			curateList();
			getCuratedListDetails(this.filterList);
//			System.out.println("Display completed");
			
			
		}catch(Exception e){
			e.printStackTrace();
			OmtLogger.logError(this.getClass().getName(), "DashboardBean", e.getMessage());
			this.message=e.getMessage();
		}
	}
	public void curateList() {
//		System.out.println("In curateList");
		if(this.start==0) {
			this.disabledPrev=true;
		}else{
			this.disabledPrev=false;
		}
		int endLoop=10;
		
		this.filterList=new ArrayList<Match>();
		
		if((this.matchList.size()-(this.start*10))<10) {
			endLoop=(this.matchList.size()-(this.start*10));
//			System.out.println(endLoop);
		}
		for(int i=0;i<endLoop;i++) {
			this.filterList.add(this.matchList.get((this.start*10)+i));
		}
//		System.out.println(this.filterList.size());
//		System.out.println("disabledNext: "+this.disabledNext);
//		System.out.println("disabledPrev: "+this.disabledPrev);
	}
	public String nextPage() {
//		System.out.println(this.start);
		if(((this.start+1)*10)<this.matchList.size()) {
			this.start+=1;
			if(((this.start+1)*10)>=this.matchList.size()) {
				this.disabledNext=true;
			}
			curateList();
			getCuratedListDetails(this.filterList);
		}
		return "";
	}
	public String prevPage() {
		if(this.start!=0) {
			this.start-=1;
			this.disabledNext=false;
			curateList();
			getCuratedListDetails(this.filterList);
		}
		return "";
	}
	public List<MatchDetails> getCuratedListDetails(List<Match> fList) {
//		System.out.println("Inside getCuratedList: "+fList.size());
		mdList=new ArrayList<MatchDetails>();
		try {
//			for(Match m: fList) {
//				System.out.println(m.getMatchId()+" , "+m.getIncId()+" , "+m.getResId());
//			}
			Incident inc=null;
			Resolution res=null;
			for(Match m:fList) {
				MatchDetails md=new MatchDetails();
				if(m.getIncId()!=0) {
//					System.out.println(m.getIncId());
					inc=Factory.createIncidentService().getIncidentById(m.getIncId());
					md.setInc(inc);
				}
				if(m.getResId()!=0) {
//					System.out.println(m.getResId());
					res=Factory.createResolutionService().getResolutionById(m.getResId());
					md.setRes(res);
				}
				md.setMatch(m);
				mdList.add(md);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			this.message=e.getMessage();
		}
		return mdList;
	}
	public String sendResolution(String incId) {
//		System.out.println(incId);
		int incid=Integer.parseInt(incId);
//		System.out.println(incid);
		Incident inc=null;
		for(MatchDetails md:this.mdList) {
			if(md.getInc()!=null) {
				if(md.getInc().getIncId()==incid) {
					inc=md.getInc();
					FacesContext context = FacesContext.getCurrentInstance();
					Map<String, Object> requestMap = context.getExternalContext().getSessionMap();
					requestMap.put("incident", inc);
					return "sendResolved.jsp";
				}
			}
		}
		return "";
	}
	public void assignBackfillStatus(ValueChangeEvent vce) {
		this.backfillStatusBool=(Boolean)vce.getNewValue();
		this.selectedResId = (int)vce.getComponent().getAttributes().get("selectedResId");
		System.out.println(this.selectedResId);
//		System.out.println(this.backfillStatusBool);
		if(this.backfillStatusBool==true) {
			this.backfillStatus="COMPLETED";
		}else {
			this.backfillStatus="PENDING";
		}
//		System.out.println(this.backfillStatus);
		
//		System.out.println(this.backfillStatusUpdate);
		for(MatchDetails md:this.mdList) {
			if(md.getRes()!=null) {
				if(md.getRes().getResId()==this.selectedResId) {
					if(!md.getRes().getBackfillStatus().equalsIgnoreCase(this.backfillStatus)) {
						this.backfillStatusUpdate="true";
					}else {
						this.backfillStatusUpdate="false";
					}
				}
			}
		}
	}
	public String updateBackfillStatus() {
		try {
			int outResId=Factory.createResolutionService().updateBackfillStatus(this.selectedResId, this.backfillStatus);
			System.out.println("Backfill Status updated for resId: "+outResId);
			this.backfillStatusUpdate="false";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.message=e.getMessage();
			e.printStackTrace();
		}
		return "dashboard.jsp?faces-redirect=true";
	}
	public String viewIncidentNotification(String incId) {
		int incid=Integer.parseInt(incId);
		Incident inc=null;
		for(MatchDetails md:this.mdList) {
			if(md.getInc()!=null) {
				if(md.getInc().getIncId()==incid) {
					inc=md.getInc();
					FacesContext context = FacesContext.getCurrentInstance();
					Map<String, Object> requestMap = context.getExternalContext().getSessionMap();
					requestMap.put("incidentView", inc);
					return "view.jsp";
				}
			}
		}
		return "";
	}
	public String viewResolvedNotification(String resId) {
		int resid=Integer.parseInt(resId);
		Resolution res=null;
		for(MatchDetails md:this.mdList) {
			if(md.getRes()!=null) {
				if(md.getRes().getResId()==resid) {
					res=md.getRes();
					FacesContext context = FacesContext.getCurrentInstance();
					Map<String, Object> requestMap = context.getExternalContext().getSessionMap();
					requestMap.put("resolvedView", res);
					return "view.jsp";
				}
			}
		}
		return "";
	}
}
