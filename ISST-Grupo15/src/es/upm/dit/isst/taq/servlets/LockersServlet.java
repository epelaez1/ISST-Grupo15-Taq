package es.upm.dit.isst.taq.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.upm.dit.isst.taq.dao.LockersDAOImpl;
import es.upm.dit.isst.taq.model.Lockers;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/admin/locker/*", "/api/v1/admin/lockers"})
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
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		
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
		
		JSONObject jsonObject;
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
		    BufferedReader reader = req.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  try {
		   jsonObject =  new JSONObject(jb.toString());
		  } catch (JSONException e) {
		    // crash and burn
		    throw new IOException("Error parsing JSON request string");
		  }
		  
		Integer lockerNumber = jsonObject.getInt("lockerNumber");
		Integer lockerStateId = jsonObject.getInt("lockerStateId");
		Integer locationId = jsonObject.getInt("locationId");

    	
		if (lockerNumber == null || lockerStateId == null) {
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
		item.setLockerNumber(lockerNumber);
		item.setLockerStateId(lockerStateId);
		item.setLocationId(locationId);
		item.setCreatedAt(date);
		item.setUpdatedAt(date);
		LockersDAOImpl.getInstance().create(item);
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
		
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
		resp.setContentType("application/json");
		resp.getWriter().print("[]");
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id;
		Lockers item = null;
		String param = req.getPathInfo();
    	
		JSONObject jsonObject;
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
		    BufferedReader reader = req.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  try {
		   jsonObject =  new JSONObject(jb.toString());
		  } catch (JSONException e) {
		    // crash and burn
		    throw new IOException("Error parsing JSON request string");
		  }
    	
		id = Integer.parseInt(param.substring(1));
		
		item = LockersDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		
		Integer lockerNumber = jsonObject.getInt("lockerNumber");
		Integer lockerStateId = jsonObject.getInt("lockerStateId");
		Integer locationId = jsonObject.getInt("locationId");
		
		
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);  
        
        if(lockerNumber != null)
        	item.setLockerNumber(lockerNumber);
        
        if(lockerStateId != null)
        	item.setLockerStateId(lockerStateId);
        
        if(locationId != null)
        	item.setLocationId(locationId);
        
		item.setUpdatedAt(date);
		LockersDAOImpl.getInstance().update(item);
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
	}
}
