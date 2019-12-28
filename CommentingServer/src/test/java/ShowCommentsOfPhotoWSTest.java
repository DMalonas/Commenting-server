import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.*;
import myRESTWebServices.*;
import utilities.MyUtilitiesClass;
/**
 * 
 * @author Dimit
 *
 */
public class ShowCommentsOfPhotoWSTest {
	/**
	 * 
	 */
	@Test
	public void testWrongInput() {
		new PhotosRepository();
		ShowCommentsOfPhotoWS showCommentsOfPhotoWS = new ShowCommentsOfPhotoWS();
		assertEquals("Wrong input", showCommentsOfPhotoWS.getMessage("0", "dummyUserName"));
	}
	/**
	 * 
	 */
	@Test
	public void testNoComments() {			
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);
		ShowCommentsOfPhotoWS showCommentsOfPhotoWS = new ShowCommentsOfPhotoWS();
		assertEquals("No comments", showCommentsOfPhotoWS.getMessage("1", "dummyUserName"));
		MyUtilitiesClass.removeInitialPopulations(photosRepository, usersPopulation);
	}
	/**
	 * 
	 */
	@Test
	public void testCorrectInputAndCommentsExist() {	
		
		CommentCode commentCode1 = new CommentCode();
		commentCode1.addLevel(1);
		Comment comment1 = new Comment(commentCode1, "photoCode-1", "Dimitrios", "Timestamp1", "Hello!");
		CommentCode commentCode2 = new CommentCode();
		commentCode2.addLevel(1);
		commentCode2.addLevel(2);
		Comment comment2 = new Comment(commentCode2, "photoCode-1", "Harry", "Timestamp2", "Hello to you too!");
		Photo photo = new Photo("photoCode-1");
		photo.addComment(comment2);	// Insert in reverse mode to check sorting too 
		photo.addComment(comment1);
		PhotosRepository photosRepository = new PhotosRepository();
		photosRepository.insertNewPhoto(photo);
		ShowCommentsOfPhotoWS showCommentsOfPhotoWS = new ShowCommentsOfPhotoWS();
		assertEquals("(1) by Dimitrios on Timestamp1 - 0 UpVotes, 0 DownVotes - : \r\n" + 
				"Hello!\r\n" + 
				"	(1.2) by Harry on Timestamp2 - 0 UpVotes, 0 DownVotes - : \r\n" + 
				"	Hello to you too!\r\n", showCommentsOfPhotoWS.getMessage("photoCode-1", "dummyUserName"));
	}
}
