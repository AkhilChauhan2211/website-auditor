package website_auditor.exception;

public class InvalidUrlException extends RuntimeException{
    public InvalidUrlException(String message) {
        super(message);
    }
}
