package br.edu.ifpb.dac.falacampus.exceptions;

public class CommentCannotUpdateException extends Exception {
	
	public CommentCannotUpdateException(){
        super(String.format("Comment cannot be updated!"));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
