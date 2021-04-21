package com.gadgetbadget;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gadgetbadget.model.Product;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/products")
public class ProductResource {
	Product product = new Product();
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProducts() {
		return product.getProduct().toString();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertProduct(String productJsonString) {
		JsonObject productJsonParsed = new JsonParser().parse(productJsonString).getAsJsonObject();
		
		return (product.insertProduct(productJsonParsed.get("researcher_id").getAsInt(), productJsonParsed.get("product_name").getAsString(), productJsonParsed.get("product_code").getAsString(), productJsonParsed.get("quantity").getAsInt(), productJsonParsed.get("product_price").getAsDouble())).toString();
	}
}
