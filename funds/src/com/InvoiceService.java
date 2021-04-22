package com;

import model.Invoice;

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

public class InvoiceService {
	
	Invoice invoiceObj = new Invoice();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readfunds()
	 {
		return invoiceObj.readinvoice();
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertinvoice
	(@FormParam("amount") String amount,
	 @FormParam("research_id") String research_id,
	 @FormParam("date") String date,
	 @FormParam("cus_id") String cus_id)
	{
		 String output = invoiceObj.insertinvoice(amount, research_id, date, cus_id);
		 return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateinvoice(String invoiceData)
	{
	//Convert the input string to a JSON object
	 JsonObject invoiceObject = new JsonParser().parse(invoiceData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String id = invoiceObject.get("id").getAsString();
	 String amount = invoiceObject.get("amount").getAsString();
	 String research_id =invoiceObject.get("research_id").getAsString();
	 String date =invoiceObject.get("date").getAsString();
	 String cus_id = invoiceObject.get("cus_id").getAsString();

	 String output = invoiceObj.updateinvoice(id, amount, research_id, date, cus_id);
	 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteinvoice(String invoiceData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(invoiceData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String id = doc.select("id").text();
	 String output = invoiceObj.deleteinvoice(id);
	 return output;
	}



}
