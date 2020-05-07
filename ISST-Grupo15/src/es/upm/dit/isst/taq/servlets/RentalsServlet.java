package es.upm.dit.isst.taq.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.upm.dit.isst.taq.dao.LockersDAO;
import es.upm.dit.isst.taq.dao.LockersDAOImpl;
import es.upm.dit.isst.taq.dao.RentalsDAOImpl;
import es.upm.dit.isst.taq.model.Lockers;
import es.upm.dit.isst.taq.model.Rentals;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/admin/rental/*", "/api/v1/admin/rentals" ,"/api/v1/rental/*"})
public class RentalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalsServlet() {
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
		
    	if(req.getRequestURI().contains("rentals")) {
    		List<Rentals> list = RentalsDAOImpl.getInstance().readAll();
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
    		Rentals item = RentalsDAOImpl.getInstance().read(id);
    		if(item != null) {
    			resp.getWriter().print(mapper.writeValueAsString(item));
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
		  
		Date param1 = new Date(System.currentTimeMillis());
		Double param2 = jsonObject.getDouble("deposit");
		Integer param3 = jsonObject.getInt("userId");
		Integer param4 = jsonObject.getInt("lockerId");
		
    	
		if (req.getRequestURI().contains("create")) {
			long millis = System.currentTimeMillis();
			Date actualDate = new Date(millis);	
			Calendar c = Calendar.getInstance();
			c.setTime(actualDate);
			c.add(Calendar.DATE, 30*6);
			Date expirationDate = (Date) c.getTime(); 
			
			if (param2 == null ||param3 == null || param4 == null) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			List<Rentals> items = RentalsDAOImpl.getInstance().readAll();
	        int id=1;
	        
	        if(items.size()!=0) {
	        	id = items.get(items.size()-1).getId()+1;
	        }
	        
	        Rentals item = new Rentals();
	        item.setId(id);
	        item.setExpirationDate(expirationDate);
	        item.setDeposit(param2);
	        item.setUserId(param3);
	        item.setLockerId(param4);
	        item.setRentalStateId(1);
			item.setCreatedAt(actualDate);
			item.setUpdatedAt(actualDate);
			
			RentalsDAOImpl.getInstance().create(item);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(item);
			resp.getWriter().print(jsonString);
			return;
		}
    	
		if (param2 == null || param3 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if (param4 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		
		Rentals item = new Rentals();
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);
        List<Rentals> items = RentalsDAOImpl.getInstance().readAll();
        int id=1;
        
        if(items.size()!=0) {
        	id = items.get(items.size()-1).getId()+1;
        }
        
        item.setId(id);
        item.setExpirationDate(param1);
        item.setDeposit(param2);
        item.setUserId(param3);
        item.setLockerId(param4);
        item.setRentalStateId(1);
		item.setCreatedAt(date);
		item.setUpdatedAt(date);
		
		RentalsDAOImpl.getInstance().create(item);
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id;
		Rentals item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = RentalsDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		RentalsDAOImpl.getInstance().delete(item);
		resp.setContentType("application/json");
		resp.getWriter().print("[]");
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id;
		Rentals item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1,2));
		item = RentalsDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		
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
		  
		String action = req.getParameter("action");
		if(action != null){
		if (action.equals("setReserved")) {
			item.setRentalStateId(2);
			Lockers locker = LockersDAOImpl.getInstance().read(item.getLockerId());
			if (locker == null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    		return;
			}
			locker.setLockerStateId(3);
			LockersDAOImpl.getInstance().update(locker);
			RentalsDAOImpl.getInstance().update(item);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(item);
			resp.getWriter().print(jsonString);
			return;
		} else if (action.equals("renew")) {
			item.setRentalStateId(5);
			Lockers locker = LockersDAOImpl.getInstance().read(item.getLockerId());
			if (locker == null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    		return;
			} 
			locker.setLockerStateId(3);
			LockersDAOImpl.getInstance().update(locker);
			RentalsDAOImpl.getInstance().update(item);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(item);
			resp.getWriter().print(jsonString);
			return;
		} else if (action.equals("return")) {
			item.setRentalStateId(6);
			Lockers locker = LockersDAOImpl.getInstance().read(item.getLockerId());
			if (locker == null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    		return;
			} 
			locker.setLockerStateId(2);
			LockersDAOImpl.getInstance().update(locker);
			RentalsDAOImpl.getInstance().update(item);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(item);
			resp.getWriter().print(jsonString);
			return;
		}}
		String param1;
		Double param2;
		Integer param3;
		Integer param4;
		Integer param5;
		
		if (jsonObject.has("expirationDate")) {
			param1 = jsonObject.get("expirationDate").toString();
			Date p1 = Date.valueOf(param1);
			item.setExpirationDate(p1);
		}
		
		if (jsonObject.has("deposit")) {
			param2 = jsonObject.getDouble("deposit");
			item.setDeposit(param2);
		}
		
		if (jsonObject.has("userId")) {
			param3 = jsonObject.getInt("userId");
			item.setUserId(param3);
		}
		
		if (jsonObject.has("lockerId")) {
			param4 = jsonObject.getInt("lockerId");
			item.setLockerId(param4);
		}
		
		if (jsonObject.has("rentalStateId")) {
			param5 = jsonObject.getInt("rentalStateId");
			item.setRentalStateId(param5);
		}
		
		long millis = System.currentTimeMillis();
        Date date=new Date(millis);   		
        	
		item.setUpdatedAt(date);
		RentalsDAOImpl.getInstance().update(item);
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
	}
}
