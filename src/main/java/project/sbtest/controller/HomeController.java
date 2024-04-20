package project.sbtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.sbtest.models.Task;
import project.sbtest.models.User;
import project.sbtest.service.TaskService;
import project.sbtest.service.UserService;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final TaskService taskService;
    @Autowired
    public HomeController(UserService userService, TaskService taskService){
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String showHomePage(){
        return "home-page";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String error) {
        model.addAttribute("error", error);
        model.addAttribute("user", new User());
        return "login-page";
    }

    @PostMapping("/login")
    public String processLogin(User user, RedirectAttributes redirectAttributes) {
        // Log the user details for debugging
        System.out.println("User email: " + user.getEmail());
        System.out.println("User password: " + user.getPassword());

        User authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());

        // Log the result of authentication
        System.out.println("Authenticated User: " + authenticatedUser);

        if (authenticatedUser != null) {
            return "redirect:/dashboard-page"; // Redirect to dashboard page
        } else {
            redirectAttributes.addAttribute("error", "Invalid credentials. Please try again.");
            return "redirect:/login"; // Redirect back to login page with error message
        }
    }

    @GetMapping("/dashboard-page")
    public String showDashboard(Model model) {
        User loggedInUser = userService.getCurrentUser();
        if (loggedInUser != null) {
            // Add the user object to the model
            model.addAttribute("user", loggedInUser);

            // Get tasks due within 3 days
            List<Task> tasks = taskService.getTasksDueWithinThreeDays();
            model.addAttribute("tasks", tasks);

            return "dashboard-page"; // Return the name of your FreeMarker template file
        } else {
            // Redirect to the login page if the user is not authenticated
            return "redirect:/login";
        }
    }
}
