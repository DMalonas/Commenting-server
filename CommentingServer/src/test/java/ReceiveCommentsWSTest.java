import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import application.CommentCode;
import application.PhotosRepository;
import application.UsersPopulation;
import myRESTWebServices.ReceiveCommentWS;
import myRESTWebServices.ReceiveVoteWS;
import utilities.MyUtilitiesClass;

public class ReceiveCommentsWSTest {
	@Test
	public void testBadRequest() {
	    String str = "1234";	// Error, not JSON Primitive Format!
	    InputStream is = new ByteArrayInputStream(str.getBytes());
	    ReceiveCommentWS receiveCommentsWS = new ReceiveCommentWS();
		Response receivedResponse = receiveCommentsWS.receiveComment(is);
		String receivedResponseEntityAsString = receivedResponse.getEntity().toString();
		assertEquals("NOK", receivedResponseEntityAsString);
	}
	
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
	    ReceiveCommentWS receiveCommentsWS = new ReceiveCommentWS();
		String receivedReply = receiveCommentsWS.receiveComment(is).getEntity().toString();
		assertEquals("Wrong Parameters", receivedReply);
	}
	
	
	@Test

	public void testCorrectParameters() {
		
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
		
		JsonPrimitive stringAsJSON = new JsonPrimitive("Dimitris");
	    jArray.add(stringAsJSON);
	    CommentCode commentCode = new CommentCode();
		commentCode.addLevel(1);
	    stringAsJSON = new JsonPrimitive(commentCode.getCodeAsString());
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("0");
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("This photo is very nice");
	    jArray.add(stringAsJSON);	    

	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
	    ReceiveCommentWS receiveCommentsWS = new ReceiveCommentWS();	
	    PhotosRepository photosRepository = new PhotosRepository();
	    UsersPopulation usersPopulation = new UsersPopulation();
	    MyUtilitiesClass.createInitialPopulations(photosRepository, usersPopulation);	// Create default photos and comments populations before calling the service
		
		String receivedReply = receiveCommentsWS.receiveComment(is).getEntity().toString();
		//System.out.println(receivedReply);
		assertEquals("OK", receivedReply);
	}
}
