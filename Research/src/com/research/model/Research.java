package com.research.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Research {
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/research-service", "root", "root");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	} 

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


			// create a prepared statement
			String query = " select * from research";


			PreparedStatement preparedStmt = con.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			
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
				//System.out.println(jsonRow.toString());
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


			// create a prepared statement
			String query = " insert into research"+
					"(`researcher_id`,`research_name`,`description`,`category`,`expected_budget`)"
					+ " values (?, ?, ?, ?, ?)";


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


}
