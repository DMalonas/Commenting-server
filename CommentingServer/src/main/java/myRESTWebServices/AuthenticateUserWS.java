package myRESTWebServices;

import application.PhotosRepository;
import application.UsersPopulation;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Authenticate user web service.
 * @author 170011408
 *
 */
@Path("/authenticateuserws")
public class AuthenticateUserWS {

  /**
   * Authenticate user.
   * @param is user name and password in JSON format.
   * @return OK if user name and password correct.
   *     Wrong Parameters, if wrong parameters are 
   *     given, and Invalid Credentials if the 
   *     wrong credentials are given.
   */
  @POST 
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response authenticateUser(InputStream is) {
    int count = 0;
    JsonParser parser = new JsonParser();
    JsonElement json = parser.parse(new InputStreamReader(is));
    String[] receivedCredentials = new String[2];
    if (json.isJsonArray()) {
      for (JsonElement e: json.getAsJsonArray()) {
        if (e.isJsonPrimitive()) {
          receivedCredentials[count] = e.getAsString();
          count++;
        }
      }
    } else {
      return Response.status(Response.Status.BAD_REQUEST).build(); // else send bad request
    } 
    if (count != 2) {
      return Response.ok().entity("Wrong Parameters").build();
    }
    boolean result = UsersPopulation.authenticate(receivedCredentials);
    if (!result) {
      return Response.ok().entity("Invalid credentials").build();
    }
    return Response.ok().entity("OK").build();
  }
}


