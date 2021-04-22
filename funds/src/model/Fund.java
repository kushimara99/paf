package model;

import java.sql.*; 

public class Fund {
	
	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
	 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/funddb", "root", "");
	 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
	 return con; 
	 
 }
	
	public String insertfunds(String sponsor_nic, String research_id, String f_amount, String sponsor, String f_date)
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
	 {
		 return "Error while connecting to the database for inserting.";
	 }
	 
	 // create a prepared statement
	 String query = "insert into funds(`id`,`sponsor_nic`,`research_id`,`f_amount`,`sponsor`,`f_date`)" + " values (?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, sponsor_nic);
	 preparedStmt.setString(3, research_id);
	 preparedStmt.setString(4, f_amount);
	 preparedStmt.setString(5, sponsor);
	 preparedStmt.setString(6, f_date);
	// execute the statement
//	 System.out.println(sponsor_nic);
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 
	 catch (Exception e)
	 {
		 output = "Error while inserting the funds";
		 System.err.println(e.getMessage());
	 }
	 	return output;
	 }
	
	
	public String readfunds()
	 {
		 String output = "";
		 try
	 {
		 Connection con = connect();
		 if (con == null)
	 {
			 return "Error while connecting to the database for reading"; 
			 
	 }
		 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>sponsor_nic</th><th>research_id</th>"  + "<th>f_amount</th>" 
	 + "<th>sponsor</th>" + "<th>f_date</th>" + "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from funds";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String id = Integer.toString(rs.getInt("id"));
		 String sponsor_nic = rs.getString("sponsor_nic");
		 String research_id = rs.getString("research_id");
		 String f_amount = rs.getString("f_amount");
		 String sponsor = rs.getString("sponsor");
		 String f_date = rs.getString("f_date");
		 
		 
		 // Add into the html table
		 output += "<tr><td>" + sponsor_nic + "</td>";
		 output += "<td>" + research_id + "</td>";
		 output += "<td>" + f_amount + "</td>";
		 output += "<td>" + sponsor + "</td>";
		 output += "<td>" + f_date + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='funds.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='id' type='hidden' value='" + id
		 + "'>" + "</form></td></tr>";
	 }
	 
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
		 catch (Exception e)
	 {
		 output = "Error while reading the funds";
		 System.err.println(e.getMessage());
	 }
		 return output;
	 } 
	
	
	public String updatefunds(String id, String sponsor_nic, String research_id, String f_amount, String sponsor, String f_date)	
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 if (con == null)
		 {
			 return "Error while connecting to the database for updating.";
		}
			 		 
		 // create a prepared statement
		 String query = "UPDATE funds SET sponsor_nic=?,research_id=?,f_amount=?,sponsor=?,f_date=? WHERE id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setString(1, sponsor_nic);
		 preparedStmt.setString(2, research_id);
		 preparedStmt.setString(3, f_amount);
		 preparedStmt.setString(4, sponsor);
		 preparedStmt.setString(5, f_date);
		 preparedStmt.setInt(6, Integer.parseInt(id));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 	catch (Exception e)
		 {
			 output = "Error while updating the funds.";
			 System.err.println(e.getMessage());
		 }
		 	return output;
		 }
	
	public String deletefunds(String id)
	 {
	 String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
	 {
		 return "Error while connecting to the database for deleting."; 
		 
	 }
	 // create a prepared statement
	 String query = "delete from funds where id=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id));
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while deleting the funds";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
}
	

