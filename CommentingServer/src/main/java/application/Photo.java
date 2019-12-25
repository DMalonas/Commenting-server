package application;

public class Photo implements Comparable{

	private String code;
	private String username;
	private CommentsRepository commentsRepository;
	

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
	
	
	public String getUsername() {
		return this.username;
	}
	
	
	public String getCode() {
		return this.code;
	}
	
	
	public void addComment(Comment comment) {
		commentsRepository.add(comment);
	}
	
	public boolean removeComment(String commentCode) {
		Comment comment = returnCommentWithCode(commentCode);
		if (comment == null) {
			return false;
		}
		comment.setCommentText("The comment has been removed by the administrator");
		return true;
	}


	private Comment returnCommentWithCode(String providedCommentCodeAsString) {
		// TODO Auto-generated method stub
		return commentsRepository.returnCommentsWithCode(providedCommentCodeAsString);
	}


	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getNumberOfComments() {
		return commentsRepository.commentsNumber();
	}
	
}
