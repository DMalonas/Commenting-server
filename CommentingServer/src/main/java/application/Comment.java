package application;

import java.util.Iterator;

/**
 * 
 * @author 170011408
 *
 */
public class Comment implements Comparable {

  private CommentCode commentCode;  //THIS IS ANOTHER CLASS
  private String photoCode;
  private String username;
  private String timeStamp;
  private String commentText;
  private int upVotes;
  private int downVotes;

  /**
   * The Constructor initialises the class attributes.
   * @param commentCode the comment code (e.g. 1.2.2)
   * @param photoCode the photo code (e.g. 1)
   * @param userName the user name
   * @param timeStamp the time stamp
   * @param commentText the comment text
  */
  public Comment(CommentCode commentCode, String photoCode,
      String userName, String timeStamp, String commentText) {
    this.commentCode = commentCode;
    this.photoCode = photoCode;
    this.username = userName;
    this.setTimeStamp(timeStamp);
    this.commentText = commentText;
    this.upVotes = 0;
    this.downVotes = 0;
  }
  
  /**
   * Getter for the CommentCode attribute.
   * @return the CommentCode object
   */
  public CommentCode getCommentCode() {
    return commentCode;
  }
  
  /**
   * Getter for the commentText attribute.
   * @return the comment text.
   */
  public String getCommentText() {
    return commentText;
  }

  /**
   * Setter for the commentText attribute.
   * @param commentText the comment text the user wants.
   */
  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }
  
  /**
   * Sort the comment based on their codes.
   */
  @Override
  public int compareTo(Object o) {
    return commentCode.compareTo(((Comment) o).getCommentCode());
  }

  /**
   * Getter for the username attribute.
   * @return
  */
  public String getUsername() {
    return username;
  }

  /**
   * Get commentCode as String.
   * @return the commentCode as String
   */
  public String getCommentCodeAsString() {
    return commentCode.getCodeAsString();
  }
  
  /**
   * Check if this comment code exists.
   * @param commentCodeAsString the comment code as String
   * @return true if it exists false otherwise.
  */
  public boolean hasCommentCode(String commentCodeAsString) {
    if (commentCodeAsString.equals(commentCode.getCodeAsString())) {
      return true;
    }
    return false;
  }

  /**
   * Get the number of code levels.
   * @return the depth level.
  */
  public int getNumberOfCodeLevels() {
    return commentCode.getSize();
  }
  
  /**
   * Up-vote.
  */
  public void upVote() {
    upVotes++;
  }

  /**
   * Down-vote.
  */
  public void downVote() {
    downVotes++;
  }

  /**
   * Get number of up-votes for a comment.
   * @return number of up-votes.
  */
  public int getUpVotes() {
    return upVotes;
  }
  
  /**
   * Get number of down-votes for a comment.
   * @return
  */
  public int getDownVotes() {
    return downVotes;
  }

  /**
   * Get time stamp of a comment.
   * @return the time stamp
  */
  public String getTimeStamp() {
    return timeStamp;
  }
  
  /**
   * Setter for timeStamp attribute.
   * @param timeStamp the time stamp.
  */
  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }
  
  /**
   * Getter for the photoCode attribute.
   * @return the photo code
  */
  public String getPhotoCode() {
    return photoCode;
  }
  
}
