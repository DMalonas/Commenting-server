import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import application.PhotosRepository;
import application.UsersPopulation;
import myRESTWebServices.AuthenticateUserWS;
import myRESTWebServices.ReceiveCommentWS;
import myRESTWebServices.ReceiveVoteWS;
import utilities.MyUtilitiesClass;


public class ReceiveAuthenticationCredentialWSTest {
	
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
	public void testNoPasswordRequest() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("Dimitris");
	    jArray.add(stringAsJSON);
	   // stringAsJSON = new JsonPrimitive("1222");
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject

	    InputStream is = new ByteArrayInputStream(str.getBytes());
	    AuthenticateUserWS authenticateUserWS = new AuthenticateUserWS();
		Response receivedResponse = authenticateUserWS.authenticateUser(is);
		String receivedResponseEntityAsString = receivedResponse.getEntity().toString();
    	assertEquals("Wrong Parameters", receivedResponseEntityAsString);
	}
	
	@Test
	public void testNoUsernameRequest() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("1234");
	  //  jArray.add(stringAsJSON);
	   // stringAsJSON = new JsonPrimitive("1222");
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject

	    InputStream is = new ByteArrayInputStream(str.getBytes());
	    AuthenticateUserWS authenticateUserWS = new AuthenticateUserWS();
		Response receivedResponse = authenticateUserWS.authenticateUser(is);
		String receivedResponseEntityAsString = receivedResponse.getEntity().toString();
    	assertEquals("Wrong Parameters", receivedResponseEntityAsString);
	}
	
	
	@Test
	public void testNoUsernameNoPasswordRequest() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("");
	  //  jArray.add(stringAsJSON);
	   // stringAsJSON = new JsonPrimitive("1222");
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject

	    InputStream is = new ByteArrayInputStream(str.getBytes());
	    AuthenticateUserWS authenticateUserWS = new AuthenticateUserWS();
		Response receivedResponse = authenticateUserWS.authenticateUser(is);
		String receivedResponseEntityAsString = receivedResponse.getEntity().toString();
    	assertEquals("Wrong Parameters", receivedResponseEntityAsString);
	}
	
	@Test
	public void testWrongParameters() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("0");
	    jArray.add(stringAsJSON);
	    // Error! WS expects 2 strings, not 1
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		AuthenticateUserWS authenticateUserWS = new AuthenticateUserWS();
		String receivedReply = authenticateUserWS.authenticateUser(is).getEntity().toString();
		assertEquals("Wrong Parameters", receivedReply);
	}
	
	
	@Test
	public void testCorrectUsernameWrongPassword() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("Dimitris"); //correct username
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("5678");	// Wrong password
	    jArray.add(stringAsJSON);
	    
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		AuthenticateUserWS authenticateUserWS = new AuthenticateUserWS();
		UsersPopulation usersPopulation = new UsersPopulation();
		MyUtilitiesClass.createUsersPopulation(usersPopulation);	// Create default users
		String receivedReply = authenticateUserWS.authenticateUser(is).getEntity().toString();
		assertEquals("Invalid credentials", receivedReply);

	}
	
	
	@Test
	public void testWrongUsernameCorrectPassword() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("Dimitrios"); // Wrong username
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("1234");	// Correct password
	    jArray.add(stringAsJSON);
	    
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		AuthenticateUserWS authenticateUserWS = new AuthenticateUserWS();
		UsersPopulation usersPopulation = new UsersPopulation();
		MyUtilitiesClass.createUsersPopulation(usersPopulation);	// Create default users
		String receivedReply = authenticateUserWS.authenticateUser(is).getEntity().toString();
		assertEquals("Invalid credentials", receivedReply);

	}
	
	@Test
	public void testWrongUsernameWrongPassword() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("Dimitrios"); // Wrong username
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("5678");	// Correct password
	    jArray.add(stringAsJSON);
	    
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		AuthenticateUserWS authenticateUserWS = new AuthenticateUserWS();
		UsersPopulation usersPopulation = new UsersPopulation();
		MyUtilitiesClass.createUsersPopulation(usersPopulation);	// Create default users
		String receivedReply = authenticateUserWS.authenticateUser(is).getEntity().toString();
		assertEquals("Invalid credentials", receivedReply);
	}
	
	
	@Test
	public void testCorrectUsernameCorrectPassword() {
		JsonArray jArray = new JsonArray();	// https://stackoverflow.com/questions/42411136/put-string-value-in-jsonarray
	    JsonPrimitive stringAsJSON = new JsonPrimitive("Dimitris"); // Wrong username
	    jArray.add(stringAsJSON);
	    stringAsJSON = new JsonPrimitive("1234");	// Correct password
	    jArray.add(stringAsJSON);
	    
	    String str = jArray.toString();	// https://stackoverflow.com/questions/23816356/how-to-create-inputstream-object-from-jsonobject
	    InputStream is = new ByteArrayInputStream(str.getBytes());
		AuthenticateUserWS authenticateUserWS = new AuthenticateUserWS();
		UsersPopulation usersPopulation = new UsersPopulation();
		MyUtilitiesClass.createUsersPopulation(usersPopulation);	// Create default users
		String receivedReply = authenticateUserWS.authenticateUser(is).getEntity().toString();
		assertEquals("OK", receivedReply);
	}
}
