package br.edu.ifpb.dac.falacampus.exceptions;

public class ExistingResponseException extends Exception {

	public ExistingResponseException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
