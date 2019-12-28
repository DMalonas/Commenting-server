package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * The repository for the comments.
 * @author 170011408
 *
 */
public class CommentsRepository {

  private ArrayList<Comment> comments;

  /**
   * Constructor initialises the ArrayList of comments.
  */
  public CommentsRepository() {
    comments = new ArrayList<Comment>();
  }

  /**
   * Get the number of comments.
   * @return the number of comments.
  */
  public int commentsNumber() {
    return comments.size();
  }

  /**
   *  Prepare and return Comments of a photo.
   * @return comments of a photo as a String.
  */
  public String returnPhotoCommentsAsString() {
    Collections.sort(comments); 
    String availableComments = "";
    Iterator<Comment> commentsIterator = comments.iterator();
    while (commentsIterator.hasNext()) {
      Comment tempComment = (Comment) commentsIterator.next();
      int indent = tempComment.getNumberOfCodeLevels();
      int i;
      for (i = 0; i < indent - 1; i++) {
        availableComments += "\t";
      }
      availableComments += "(" + tempComment.getCommentCodeAsString() + ") "
          + "by " + tempComment.getUsername() + " on " + tempComment.getTimeStamp() 
          + " - " + tempComment.getUpVotes() + " UpVotes, " + tempComment.getDownVotes() 
          + " DownVotes - : \r\n";
      for (i = 0; i < indent - 1; i++) {
        availableComments += "\t";
      }
      availableComments += tempComment.getCommentText() + "\r\n";
    }
    return availableComments;
  }
  
  /**
   * Return  comments made on a comment in a String.
   * @param commentCode the comment code.
   * @return the comments made on a comment, in a String format.
  */
  public String returnCommentsOfCommentAsAString(String commentCode) {
    Collections.sort(comments);
    /* Say 1.4.2.1 */
    Comment parentComment = returnCommentWithCode(commentCode); 
    if (parentComment == null) {
      return "";
    }
    CommentCode parentCommentCode = hasCommentWithCode(commentCode);
    int levels = parentCommentCode.getSize();
    CommentCode nextCommentCodeOfSameLevel = new CommentCode();
    int i;
    for (i = 0; i < levels - 1; i++) {
      nextCommentCodeOfSameLevel.addLevel(parentCommentCode.getValueOfIndex(i));
    }
    nextCommentCodeOfSameLevel.addLevel(parentCommentCode
        .getValueOfIndex(levels - 1) + 1);
    /* We have to print all comments among 1.4.2.1 inclusive and 1.4.2.2 exclusive */
    String availableComments = "";
    Iterator<Comment> commentsIterator = comments.iterator();
    boolean finished = false;
    while (commentsIterator.hasNext() && !finished) {
      Comment tempComment = (Comment) commentsIterator.next();
      if (tempComment.compareTo(parentComment) >= 0 && tempComment
          .getCommentCode().compareTo(nextCommentCodeOfSameLevel) < 0) {
        int indent = tempComment.getNumberOfCodeLevels() - levels;
        for (i = 0; i < indent; i++) {
          availableComments += "\t";
        }
        availableComments += "(" + tempComment.getCommentCodeAsString() + ") by " 
            + tempComment.getUsername() + " on " + tempComment.getTimeStamp() 
            + " - " + tempComment.getUpVotes() + " UpVotes, " 
            + tempComment.getDownVotes() + " DownVotes - : \r\n";
        for (i = 0; i < indent - 1; i++) {
          availableComments += "\t";
        }
        availableComments += tempComment.getCommentText() + "\r\n";
      }
      if (tempComment.getCommentCode()
          .compareTo(nextCommentCodeOfSameLevel) > 0) {
        finished = true;
      }
    }
    return availableComments;
  }

  /**
   * Add comment.
   * @param comment the Comment.
   */
  public void add(Comment comment) {
    comments.add(comment);
  }
  
