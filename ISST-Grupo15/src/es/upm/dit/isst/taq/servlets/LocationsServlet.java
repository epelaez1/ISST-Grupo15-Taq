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

import es.upm.dit.isst.taq.dao.LocationsDAOImpl;
import es.upm.dit.isst.taq.model.Locations;
/**
 * Servlet implementation class Form2Profesor
 */
@WebServlet({"/api/v1/admin/location/*", "/api/v1/admin/locations"})
public class LocationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationsServlet() {
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
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    	ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(df);
    	if(req.getRequestURI().contains("locations")) {
    		List<Locations> list = LocationsDAOImpl.getInstance().readAll();
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
    		Locations item = LocationsDAOImpl.getInstance().read(id);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		JSONObject jsonObject;
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  try {
		   jsonObject =  new JSONObject(jb.toString());
		  } catch (JSONException e) {
		    // crash and burn
		    throw new IOException("Error parsing JSON request string");
		  }

		Locations item = new Locations();
		long millis = System.currentTimeMillis();  
        Date date=new Date(millis);
        List<Locations> items = LocationsDAOImpl.getInstance().readAll();
        int id=1;
        
        if(items.size()!=0) {
        	id = items.get(items.size()-1).getId()+1;
        }
        
        item.setId(id);
		item.setName(jsonObject.getString("name"));
		item.setDescription(jsonObject.getString("description"));
		item.setCreatedAt(date);
		item.setUpdatedAt(date);
		LocationsDAOImpl.getInstance().create(item);
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		int id;
		Locations item = null;
		String param = req.getPathInfo();
    	
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = LocationsDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		LocationsDAOImpl.getInstance().delete(item);
		resp.getWriter().print("[]");
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id;
		Locations item = null;
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
		  
    	if (param == null) {
    		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
		id = Integer.parseInt(param.substring(1));
		
		item = LocationsDAOImpl.getInstance().read(id);
		
		if(item == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		return;
		}
		
		String param1 = jsonObject.getString("name");
		String param2 = jsonObject.getString("description");
		
		long millis = System.currentTimeMillis();
        Date date=new Date(millis);  
        
        if(param1 != "" && param1 != null) {
        	item.setName(param1);
        }
        
        if(param2 != "" && param2 != null) {
        	item.setDescription(param2);
        }
        
		item.setUpdatedAt(date);
		LocationsDAOImpl.getInstance().update(item);
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		mapper.setDateFormat(df);
		String jsonString = mapper.writeValueAsString(item);
		resp.getWriter().print(jsonString);
	}
}
