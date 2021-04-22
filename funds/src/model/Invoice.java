package model;

import java.sql.*; 

public class Invoice {
	
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
	
	public String insertinvoice(String amount, String research_id, String date, String cus_id)
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
	 String query = "insert into invoice(`id`,`amount`,`research_id`,`date`,`cus_id`)" + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, amount);
	 preparedStmt.setString(3, research_id);
	 preparedStmt.setString(4, date);
	 preparedStmt.setString(5, cus_id);
	
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 
	 catch (Exception e)
	 {
		 output = "Error while inserting the invoice";
		 System.err.println(e.getMessage());
	 }
	 	return output;
	 }
	
	
	public String readinvoice()
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
	 output = "<table border='1'><tr><th>amount</th><th>research_id</th>"  + "<th>date</th>" 
	 + "<th>cus_id</th>" + "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from invoice";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String id = Integer.toString(rs.getInt("id"));
		 String amount = rs.getString("amount");
		 String research_id = rs.getString("research_id");
		 String date = rs.getString("date");
		 String cus_id = rs.getString("cus_id");	 
		 
		 // Add into the html table
		 output += "<tr><td>" + amount + "</td>";
		 output += "<td>" + research_id + "</td>";
		 output += "<td>" + date + "</td>";
		 output += "<td>" + cus_id + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='invoice.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='id' type='hidden' value='" + id
		 + "'>" + "</form></td></tr>";
	 }
	 
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
		 catch (Exception e)
	 {
		 output = "Error while reading the invoice";
		 System.err.println(e.getMessage());
	 }
		 return output;
	 } 
	
	
	public String updateinvoice(String id, String amount, String research_id, String date, String cus_id)	
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
		 String query = "UPDATE invoice SET amount=?,research_id=?,date=?,cus_id=? WHERE id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setString(1, amount);
		 preparedStmt.setString(2, research_id);
		 preparedStmt.setString(3, date);
		 preparedStmt.setString(4, cus_id);
		 preparedStmt.setInt(5, Integer.parseInt(id));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 	catch (Exception e)
		 {
			 output = "Error while updating the invoice";
			 System.err.println(e.getMessage());
		 }
		 	return output;
		 }
	
	public String deleteinvoice(String id)
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
	 String query = "delete from invoice where id=?";
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
		 output = "Error while deleting the invoice";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
}
	

