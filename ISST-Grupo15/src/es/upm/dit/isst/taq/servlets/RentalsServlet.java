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

import es.upm.dit.isst.taq.dao.RentalsDAOImpl;
import es.upm.dit.isst.taq.model.Rentals;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/rental/*", "/api/v1/rentals"})
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
		
		String param1 = req.getParameter("expirationDate");
		String param2 = req.getParameter("deposit");
		String param3 = req.getParameter("userId");
		String param4 = req.getParameter("lockerId");
		String param5 = req.getParameter("rentalStateId");
		
		String param = req.getPathInfo();
    	
    	if (param != null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		if (param1 == "" || param1 == null || param2 == "" || param2 == null || param3 == "" || param3 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if (param4 == "" || param4 == null || param5 == "" || param5 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Date p1 = Date.valueOf(param1);
		Double p2 = Double.parseDouble(param2);
		Integer p3 = Integer.parseInt(param3);
		Integer p4 = Integer.parseInt(param4);
		Integer p5 = Integer.parseInt(param5);
		
		Rentals item = new Rentals();
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);
        List<Rentals> items = RentalsDAOImpl.getInstance().readAll();
        int id=1;
        
        if(items.size()!=0) {
        	id = items.get(items.size()-1).getId()+1;
        }
        
        item.setId(id);
        item.setExpirationDate(p1);
        item.setDeposit(p2);
        item.setUserId(p3);
        item.setLockerId(p4);
        item.setRentalStateId(p5);
		item.setCreatedAt(date);
		item.setUpdatedAt(date);
		
		RentalsDAOImpl.getInstance().create(item);
			
		
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
		
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		
		String param1 = req.getParameter("expirationDate");
		String param2 = req.getParameter("deposit");
		String param3 = req.getParameter("userId");
		String param4 = req.getParameter("lockerId");
		String param5 = req.getParameter("rentalStateId");
		
		
		long millis = System.currentTimeMillis();
        Date date=new Date(millis);  
        
        if(param1 != "" || param1 != null) {
    		Date p1 = Date.valueOf(param1);
        	item.setExpirationDate(p1);
        }
        
        if(param2 != "" || param2 != null) {
    		Double p2 = Double.parseDouble(param2);
        	item.setDeposit(p2);
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
        	item.setRentalStateId(p5);
        }
        

		item.setUpdatedAt(date);
		RentalsDAOImpl.getInstance().update(item);

	}
}
