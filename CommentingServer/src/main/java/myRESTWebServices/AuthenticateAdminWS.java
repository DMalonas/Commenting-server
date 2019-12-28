package myRESTWebServices;

import application.PhotosRepository;
import application.UsersPopulation;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Check if user is admin or not.
 * @author 170011408
 *
 */
@Path("/authenticateadminws")
public class AuthenticateAdminWS {
  @GET
  @Path("{username}")
  @Produces("text/plain")
  public String getMessage(@PathParam("username") String username) {
    if (UsersPopulation.checkIfAdmin(username)) {
      return "ADMIN";
    }
    else {
      return "NOT-ADMIN";
    }
  }
}  
