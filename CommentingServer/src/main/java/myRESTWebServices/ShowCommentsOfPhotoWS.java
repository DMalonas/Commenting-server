package myRESTWebServices;

import application.PhotosRepository;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Show comments of a photo web service.
 * @author 170011408
 *
 */

@Path("/showcommentsofphotows")
public class ShowCommentsOfPhotoWS {
  /**
   * Get message containing photo's code as String.
   * @param photoCode the photo's code.
   * @return the comments of the requested photo as a string.
   */
  @GET
  @Path("{photoCode}/{username}")  //parameter
  @Produces("text/plain")
  public String getMessage(@PathParam("photoCode") String photoCode, @PathParam("username") String username) {
    if (!PhotosRepository.photoExists(photoCode)) {
      return "Wrong input";
    }
    if (PhotosRepository.commentsNumberOfPhoto(photoCode) == 0) {
      return "No comments";
    }
    return PhotosRepository.returnPhotoCommentsAsString(photoCode, username);
  }
}
