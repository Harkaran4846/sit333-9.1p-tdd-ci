package sit333.ontrack;

import java.util.ArrayList;
import java.util.List;

public class OnTrackTaskFeedbackService {

    private List<OnTrackTask> tasks = new ArrayList<>();

    public void addTask(OnTrackTask task) {
        if (task != null) {
            tasks.add(task);
        }
    }

    public TaskFeedbackResponse viewTaskFeedbackStatus(String studentId, String taskId) {

        if (studentId == null || studentId.trim().isEmpty()
                || taskId == null || taskId.trim().isEmpty()) {
            return new TaskFeedbackResponse("Invalid", "Invalid input", false);
        }

        for (OnTrackTask task : tasks) {
            if (task.getStudentId().equals(studentId) && task.getTaskId().equals(taskId)) {

                if (task.getStatus().equalsIgnoreCase("Submitted")) {
                    return new TaskFeedbackResponse(
                            "Submitted",
                            "Waiting for tutor feedback",
                            false
                    );
                }

                if (task.getStatus().equalsIgnoreCase("Feedback Available")) {
                    return new TaskFeedbackResponse(
                            "Feedback Available",
                            task.getFeedbackMessage(),
                            task.isResubmissionRequired()
                    );
                }

                if (task.getStatus().equalsIgnoreCase("Completed")) {
                    return new TaskFeedbackResponse(
                            "Completed",
                            "Task completed",
                            false
                    );
                }

                if (task.getStatus().equalsIgnoreCase("Resubmit Required")) {
                    return new TaskFeedbackResponse(
                            "Resubmit Required",
                            task.getFeedbackMessage(),
                            true
                    );
                }

                return new TaskFeedbackResponse(
                        task.getStatus(),
                        "Status available",
                        task.isResubmissionRequired()
                );
            }
        }

        return new TaskFeedbackResponse("Not Found", "Task not found", false);
    }
}