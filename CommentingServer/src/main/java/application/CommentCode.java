package application;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The CommentCode class for the code of the comment.
 * @author 170011408
 *
 */
public class CommentCode implements Comparable {
  /* Example: 1 1.2 1.2.3 */
  private ArrayList<Integer> segmentedCode;

  public CommentCode() {
    segmentedCode = new ArrayList<Integer>();
  }
  
  /**
   * Add level (e.g. If it is 1, 1.1).
   * @param level the level to add.
  */
  public void addLevel(int level) {
    segmentedCode.add(level);
  }
  
  /**
   * Get the comment code as a String.
   * @return the comment code as a String.
  */
  public String getCodeAsString() {
    String codeAsString = "";
    Iterator<Integer> segmentsIterator = segmentedCode.iterator();
    if (segmentedCode.size() > 0) {
      codeAsString += segmentsIterator.next().toString();
      while (segmentsIterator.hasNext()) {
        codeAsString += "." + segmentsIterator.next().toString();
      }
    }
    return codeAsString;
  }
  
  /**
   * Get the depth level of the comment tree.
   * @return the depth level.
  */
  public Integer getSize() {
    return segmentedCode.size();
  }
  
  /**
   * Get value of index of a specific comment.
   * @param index the index.
   * @return index.
  */
  public Integer getValueOfIndex(int index) {
    if (index < getSize()) {
      return segmentedCode.get(index);
    } else {
      return 0;
    }
  }

  /**
   * Sort comment codes 1.1 is before than 1.2, 1.2 is before than 1.2.3, 2.9.3 is before 2.10.
  */
  @Override
  public int compareTo(Object o) {
    CommentCode commentCode = (CommentCode) o;
    int minSize = commentCode.getSize();
    if (getSize() < minSize) {
      minSize = getSize();
    }
    int i = 0;
    while (i < minSize && getValueOfIndex(i) == commentCode.getValueOfIndex(i)) {
      i++;
    }
    if (i == minSize) {
      return getSize().compareTo(commentCode.getSize());
    } else {
      return getValueOfIndex(i).compareTo(commentCode.getValueOfIndex(i));
	}
  }
}
