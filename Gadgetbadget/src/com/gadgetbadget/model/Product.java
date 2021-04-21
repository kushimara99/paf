package com.gadgetbadget.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Product {

	private Connection connect()
    {
        Connection con = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

 

            //Provide the correct details: DBServer/DBName, username, password
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product", "root", "root");
        }
        catch (Exception e)
        {e.printStackTrace();}
        return con;
    } 

 

    //Read Research
    public JsonObject getProduct() {
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
            String query = " select * from product";

 


            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            
            JsonArray resultArray = new JsonArray();
            while(rs.next()) {
                JsonObject jsonRow = new JsonObject();
                jsonRow.addProperty("product_id", rs.getInt("product_id") );
                jsonRow.addProperty("researcher_id", rs.getInt("researcher_id") );
                jsonRow.addProperty("product_name", rs.getString("product_name") );
                jsonRow.addProperty("product_code", rs.getString("product_code") );
                jsonRow.addProperty("quantity", rs.getInt("quantity") );
                jsonRow.addProperty("product_price", rs.getDouble("product_price") );
                jsonRow.addProperty("timestamp", rs.getString("timestamp") );
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

 

    public JsonObject insertProduct( int researcher_id , String name, String code,int qnty,double price)
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
            String query = " insert into product"+
                    "(`researcher_id`,`product_name`,`product_code`,`quantity`,`product_price`)"
                    + " values (?, ?, ?, ?, ?)";

 


            PreparedStatement preparedStmt = con.prepareStatement(query);

 


            // binding values
            preparedStmt.setInt(1, researcher_id);
            preparedStmt.setString(2, name);
            preparedStmt.setString(3, code);
            preparedStmt.setInt(4, qnty);
            preparedStmt.setDouble(5,price);
        
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