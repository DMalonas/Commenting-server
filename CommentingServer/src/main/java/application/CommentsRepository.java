package application;

import java.util.ArrayList;

public class CommentsRepository {
	
	public ArrayList<Comment> comments;
	
	public CommentsRepository() {
		comments = new ArrayList<Comment>();
	}

	public void add(Comment comment) {
		// TODO Auto-generated method stub
		comments.add(comment);
	}

	public int commentsNumber() {
		// TODO Auto-generated method stub
		return comments.size();
	}

	public Comment returnCommentsWithCode(String providedCommentCodeAsString) {
		// TODO Auto-generated method stub
		return null;
	}
}
