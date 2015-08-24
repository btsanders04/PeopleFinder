

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PeopleFinder
 */
@WebServlet("/PeopleFinder")
public class PeopleFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeopleFinder() {
        super();
        // TODO Auto-generated constructor stub
    }


	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBQuery.openConnection();
	}


	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/SearchPeople.jsp").forward(
				request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("POST");
		//if(request.getParameter("submit")!=null){
		String lastName = request.getParameter("name");
		String details = printUserDetails(getUsers(lastName));
		String companies = printCompanies(lastName);
		request.setAttribute("details", details);
		request.setAttribute("companies", companies);
		//.out.println(lastName);
	//	}
		
		
		getServletContext().getRequestDispatcher("/SearchPeople.jsp").forward(
				request, response);	
	}
	
	protected ArrayList<User> getUsers(String name){
		
		ArrayList<User> users = new ArrayList<User>();
		String sql = "select firstname, lastname from customers where lastname like '"
				+name+"%'";
		System.out.println(sql);
		try {
			ResultSet result = DBQuery.getFromDB(sql);
			while(result.next()){
				users.add(new User(result.getString("firstName"),result.getString("lastName")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	protected String printUserDetails(ArrayList<User> users){
		String html="";
		if(users.size()>0){
		html+="<div class=\"container\"><h2>Customer Details</h2><table class=\"table table-bordered\">"+
		    "<thead><tr><th>Firstname</th><th>Lastname</th><th>Email</th><th>Company</th>"+
			"<th>Location</th></tr></thead><tbody>";
		for(User u: users){     
			html+= u.displayDetails();
		}
		   html+="</tbody></table></div>";
		   System.out.println(html);
		}
		return html;
	}
	
	protected String printCompanies(String companyName){
		String sql = "select name from companies where name like '"
				+companyName+"%'";
		String html="";
		try {
			ResultSet result = DBQuery.getFromDB(sql);
			if(result.next()){
				html+="<div class=\"container\"><h2>Company Details</h2><table class=\"table table-bordered\">"+
					    "<thead><tr><th>Name</th></tr></thead><tbody>";
				html+="<td>"+result.getString("name")+"</td></tr>";
			}
			while(result.next()){
				html+="<tr><td>"+result.getString("name")+"</td></tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}

}
