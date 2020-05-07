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
import es.upm.dit.isst.taq.dao.PaymentsDAOImpl;
import es.upm.dit.isst.taq.dao.RentalsDAOImpl;
import es.upm.dit.isst.taq.model.Lockers;
import es.upm.dit.isst.taq.model.Payments;
import es.upm.dit.isst.taq.model.Rentals;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/admin/payments/*", "/api/v1/admin/payments", "/api/v1/payments/*"})
public class PaymentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentsServlet() {
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
		
    	if(req.getRequestURI().contains("payments")) {
    		List<Payments> list = PaymentsDAOImpl.getInstance().readAll();
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
    		Payments item = PaymentsDAOImpl.getInstance().read(id);
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
		  
		Double param1 = jsonObject.getDouble("quantity");
		Integer param2 = jsonObject.getInt("userId");
		Integer param3 = jsonObject.getInt("rentalId");
		Integer param4 = jsonObject.getInt("paymentMethodId");
    	
		if (param1 == null ||param2 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if ( param3 == null || param4 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Payments item = new Payments();
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);
        List<Payments> items = PaymentsDAOImpl.getInstance().readAll();
        int id=1;
        
        if(items.size()!=0) {
        	id = items.get(items.size()-1).getId()+1;
        }
        
        item.setId(id);
        item.setQuantity(param1);
        item.setUserId(param2);
        item.setRentalId(param3);
        item.setPaymentMethodId(param4);
		item.setCreatedAt(date);
		item.setUpdatedAt(date);
		
    	if (req.getRequestURL().toString().contains("create")) {
    		Rentals rental = RentalsDAOImpl.getInstance().read(param3);
    		if (rental == null) {
    			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    			return;
    		}
    		rental.setRentalStateId(3);
    		RentalsDAOImpl.getInstance().update(rental);
    		Lockers locker = LockersDAOImpl.getInstance().read(rental.getLockerId());
    		if (locker == null) {
    			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    			return;
    		}
    		locker.setLockerStateId(4);
    		LockersDAOImpl.getInstance().update(locker);
    	}
		
		PaymentsDAOImpl.getInstance().create(item);
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id;
		Payments item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = PaymentsDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		PaymentsDAOImpl.getInstance().delete(item);
		resp.setContentType("application/json");
		resp.getWriter().print("[]");
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id;
		Payments item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = PaymentsDAOImpl.getInstance().read(id);
		
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
		  
		Double param1 = jsonObject.getDouble("quantity");
		Integer param2 = jsonObject.getInt("userId");
		Integer param3 = jsonObject.getInt("rentalId");
		Integer param4 = jsonObject.getInt("paymentMethodId");
		
		
		long millis = System.currentTimeMillis();
        Date date=new Date(millis);  
        
        if(param1 != null) {
        	item.setQuantity(param1);
        }
        
        if(param2 != null) {
        	item.setUserId(param2);
        }
        
        if(param3 != null) {
        	item.setRentalId(param3);
        }

        if(param4 != null) {
        	item.setPaymentMethodId(param4);
        }
        

		item.setUpdatedAt(date);
		PaymentsDAOImpl.getInstance().update(item);
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
	}
}
