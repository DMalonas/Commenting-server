import org.junit.Test;
import static org.junit.Assert.assertEquals;

import application.PhotosRepository;
import myRESTWebServices.*;
import utilities.MyUtilitiesClass;

/**
 * 
 * @author Dimit
 *
 */
public class ShowPhotoCodesWSTest {
	/**
	 * 
	 */
	@Test
	public void testEmptyPhotoRepository() {
		new PhotosRepository();
		ShowPhotoCodesWS showPhotoCodesWS = new ShowPhotoCodesWS();
		assertEquals("", showPhotoCodesWS.getMessage());
	}
	/**
	 * 
	 */
	@Test
	public void testDefaultPhotoRepository() {	// Check default values
	    MyUtilitiesClass.createPhotosPopulation(new PhotosRepository());
		ShowPhotoCodesWS showPhotoCodesWS = new ShowPhotoCodesWS();
		assertEquals("0 by Dimitris\r\n1 by Dimitris\r\n2 by Dimitris\r\n3 by James\r\n4 by James\r\n5 by James\r\n6 by John\r\n7 by John\r\n8 by Rory\r\n9 by Rory\r\n", showPhotoCodesWS.getMessage());
	}
}
