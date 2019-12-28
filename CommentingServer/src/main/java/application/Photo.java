package application;

/**
 * The Photo class. Each code represents a photo and each
 * photo carries a (comment) repository. The comparable 
 * interface is utilise to implement the Comparable overriden
 * method.
 * @author 170011408
 *
 */
public class Photo implements Comparable {

  private String code;
  private String username;
  private CommentsRepository commentsRepository;
  
  /**
   * The Constructor initialises the CommentsRepository.
   * @param code the photo code.
   */
  public Photo(String code) {
	    this.code = code;
	    this.username = "";
	    commentsRepository = new CommentsRepository();
	  }
  
  public Photo(String code, String username) {
    this.code = code;
    this.username = username;
    commentsRepository = new CommentsRepository();
  }

  /**
   * Getter for the code attribute.
   * @return String, the code.
   */
  public String getCode() {
    return code;
  }

  @Override
  public int compareTo(Object o) {
    return code.compareTo(((Photo) o).getCode()); // For easy sorting
  }

  /**
   * Getter for getting the number of comments.
   * @return The number of comments (int).
   */
  public int getNumberOfComments() {
    return commentsRepository.commentsNumber();
  }

  /**
   * Collects all the comments into a String.
   * @return the comments of the photo as String.
   */
  public String returnCommentsAsAString() {
    return commentsRepository.returnPhotoCommentsAsString();
  }
  
  public String returnCommentsOfCommentAsAString(String commentCode) {
    return commentsRepository.returnCommentsOfCommentAsAString(commentCode);
  }

  /**
   * Add a comment.
   * @param comment The Comment entered by the client.
   */
  public void addComment(Comment comment) {
    commentsRepository.add(comment);
  }

  /**
   * Identifies the code to be allocated in the current client's comment.
   * @param providedCommentCode String the comment code of the comment
   *     the client requested to comment upon.
   * @return The comment code to be allocated in the new comment.
   */
  public CommentCode nextCommentCodeOnTheLowerLevelOf(String providedCommentCode) {
    return commentsRepository.nextCommentCodeOnTheLowerLevelOf(providedCommentCode);
  }

  /**
   * Return the comment code in CommentCode format if it exists.
   * @param providedCommentCodeAsString a comment code in the form of a String.
   * @return the comment code as CommentCode, if it exists.
   */
  public CommentCode hasCommentWithCode(String providedCommentCodeAsString) {
    return commentsRepository.hasCommentWithCode(providedCommentCodeAsString);
  }
  
  /**
   * Return the comment in Comment format if it exists. 
   * @param providedCommentCodeAsString the comment code in String format.
   * @return the comment in Comment format if it exists.
   */
  public Comment returnCommentWithCode(String providedCommentCodeAsString) {
    return commentsRepository.returnCommentWithCode(providedCommentCodeAsString);
  }

  public String getUsername() {
	return username;
  }

  public boolean removeComment(String commentCode) {
	Comment comment = returnCommentWithCode(commentCode);
	if (comment == null) {
		return false;
	}
	comment.setCommentText("The comment has been removed by administrator");
	return true;
  }



}