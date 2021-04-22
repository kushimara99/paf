package com;

import model.Fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/funds") 

public class FundService {
	
	Fund fundObj = new Fund();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readfunds()
	 {
		return fundObj.readfunds();
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertfunds
	(@FormParam("sponsor_nic") String sponsor_nic,
	 @FormParam("research_id") String research_id,
	 @FormParam("f_amount") String f_amount,
	 @FormParam("sponsor") String sponsor,
	 @FormParam("f_date") String f_date)
	{
		 String output = fundObj.insertfunds(sponsor_nic, research_id, f_amount, sponsor, f_date);
		 return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatefunds(String fundData)
	{
	//Convert the input string to a JSON object
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String id = fundObject.get("id").getAsString();
	 String sponsor_nic = fundObject.get("sponsor_nic").getAsString();
	 String research_id =fundObject.get("research_id").getAsString();
	 String f_amount =fundObject.get("f_amount").getAsString();
	 String sponsor = fundObject.get("sponsor").getAsString();
	 String f_date = fundObject.get("f_date").getAsString();
	 
	 String output = fundObj.updatefunds(id, sponsor_nic, research_id, f_amount, sponsor, f_date);
	 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletefunds(String fundData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String id = doc.select("id").text();
	 String output = fundObj.deletefunds(id);
	 return output;
	}



}
