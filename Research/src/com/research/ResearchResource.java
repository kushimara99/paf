package com.research;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.research.model.Research;

@Path("/research")
public class ResearchResource {
	Research research = new Research();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getResearch() {
		return research.getResearch().toString();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertResearch(String researchJsonString) {
		JsonObject researchJson =new  JsonParser().parse(researchJsonString).getAsJsonObject();
		
		return (research.insertResearch(researchJson.get("researcher_id").getAsInt(), researchJson.get("research_name").getAsString(), researchJson.get("description").getAsString(), researchJson.get("category").getAsString(), researchJson.get("expected_budget").getAsDouble())).toString();
	}
	
	
	
	
	
}
