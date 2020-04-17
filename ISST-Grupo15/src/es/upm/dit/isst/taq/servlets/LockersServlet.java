package es.upm.dit.isst.taq.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;

import es.upm.dit.isst.taq.dao.LockersDAOImpl;
import es.upm.dit.isst.taq.model.Lockers;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/locker/*", "/api/v1/lockers"})
public class LockersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LockersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
    	resp.setContentType("application/json");
    	ObjectMapper mapper = new ObjectMapper();
    	
    	if(req.getRequestURI().contains("lockers")) {
    		List<Lockers> list = LockersDAOImpl.getInstance().readAll();
    		if (list.isEmpty()) {
    			resp.getWriter().print("[]");
    			return;
    		}
    		
    		String jsonString = mapper.writeValueAsString(list);
    		resp.getWriter().print(jsonString);
    		return;
    	}
    	
    	String param = req.getPathInfo();
    	int id;
    	if (param != null) {
    		id = Integer.parseInt(param.substring(1));
    		Lockers locker = LockersDAOImpl.getInstance().read(id);
    		if(locker != null) {
    			resp.getWriter().print(mapper.writeValueAsString(locker));
    		}
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		resp.getWriter().print("{}");
    	}
    	
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String lockerNumber = req.getParameter("lockerNumber");
		String lockerStateId = req.getParameter("lockerStateId");
		String locationId = req.getParameter("locationId");
		String param = req.getPathInfo();
    	
    	if (param != null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		if (lockerNumber == "" || lockerNumber == null || lockerStateId == "" || lockerStateId == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Lockers item = new Lockers();
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);  
        List<Lockers> items = LockersDAOImpl.getInstance().readAll();
        int id=1;
        
        if(items.size()!=0) {
        	id = items.get(items.size()-1).getId()+1;
        }
        
        item.setId(id);
		item.setLockerNumber(Integer.parseInt(lockerNumber));
		item.setLockerStateId(Integer.parseInt(lockerStateId));
		item.setLocationId(Integer.parseInt(locationId));
		item.setCreatedAt(date);
		item.setUpdatedAt(date);
		LockersDAOImpl.getInstance().create(item);
			
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id;
		Lockers item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = LockersDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		LockersDAOImpl.getInstance().delete(item);
		
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id;
		Lockers item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = LockersDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		
		String lockerNumber = req.getParameter("lockerNumber");
		String lockerStateId = req.getParameter("lockerStateId");
		String locationId = req.getParameter("locationId");
		
		
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);  
        
        if(lockerNumber != "" || lockerNumber != null)
        	item.setLockerNumber(Integer.parseInt(lockerNumber));
        
        if(lockerStateId != "" || lockerStateId != null)
        	item.setLockerStateId(Integer.parseInt(lockerStateId));
        
        if(lockerStateId != "" || lockerStateId != null)
        	item.setLocationId(Integer.parseInt(locationId));
        
		item.setUpdatedAt(date);
		LockersDAOImpl.getInstance().update(item);

	}
}
