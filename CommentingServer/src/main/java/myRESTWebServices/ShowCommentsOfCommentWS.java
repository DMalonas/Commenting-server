package myRESTWebServices;

import application.PhotosRepository;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Show comments under a commeent web service.s
 * @author 170011408
 *
 */
@Path("/showcommentsofcommentws")
public class ShowCommentsOfCommentWS {
  /**
   * Show comments under chosen comment.
   * @param photoCode the photo's code.
   * @param commentCode the comment' code.
   * @return 
   */
  @GET
  @Path("{photoCode}/{commentCode}")  // https://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
  @Produces("text/plain")
  public String getMessage(@PathParam("photoCode") String photoCode, 
      @PathParam("commentCode") String commentCode) {
    return PhotosRepository.returnCommentsOfCommentAsString(photoCode, commentCode);
  }
}