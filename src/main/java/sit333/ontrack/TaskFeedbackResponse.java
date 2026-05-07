package sit333.ontrack;

public class TaskFeedbackResponse {

    private String status;
    private String message;
    private boolean resubmissionRequired;

    public TaskFeedbackResponse(String status, String message, boolean resubmissionRequired) {
        this.status = status;
        this.message = message;
        this.resubmissionRequired = resubmissionRequired;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isResubmissionRequired() {
        return resubmissionRequired;
    }
}