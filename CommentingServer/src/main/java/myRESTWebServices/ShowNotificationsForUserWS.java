package myRESTWebServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import application.UsersPopulation;

@Path("/shownotificationsforuserws")	
public class ShowNotificationsForUserWS {

	@GET
	  @Path("{username}")  //HERE FENETAI OTI TO WS AUTO EXEI PARAMETRO 
	  @Produces("text/plain")
	  public String getMessage(@PathParam("username") String username) { //KAI EDO FENETAI OTI EXEI PARAMETRO
		if (!UsersPopulation.userExists(username))
			return "Wrong input";
	    return UsersPopulation.returnUserNotificationsAsString(username);
	  }
}

