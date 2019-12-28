import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.PhotosRepository;
import application.UsersPopulation;
import myRESTWebServices.ShowCommentsOfUserWS;
import myRESTWebServices.ShowNotificationsForUserWS;
import utilities.MyUtilitiesClass;

public class ShowNotificationsForUserWSTest {

	@Test
	public void testNoSuchUserInEmptyPopulation() {
		ShowNotificationsForUserWS showNotificationsForUserWS = new ShowNotificationsForUserWS();
		assertEquals("Wrong input", showNotificationsForUserWS.getMessage("Dimitrios"));
	}
	
	@Test
	public void testNoSuchUserInNonEmptyPopulation() {
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);
	    ShowNotificationsForUserWS showNotificationsForUserWS = new ShowNotificationsForUserWS();
		assertEquals("Wrong input", showNotificationsForUserWS.getMessage("Dimitrios"));
		MyUtilitiesClass.removeInitialPopulations(photosRepository, usersPopulation);
	}
	
	@Test
	public void testNoNotificationsToShow() {
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);
	    ShowNotificationsForUserWS showNotificationsForUserWS = new ShowNotificationsForUserWS();
		assertEquals("", showNotificationsForUserWS.getMessage("Dimitris"));
		MyUtilitiesClass.removeInitialPopulations(photosRepository, usersPopulation);
	}
	
	@Test
	public void createAndShowNotificationCorrectly() {
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);
	    usersPopulation.insertNewNotification("1.2.2", "0", "Dimitris", "19-03-2018 12:50:11");
	    ShowNotificationsForUserWS showNotificationsForUserWS = new ShowNotificationsForUserWS();
		assertEquals("User Dimitris commented on your comment 1.2.2 on photo 0 on 19-03-2018 12:50:11\r\n", showNotificationsForUserWS.getMessage("James"));
		MyUtilitiesClass.removeInitialPopulations(photosRepository, usersPopulation);
	}
	
	@Test
	public void createShowAndRemoveNotificationCorrectly() {
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);
	    usersPopulation.insertNewNotification("1.2.2", "0", "Dimitris", "19-03-2018 12:50:11");
	    ShowNotificationsForUserWS showNotificationsForUserWS = new ShowNotificationsForUserWS();
		assertEquals("User Dimitris commented on your comment 1.2.2 on photo 0 on 19-03-2018 12:50:11\r\n", showNotificationsForUserWS.getMessage("James"));
		photosRepository.returnPhotoCommentsAsString("0", "James");
		assertEquals("", showNotificationsForUserWS.getMessage("James"));
		MyUtilitiesClass.removeInitialPopulations(photosRepository, usersPopulation);
	}
}
