package project.sbtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import project.sbtest.models.Task;
import project.sbtest.models.User;
import project.sbtest.service.TaskService;
import project.sbtest.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    @Autowired
    public TaskController(TaskService taskService, UserService userService){
        this.taskService = taskService;
        this.userService = userService;
    }
    @GetMapping("/viewall-tasks")
    public String viewAllTasks(@RequestParam(name = "category", required = false) String category, Model model) {
        List<String> categories = Arrays.asList("Fitness", "Education", "Home", "Finance", "Health", "Travel", "Social", "Hobby", "Relationships", "Business");
        model.addAttribute("categories", categories);

        List<Task> tasks;
        if (category != null && !category.isEmpty()) {
            tasks = taskService.getAllTasks().stream()
                    .filter(task -> task.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
            model.addAttribute("selectedCategory", category);
        } else {
            tasks = taskService.getAllTasks();
        }
        model.addAttribute("tasks", tasks);

        return "viewall-tasks";
    }

    @GetMapping("/view-by-category")
    public String viewTasksByCategory(@RequestParam(name = "category", required = false) String category,
                                      @RequestParam(name = "priority", required = false) String priority,
                                      Model model) {
        List<Task> tasks;

        // Check if category or priority is selected
        if (category != null && !category.isEmpty()) {
            tasks = taskService.getAllTasks().stream()
                    .filter(task -> task.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
            model.addAttribute("selectedCategory", category);
        } else if (priority != null && !priority.isEmpty()) {
            tasks = taskService.getAllTasks().stream()
                    .filter(task -> task.getPriority().equalsIgnoreCase(priority))
                    .collect(Collectors.toList());
            model.addAttribute("selectedPriority", priority);
        } else {
            // No filter applied, show all tasks
            tasks = taskService.getAllTasks();
        }

        model.addAttribute("tasks", tasks);

        // Add categories and priorities to the model
        List<String> categories = Arrays.asList("Fitness", "Education", "Home", "Finance", "Health", "Travel", "Social", "Hobby", "Relationships", "Business");
        List<String> priorities = Arrays.asList("High", "Medium", "Low");

        model.addAttribute("categories", categories);
        model.addAttribute("priorities", priorities);

        return "viewall-tasks";
    }

    @GetMapping("/view-by-priority")
    public String viewTasksByPriority(@RequestParam(name = "priority") String priority, Model model) {
        List<Task> tasks = taskService.getAllTasks().stream()
                .filter(task -> task.getPriority().equalsIgnoreCase(priority))
                .collect(Collectors.toList());
        model.addAttribute("tasks", tasks);
        model.addAttribute("selectedPriority", priority);

        // Add priorities to the model
        List<String> priorities = Arrays.asList("High", "Medium", "Low");
        model.addAttribute("priorities", priorities);

        // Add categories to the model
        List<String> categories = Arrays.asList("Fitness", "Education", "Home", "Finance", "Health", "Travel", "Social", "Hobby", "Relationships", "Business");
        model.addAttribute("categories", categories);

        return "viewall-tasks";
    }

    @GetMapping("/new-task")
    public String showNewTaskPage(Model model) {
        List<String> categories = Arrays.asList("Fitness", "Education", "Home", "Finance", "Health", "Travel", "Social", "Hobby", "Relationships", "Business");
        model.addAttribute("categories", categories);
        model.addAttribute("task", new Task());
        return "new-task";
    }
    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
        // Save the task
        Task savedTask = taskService.saveTask(task);

        // Get the saved task ID
        Long taskId = savedTask.getTaskId();

        // Generate the task URL using the task ID
        String taskUrl = "/task-details/" + taskId;

        // Update the task with the generated URL
        savedTask.setTaskUrl(taskUrl);
        taskService.saveTask(savedTask);

        redirectAttributes.addFlashAttribute("successMessage", "Task created successfully!");
        return "redirect:/dashboard-page";
    }

    @GetMapping("/task-details/{taskId}")
    public String showTaskDescription(@PathVariable Long taskId, Model model) {
        User loggedInUser = userService.getCurrentUser();
        if (loggedInUser != null) {
            Task task = taskService.getTaskById(taskId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + taskId));
            model.addAttribute("task", task);
            return "task-details";
        } else {
            return "redirect:/login"; // Redirect to your login page if not authenticated
        }
    }

    @GetMapping("/edit/{taskId}")
    public String showEditTaskForm(@PathVariable Long taskId, Model model) {
        User loggedInUser = userService.getCurrentUser();
        if (loggedInUser != null) {
            Task task = taskService.getTaskById(taskId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + taskId));
            model.addAttribute("task", task);

            // Add the list of categories to the model for rendering the dropdown
            List<String> categories = Arrays.asList("Fitness", "Education", "Home", "Finance", "Health", "Travel", "Social", "Hobby", "Relationships", "Business");
            model.addAttribute("categories", categories);

            return "edit-task";
        } else {
            return "redirect:/login"; // Redirect to your login page if not authenticated
        }
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute("task") Task updatedTask, RedirectAttributes redirectAttributes, Model model) {
        // Get the existing task from the database
        Task existingTask = taskService.getTaskById(updatedTask.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + updatedTask.getTaskId()));

        // Update the existing task with the new information
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setTaskStatus(updatedTask.getTaskStatus());
        existingTask.setCategory(updatedTask.getCategory());

        // Save the updated task
        taskService.saveTask(existingTask);

        // Add the list of categories to the model for rendering the dropdown
        List<String> categories = Arrays.asList("Fitness", "Education", "Home", "Finance", "Health", "Travel", "Social", "Hobby", "Relationships", "Business");
        model.addAttribute("categories", categories);

        redirectAttributes.addFlashAttribute("successMessage", "Task updated successfully!");
        return "redirect:/dashboard-page";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable Long taskId, RedirectAttributes redirectAttributes) {
        taskService.deleteTaskById(taskId);
        redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully!");
        return "redirect:/dashboard-page";
    }
    @PostMapping("/delete-multiple")
    public String deleteMultipleTasks(@RequestBody Map<String, List<Long>> requestBody, RedirectAttributes redirectAttributes) {
        List<Long> taskIds = requestBody.get("taskIds");
        taskService.deleteTasksByIds(taskIds);
        redirectAttributes.addFlashAttribute("successMessage", "Selected tasks deleted successfully!");
        return "redirect:/dashboard-page";
    }

}
