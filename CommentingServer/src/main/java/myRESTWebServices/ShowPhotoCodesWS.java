package myRESTWebServices;

import application.PhotosRepository;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



/**
 * Get all photo codes web service.
 * @author 170011408
 *
 */
@Path("/showphotocodesws")
public class ShowPhotoCodesWS {
  /**
   * Get the photo codes from the PhotosRepository.
   * @return
   */
  @GET
  @Produces("text/plain")
  public String getMessage() {
    return PhotosRepository.returnAvailablePhotoCodesAsString();
  }
}

