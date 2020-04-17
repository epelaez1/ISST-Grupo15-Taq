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

import es.upm.dit.isst.taq.dao.PaymentsDAOImpl;
import es.upm.dit.isst.taq.model.Payments;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/payment/*", "/api/v1/payments"})
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
		
		String param1 = req.getParameter("quantity");
		String param2 = req.getParameter("userId");
		String param3 = req.getParameter("rentalId");
		String param4 = req.getParameter("paymentMethodId");
		
		String param = req.getPathInfo();
    	
    	if (param != null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		if (param1 == "" || param1 == null || param2 == "" || param2 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if (param3 == "" || param3 == null || param4 == "" || param4 == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Double p1 = Double.parseDouble(param1);
		Integer p2 = Integer.parseInt(param2);
		Integer p3 = Integer.parseInt(param3);
		Integer p4 = Integer.parseInt(param4);

		Payments item = new Payments();
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);
        List<Payments> items = PaymentsDAOImpl.getInstance().readAll();
        int id=1;
        
        if(items.size()!=0) {
        	id = items.get(items.size()-1).getId()+1;
        }
        
        item.setId(id);
        item.setQuantity(p1);
        item.setUserId(p2);
        item.setRentalId(p3);
        item.setPaymentMethodId(p4);
		item.setCreatedAt(date);
		item.setUpdatedAt(date);
		
		PaymentsDAOImpl.getInstance().create(item);
			
		
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
		
		String param1 = req.getParameter("quantity");
		String param2 = req.getParameter("userId");
		String param3 = req.getParameter("rentalId");
		String param4 = req.getParameter("paymentMethodId");
		
		
		long millis = System.currentTimeMillis();
        Date date=new Date(millis);  
        
        if(param1 != "" || param1 != null) {
        	Double p1 = Double.parseDouble(param1);
        	item.setQuantity(p1);
        }
        
        if(param2 != "" || param2 != null) {
    		Integer p2 = Integer.parseInt(param2);
        	item.setUserId(p2);
        }
        
        if(param3 != "" || param3 != null) {
    		Integer p3 = Integer.parseInt(param3);
        	item.setRentalId(p3);
        }

        if(param4 != "" || param4 != null) {
    		Integer p4 = Integer.parseInt(param4);
        	item.setPaymentMethodId(p4);
        }
        

		item.setUpdatedAt(date);
		PaymentsDAOImpl.getInstance().update(item);

	}
}
