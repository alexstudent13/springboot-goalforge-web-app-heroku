package project.sbtest.models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskID")
    private Long taskId;

    @Column(name = "UserID")
    private Long userId;

    @Column(name = "TaskName")
    private String taskName;

    @Column(name = "TaskDescription")
    private String taskDescription;

    @Column(name = "TaskCreationDate")
    private Date taskCreationDate;

    @Column(name = "TaskURL")
    private String taskUrl;

    @Column(name = "DueDate")
    private Date dueDate;

    @Column(name = "Priority")
    private String priority;

    @Column(name = "TaskStatus")
    private String taskStatus;

    @Column(name = "Category")
    private String category;

    // Getters and setters

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getTaskCreationDate() {
        return taskCreationDate;
    }

    public void setTaskCreationDate(Date taskCreationDate) {
        this.taskCreationDate = taskCreationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }
}
