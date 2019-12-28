package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * The User class. 
 * @author 170011408
 *
 */
public class User {

  private String username;
  private String password; // sorry security is not implemented here (not encrypted password)
  private boolean isAdmin;
  private ArrayList<Comment> comments;
  private ArrayList<Notification> notifications;

  /**
   * Constructor initialises the class attributes.
   * @param username the user name.
   * @param password the password.
   * @param admin the admin status.
  */
  public User(String username, String password, boolean isAdmin) {
	    this.username = username;
	    this.password = password; 
	    this.isAdmin = isAdmin;
    comments = new ArrayList<Comment>();
    notifications = new ArrayList<Notification>();
  }

  public boolean isAdmin() {
	    return this.isAdmin;
	  }
  
  /**
   * Add comment.
   * @param comment the comment.
   */
  public void addComment(Comment comment) {
    comments.add(comment);
  }
  
  public void addNotification(Notification notification) {
	  notifications.add(notification);
	  }

  /**
   * Getter for password attribute.
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Getter for the username attribute.
   * @return the username.
   */
  public String getUsername() {
    return username;
  }
  
  /**
   * Get number of comments. 
   * @return the number of comments the user has made.
   */
  public int getNumberOfComments() {
    return comments.size();
  }
  
  public int getNumberOfNotifications() {
	    return notifications.size();
	  }

  /**
   * Return the user's comments in String format.
   * @return the user's comments in String format.
   */
  public String returnCommentsAsAString() {
    String availableComments = "";
    Iterator<Comment> commentsIterator = comments.iterator();
    while (commentsIterator.hasNext()) {
      Comment tempComment = (Comment) commentsIterator.next();
      availableComments += "Photo " + tempComment.getPhotoCode() 
          + " (" + tempComment.getCommentCodeAsString() + ") on " 
          + tempComment.getTimeStamp() +  " - " + tempComment.getUpVotes() 
          + " UpVotes, " + tempComment.getDownVotes() + " DownVotes - : \r\n";
      availableComments += tempComment.getCommentText() + "\r\n";
    }
    return availableComments;
  }
  
  public String returnNotificationsAsAString() {
	    String availableNotifications = "";
	    Iterator<Notification> notificationsIterator = notifications.iterator();
	    while (notificationsIterator.hasNext()) {
	    	Notification tempNotification = (Notification) notificationsIterator.next();
	    	availableNotifications += tempNotification.returnNotificationAsAString();
	    }
	    return availableNotifications;
	  }
  
  public void removeNotificationsOnPhoto(String photoCode) {
	  	Iterator<Notification> notificationsIterator = notifications.iterator();
	    while (notificationsIterator.hasNext()) {
	    	Notification tempNotification = (Notification) notificationsIterator.next();
	    	if (tempNotification.getOnPhoto().getCode().equals(photoCode)) {
	    		notificationsIterator.remove();	// https://stackoverflow.com/questions/223918/iterating-through-a-collection-avoiding-concurrentmodificationexception-when-re
	    	}
	    }
  }
}
