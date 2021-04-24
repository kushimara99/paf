package com.research.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	protected Connection connect()
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
	
	
	
	
	
}
