import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.Comment;
import application.CommentCode;
import application.PhotosRepository;
import application.UsersPopulation;
import myRESTWebServices.ShowCommentsOfUserWS;
import myRESTWebServices.ShowPhotoCodesWS;
import utilities.MyUtilitiesClass;

public class ShowCommentsOfUserWSTest {

	@Test
	public void testNoSuchUserInEmptyPopulation() {
		ShowCommentsOfUserWS showCommentsOfUserWS = new ShowCommentsOfUserWS();
		assertEquals("Wrong input", showCommentsOfUserWS.getMessage("Dimitrios"));
	}
	
	@Test
	public void testNoSuchUserInNonEmptyPopulation() {
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);
		ShowCommentsOfUserWS showCommentsOfUserWS = new ShowCommentsOfUserWS();
		assertEquals("Wrong input", showCommentsOfUserWS.getMessage("Dimitrios"));
		MyUtilitiesClass.removeInitialPopulations(photosRepository, usersPopulation);
	}
	
	@Test
	public void testNoCommentsToShow() {
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createUsersPopulation(usersPopulation);
	    MyUtilitiesClass.createPhotosPopulation(photosRepository);
		ShowCommentsOfUserWS showCommentsOfUserWS = new ShowCommentsOfUserWS();
		assertEquals("No comments", showCommentsOfUserWS.getMessage("Dimitris"));
		MyUtilitiesClass.removeInitialPopulations(photosRepository, usersPopulation);
	}
	
	@Test
	public void testCorrectInput() {
		PhotosRepository photosRepository = new PhotosRepository();
		UsersPopulation usersPopulation = new UsersPopulation();
		MyUtilitiesClass.createUsersPopulation(usersPopulation);
	    MyUtilitiesClass.createPhotosPopulation(photosRepository);
		CommentCode commentCode = new CommentCode();
	    commentCode.addLevel(1);
	    Comment comment = new Comment(commentCode, "0", "Dimitris",
	        "18-03-2018 11:28:52", "This photo is very nice");
	    photosRepository.returnPhotoByCode("0").addComment(comment);
	    usersPopulation.returnUserIfExists("Dimitris").addComment(comment);
		ShowCommentsOfUserWS showCommentsOfUserWS = new ShowCommentsOfUserWS();
		assertEquals("Photo 0 (1) on 18-03-2018 11:28:52 - 0 UpVotes, 0 DownVotes - : \r\n" + 
				"This photo is very nice\r\n", showCommentsOfUserWS.getMessage("Dimitris"));
		MyUtilitiesClass.removeInitialPopulations(photosRepository, usersPopulation);
	}
}
