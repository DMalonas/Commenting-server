package application;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The repository class for holding all the users.
 * @author 170011408
 *
 */
public class UsersPopulation {
  private static ArrayList<User> users;

  /**
   * Constructor initialises the ArrayList
   * of User objects where we hold the 
   * users.
   */
  public UsersPopulation() {
    users = new ArrayList<User>();
  }


  /**
   * Return the User object if 
   * the user exists.
   * @param username the user name of User.
   * @return the User object
  */
  public static User returnUserIfExists(String username) {
    Iterator<User> usersIterator = users.iterator();
    User tempUser;
    while (usersIterator.hasNext()) {
      tempUser = usersIterator.next();
      if (tempUser.getUsername().equals(username)) {
        return tempUser;
      }
    }
    return null;
  }
  
  /**
   * Check if the User objects with given username exists.
   * @param username the user name
   * @return true if it exists false otherwise.
   */
  public static boolean userExists(String username) {
    if (returnUserIfExists(username) != null) {
      return true;
    }
    return false;
  }

  /**
   * Check the number of comments of a specific user.
   * @param username the user name of the User object 
   *     we want to see the comments of.
   * @return the number of comments.
   */
  public static int commentsNumberOfUser(String username) {
    User user = returnUserIfExists(username);
    if (user != null) {
      return user.getNumberOfComments();
    }
    return 0;
  }
  
  public static int notificationsNumberOfUser(String username) {
    User user = returnUserIfExists(username);
    if (user != null) {
      return user.getNumberOfNotifications();
    }
    return 0;
  }
  
  /**
   * Not an encrypted authentication, but an authentication.
   * @param username The user name
   * @param password The password
   * @return the User object if there is one with the user name and password provided.
   */
  public static User checkCredentials(String username, String password) {
    Iterator<User> usersIterator = users.iterator();
    User tempUser;
    while (usersIterator.hasNext()) {
      tempUser = usersIterator.next();
      if (tempUser.getUsername().equals(username) && tempUser.getPassword().equals(password)) {
        return tempUser;
      }
    }
    return null;
  }

  
  /**
   * Check if the user is an admin.
   * @param username
   * @return
   */
  public static boolean checkIfAdmin(String username) {
    User user = returnUserIfExists(username);
    if (user != null && user.isAdmin()) {
    	return true;
    }
    return false;
    
  }
 

  /**
   * Check if the user exists.
   * @param receivedCredentials the user name and password.
   * @return true if User object exists false otherwise.
   */
  public static boolean authenticate(String[] receivedCredentials) {
    if (checkCredentials(receivedCredentials[0], receivedCredentials[1]) != null) {
      return true;
    }
    return false;
  }
  
  /**
   * Add user.
   * @param user the User object to add, containing user name and password.
   */
  public void addUser(User user) {
    users.add(user);
  }

  /**
   * Insert new comment after evaluating their user name.
   * @param commentToAdd the comment to add.
   * @return 0 if the user name given does no belong 
   *     to a User object stored in the server's pseudo-database
   *     -1 otherwise.
   */
  public static int insertNewComment(Comment commentToAdd) {
    User user = returnUserIfExists(commentToAdd.getUsername());
    if (user != null) {
      user.addComment(commentToAdd);
      return 0;
    }
    return -1;
  }
  
  public static int insertNewNotification(String onCommentAsString, String forPhotoAsString, String byUsername, String timeStamp) {
	  Notification newNotification;
	  Photo onPhoto = PhotosRepository.returnPhotoByCode(forPhotoAsString);
	  if (onCommentAsString.equals("")) {	// Comment on photo
		  String forUserName = onPhoto.getUsername();
		  User forUser = returnUserIfExists(forUserName);
		  newNotification = new Notification(forUser, onPhoto, byUsername, timeStamp);
		  forUser.addNotification(newNotification);
	  } else {	// Comment on comment
		  Comment onComment = onPhoto.returnCommentWithCode(onCommentAsString);
		  String forUserName = onComment.getUsername();
		  User forUser = returnUserIfExists(forUserName);
		  newNotification = new Notification(forUser, onPhoto, onComment.getCommentCode(), byUsername, timeStamp);
		  forUser.addNotification(newNotification);
	  }
	  
	  return 0;
  }
  
  public static void removeNotificationsOnPhoto(String photoCode, String username) {
	  User user = returnUserIfExists(username);
	  if (user != null) {
		  user.removeNotificationsOnPhoto(photoCode);
	  }
  }

  /**
   * Return user's comment as a String.
   * @param username the user name of the User object requesting the comment.
   * @return "" if the user name provided by the client does not belongs to a User
   *     and the comment if it does.
   */
  public static String returnUserCommentsAsString(String username) {
    User user = returnUserIfExists(username);
    if (user == null) {
      return "";
    }
    return user.returnCommentsAsAString();
  }
  
  public static String returnUserNotificationsAsString(String username) {
    User user = returnUserIfExists(username);
    if (user == null) {
      return "";
    }
    return user.returnNotificationsAsAString();
  }
  
  public void removeAll() {
	  users.clear();
  }
}
