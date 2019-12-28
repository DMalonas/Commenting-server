import static org.junit.Assert.assertEquals;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import application.PhotosRepository;
import application.UsersPopulation;
import myRESTWebServices.ReceiveVoteWS;
import myRESTWebServices.ShowPhotoCodesWS;
import utilities.MyUtilitiesClass;
/**
 * 
 * @author Dimit
 *
 */
public class ReceiveVoteWSTest {
	/**
	 * 
	 */
	@Test
	public void testBadRequest() {
	    String str = "1234";	// Error, not JSON Primitive Format!
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		ReceiveVoteWS receiveVoteWS = new ReceiveVoteWS();
		Response receivedResponse = receiveVoteWS.receiveVote(is);
		String receivedResponseEntityAsString = receivedResponse.getEntity().toString();
		assertEquals("NOK", receivedResponseEntityAsString);
	}
	/**
	 * 
	 */
	@Test
	public void testWrongParameters() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("0");
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("1");
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("2");
	    jArray.add(stringAsJSON);
	    // Error! WS expects 4 strings, not 3
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		ReceiveVoteWS receiveVoteWS = new ReceiveVoteWS();
		String receivedReply = receiveVoteWS.receiveVote(is).getEntity().toString();
		assertEquals("Wrong Parameters", receivedReply);
	}
	/**
	 * 
	 */
	@Test
	public void testNoSuchComment() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("0");
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("1");
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("2");
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("3");
	    jArray.add(stringAsJSON);
	    
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		ReceiveVoteWS receiveVoteWS = new ReceiveVoteWS();
		String receivedReply = receiveVoteWS.receiveVote(is).getEntity().toString();	// Error, no photo repository, hence no such comment
		assertEquals("No such comment", receivedReply);
	}
	/**
	 * 
	 */
	@Test
	public void testCorrectInputAndUpVote() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("username");
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("1.2");	// Existing comment
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("0");	// Correct photo
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("true");	// UpVote
	    jArray.add(stringAsJSON);
	    
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		ReceiveVoteWS receiveVoteWS = new ReceiveVoteWS();
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);	// Create default photos and comments populations before calling the service
		String receivedReply = receiveVoteWS.receiveVote(is).getEntity().toString();
		assertEquals("OK", receivedReply);
		int upVotes = photosRepository.returnPhotoByCode("0").returnCommentWithCode("1.2").getUpVotes();
		assertEquals(1, upVotes);
	}
	/**
	 * 
	 */
	@Test
	public void testCorrectInputAndDownVote() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("username");
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("1.2.1");	// Existing comment
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("0");	// Correct photo
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("false");	// DownVote
	    jArray.add(stringAsJSON);
	    
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		ReceiveVoteWS receiveVoteWS = new ReceiveVoteWS();
		PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);	// Create default photos and comments populations before calling the service
		String receivedReply = receiveVoteWS.receiveVote(is).getEntity().toString();
		assertEquals("OK", receivedReply);
		int downVotes = photosRepository.returnPhotoByCode("0").returnCommentWithCode("1.2.1").getDownVotes();
		assertEquals(1, downVotes);
	}

}
