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

import es.upm.dit.isst.taq.dao.EventsDAOImpl;
import es.upm.dit.isst.taq.model.Events;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/event/*", "/api/v1/events"})
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
		
		String param1 = req.getParameter("description");
		String param2 = req.getParameter("eventTypeId");
		String param3 = req.getParameter("userId");
		String param4 = req.getParameter("lockerId");
		String param5 = req.getParameter("paymentId");
		String param6 = req.getParameter("rentalId");
		
		String param = req.getPathInfo();
    	
    	if (param != null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		if (param1 == "" || param1 == null || param2 == "" || param2 == null || param3 == "" || param3 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if (param4 == "" || param4 == null || param5 == "" || param5 == null || param6 == "" || param6 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//Integer p1 = Integer.parseInt(param1);
		Integer p2 = Integer.parseInt(param2);
		Integer p3 = Integer.parseInt(param3);
		Integer p4 = Integer.parseInt(param4);
		Integer p5 = Integer.parseInt(param5);
		Integer p6 = Integer.parseInt(param6);
		
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
		item.setEventTypeId(p2);
		item.setUserId(p3);
		item.setLockerId(p4);
		item.setPaymentId(p5);
		item.setRentalId(p6);
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
		
		String param1 = req.getParameter("description");
		String param2 = req.getParameter("eventTypeId");
		String param3 = req.getParameter("userId");
		String param4 = req.getParameter("lockerId");
		String param5 = req.getParameter("paymentId");
		String param6 = req.getParameter("rentalId");
		
		
		long millis = System.currentTimeMillis();
        Date date=new Date(millis);  
        
        if(param1 != "" || param1 != null) {
    		//Integer p1 = Integer.parseInt(param1);
        	item.setDescription(param1);
        }
        
        if(param2 != "" || param2 != null) {
    		Integer p2 = Integer.parseInt(param2);
        	item.setEventTypeId(p2);
        }
        
        if(param3 != "" || param3 != null) {
    		Integer p3 = Integer.parseInt(param3);
        	item.setUserId(p3);
        }

        if(param4 != "" || param4 != null) {
    		Integer p4 = Integer.parseInt(param4);
        	item.setLockerId(p4);
        }
        
        if(param5 != "" || param5 != null) {
    		Integer p5 = Integer.parseInt(param5);
        	item.setPaymentId(p5);
        }
        
        if(param6 != "" || param6 != null) {
    		Integer p6 = Integer.parseInt(param6);
        	item.setRentalId(p6);
        }

		item.setUpdatedAt(date);
		EventsDAOImpl.getInstance().update(item);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
	}
}
