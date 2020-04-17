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

import es.upm.dit.isst.taq.dao.UsersDAOImpl;
import es.upm.dit.isst.taq.model.Users;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/user/*", "/api/v1/users"})
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
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
    	
    	if(req.getRequestURI().contains("users")) {
    		List<Users> list = UsersDAOImpl.getInstance().readAll();
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
    		Users item = UsersDAOImpl.getInstance().read(id);
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
		
		ObjectMapper mapper = new ObjectMapper();
		
		String param1 = req.getParameter("name");
		String param2 = req.getParameter("phone");
		String param3 = req.getParameter("dni");
		String param4 = req.getParameter("email");
		String param5 = req.getParameter("isAdmin");
		
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
		
		Boolean p5 = Boolean.parseBoolean(param5);
		
		Users item = new Users();
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);
        List<Users> items = UsersDAOImpl.getInstance().readAll();
        int id=1;
        
        if(items.size()!=0) {
        	id = items.get(items.size()-1).getId()+1;
        }
        
        item.setId(id);
        item.setName(param1);
        item.setPhone(param2);
        item.setDni(param3);
        item.setEmail(param4);
        item.setAdmin(p5);
		item.setCreatedAt(date);
		item.setUpdatedAt(date);
		
		UsersDAOImpl.getInstance().create(item);
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id;
		Users item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = UsersDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		UsersDAOImpl.getInstance().delete(item);
		
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id;
		Users item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = UsersDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		
		String param1 = req.getParameter("name");
		String param2 = req.getParameter("phone");
		String param3 = req.getParameter("dni");
		String param4 = req.getParameter("email");
		String param5 = req.getParameter("isAdmin");
		
		
		long millis = System.currentTimeMillis();
        Date date=new Date(millis);  
        
        if(param1 != "" || param1 != null) {
        	item.setName(param1);
        }
        
        if(param2 != "" || param2 != null) {
        	item.setPhone(param2);
        }
        
        if(param3 != "" || param3 != null) {
        	item.setDni(param3);
        }

        if(param4 != "" || param4 != null) {
        	item.setEmail(param4);
        }
        
        if(param5 != "" || param5 != null) {
    		Boolean p5 = Boolean.parseBoolean(param5);
        	item.setAdmin(p5);
        }
        
		item.setUpdatedAt(date);
		UsersDAOImpl.getInstance().update(item);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
	}
}
