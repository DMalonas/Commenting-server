package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

/**
 * The repository where all the photos are kept.
 * @author 170011408
 *
 */
public class PhotosRepository {
  
  private static ArrayList<Photo> photos;
  
  /**
   * The constructor initialises the class attribute,
   *     which is the ArrayList we keep the Photo 
   *     objects. 
   */
  public PhotosRepository() {
    photos = new ArrayList<Photo>();
  }

  /**
   * Insert new photo.
   * @param photo the Photo object.
   */
  public void insertNewPhoto(Photo photo) {
    photos.add(photo);
  }
  
  /**
   * Return the existed photo codes in String format.
   *     To sort the pictures they need to be comparable, 
   *     so the class needs to implement the Comparable
   *     interface. 
   * @return the photo codes in String format.
   */
  public static String returnAvailablePhotoCodesAsString() {
    Collections.sort(photos);
    String availablePhotoCodes = "";
    Iterator<Photo> photosIterator = photos.iterator();
    while (photosIterator.hasNext()) {
      /* Create a String with the photo codes */
      Photo tempPhoto = (Photo) photosIterator.next();
      availablePhotoCodes += tempPhoto.getCode() + " by " + tempPhoto.getUsername() + "\r\n";
    }
    return availablePhotoCodes;
  }
  
  /**
   * Provide a photo code in String format to check if it exists. 
   * @param photoCodeToSearch the photo code in String format.
   * @return true if the PhotoCode exists, false otherwise.
   */
  public static boolean photoExists(String photoCodeToSearch) {
    Iterator<Photo> photosIterator = photos.iterator();
    while (photosIterator.hasNext()) {
      if (photoCodeToSearch.equals(((Photo) photosIterator
          .next()).getCode())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the number of comments on the photo. 
   * @param photoCode the photo code.
   * @return the number of comments on the photo.
   */
  public static int commentsNumberOfPhoto(String photoCode) {
    Photo photo = returnPhotoByCode(photoCode);
    if (photo != null) {
      return photo.getNumberOfComments();
    }
    return 0;
  }
  
  /**
   * Return a photo's comments in String format. 
   * @param photoCode the photo code.
   * @return The photo's comments as a String format if the picture is found, null otherwise.
   */
  public static String returnPhotoCommentsAsString(String photoCode, String username) {
    Photo photo = returnPhotoByCode(photoCode);
    if (photo == null) {
      return "";
    }
    UsersPopulation.removeNotificationsOnPhoto(photoCode, username);
    return photo.returnCommentsAsAString();
  }
  
  /**
   * Return comments posted under a specific comment in String format.
   * @param photoCode the photo code.
   * @param commentCode  the comment code.
   * @return comments posted under a specific comment in String format.
   */
  public static String returnCommentsOfCommentAsString(String photoCode, String commentCode) {
    Photo photo = returnPhotoByCode(photoCode);
    if (photo == null) {
      return "";
    }
    return photo.returnCommentsOfCommentAsAString(commentCode);
  }
  
  /**
   * Return the photo which code was provided if it exists. 
   * @param photoCode the photo code of the photo we want.
   * @return the photo which code was provided if it exists, null if it does not.
   */
  public static Photo returnPhotoByCode(String photoCode) {
    int i = 0;
    int size = photos.size();
    while (i < size) {
      if (photoCode.equals(photos.get(i).getCode())) {
        return photos.get(i);
      }
      i++;
    }
    return null;
  }
  
  /**
   * Insert a new Comment object.
   * @param receivedCommentParameters the comment parameters (5 in total).
   * @return -1 if unsuccessful, 0 if successful
   */
  public static int insertNewComment(String[] receivedCommentParameters) {
    String username = receivedCommentParameters[0];
    String onCommentAsString = receivedCommentParameters[1];
    String forPhotoAsString = receivedCommentParameters[2];
    String commentText = receivedCommentParameters[3];
    Photo forPhoto = returnPhotoByCode(forPhotoAsString);
    if (!onCommentAsString.equals("") && forPhoto.hasCommentWithCode(onCommentAsString) == null) {
      return -1; // No such comment to comment on
    }
    CommentCode newCommentCode = forPhoto.nextCommentCodeOnTheLowerLevelOf(onCommentAsString);
    // https://stackoverflow.com/questions/26360974/convert-timestamp-to-string
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String timeStamp  = dateFormat.format(new Date());
    Comment commentToAdd = new Comment(newCommentCode, 
        forPhotoAsString, username, timeStamp, commentText);
    forPhoto.addComment(commentToAdd);
    UsersPopulation.insertNewComment(commentToAdd);	// Add comment
    UsersPopulation.insertNewNotification(onCommentAsString, forPhotoAsString, username, timeStamp);	// Add notification
    return 0;
  }
  
  /**
   * Insert a new vote. 
   * @param receivedVoteParameters the Vote parameters.
   * @return 0 upon success, -1 otherwise.
   */
  public static int insertNewVote(String[] receivedVoteParameters) {
	// String userName = receivedVoteParameters[0]; // Username currently not in the scope of the vote functionality
    String onCommentAsString = receivedVoteParameters[1];
    String forPhotoAsString = receivedVoteParameters[2];
    String vote = receivedVoteParameters[3];
    Photo forPhoto = returnPhotoByCode(forPhotoAsString);
    if (!onCommentAsString.equals("") && forPhoto.hasCommentWithCode(onCommentAsString) == null) {
      return -1; // No such comment to comment on
    }
    Comment onComment = forPhoto.returnCommentWithCode(onCommentAsString);
    if (vote.equals("true")) {
      onComment.upVote();
    } else if (vote.equals("false")) {
      onComment.downVote();
    } else {
      return -1;
    }
    return 0;
  }
  
  public static int removeComment(String insertedCommentCode, String photoCode) {
	  Photo photo = returnPhotoByCode(photoCode);
	  if (photo.removeComment(insertedCommentCode)) {
		  return 0;
	  }
	  return -1;
  }
  
  public void removeAll() {
		photos.clear();
  }
}
