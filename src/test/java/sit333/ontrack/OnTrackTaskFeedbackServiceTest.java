package sit333.ontrack;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

public class OnTrackTaskFeedbackServiceTest {

    @Test
    public void testInvalidStudentIdReturnsInvalidInput() {
        OnTrackTaskFeedbackService service = new OnTrackTaskFeedbackService();

        TaskFeedbackResponse response = service.viewTaskFeedbackStatus("", "SIT333-9.1P");

        assertEquals("Invalid", response.getStatus());
        assertEquals("Invalid input", response.getMessage());
        assertFalse(response.isResubmissionRequired());
    }

    @Test
    public void testInvalidTaskIdReturnsInvalidInput() {
        OnTrackTaskFeedbackService service = new OnTrackTaskFeedbackService();

        TaskFeedbackResponse response = service.viewTaskFeedbackStatus("225191856", "");

        assertEquals("Invalid", response.getStatus());
        assertEquals("Invalid input", response.getMessage());
        assertFalse(response.isResubmissionRequired());
    }

    @Test
    public void testUnknownTaskReturnsTaskNotFound() {
        OnTrackTaskFeedbackService service = new OnTrackTaskFeedbackService();

        TaskFeedbackResponse response = service.viewTaskFeedbackStatus("225191856", "SIT333-9.1P");

        assertEquals("Not Found", response.getStatus());
        assertEquals("Task not found", response.getMessage());
        assertFalse(response.isResubmissionRequired());
    }

    @Test
    public void testSubmittedTaskReturnsWaitingForFeedback() {
        OnTrackTaskFeedbackService service = new OnTrackTaskFeedbackService();

        service.addTask(new OnTrackTask(
                "225191856",
                "SIT333-9.1P",
                "Submitted",
                "",
                false
        ));

        TaskFeedbackResponse response = service.viewTaskFeedbackStatus("225191856", "SIT333-9.1P");

        assertEquals("Submitted", response.getStatus());
        assertEquals("Waiting for tutor feedback", response.getMessage());
        assertFalse(response.isResubmissionRequired());
    }

    @Test
    public void testFeedbackAvailableReturnsTutorFeedbackMessage() {
        OnTrackTaskFeedbackService service = new OnTrackTaskFeedbackService();

        service.addTask(new OnTrackTask(
                "225191856",
                "SIT333-9.1P",
                "Feedback Available",
                "Please add screenshots of CI failure and success.",
                true
        ));

        TaskFeedbackResponse response = service.viewTaskFeedbackStatus("225191856", "SIT333-9.1P");

        assertEquals("Feedback Available", response.getStatus());
        assertEquals("Please add screenshots of CI failure and success.", response.getMessage());
        assertTrue(response.isResubmissionRequired());
    }

    @Test
    public void testCompletedTaskReturnsTaskCompleted() {
        OnTrackTaskFeedbackService service = new OnTrackTaskFeedbackService();

        service.addTask(new OnTrackTask(
                "225191856",
                "SIT333-9.1P",
                "Completed",
                "Good work.",
                false
        ));

        TaskFeedbackResponse response = service.viewTaskFeedbackStatus("225191856", "SIT333-9.1P");

        assertEquals("Completed", response.getStatus());
        assertEquals("Wrong completed message", response.getMessage());
        assertFalse(response.isResubmissionRequired());
    }

    @Test
    public void testResubmitRequiredReturnsTrue() {
        OnTrackTaskFeedbackService service = new OnTrackTaskFeedbackService();

        service.addTask(new OnTrackTask(
                "225191856",
                "SIT333-9.1P",
                "Resubmit Required",
                "More explanation is required in the TDD section.",
                true
        ));

        TaskFeedbackResponse response = service.viewTaskFeedbackStatus("225191856", "SIT333-9.1P");

        assertEquals("Resubmit Required", response.getStatus());
        assertEquals("More explanation is required in the TDD section.", response.getMessage());
        assertTrue(response.isResubmissionRequired());
        
    }
}