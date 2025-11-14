package Workshop.Example1.Other;

public class Response {
    private final boolean successful;
    private final String message;

    public Response(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public static Response success() {
        return new Response(true, "Success");
    }

    public static Response failure(String message) {
        return new Response(false, message);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}