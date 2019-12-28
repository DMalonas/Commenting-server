import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.CommentsRepository;
import application.PhotosRepository;
import application.UsersPopulation;
import myRESTWebServices.RemoveCommentFromPhotoWS;
import myRESTWebServices.ShowCommentsOfPhotoWS;
import utilities.MyUtilitiesClass;

public class RemoveCommentFromPhotoWsTest {
	
	@Test
	public void testInexistentComment() {
		MyUtilitiesClass.createPhotosPopulation(new PhotosRepository());
		new CommentsRepository();
		RemoveCommentFromPhotoWS removeCommentFromPhotoWS = new RemoveCommentFromPhotoWS();
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1", "0"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1.1", "0"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("2", "0"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("3", "0"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("3", "1"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1", "2"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1", "3"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1", "4"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1.1", "5"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("2", "6"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("2.1", "7"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1.2", "8"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("4", "0"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("5", "1"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("6", "2"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1.7", "3"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1.9", "4"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1.5", "5"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("3", "6"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("2.2", "7"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("7.2", "8"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("5", "3"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("5", "2"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("6", "6"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1.7", "9"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1.9", "7"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("1.5", "3"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("3", "2"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("2.2", "9"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("3.2", "0"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("2.7", "9"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("3.9", "7"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("4.5", "3"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("6", "2"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("2.1.2", "9"));
		assertEquals("No such comment exists", removeCommentFromPhotoWS.getMessage("3.1.2", "0"));

	}
	
	
	@Test
	public void noSuchCommentTest() {
		new CommentsRepository();
		RemoveCommentFromPhotoWS removeCommentFromPhotoWS = new RemoveCommentFromPhotoWS();
		assertEquals("No such photo", removeCommentFromPhotoWS.getMessage("1", "110"));
		assertEquals("No such photo", removeCommentFromPhotoWS.getMessage("1", "11"));
	}
	
	
	@Test
	public void commentRemovedSuccessfullyTest() {
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);
		RemoveCommentFromPhotoWS removeCommentFromPhotoWS = new RemoveCommentFromPhotoWS();
		assertEquals("OK", removeCommentFromPhotoWS.getMessage("1", "0"));
		MyUtilitiesClass.removeInitialPopulations(photosRepository, usersPopulation);
	}
}

