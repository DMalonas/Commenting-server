import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.Comment;
import application.CommentCode;
import application.Photo;
import application.PhotosRepository;
import application.UsersPopulation;
import myRESTWebServices.ShowCommentsOfCommentWS;
import myRESTWebServices.ShowCommentsOfPhotoWS;
import utilities.MyUtilitiesClass;

public class ShowCommentsOfCommentWSTest {
	
	@Test
	public void testNoPhoto() {
		new PhotosRepository();
		ShowCommentsOfCommentWS showCommentsOfCommentWS = new ShowCommentsOfCommentWS();
		assertEquals("", showCommentsOfCommentWS.getMessage("0", "1.1"));
	}
	/**
	 * 
	 */
	@Test
	public void testNoComments() {			
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);
	    ShowCommentsOfCommentWS showCommentsOfCommentWS = new ShowCommentsOfCommentWS();
		assertEquals("", showCommentsOfCommentWS.getMessage("0", "2"));
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
		commentCode2.addLevel(1);
		Comment comment2 = new Comment(commentCode2, "photoCode-1", "Harry", "Timestamp2", "Hello to you too!");
		CommentCode commentCode3 = new CommentCode();
		commentCode3.addLevel(1);
		commentCode3.addLevel(1);
		commentCode3.addLevel(1);
		Comment comment3 = new Comment(commentCode3, "photoCode-1", "James", "Timestamp3", "Hi to all!");
		Photo photo = new Photo("photoCode-1");
		photo.addComment(comment2);	// Insert in reverse mode to check sorting too 
		photo.addComment(comment1);
		photo.addComment(comment3);
		PhotosRepository photosRepository = new PhotosRepository();
		photosRepository.insertNewPhoto(photo);
		ShowCommentsOfCommentWS showCommentsOfCommentWS = new ShowCommentsOfCommentWS();
		assertEquals("(1.1) by Harry on Timestamp2 - 0 UpVotes, 0 DownVotes - : \r\n" + 
				"Hello to you too!\r\n" + 
				"	(1.1.1) by James on Timestamp3 - 0 UpVotes, 0 DownVotes - : \r\n" + 
				"Hi to all!\r\n", showCommentsOfCommentWS.getMessage("photoCode-1", "1.1"));
	}

}