  /**
   * Identify the next comment code on the lower level of the provided comment code.
   * Namely, this method decide what code to give you.
   * @param providedCommentCodeAsString the comment code provided by the user.
   * @return the next depth level.
   */
  public CommentCode nextCommentCodeOnTheLowerLevelOf(String providedCommentCodeAsString) {
    int numberOfCommentsCurrently = commentsNumber();
    /* New comment on the photo level */
    if (providedCommentCodeAsString.equals("")) {
      /* 1st comment */
      if (numberOfCommentsCurrently == 0) {
        CommentCode firstCommentCode = new CommentCode();
        firstCommentCode.addLevel(1);
        return firstCommentCode;
        /* 1st level only (comment on the photo, not on another comment) */
      } else {
        int maxCommentCodeOnFirstLevel = 0;
        int i;
        CommentCode tempCommentCode;
        for (i = 0; i < numberOfCommentsCurrently; i++) {
          tempCommentCode = comments.get(i).getCommentCode();
          if (tempCommentCode.getValueOfIndex(0) > maxCommentCodeOnFirstLevel) {
            maxCommentCodeOnFirstLevel = tempCommentCode.getValueOfIndex(0);
          }
        }
        CommentCode newCommentCode = new CommentCode();
        newCommentCode.addLevel(maxCommentCodeOnFirstLevel + 1);
        return newCommentCode;
      }
    /* New comment on another comment, say 1.2.4 */
    } else {
      CommentCode parentCommentCode = hasCommentWithCode(providedCommentCodeAsString);
      CommentCode newCommentCode = new CommentCode();
      int parentCommentCodeSize = parentCommentCode.getSize();
      int i;
      /* Fill first levels of the new code with 1, 2, 4 */
      for (i = 0; i < parentCommentCodeSize; i++) {
        newCommentCode.addLevel(parentCommentCode.getValueOfIndex(i));
      }
      /* Now search for the last level */
      int maxCommentCodeOnNextLevel = 0;
      CommentCode tempCommentCode;
      /* Search the max code of the 4th level, say 1.2.4.3 */
      for (i = 0; i < numberOfCommentsCurrently; i++) {
        tempCommentCode = comments.get(i).getCommentCode();
        /* Do not examine comment codes with less levels */
        if (tempCommentCode.getSize() >= parentCommentCodeSize) {
          boolean firstLevelsMatch = true;
          int j;
          /* First check whether the first levels match, if not do not examine */
          for (j = 0; j < parentCommentCodeSize; j++) {
            if (tempCommentCode.getValueOfIndex(j) != parentCommentCode.getValueOfIndex(j)) {
              firstLevelsMatch = false;
            }
          }
          /* Doesn't throw exception, getValueOfIndex returns 0 at the case no such level */
          if (firstLevelsMatch && tempCommentCode.getValueOfIndex(parentCommentCodeSize) 
              > maxCommentCodeOnNextLevel) {
            maxCommentCodeOnNextLevel = tempCommentCode.getValueOfIndex(parentCommentCodeSize);
          }
        }
      }
      newCommentCode.addLevel(maxCommentCodeOnNextLevel + 1);
      return newCommentCode;
    }
  }
  
  /**
   * Check if there is comment with the comment code provided.
   * @param providedCommentCodeAsString the commetCode provided.
   * @return the comment code in CommentCode format if provided comment code exists, null otherwise.
  */
  public CommentCode hasCommentWithCode(String providedCommentCodeAsString) {
    int size = commentsNumber();
    int i = 0;
    for (i = 0; i < size; i++) {
      if (comments.get(i).hasCommentCode(providedCommentCodeAsString)) {
        return comments.get(i).getCommentCode();
      }
    }
    return null;
  }
  
  
  /**
   * Return the Comment with the comment code provided as parameter.
   * @param providedCommentCodeAsString the comment code provided as parameter.
   * @return the Comment with CommentCode providedCommentCodeAsString.
   */
  public Comment returnCommentWithCode(String providedCommentCodeAsString) {
    int size = commentsNumber();
    int i = 0;
    for (i = 0; i < size; i++) {
      if (comments.get(i).hasCommentCode(providedCommentCodeAsString)) {
        return comments.get(i);
      }
    }
    return null;
  }
}
