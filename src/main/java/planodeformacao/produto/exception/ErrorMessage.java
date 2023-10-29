package planodeformacao.produto.exception;

public class ErrorMessage {
    private final int statusCode;
    private final String message;
    private final String description;

    public ErrorMessage(final int statusCode, final String message, final String description) {
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}

