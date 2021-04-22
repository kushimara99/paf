package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class user {
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");



			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb", "root","");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	} 



	//Read user
	public JsonObject getUser() {
		JsonObject result = null;
		try
		{
			Connection con = connect();



			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for inserting."); 
			}


			// create a prepared statement
			String query = " select * from usertb";


			PreparedStatement preparedStmt = con.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			JsonArray resultArray = new JsonArray();
			while(rs.next()) {
				JsonObject jsonRow = new JsonObject();
				jsonRow.addProperty("id", rs.getInt("id") );
				jsonRow.addProperty("name", rs.getString("name") );
				jsonRow.addProperty("email", rs.getString("email") );
				jsonRow.addProperty("contact", rs.getString("contact") );
				jsonRow.addProperty("type", rs.getString("type") );

				resultArray.add(jsonRow);
				//System.out.println(jsonRow.toString());
			}
			result = new JsonObject();
			result.add("usertb", resultArray);

			con.close();
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION", "Error occured while reading user");
			System.err.println(e.getMessage());
		}
		return result;
	}

	//insert user
	public JsonObject insertuser( String name, String email,String contact,String type)
	{
		JsonObject result = null;
		try
		{
			Connection con = connect();

			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for inserting.");
			}

			// create a prepared statement
			String query = " insert into usertb"+
					"(`name`,`email`,`contact`,`type`)"
					+ " values (?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, contact);
			preparedStmt.setString(4, type);
			// execute the statement

			preparedStmt.execute();
			con.close();
			result = new JsonObject();
			result.addProperty("SUCCESSFUL", "Inserted successfully");
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION","Error while inserting the user.");
			System.err.println(e.getMessage());
		}
		return result;
	}

	//update user
	public JsonObject updateuser( int id, String name, String email,String contact,String type)
	{
		JsonObject result = null;
		try
		{
			Connection con = connect();

			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for inserting.");
			}

			// create a prepared statement
			String query = "UPDATE usertb SET name=?,email=?,contact=?,type=? WHERE id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, contact);
			preparedStmt.setString(4, type);
			preparedStmt.setInt(5, id);
			// execute the statement
			int sts = preparedStmt.executeUpdate();
			con.close();
			result = new JsonObject();
			if(sts > 0) {
				result.addProperty("SUCCESSFUL", "Updated successfully");
			}else {
				result.addProperty("UNSUCCESSFUL", "Updated failed");
			}

		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION","Error while updating the user.");
			System.err.println(e.getMessage());
		}
		return result;
	}

	//delete user
	public JsonObject deleteuser( int id )
	{
		JsonObject result = null;
		try
		{
			Connection con = connect();

			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for inserting.");
			}

			// create a prepared statement
			String query = "delete from usertb where id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, id);

			int sts = preparedStmt.executeUpdate();
			con.close();
			result = new JsonObject();
			if(sts > 0) {
				result.addProperty("SUCCESSFUL", "Deleted successfully");
			}else {
				result.addProperty("UNSUCCESSFUL", "Deleted failed");
			}
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION","Error while deleting the user.");
			System.err.println(e.getMessage());
		}
		return result;
	}

}
