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
 * Receive comments web service.
 * @author 170011408
 *
 */
@Path("/receivecommentws")
public class ReceiveCommentWS {
  /**
   * Receive comment web service. 
   * @param is the input stream containing the comment in JSON format.
   * @return "OK", "Wrong Parameters", or "NOK" for successful, 
   *     successful but with wrong parameters 
   *     and unsucessful request correspondingly.
   */
  @POST 
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response receiveComment(InputStream is) {
    int count = 0;
    JsonParser parser = new JsonParser();
    JsonElement json = parser.parse(new InputStreamReader(is));
    String[] receivedCommentParameters = new String[4];
    if (json.isJsonArray()) {
      for (JsonElement e: json.getAsJsonArray()) {
        if (e.isJsonPrimitive()) {
          receivedCommentParameters[count] = e.getAsString();
          count++;
        }
      }
    } else {
      return Response.status(Response.Status.BAD_REQUEST).entity("NOK").build();
    } 
    if (count != 4) {
      return Response.ok().entity("Wrong Parameters").build();
    }
    int result = PhotosRepository.insertNewComment(receivedCommentParameters);
    if (result < 0) {
      return Response.ok().entity("No such comment").build();
    }
    return Response.ok().entity("OK").build();
  }
}