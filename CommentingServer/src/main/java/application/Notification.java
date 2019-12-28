package application;

/**
 * The class for notification.
 * @author 170011408
 *
 */
public class Notification {

  private User forUser;
  private Photo onPhoto;
  private CommentCode onCommentCode;
  private String byUsername;
  private String timeStamp;

  /**
   * The constructor initialises the class parameters.
   * @param forUser the user.
   * @param onPhoto the photo.
   * @param onCommentCode on which comment of the photo.
   * @param byUsername the user from whom the notification comes.
   * @param timeStamp the time stamp.
   */
  public Notification(User forUser, Photo onPhoto, 
      CommentCode onCommentCode, String byUsername, String timeStamp) {
    this.forUser = forUser;
    this.onPhoto = onPhoto;
    this.onCommentCode = onCommentCode;
    this.byUsername = byUsername;
    this.timeStamp = timeStamp;
  }

  /**
   * Constructor for notifications from comments on a photo.
   * @param forUser for which user.
   * @param onPhoto on which photo.
   * @param byUsername by who the notification comes from.
   * @param timeStamp the time stamp.
   */
  public Notification(User forUser, Photo onPhoto,
      String byUsername, String timeStamp) {
    this.forUser = forUser;
    this.onPhoto = onPhoto;
    this.onCommentCode = null;
    this.byUsername = byUsername;
    this.timeStamp = timeStamp;
  }

  /**
   * 
   * @return
   */
  public String returnNotificationAsAString() {
    if (onCommentCode == null) {
      return "User " + byUsername + " commented on your photo "
        + onPhoto.getCode() + " on " + timeStamp + "\r\n";
    } else {
      return "User " + byUsername + " commented on your comment "
          + onCommentCode.getCodeAsString() + " on photo " 
          + onPhoto.getCode() + " on " + timeStamp + "\r\n";
    }
  }

  /**
   * Get photo for which notification is.
   * @return the Photo.
   */
  public Photo getOnPhoto() {
    return onPhoto;
  }
}
