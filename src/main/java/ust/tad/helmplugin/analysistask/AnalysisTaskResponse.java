package ust.tad.helmplugin.analysistask;

import java.util.Objects;
import java.util.UUID;

public class AnalysisTaskResponse {

  private UUID taskId;

  private boolean success;

  private String errorMessage;

  public AnalysisTaskResponse() {}

  /**
   * Constructs an AnalysisTaskResponse object with the specified task ID, success status, and error
   * message.
   *
   * @param taskId The unique identifier of the task.
   * @param success Indicates whether the task was successful or not.
   * @param errorMessage The error message associated with the task, if any.
   */
  public AnalysisTaskResponse(UUID taskId, boolean success, String errorMessage) {
    this.taskId = taskId;
    this.success = success;
    this.errorMessage = errorMessage;
  }

  public UUID getTaskId() {
    return this.taskId;
  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  public boolean getSuccess() {
    return this.success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * Sets the task ID and returns the current AnalysisTaskResponse object.
   *
   * @param taskId The unique identifier of the task.
   * @return The current AnalysisTaskResponse object.
   */
  public AnalysisTaskResponse taskId(UUID taskId) {
    setTaskId(taskId);
    return this;
  }

  /**
   * Sets the success status of the task and returns the current AnalysisTaskResponse object.
   *
   * @param success {@code true} if the task was successful, {@code false} otherwise.
   * @return The current AnalysisTaskResponse object.
   */
  public AnalysisTaskResponse success(boolean success) {
    setSuccess(success);
    return this;
  }

  /**
   * Sets the error message associated with the task and returns the current AnalysisTaskResponse
   * object.
   *
   * @param errorMessage The error message, or {@code null} if no error occurred.
   * @return The current AnalysisTaskResponse object.
   */
  public AnalysisTaskResponse errorMessage(String errorMessage) {
    setErrorMessage(errorMessage);
    return this;
  }

  /**
   * Checks if this AnalysisTaskResponse is equal to another object.
   *
   * @param o The object to compare.
   * @return {@code true} if the objects are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof AnalysisTaskResponse)) {
      return false;
    }
    AnalysisTaskResponse analysisTaskResponse = (AnalysisTaskResponse) o;
    return Objects.equals(taskId, analysisTaskResponse.taskId)
            && success == analysisTaskResponse.success
            && Objects.equals(errorMessage, analysisTaskResponse.errorMessage);
  }

  /**
   * Returns the hash code value for this AnalysisTaskResponse.
   *
   * @return The hash code value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(taskId, success, errorMessage);
  }

  /**
   * Returns a string representation of this AnalysisTaskResponse.
   *
   * @return A string representation of the object.
   */
  @Override
  public String toString() {
    return "{"
            + " taskId='"
            + getTaskId()
            + "'"
            + ", success='"
            + getSuccess()
            + "'"
            + ", errorMessage='"
            + getErrorMessage()
            + "'"
            + "}";
  }
}
