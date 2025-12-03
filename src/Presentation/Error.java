package Presentation;

public enum Error {
    ERROR_WRONG_OPTION("Error: Wrong option selected.\n"),
    ERROR_WRONG_LOGIN_ID("Error: Invalid login ID.\n"),
    ERROR_WRONG_PHONE_NUMBER_FORMAT("Error: Invalid phone number format.\n"),
    ERROR_WRONG_CONFIRMATION("Error: Wrong confirmation input.\n");

    private final String message;

    Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
