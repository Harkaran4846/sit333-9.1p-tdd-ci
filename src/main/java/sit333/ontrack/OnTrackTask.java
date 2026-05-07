package sit333.ontrack;

public class OnTrackTask {

    private String studentId;
    private String taskId;
    private String status;
    private String feedbackMessage;
    private boolean resubmissionRequired;

    public OnTrackTask(String studentId, String taskId, String status, String feedbackMessage, boolean resubmissionRequired) {
        this.studentId = studentId;
        this.taskId = taskId;
        this.status = status;
        this.feedbackMessage = feedbackMessage;
        this.resubmissionRequired = resubmissionRequired;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getStatus() {
        return status;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public boolean isResubmissionRequired() {
        return resubmissionRequired;
    }
}