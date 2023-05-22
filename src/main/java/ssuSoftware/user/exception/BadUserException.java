package ssuSoftware.user.exception;


public class BadUserException extends NotFoundException {

    private static final String MESSAGE = "해당사용자가 존재하지 않습니다";

    public BadUserException() {
        super(MESSAGE);
    }
}
