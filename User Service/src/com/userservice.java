package com;

import model.user;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.tomcat.jni.User;

//For JSON
import com.google.gson.*;

@Path("/user")
public class userservice{
	user u1 = new user();
	

@GET
@Path("/")
@Produces(MediaType.APPLICATION_JSON)

public String getUser() 
{
return u1.getUser().toString();
}

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String insertuser(String u1JsonString) {
JsonObject userJson =new JsonParser().parse(u1JsonString).getAsJsonObject();

 return (u1.insertuser(userJson.get("name").getAsString(), userJson.get("email").getAsString(), userJson.get("contact").getAsString(), userJson.get("type").getAsString())).toString();
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String updateuser(String u1JsonString) {
JsonObject userJson =new JsonParser().parse(u1JsonString).getAsJsonObject();

 return (u1.updateuser(userJson.get("id").getAsInt(),userJson.get("name").getAsString(), userJson.get("email").getAsString(), userJson.get("contact").getAsString(), userJson.get("type").getAsString())).toString();
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String deleteuser(String u1JsonString) {
JsonObject userJson =new JsonParser().parse(u1JsonString).getAsJsonObject();

 return (u1.deleteuser(userJson.get("id").getAsInt())).toString();
}

}
