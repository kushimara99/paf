package com.research.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Research extends DbConnection {


	//Read Research
	public JsonObject getResearch() {
		JsonObject result = null;
		try
		{
			Connection con = connect();

			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for inserting."); 
			}


			// sql query
			String query = " select * from research";

			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			
			//binding values
			JsonArray resultArray = new JsonArray();
			while(rs.next()) {
				JsonObject jsonRow = new JsonObject();
				jsonRow.addProperty("research_id", rs.getInt("research_id") );
				jsonRow.addProperty("researcher_id", rs.getInt("researcher_id") );
				jsonRow.addProperty("research_name", rs.getString("research_name") );
				jsonRow.addProperty("description", rs.getString("description") );
				jsonRow.addProperty("category", rs.getString("category") );
				jsonRow.addProperty("expected_budget", rs.getDouble("expected_budget") );
				resultArray.add(jsonRow);
				
			}
			result = new JsonObject();
			result.add("research", resultArray);
			
			con.close();
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION", "Error occured while reading research");
			System.err.println(e.getMessage());
		}
		return result;
	}

	//insert research
	public JsonObject insertResearch( int researcher_id , String name, String description,String category,double budget)
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


			// sql query
			String query = " insert into research"+
					"(`researcher_id`,`research_name`,`description`,`category`,`expected_budget`)"
					+ " values (?, ?, ?, ?, ?)";

			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);


			// binding values
			preparedStmt.setInt(1, researcher_id);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, category);
			preparedStmt.setDouble(5,budget);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			result = new JsonObject();
			result.addProperty("SUCCESSFUL", "Inserted successfully");
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION","Error while inserting the item.");
			System.err.println(e.getMessage());
		}
		return result;
	} 
	
	//update research
	public JsonObject updateResearch( int research_id, int researcher_id , String name, String description,String category,double budget)
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


			//sql query
			String query = "UPDATE research SET researcher_id=?,research_name=?,description=?,category=?,expected_budget=? WHERE research_id=?";

			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);


			// binding values
			preparedStmt.setInt(1, researcher_id);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, category);
			preparedStmt.setDouble(5,budget);
			preparedStmt.setInt(6, research_id);
			
			// execute the statement
			int status = preparedStmt.executeUpdate();
			con.close();
			
			result = new JsonObject();
			if(status > 0) {
				result.addProperty("SUCCESSFUL", "Updated successfully");
				
			}else {
				result.addProperty("UNSUCCESSFUL", "Updated failed");
			}
				
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION","Error while updating the item.");
			System.err.println(e.getMessage());
		}
		return result;
	} 
	
	
	
	//delete research
	public JsonObject deleteResearch( int research_id )
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


			// sql query
			String query = "delete from research where research_id=?";
					 
			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);


			// binding values
			preparedStmt.setInt(1, research_id);

			
			// execute the statement
			int status = preparedStmt.executeUpdate();
			con.close();
			
			result = new JsonObject();
			if(status > 0) {
				result.addProperty("SUCCESSFUL", "Deleted successfully");
				
			}else {
				result.addProperty("UNSUCCESSFUL", "Delete failed");
			}
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION","Error while deleting the item.");
			System.err.println(e.getMessage());
		}
		return result;
	} 
	
	
	//get a single record
	public JsonObject getsingleResearch(int research_id) {
		JsonObject result = null;
		try
		{
			Connection con = connect();

			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for inserting."); 
			}


			//sql query
			String query = " select * from research where research_id=?";

			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, research_id);
			ResultSet rs = preparedStmt.executeQuery();
			
			// binding values
			JsonArray resultArray = new JsonArray();
			while(rs.next()) {
				JsonObject jsonRow = new JsonObject();
				jsonRow.addProperty("research_id", rs.getInt("research_id") );
				jsonRow.addProperty("researcher_id", rs.getInt("researcher_id") );
				jsonRow.addProperty("research_name", rs.getString("research_name") );
				jsonRow.addProperty("description", rs.getString("description") );
				jsonRow.addProperty("category", rs.getString("category") );
				jsonRow.addProperty("expected_budget", rs.getDouble("expected_budget") );
				resultArray.add(jsonRow);
				
			}
			result = new JsonObject();
			result.add("research", resultArray);
			
			con.close();
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION", "Error occured while reading research");
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	


}
