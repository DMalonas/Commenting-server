package myRESTWebServices;

import application.Photo;
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
 * Remove comment from photo.
 * @author 170011408
 *
 */
@Path("/removecommentfromphotows")
public class RemoveCommentFromPhotoWS {
	  @GET
	  @Path("{insertedCommentCode}/{photoCode}")
	  @Produces("text/plain")
	  public String getMessage(@PathParam("insertedCommentCode") String insertedCommentCode, @PathParam("photoCode") String photoCode ) {
	    if (!PhotosRepository.photoExists(photoCode)) {
	      return "No such photo";
	    }
	    Photo photo = PhotosRepository.returnPhotoByCode(photoCode);
	    if (photo.hasCommentWithCode(insertedCommentCode) == null) {
	      return "No such comment exists";
	    }
	    if (PhotosRepository.removeComment(insertedCommentCode, photoCode) != -1){
	    	return "OK";
	    }
	    return "NOK";
	  }
}