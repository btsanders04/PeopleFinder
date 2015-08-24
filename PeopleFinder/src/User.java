import java.sql.*;


public class User {

	String first,last;
	
	public User(String f, String l){
		first=f;
		last=l;
	}
	
	public String displayDetails(){
		String sql="select * from customers where firstname = '"+first +"' and "
				+ "lastname = '" +last+"'";
		String details="";
		try {
			ResultSet customer = DBQuery.getFromDB(sql);
			
			if(customer.next()){
				String company = getCompany(customer);
				String location = getLocation(customer);
				details+="<tr><td>"+customer.getString("firstName")+"</td><td>"+
			customer.getString("lastName")+"</td><td>"+customer.getString("emailAddress")+"</td><td>"+
						company+"</td><td>" + location + "</td></tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return details;
	}
	
	private String getCompany(ResultSet r){
		try {
			String sql="select name from companies where company_id ="+ r.getString("company_Id");
			ResultSet company = DBQuery.getFromDB(sql);
			if(company.next()){
				return company.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	private String getLocation(ResultSet r){
		try {
			String sql="select * from locations where location_id ="+ r.getString("location_Id");
			ResultSet location = DBQuery.getFromDB(sql);
			if(location.next()){
				return ""+location.getString("City")+", "+location.getString("States")
						+" "+location.getString("zipcode");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
