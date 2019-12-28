package myRESTWebServices;

import application.PhotosRepository;
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
 * Receive vote web service (up/down-vote).
 * @author 170011408
 *
 */
@Path("/receivevotews")
public class ReceiveVoteWS {
  /**
   * Receive vote.
   * @param is the vote.
   * @return "OK", "Wrong Parameters", or "NOK" for successful, 
   *     successful but with wrong parameters, No such comment for 
   *     successful but for a non-existent comment,
   *     and unsuccessful request correspondingly. 
  */
  @POST 
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response receiveVote(InputStream is) {
    int count = 0;
    JsonParser parser = new JsonParser();
    JsonElement json = parser.parse(new InputStreamReader(is));
    String[] receivedVoteParameters = new String[4];
    if (json.isJsonArray()) {
      for (JsonElement e: json.getAsJsonArray()) {
        if (e.isJsonPrimitive()) {
          receivedVoteParameters[count] = e.getAsString();
          count++;
        }
      }
    } else {
      return Response.status(Response.Status.BAD_REQUEST).entity("NOK").build();
    } 
    if (count != 4) {
      return Response.ok().entity("Wrong Parameters").build();
    }
    int result = PhotosRepository.insertNewVote(receivedVoteParameters);
    if (result < 0) {
      return Response.ok().entity("No such comment").build();
    }
    return Response.ok().entity("OK").build();
  }
}
