package project.sbtest.scope;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.sbtest.models.Task;

import java.sql.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Find tasks by user ID
    List<Task> findByUserId(Long userId);
    //Find tasks between due dates
    List<Task> findByUserIdAndDueDateBetween(Long userId, Date startDate, Date endDate);
    // Find tasks by status
    List<Task> findByTaskStatus(String taskStatus);

    // Find tasks by user ID and status
    List<Task> findByUserIdAndTaskStatus(Long userId, String taskStatus);

    // Find tasks by task name containing a keyword
    List<Task> findByTaskNameContaining(String keyword);

    // Custom query example using JPQL
    @Query("SELECT t FROM Task t WHERE t.userId = :userId AND t.taskStatus = :taskStatus")
    List<Task> findTasksByUserIdAndStatus(Long userId, String taskStatus);
}


