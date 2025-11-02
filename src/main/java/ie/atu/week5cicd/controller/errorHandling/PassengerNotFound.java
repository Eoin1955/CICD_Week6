package ie.atu.week5cicd.controller.errorHandling;

public class PassengerNotFound extends RuntimeException {
    private String message;
    private String field;

    public PassengerNotFound(String field, String message) {
        this.field = field;
    }

    public PassengerNotFound(String message) {
        super(message);
    }
}
