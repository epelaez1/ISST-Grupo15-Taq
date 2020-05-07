package es.upm.dit.isst.taq.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.upm.dit.isst.taq.dao.EventsDAOImpl;
import es.upm.dit.isst.taq.model.Events;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/admin/event/*", "/api/v1/admin/events"})
public class EventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventsServlet() {
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
    	
    	if(req.getRequestURI().contains("events")) {
    		List<Events> list = EventsDAOImpl.getInstance().readAll();
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
    		Events item = EventsDAOImpl.getInstance().read(id);
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
		  
		String param1 = jsonObject.getString("description");
		Integer param2 = jsonObject.getInt("eventTypeId");
		Integer param3 = jsonObject.getInt("userId");
		Integer param4 = jsonObject.getInt("lockerId");
		Integer param5 = jsonObject.getInt("paymentId");
		Integer param6 = jsonObject.getInt("rentalId");
		
		String param = req.getPathInfo();
    	
    	if (param != null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		if (param1 == null || param2 == null || param3 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if (param4 == null || param5 == null || param6 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Events item = new Events();
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);
        List<Events> items = EventsDAOImpl.getInstance().readAll();
        int id=1;
        
        if(items.size()!=0) {
        	id = items.get(items.size()-1).getId()+1;
        }
        
        item.setId(id);
		item.setDescription(param1);
		item.setEventTypeId(param2);
		item.setUserId(param3);
		item.setLockerId(param4);
		item.setPaymentId(param5);
		item.setRentalId(param6);
		item.setCreatedAt(date);
		item.setUpdatedAt(date);
		
		EventsDAOImpl.getInstance().create(item);
			
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id;
		Events item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = EventsDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		EventsDAOImpl.getInstance().delete(item);
		resp.setContentType("application/json");
		resp.getWriter().print("[]");
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id;
		Events item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = EventsDAOImpl.getInstance().read(id);
		
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
		  
		String param1 = jsonObject.getString("description");
		Integer param2 = jsonObject.getInt("eventTypeId");
		Integer param3 = jsonObject.getInt("userId");
		Integer param4 = jsonObject.getInt("lockerId");
		Integer param5 = jsonObject.getInt("paymentId");
		Integer param6 = jsonObject.getInt("rentalId");
		
		
		long millis = System.currentTimeMillis();
        Date date=new Date(millis);  
        
        if(param1 != "" || param1 != null) {
    		//Integer p1 = Integer.parseInt(param1);
        	item.setDescription(param1);
        }
        
        if(param2 != null) {
        	item.setEventTypeId(param2);
        }
        
        if(param3 != null) {
        	item.setUserId(param3);
        }

        if(param4 != null) {
        	item.setLockerId(param4);
        }
        
        if(param5 != null) {
        	item.setPaymentId(param5);
        }
        
        if(param6 != null) {
        	item.setRentalId(param6);
        }

		item.setUpdatedAt(date);
		EventsDAOImpl.getInstance().update(item);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
	}
}
