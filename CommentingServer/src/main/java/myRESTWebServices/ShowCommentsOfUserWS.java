package myRESTWebServices;

import application.PhotosRepository;
import application.UsersPopulation;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Show comments of chosen user web service.
 * @author 170011408
 *
 */
@Path("/showcommentsofuserws")
public class ShowCommentsOfUserWS {
    
  /**
  * Get user name and return user's comments.
  * @param username the user name.
  * @return the user's comments.
  */
  @GET
  @Path("{username}")
  @Produces("text/plain")
  public String getMessage(@PathParam("username") String username) {
    if (!UsersPopulation.userExists(username)) {
      return "Wrong input";
    }
    if (UsersPopulation.commentsNumberOfUser(username) == 0) {
      return "No comments";
    }
    return UsersPopulation.returnUserCommentsAsString(username);
  }
}
