package pl.codeset.pocket;

public class PocketException extends RuntimeException {

    private Integer errorCode;
    private boolean authenticationProblem;
    private boolean invalidRequest;
    private boolean accessDenied;
    private boolean maintenanceShouldDown;

    PocketException(Integer errorCode, String errorMessage, int httpStatusCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.invalidRequest = httpStatusCode == 400;
        this.authenticationProblem = httpStatusCode == 401;
        this.accessDenied = httpStatusCode == 403;
        this.maintenanceShouldDown = httpStatusCode == 503;
    }

    public PocketException(String message) {
        super(message);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public boolean isAuthenticationProblem() {
        return authenticationProblem;
    }

    public boolean isAccessDenied() {
        return accessDenied;
    }

    public boolean isInvalidRequest() {
        return invalidRequest;
    }

    public boolean isMaintenanceShouldDown() {
        return maintenanceShouldDown;
    }
}
