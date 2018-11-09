package pl.codeset.pocket;

public class PocketException extends RuntimeException {

    private Integer errorCode;

    PocketException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public PocketException(String message) {
        super(message);
    }

    public Integer getErrorCode() {
        return errorCode;
    }


}
