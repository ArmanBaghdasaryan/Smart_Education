package am.itspace.smart_education_rest.exception;

public class AuthenticationException extends Exception {

    public AuthenticationException(String error) {
        super(error);
    }
}
