package project.sbtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sbtest.models.Task;
import project.sbtest.models.User;
import project.sbtest.scope.TaskRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public Task saveTask(Task task) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            task.setUserId(currentUser.getUserID()); // Set the task's UserID
            task.setTaskCreationDate(new Date(System.currentTimeMillis()));
            if (task.getTaskStatus() == null) {
                task.setTaskStatus("Not Started"); // Set default status
            }
            return taskRepository.save(task);
        }
        throw new IllegalStateException("User not authenticated or task saving failed");
    }

    public List<Task> getAllTasks() {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            return taskRepository.findByUserId(currentUser.getUserID());
        }
        throw new IllegalStateException("User not authenticated or task retrieval failed");
    }

    public List<Task> getTasksDueWithinThreeDays() {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            // Calculate the start and end dates for the next three days
            LocalDate currentDate = LocalDate.now();
            LocalDate startDate = currentDate.plusDays(0);  // Two days from now
            LocalDate endDate = currentDate.plusDays(3);    // Three days from now

            // Convert LocalDate to Date (java.sql.Date) for database query
            Date sqlStartDate = Date.valueOf(startDate);
            Date sqlEndDate = Date.valueOf(endDate);

            return taskRepository.findByUserIdAndDueDateBetween(currentUser.getUserID(), sqlStartDate, sqlEndDate);
        }
        throw new IllegalStateException("User not authenticated or task retrieval failed");
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }
    public String generateTaskUrl(Long taskId) {
        return "/task-details/" + taskId;
    }

    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
    public void deleteTasksByIds(List<Long> taskIds) {
        taskRepository.deleteAllById(taskIds);
    }
}
